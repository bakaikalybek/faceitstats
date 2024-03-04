package el.professor.faceitstatistics.data.remote.dto.matchDto

import com.google.gson.annotations.SerializedName

data class MatchStatsBody(
    @SerializedName("rounds") val rounds: List<Round>
)

data class Round(
    @SerializedName("best_of") val bestOf: String,
    @SerializedName("competition_id") val competitionId: Any,
    @SerializedName("game_id") val gameId: String,
    @SerializedName("game_mode") val gameMode: String,
    @SerializedName("match_id") val matchId: String,
    @SerializedName("match_round") val matchRound: String,
    @SerializedName("played") val played: String,
    @SerializedName("round_stats") val roundStats: RoundStats,
    @SerializedName("teams") val teams: List<Team>
)

data class RoundStats(
    @SerializedName("Map") val map: String,
    @SerializedName("Region") val region: String,
    @SerializedName("Rounds") val rounds: String,
    @SerializedName("Score") val score: String,
    @SerializedName("Winner") val winner: String
)

data class Team(
    @SerializedName("players") val players: List<Player>,
    @SerializedName("premade") val premade: Boolean,
    @SerializedName("team_id") val teamId: String,
    @SerializedName("team_stats") val teamStats: TeamStats
)

data class Player(
    @SerializedName("nickname") val nickname: String,
    @SerializedName("player_id") val playerId: String,
    @SerializedName("player_stats") val playerStats: PlayerStats
)

data class PlayerStats(
    @SerializedName("Assists") val assists: String,
    @SerializedName("Deaths") val deaths: String,
    @SerializedName("Headshots") val headshots: String,
    @SerializedName("Headshots %") val headshotsPercentage: String,
    @SerializedName("K/D Ratio") val kdRatio: String,
    @SerializedName("K/R Ratio") val krRatio: String,
    @SerializedName("Kills") val kills: String,
    @SerializedName("MVPs") val mvps: String,
    @SerializedName("Penta Kills") val pentaKills: String,
    @SerializedName("Quadro Kills") val quadroKills: String,
    @SerializedName("Result") val result: String,
    @SerializedName("Triple Kills") val tripleKills: String
)

data class TeamStats(
    @SerializedName("Final Score") val finalScore: String,
    @SerializedName("First Half Score") val firstHalfScore: String,
    @SerializedName("Overtime score") val overtimeScore: String,
    @SerializedName("Second Half Score") val secondHalfScore: String,
    @SerializedName("Team") val team: String,
    @SerializedName("Team Headshots") val teamHeadshots: String,
    @SerializedName("Team Win") val teamWin: String
)