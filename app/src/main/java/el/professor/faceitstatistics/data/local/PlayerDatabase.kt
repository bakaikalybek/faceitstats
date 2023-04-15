package el.professor.faceitstatistics.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PlayerEntity::class],
    version = 2,
    exportSchema = false
)
abstract  class PlayerDatabase: RoomDatabase() {
    abstract val dao: PlayerDao
}