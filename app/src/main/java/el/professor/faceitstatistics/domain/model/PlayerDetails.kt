package el.professor.faceitstatistics.domain.model

import com.squareup.moshi.Json

data class PlayerDetails(
    val gameId: String,
    val maps: List<Map>,
    val lifetime: LifetimeStats,
    val avatar: String,
    val country: String,
    val friends: List<String>,
    val games: Games,
    val nickname: String
)

data class LifetimeStats(
    val headshots: String,
    val kdRatio: String,
    val currentWinStreak: String,
    val longestWinStreak: String,
    val totalMatches: String,
    val recentResults: List<String>,
    val winrate: String,
    val wins: String
)

data class Map(
    val imgRegular: String,
    val imgSmall: String,
    val name: String,
    val mapStats: MapStats
)

data class MapStats(
    val avgAssists: String,
    val avgDeaths: String,
    val avgHeadshots: String,
    val avgKdRatio: String,
    val avgKrRatio: String,
    val avgKills: String,
    val matches: String,
    val pentaKills: String,
    val quadroKills: String,
    val tripleKills: String,
    val winrate: String,
    val wins: String
)

data class Games(
    val csgo: Csgo
)

data class Csgo(
    val elo: Int,
    val playerNameInGame: String,
    val level: Int,
    val region: String,
)