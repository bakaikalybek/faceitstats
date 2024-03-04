package el.professor.faceitstatistics.data.remote

import el.professor.faceitstatistics.data.remote.dto.PlayerDetailsDto
import el.professor.faceitstatistics.data.remote.dto.PlayerInfoDto
import el.professor.faceitstatistics.data.remote.dto.SearchResult
import el.professor.faceitstatistics.data.remote.dto.matchDto.MatchHistoryBody
import el.professor.faceitstatistics.data.remote.dto.matchDto.MatchStatsBody
import el.professor.faceitstatistics.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {

    @GET("search/players")
    suspend fun searchPlayers(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Query("nickname") nickname: String,
        @Query("game") game: String = Constants.game,
        @Query("limit") limit: Int = 20
    ): Response<SearchResult>

    @GET("players/{player_id}/stats/{game_id}")
    suspend fun getPlayerStatistics(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Path("player_id") playerId: String,
        @Path("game_id") game: String = Constants.game
    ): Response<PlayerDetailsDto>

    @GET("players/{player_id}")
    suspend fun getPlayerInfo(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Path("player_id") playerId: String,
    ): Response<PlayerInfoDto>

    @GET("players/{player_id}/history")
    suspend fun getMatchHistory(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Path("player_id") playerId: String,
        @Query("game") game: String = Constants.game,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 5,
    ): Response<MatchHistoryBody>

    @GET("matches/{match_id}/stats")
    suspend fun getMatchStats(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Path("match_id") matchId: String,
    ): Response<MatchStatsBody>

    companion object {
        const val API_KEY = "0efd20ce-ba50-4480-9fbf-7e02e428d945"
        const val BASE_URL = "https://open.faceit.com/data/v4/"
    }
}