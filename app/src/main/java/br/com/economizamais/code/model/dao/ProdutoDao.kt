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

    /** Query **/

    // Insere um Produto no Banco de Dados
    @Insert
    fun insertProduto(produto: Produto)

    // Recupera o último ID cadastrado
    @Query("SELECT MAX(id) FROM Produto")
    fun findLastProdutoId (): Long

    // Recupera todos os Produtos
    @Query("SELECT * from Produto ORDER BY id ASC")
    fun getAllProduto(): List<Produto>

    // Deleta todos os Produtos
    @Query("DELETE from Produto")
    fun deleteAllProduto()
}

/** Funções **/


// Class assyncrona para manipulação dos dados
class InsertProdutoTask(val database: ProdutoDatabase?, val produto: Produto): AsyncTask<Void, Void, Void?>(){

    // Insere um produto
    override fun doInBackground(vararg params: Void?): Void? {
        database?.ProdutoDao()?.insertProduto(produto)

        return null
    }
}

// Class assyncrona para manipulação dos dados
class DeleteAllProdutoTask (val database: ProdutoDatabase?): AsyncTask<Void, Void, Void?>(){

    // Deleta todos os Produtos
    override fun doInBackground(vararg params: Void?): Void? {
        database?.ProdutoDao()?.deleteAllProduto()

        return null
    }

}

