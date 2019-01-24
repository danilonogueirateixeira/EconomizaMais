package br.com.economizamais.code.activities

import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.economizamais.R
import kotlinx.android.synthetic.main.activity_main2.*
import android.util.Log
import br.com.economizamais.code.DataBase.ProdutoDatabase
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



        InsertProdutoTask(database).execute()

        ListarItens(database).execute()
        Log.i("TESTE ----->", "FUNCIONOU")


    }
}


private class InsertProdutoTask(val database: ProdutoDatabase?): AsyncTask<Void, Void, Void?>(){

    override fun doInBackground(vararg params: Void?): Void? {

        var lastId = database?.ProdutoDao()?.findLastCarId()

        if (lastId == null){
            lastId = 1
        }else{
            lastId ++
        }

        val car = Produto(null,"Car DANILO")
        database?.ProdutoDao()?.insert(car)

        Log.i("TESTE ----->", "INSERT")




        return null
    }
}

private class ListarItens (val database: ProdutoDatabase?): AsyncTask<Void, Void, Void?>(){

    override fun doInBackground(vararg params: Void?): Void? {

        val lista = database?.ProdutoDao()?.getAllCar()


        if (lista != null) {
            Log.i("TESTE ----->", lista.size.toString())


            for (i in 0.. lista.size-1) {
                Log.i("TESTE ----->", lista[i].name)
            }

            for (i in 0.. lista.size-1) {
                Log.i("TESTE ----->", lista[i].id.toString())
            }

        }


//danilo danilo nogueira



        return null
    }
}