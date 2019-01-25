package br.com.economizamais.code.activities

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.economizamais.R
import kotlinx.android.synthetic.main.activity_main2.*
import android.util.Log
import br.com.economizamais.code.DataBase.ProdutoDatabase
import br.com.economizamais.code.dao.DeleteAllProdutoTask
import br.com.economizamais.code.dao.GetAllProdutoTask
import br.com.economizamais.code.dao.InsertProdutoTask
import br.com.economizamais.code.dao.ProdutoDao
import br.com.economizamais.code.entities.Produto


class Main2Activity : AppCompatActivity() {

    private var database: ProdutoDatabase? = null


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_produtos)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_lojas)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_listas)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        database = ProdutoDatabase.getInstance(this)


        DeleteAllProdutoTask(database).execute()


        val produto = Produto(1, "Arroz", "Imagem",
            "Descricao", "Detalhe", 10,
            "Tio Jorge", "Atacado", "GO",
            "Luziania", 88888, 99999)

        InsertProdutoTask(database, produto).execute()

        GetAllProdutoTask(database).execute()


    }
}






