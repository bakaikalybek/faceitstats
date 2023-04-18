package el.professor.faceitstatistics.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlayerDetailsDto(
    @SerializedName("game_id")val gameId: String,
    @SerializedName("segments") val maps: List<MapDto>,
    @SerializedName("lifetime") val lifetime: LifetimeStatsDto
)

data class LifetimeStatsDto(
    @SerializedName("Average Headshots %") val headshots: String,
    @SerializedName("Average K/D Ratio") val kdRatio: String,
    @SerializedName("Current Win Streak") val currentWinStreak: String,
    @SerializedName("Longest Win Streak") val longestWinStreak: String,
    @SerializedName("Matches") val totalMatches: String,
    @SerializedName("Recent Results") val recentResults: List<String>,
    @SerializedName("Win Rate %") val winrate: String,
    @SerializedName("Wins") val wins: String
)

data class MapDto(
    @SerializedName("img_regular") val imgRegular: String,
    @SerializedName("img_small") val imgSmall: String,
    @SerializedName("label") val name: String,
    @SerializedName("stats") val mapStats: MapStatsDto,
    @SerializedName("mode") val mode: String
)

data class MapStatsDto(
    @SerializedName("Average Assists") val avgAssists: String,
    @SerializedName("Average Deaths") val avgDeaths: String,
    @SerializedName("Average Headshots %") val avgHeadshots: String,
    @SerializedName("Average K/D Ratio") val avgKdRatio: String,
    @SerializedName("Average K/R Ratio") val avgKrRatio: String,
    @SerializedName("Average Kills") val avgKills: String,
    @SerializedName("Matches") val matches: String,
    @SerializedName("Penta Kills") val pentaKills: String,
    @SerializedName("Quadro Kills") val quadroKills: String,
    @SerializedName("Triple Kills") val tripleKills: String,
    @SerializedName("Win Rate %") val winrate: String,
    @SerializedName("Wins") val wins: String
)