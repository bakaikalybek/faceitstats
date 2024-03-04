package el.professor.faceitstatistics.domain.repository

import el.professor.faceitstatistics.domain.model.MatchStatistics
import el.professor.faceitstatistics.domain.model.Player
import el.professor.faceitstatistics.domain.model.PlayerDetails
import el.professor.faceitstatistics.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    suspend fun searchPlayer(query: String): Flow<Resource<List<Player>>>
    suspend fun getPlayersStatistics(playerId: String): Flow<Resource<PlayerDetails>>
    suspend fun addOrDeletePlayer(player: Player): Flow<Resource<String>>
    suspend fun getMatchStatistics(matchId: String): Flow<Resource<MatchStatistics>>

}