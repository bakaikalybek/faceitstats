package el.professor.faceitstatistics.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Player(
    val nickname: String,
    val playerId: String,
    val level: String,
): java.io.Serializable
