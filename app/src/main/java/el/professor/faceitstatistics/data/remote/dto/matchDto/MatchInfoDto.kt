package el.professor.faceitstatistics.data.remote.dto.matchDto

import com.google.gson.annotations.SerializedName

data class MatchHistoryBody(
    @SerializedName("items") val matches: List<MatchInfoDto>
)
data class MatchInfoDto(
    @SerializedName("faceit_url") val faceitUrl: String,
    @SerializedName("started_at") val startedAt: Int,
    @SerializedName("finished_at") val finishedAt: Int,
    @SerializedName("match_id") val matchId: String,
    @SerializedName("results") val results: ResultsDto,
    @SerializedName("teams") val teams: TeamsDto,
)

data class TeamsDto(
    @SerializedName("faction1") val faction1: FactionDto,
    @SerializedName("faction2") val faction2: FactionDto
)

data class FactionDto(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("players") val players: List<MatchPlayerDto>,
    @SerializedName("team_id") val teamId: String,
    @SerializedName("type") val type: String
)

data class MatchPlayerDto(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("faceit_url") val faceitUrl: String,
    @SerializedName("game_player_id") val gamePlayerId: String,
    @SerializedName("game_player_name") val gamePlayerName: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("player_id") val playerId: String,
    @SerializedName("skill_level") val skillLevel: Int
)

data class ResultsDto(
    @SerializedName("score") val score: ScoreDto,
    @SerializedName("winner") val winner: String
)

data class ScoreDto(
    @SerializedName("faction1") val faction1: Int,
    @SerializedName("faction2") val faction2: Int
)