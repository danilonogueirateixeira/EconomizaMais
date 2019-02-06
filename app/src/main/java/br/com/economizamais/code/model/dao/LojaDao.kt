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


    @Insert
    fun insertLoja(loja: Loja)

    @Query("SELECT MAX(id) FROM Loja")
    fun findLastLojaId (): Long

    @Query("SELECT * from Loja ORDER BY id ASC")
    fun getAllLoja(): List<Loja>

    @Query("DELETE from Loja")
    fun deleteAllLoja()
}


class InsertLojaTask(val database: LojaDatabase?, val loja: Loja): AsyncTask<Void, Void, Void?>(){

    override fun doInBackground(vararg params: Void?): Void? {

        database?.LojaDao()?.insertLoja(loja)

        return null
    }
}




class DeleteAllLojaTask (val database: LojaDatabase?): AsyncTask<Void, Void, Void?>(){

    override fun doInBackground(vararg params: Void?): Void? {

        database?.LojaDao()?.deleteAllLoja()

        return null
    }



}

