package el.professor.faceitstatistics.data.local

import androidx.room.*

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(playerEntity: PlayerEntity): Long

    @Query("SELECT * FROM PlayerEntity")
    suspend fun getAllPlayers(): List<PlayerEntity>

    @Query("DELETE FROM PlayerEntity WHERE playerId=:id")
    suspend fun deletePlayer(id: String): Int

    @Query("SELECT * FROM PlayerEntity WHERE playerId=:id")
    suspend fun getPlayerById(id: String): List<PlayerEntity>

}