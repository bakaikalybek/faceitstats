package el.professor.faceitstatistics.domain.model


import com.google.gson.annotations.SerializedName
import el.professor.faceitstatistics.data.remote.dto.matchDto.RoundStatsDto
import el.professor.faceitstatistics.data.remote.dto.matchDto.TeamDto

data class MatchStatistics(
    val bestOf: String,
    val competitionId: Any?,
    val gameId: String,
    val gameMode: String,
    val matchId: String,
    val matchRound: String,
    val played: String,
    val roundStats: RoundStats,
    val teams: List<MatchTeam>
)


data class RoundStats(
    val map: String,
    val region: String,
    val rounds: String,
    val score: String,
    val winner: String
)

data class MatchTeam(
    val players: List<TeamPlayer>,
    val premade: Boolean,
    val teamId: String,
    val teamStats: TeamStats
)

data class TeamPlayer(
    val nickname: String,
    val playerId: String,
    val playerStats: PlayerStats
)

data class PlayerStats(
    val assists: String,
    val deaths: String,
    val headshots: String,
    val headshotsPercentage: String,
    val kdRatio: String,
    val krRatio: String,
    val kills: String,
    val mvps: String,
    val pentaKills: String,
    val quadroKills: String,
    val result: String,
    val tripleKills: String
)

data class TeamStats(
    val finalScore: String,
    val firstHalfScore: String,
    val overtimeScore: String,
    val secondHalfScore: String,
    val team: String,
    val teamHeadshots: String,
    val teamWin: String
)