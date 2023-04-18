package el.professor.faceitstatistics.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PlayerInfoDto(
    @SerializedName("avatar") val avatar: String,
    @SerializedName("country") val country: String,
    @SerializedName("friends_ids") val friends: List<String>,
    @SerializedName("games") val games: GamesDto,
    @SerializedName("nickname") val nickname: String
)

data class GamesDto(
    @SerializedName("csgo") val csgo: CsgoDto
)

data class CsgoDto(
    @SerializedName("faceit_elo") val elo: Int,
    @SerializedName("game_player_name") val playerNameInGame: String,
    @SerializedName("skill_level") val level: Int,
    @SerializedName("region") val region: String,
)