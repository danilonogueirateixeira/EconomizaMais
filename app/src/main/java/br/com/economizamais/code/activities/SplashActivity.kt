package br.com.economizamais.code.activities

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import br.com.economizamais.R
import br.com.economizamais.code.controller.database.LojaDatabase
import br.com.economizamais.code.controller.database.ProdutoDatabase
import br.com.economizamais.code.controller.network_utils.NetworkUtils
import br.com.economizamais.code.model.dao.DeleteAllLojaTask
import br.com.economizamais.code.model.dao.DeleteAllProdutoTask
import br.com.economizamais.code.model.dao.InsertLojaTask
import br.com.economizamais.code.model.dao.InsertProdutoTask
import br.com.economizamais.code.model.entities.EndpointLoja
import br.com.economizamais.code.model.entities.EndpointProduto
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashActivity : AppCompatActivity() {

    var latitude: Double = 0.0
    var longitude: Double = 0.0


    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        obtieneLocalizacion()


        getLojasServidor()


        var databaseProduto: ProdutoDatabase? = null
        databaseProduto = ProdutoDatabase.getInstance(this)
        GetAllProdutoTask(databaseProduto).execute()

        var databaseLoja: LojaDatabase? = null
        databaseLoja = LojaDatabase.getInstance(this)
        GetAllLojaTask(databaseLoja).execute()


    }








    private fun mostrarMain() {
        val intent = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }


    fun getLojasServidor() {

        Log.i("TESTE LOJAS SERV ENTROU","OK")


        var database: LojaDatabase? = null

        database = LojaDatabase.getInstance(this)


        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://economizamais.herokuapp.com/")


        val endpoint = retrofitClient.create(EndpointLoja::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Loja>> {


            override fun onFailure(call: Call<List<Loja>>, t: Throwable) {
                Log.i("TESTE LOJA SERV FALHA",t.message)
                getProdutosServidor()


            }


            override fun onResponse(call: Call<List<Loja>>, response: Response<List<Loja>>) {
                DeleteAllLojaTask(database).execute()
                Log.i("TESTE LOJA SERV delBD","OK")

                response.body()?.forEach {

                    InsertLojaTask(database, it).execute()
                    Log.i("TESTE LOJA SERV ",it.toString())


                }

                getProdutosServidor()
            }
        })
    }

    fun getProdutosServidor() {

        Log.i("TESTE PROD SERV ENTROU","OK")


        var database: ProdutoDatabase? = null

        database = ProdutoDatabase.getInstance(this)


        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://economizamais.herokuapp.com/")


        val endpoint = retrofitClient.create(EndpointProduto::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Produto>> {


            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {
                Log.i("TESTE PROD SERV FALHA",t.message)
                getProdutosServidor()


            }


            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {
                DeleteAllProdutoTask(database).execute()
                Log.i("TESTE PROD SERV delBD","OK")

                response.body()?.forEach {

                    InsertProdutoTask(database, it).execute()
                    Log.i("TESTE PROD SERV ",it.toString())


                }


               //

                var intent: Intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.putExtra("latitude", latitude)
                intent.putExtra("longitude", longitude)
                startActivity(intent)
                //mostrarMain()
            }
        })
    }
//    Handler().postDelayed({
//        InsertProdutoTask(database, it).execute()
//        Log.i("TESTE webservice OK",  it.toString())
//
//
//    }, 3000)


    @SuppressLint("MissingPermission")
    private fun obtieneLocalizacion(){



        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: android.location.Location? ->
                latitude = location?.latitude!!
                longitude = location?.longitude!!

                Log.i("TESTE LOCATION",  latitude.toString())
                Log.i("TESTE LOCATION",  longitude.toString())


            }
    }


}



