package el.professor.faceitstatistics.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerEntity(
    val nickname: String,
    val playerId: String,
    val level: Int,
    @PrimaryKey val id: Int? = null
)
