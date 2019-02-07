package br.com.economizamais.code.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Handler
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Toast
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
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SplashActivity : AppCompatActivity() {

    var latitude: Double = 0.0
    var longitude: Double = 0.0

    private val ACCESS_FINE_LOCATION_RESULT_CODE = 111
    //private val ACCESS_FINE_LOCATION_RESULT_CODE = 111




    private lateinit var fusedLocationClient: FusedLocationProviderClient


    @SuppressLint("NewApi")
    fun makeCall() {
        Log.i("TESTE PERMISSAO","MAKE")

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            Log.i("TESTE PERMISSAO","NEGADA")


            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                createMoreInfoDialog()
                Log.i("TESTE PERMISSAO","MOREINFO")

            } else {
                requestCallPermission()
                Log.i("TESTE PERMISSAO","REQUSTCALLPERMI")

            }
        } else {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            obtieneLocalizacion()

            getLojasServidor()


            Log.i("TESTE PERMISSAO","LIBERADA")

        }
    }

    @SuppressLint("NewApi")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.i("TESTE PERMISSAO","REQUEST")

        when (requestCode) {
            ACCESS_FINE_LOCATION_RESULT_CODE -> {
                if (grantResults.isNotEmpty()
                    && grantResults.first() == PackageManager.PERMISSION_GRANTED) {

                    fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

                    obtieneLocalizacion()

                    getLojasServidor()





                    Log.i("TESTE PERMISSAO","ACEITOU")

                    //CHAMAR OUTRA PERMISSÃO

                } else if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Log.i("TESTE PERMISSAO","NEVERASK")

                    createNeverAskAgainDialog()
                } else {
                    Log.i("TESTE PERMISSAO","DENIED")
                    createMoreInfoDialog()
                    Toast.makeText(this, R.string.permission_denied, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun createNeverAskAgainDialog() {
        Log.i("TESTE PERMISSAO","ASKAGAINDIALOG")

        AlertDialog.Builder(this).apply {
            setMessage(R.string.never_ask_again_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.go_to_settings) { _, _ -> goToAppDetailsSettings() }
            setNegativeButton(R.string.no) { d, _ -> finish()  }
        }.show()
    }

    private fun createMoreInfoDialog() {
        Log.i("TESTE PERMISSAO","MORE INFO")

        AlertDialog.Builder(this).apply {
            setMessage(R.string.more_info_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.yes) { _, _ -> requestCallPermission() }
            setNegativeButton(R.string.no) { d, _ -> finish() }
        }.show()
    }

    private fun requestCallPermission() {
        Log.i("TESTE PERMISSAO","REQUESTCALL")

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            ACCESS_FINE_LOCATION_RESULT_CODE)
    }

    private fun goToAppDetailsSettings() {
        Log.i("TESTE PERMISSAO","DETAILS SETTINGS")

        val appSettings = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", this@SplashActivity.packageName, null)
        }
        startActivity(appSettings)
    }

    fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        showHide(splash_progressbar)



            Handler().postDelayed({
                makeCall()
            }, 1000)

        Handler().postDelayed({
            showHide(splash_progressbar)
        }, 2000)








        //obtieneLocalizacion()


        //getLojasServidor()

//        var databaseProduto: ProdutoDatabase? = null
//        databaseProduto = ProdutoDatabase.getInstance(this)
//        GetAllProdutoTask(databaseProduto).execute()
//
//        var databaseLoja: LojaDatabase? = null
//        databaseLoja = LojaDatabase.getInstance(this)
//        GetAllLojaTask(databaseLoja).execute()



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
                getLojasServidor()


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


                var databaseProduto: ProdutoDatabase? = null
                databaseProduto = ProdutoDatabase.getInstance(this@SplashActivity)
                GetAllProdutoTask(databaseProduto).execute()

                var databaseLoja: LojaDatabase? = null
                databaseLoja = LojaDatabase.getInstance(this@SplashActivity)
                GetAllLojaTask(databaseLoja).execute()

                //Handler().postDelayed({
                    var intent: Intent = Intent(this@SplashActivity, MainActivity::class.java)
                    intent.putExtra("latitude", latitude)
                    intent.putExtra("longitude", longitude)
                    startActivity(intent)
                    finish()
                //}, 3000)


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



