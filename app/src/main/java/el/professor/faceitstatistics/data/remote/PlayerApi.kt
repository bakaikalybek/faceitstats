package el.professor.faceitstatistics.data.remote

import el.professor.faceitstatistics.data.remote.dto.PlayerDetailsDto
import el.professor.faceitstatistics.data.remote.dto.PlayerInfoDto
import el.professor.faceitstatistics.data.remote.dto.SearchResult
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {

    @GET("/data/v4/search/players")
    suspend fun searchPlayers(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Query("nickname") nickname: String,
        @Query("game") game: String = "csgo",
        @Query("limit") limit: Int = 20
    ): Response<SearchResult>

    @GET("/data/v4/players/{player_id}/stats/{game_id}")
    suspend fun getPlayerStatistics(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Path("player_id") playerId: String,
        @Path("game_id") game: String = "csgo"
    ): Response<PlayerDetailsDto>

    @GET("/data/v4/players/{player_id}")
    suspend fun getPlayerInfo(
        @Header("Authorization") key: String = "Bearer $API_KEY",
        @Path("player_id") playerId: String,
    ): Response<PlayerInfoDto>

    companion object {
        const val API_KEY = "0efd20ce-ba50-4480-9fbf-7e02e428d945"
        const val BASE_URL = "https://open.faceit.com"
    }
}