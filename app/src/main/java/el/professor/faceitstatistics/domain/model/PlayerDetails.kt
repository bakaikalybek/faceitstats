package el.professor.faceitstatistics.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class PlayerDetails(
    val gameId: String,
    val maps: List<Map>,
    val lifetime: LifetimeStats,
    val avatar: String,
    val country: String,
    val friends: List<String>,
    val games: Games,
    val nickname: String,
    val matches: List<MatchInfo>
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

@Serializable
data class Map(
    val imgRegular: String,
    val imgSmall: String,
    val name: String,
    val mapStats: MapStats,
    val mode: String
): java.io.Serializable

@Serializable
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
): java.io.Serializable

data class Games(
    val cs: Cs
)

data class Cs(
    val elo: Int,
    val playerNameInGame: String,
    val level: Int,
    val region: String,
)

data class MatchInfo(
    val title: String? = null,
    val status: String? = null,
    val startedAt: Int,
    val finishedAt: Int,
    val matchId: String,
    val results: Results,
    val teams: Teams,
)

data class Teams(
    val faction1: Faction,
    val faction2: Faction
)

data class Faction(
    val avatar: String,
    val nickname: String,
    val players: List<MatchPlayer>,
    val teamId: String,
    val type: String
)

data class MatchPlayer(
    val avatar: String,
    val faceitUrl: String,
    val gamePlayerId: String,
    val gamePlayerName: String,
    val nickname: String,
    val playerId: String,
    val skillLevel: Int
)

data class Results(
    val score: Score,
    val winner: String
)

data class Score(
    val faction1: Int,
    val faction2: Int
)