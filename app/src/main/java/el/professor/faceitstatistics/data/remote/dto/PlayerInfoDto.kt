package el.professor.faceitstatistics.data.remote.dto

import com.squareup.moshi.Json

data class PlayerInfoDto(
    @field:Json(name = "avatar") val avatar: String,
    @field:Json(name = "country") val country: String,
    @field:Json(name = "friends_ids") val friends: List<String>,
    @field:Json(name = "games") val games: GamesDto,
    @field:Json(name = "nickname") val nickname: String
)

data class GamesDto(
    @field:Json(name = "csgo") val csgo: CsgoDto
)

data class CsgoDto(
    @field:Json(name = "faceit_elo") val elo: Int,
    @field:Json(name = "game_player_name") val playerNameInGame: String,
    @field:Json(name = "skill_level") val level: Int,
    @field:Json(name = "region") val region: String,
)