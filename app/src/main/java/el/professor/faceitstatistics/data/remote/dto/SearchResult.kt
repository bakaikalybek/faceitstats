package el.professor.faceitstatistics.data.remote.dto

import com.squareup.moshi.Json

data class SearchResult(
    val end: Int,
    @field:Json(name = "items")
    val players: List<Item>,
    val start: Int
)

data class Item(
    val avatar: String,
    val country: String,
    val games: List<Game>,
    val nickname: String,
    @field:Json(name = "player_id")
    val playerId: String,
    val status: String,
    val verified: Boolean
)

data class Game(
    val name: String,
    val skill_level: String
)