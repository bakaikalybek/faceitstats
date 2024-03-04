package el.professor.faceitstatistics.data.repository

import android.util.Log
import el.professor.faceitstatistics.data.local.PlayerDatabase
import el.professor.faceitstatistics.data.mapper.toPlayer
import el.professor.faceitstatistics.data.mapper.toPlayerDetails
import el.professor.faceitstatistics.data.mapper.toPlayerEntity
import el.professor.faceitstatistics.data.remote.PlayerApi
import el.professor.faceitstatistics.domain.model.Player
import el.professor.faceitstatistics.domain.model.PlayerDetails
import el.professor.faceitstatistics.domain.repository.PlayerRepository
import el.professor.faceitstatistics.util.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONArray
import org.json.JSONObject

class PlayerRepositoryImpl(
    private val api: PlayerApi,
    private val db: PlayerDatabase
) : PlayerRepository {

    private val dao = db.dao

    override suspend fun searchPlayer(query: String): Flow<Resource<List<Player>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            if (query.isNullOrBlank() || query.isNullOrEmpty()) {
                val favoritePlayers = dao.getAllPlayers()
                emit(Resource.Success(data = favoritePlayers.map { it.toPlayer() }))
            } else {
                val searchResult = api.searchPlayers(nickname = query)
                if (searchResult.isSuccessful) {
                    emit(Resource.Success(data = searchResult.body()?.players?.map { it.toPlayer() }))
                } else {
                    emit(Resource.Error(message = searchResult.message()))
                }
            }
        }
    }

    override suspend fun getPlayersStatistics(playerId: String): Flow<Resource<PlayerDetails>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            coroutineScope {
                val playerInfo = async { api.getPlayerInfo(playerId = playerId) }.await()
                val playerStats = async { api.getPlayerStatistics(playerId = playerId) }.await()
                val playerHistory = async { api.getMatchHistory(playerId = playerId) }.await()
                if (playerInfo.isSuccessful && playerStats.isSuccessful) {
                    emit(Resource.Success(data = playerStats.body()?.toPlayerDetails(playerId, playerInfo.body()!!, playerHistory.body()!!.matches)))
                }
            }
        }
    }

    override suspend fun addOrDeletePlayer(player: Player): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            if (dao.getPlayerById(player.playerId).isEmpty()) {
                coroutineScope {
                    val playerId = dao.insertPlayer(player.toPlayerEntity())
                    if (playerId != 0L) {
                        emit(Resource.Success("Player added"))
                    } else {
                        emit(Resource.Error("Failed to add"))
                    }
                }
            } else {
                coroutineScope {
                    val rowsChanged = dao.deletePlayer(player.playerId)
                    Log.e("PlayerRepository", "$rowsChanged")
                    if (rowsChanged == 0) {
                        emit(Resource.Error("Couldn't delete"))
                    } else {
                        emit(Resource.Success("Deleted"))
                    }
                }
            }
        }
    }

    override suspend fun getMatchStatistics(matchId: String): Flow<Resource<String>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            val response = api.getMatchStats(matchId = matchId)
            if (response.isSuccessful) {
                emit(Resource.Success(data = response.body()?.rounds.toString()))
            } else {
                emit(Resource.Error(message = response.message()))
            }
        }
    }
}