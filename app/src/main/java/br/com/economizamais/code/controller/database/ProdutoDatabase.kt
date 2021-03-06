package br.com.economizamais.code.controller.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.economizamais.code.model.dao.ProdutoDao
import br.com.economizamais.code.model.entities.Produto



@Database(entities = [Produto::class], version = 1,exportSchema = false)
abstract class ProdutoDatabase : RoomDatabase(){

    abstract fun ProdutoDao(): ProdutoDao

    companion object {
        private var INSTANCE: ProdutoDatabase? = null

        fun getInstance(context: Context): ProdutoDatabase? {
            if (INSTANCE == null) {
                synchronized(ProdutoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ProdutoDatabase::class.java, "produto.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }

}