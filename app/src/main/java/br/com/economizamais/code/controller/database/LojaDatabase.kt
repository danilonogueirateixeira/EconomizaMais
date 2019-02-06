package br.com.economizamais.code.controller.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.economizamais.code.model.dao.LojaDao
import br.com.economizamais.code.model.dao.ProdutoDao
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto



@Database(entities = [Loja::class], version = 1,exportSchema = false)
abstract class LojaDatabase : RoomDatabase(){

    abstract fun LojaDao(): LojaDao

    companion object {
        private var INSTANCE: LojaDatabase? = null

        fun getInstance(context: Context): LojaDatabase? {
            if (INSTANCE == null) {
                synchronized(LojaDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        LojaDatabase::class.java, "loja.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }

}