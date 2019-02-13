package br.com.economizamais.code.model.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.os.AsyncTask
import android.util.Log
import br.com.economizamais.code.controller.database.LojaDatabase
import br.com.economizamais.code.controller.database.ProdutoDatabase
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto

@Dao
interface LojaDao {

    /** Query **/

    // Insere uma loja no Banco de Dados
    @Insert
    fun insertLoja(loja: Loja)

    // Recupera o último ID cadastrado
    @Query("SELECT MAX(id) FROM Loja")
    fun findLastLojaId (): Long

    // Recupera todas as Lojas
    @Query("SELECT * from Loja ORDER BY id ASC")
    fun getAllLoja(): List<Loja>

    // Deleta Todas as Lojas
    @Query("DELETE from Loja")
    fun deleteAllLoja()
}

/** Funções **/


// Class assyncrona para manipulação dos dados
class InsertLojaTask(val database: LojaDatabase?, val loja: Loja): AsyncTask<Void, Void, Void?>(){

    // Insere uma Loja
    override fun doInBackground(vararg params: Void?): Void? {
        database?.LojaDao()?.insertLoja(loja)

        return null
    }
}

// Class assyncrona para manipulação dos dados
class DeleteAllLojaTask (val database: LojaDatabase?): AsyncTask<Void, Void, Void?>(){

    // Deleta todas as lojas
    override fun doInBackground(vararg params: Void?): Void? {
        database?.LojaDao()?.deleteAllLoja()

        return null
    }
}

