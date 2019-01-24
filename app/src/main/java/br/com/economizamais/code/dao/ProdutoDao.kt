package br.com.economizamais.code.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.economizamais.code.entities.Produto

@Dao
interface ProdutoDao {

    @Insert
    fun insert(produto: Produto)

    @Query("SELECT MAX(id) FROM Produto")
    fun findLastCarId (): Long

    @Query("SELECT * from Produto ORDER BY name ASC")
    fun getAllCar(): List<Produto>

    @Query("DELETE from Produto")
    fun deleteAll()
}