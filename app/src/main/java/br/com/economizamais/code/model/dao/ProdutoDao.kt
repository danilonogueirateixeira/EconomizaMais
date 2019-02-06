package br.com.economizamais.code.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.os.AsyncTask
import android.util.Log
import br.com.economizamais.code.controller.database.ProdutoDatabase
import br.com.economizamais.code.model.entities.Produto

@Dao
interface ProdutoDao {


    @Insert
    fun insertProduto(produto: Produto)

    @Query("SELECT MAX(id) FROM Produto")
    fun findLastProdutoId (): Long

    @Query("SELECT * from Produto ORDER BY id ASC")
    fun getAllProduto(): List<Produto>

    @Query("DELETE from Produto")
    fun deleteAllProduto()
}


class InsertProdutoTask(val database: ProdutoDatabase?, val produto: Produto): AsyncTask<Void, Void, Void?>(){

    override fun doInBackground(vararg params: Void?): Void? {

        database?.ProdutoDao()?.insertProduto(produto)

        return null
    }
}




class DeleteAllProdutoTask (val database: ProdutoDatabase?): AsyncTask<Void, Void, Void?>(){

    override fun doInBackground(vararg params: Void?): Void? {

        database?.ProdutoDao()?.deleteAllProduto()

        return null
    }



}

