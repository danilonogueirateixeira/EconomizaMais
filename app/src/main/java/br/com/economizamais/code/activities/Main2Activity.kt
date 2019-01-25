package br.com.economizamais.code.activities

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import br.com.economizamais.R
import kotlinx.android.synthetic.main.activity_main2.*
import android.util.Log
import android.widget.Toast
import br.com.economizamais.code.DataBase.ProdutoDatabase
import br.com.economizamais.code.dao.DeleteAllProdutoTask
import br.com.economizamais.code.dao.GetAllProdutoTask
import br.com.economizamais.code.dao.InsertProdutoTask
import br.com.economizamais.code.dao.ProdutoDao
import br.com.economizamais.code.entities.Produto
import br.com.economizamais.code.network_utils.Endpoint
import br.com.economizamais.code.network_utils.NetworkUtils
import br.com.economizamais.code.network_utils.ProdutoServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        getData()

    }


    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://economizamais.herokuapp.com/")

        Log.i("TESTE ----->", "TESTE")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<br.com.economizamais.code.network_utils.ProdutoServer>> {


            override fun onFailure(call: Call<List<br.com.economizamais.code.network_utils.ProdutoServer>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                Log.i("TESTE ----->",t.message)

            }


            override fun onResponse(call: Call<List<br.com.economizamais.code.network_utils.ProdutoServer>>, response: Response<List<br.com.economizamais.code.network_utils.ProdutoServer>>) {
                response.body()?.forEach {
                    //Toast.makeText(baseContext, it.id.toString(), Toast.LENGTH_SHORT).show()
                    Log.i("TESTE ----->",  it.toString())

                }
            }
        })

    }

}






