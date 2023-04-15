package el.professor.faceitstatistics.data.remote.dto

import com.squareup.moshi.Json

data class PlayerDetailsDto(
    @field:Json(name = "game_id") val gameId: String,
    @field:Json(name = "segments") val maps: List<MapDto>,
    @field:Json(name = "lifetime") val lifetime: LifetimeStatsDto
)

data class LifetimeStatsDto(
    @field:Json(name = "Average Headshots %") val headshots: String,
    @field:Json(name = "Average K/D Ratio") val kdRatio: String,
    @field:Json(name = "Current Win Streak") val currentWinStreak: String,
    @field:Json(name = "Longest Win Streak") val longestWinStreak: String,
    @field:Json(name = "Matches") val totalMatches: String,
    @field:Json(name = "Recent Results") val recentResults: List<String>,
    @field:Json(name = "Win Rate %") val winrate: String,
    @field:Json(name = "Wins") val wins: String
)

data class MapDto(
    @field:Json(name = "img_regular") val imgRegular: String,
    @field:Json(name = "img_small") val imgSmall: String,
    @field:Json(name = "label") val name: String,
    @field:Json(name = "stats") val mapStats: MapStatsDto
)

data class MapStatsDto(
    @field:Json(name = "Average Assists") val avgAssists: String,
    @field:Json(name = "Average Deaths") val avgDeaths: String,
    @field:Json(name = "Average Headshots %") val avgHeadshots: String,
    @field:Json(name = "Average K/D Ratio") val avgKdRatio: String,
    @field:Json(name = "Average K/R Ratio") val avgKrRatio: String,
    @field:Json(name = "Average Kills") val avgKills: String,
    @field:Json(name = "Matches") val matches: String,
    @field:Json(name = "Penta Kills") val pentaKills: String,
    @field:Json(name = "Quadro Kills") val quadroKills: String,
    @field:Json(name = "Triple Kills") val tripleKills: String,
    @field:Json(name = "Win Rate %") val winrate: String,
    @field:Json(name = "Wins") val wins: String
)