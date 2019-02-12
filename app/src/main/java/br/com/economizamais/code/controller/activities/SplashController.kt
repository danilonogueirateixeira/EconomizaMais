package br.com.economizamais.code.controller.activities

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.util.Log
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
import br.com.economizamais.code.view.GetAllLojaTask
import br.com.economizamais.code.view.GetAllProdutoTask
import br.com.economizamais.code.view.MainActivity
import br.com.economizamais.code.view.SplashActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class SplashController{

    /** PERMISSÕES **/

    // Resposável pela primeira verificação de permissões
    @SuppressLint("NewApi")
    fun getMinhaLocalizacao(contexto: Context, contextoWrapper: ContextWrapper) {

        // Se a permissão estiver negada
        if (ContextCompat.checkSelfPermission(contexto, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            // Se a permissão já foinegada anteriomente
            if ((contexto as SplashActivity).shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                contexto.createMoreInfoDialog()

            // Se a permissão ainda não foi negada
            } else {
                requestMinhaLocalizacao(contexto)
            }

        // Se a permissão estiver liberada
        } else {

            // Inicia a conexão com o servidor de lojas
            SplashController().getLojasServidor(contexto, contextoWrapper)
        }
    }


    // Verifica a situação da solicitação de permissão
    fun requestMinhaLocalizacao(contexto: Context) {
        val ACCESS_FINE_LOCATION_RESULT_CODE = 111

        ActivityCompat.requestPermissions(
            (contexto as SplashActivity),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            ACCESS_FINE_LOCATION_RESULT_CODE)
    }


    // Abre as configurações do aplicativo para alterar as permissões manualmente
     fun goToAppDetailsSettings(contexto: Context) {
        val appSettings = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", contexto.packageName, null)
        }

        contexto.startActivity(appSettings)
        (contexto as SplashActivity).finish()
    }




    /** CONEXÃO COM SERVIDOR **/

    // Base do endereço para conexão
    val enderecoConexao = "https://economizamais.herokuapp.com/"

    // Contador para limite de erros de conexão
    var contadorLojas: Int = 0

    // Conexão com servidor e armazenamento interno dos dados das lojas
    fun getLojasServidor(contexto: Context, contextoWrapper: ContextWrapper) {

        // Instância do Banco de Dados
        var database: LojaDatabase? = LojaDatabase.getInstance(contexto)

        // Conexão com servidor
        val retrofitClient = NetworkUtils
            .getRetrofitInstance(enderecoConexao)
        val endpoint = retrofitClient.create(EndpointLoja::class.java)
        val callback = endpoint.getPosts()
        callback.enqueue(object : Callback<List<Loja>> {

            // Tratamento em caso de falha na conexão
            override fun onFailure(call: Call<List<Loja>>, t: Throwable) {

                // Se Contador menor que 3 é realizada uma nova chamada
                if (contadorLojas<3){
                    getLojasServidor(contexto, contextoWrapper)
                    contadorLojas = contadorLojas+1

                // Se contador maior que 3
                } else{

                    // Se não existir nenhum banco de dados exibir a mensagem selecionada
                    if (dataBaseExist(contextoWrapper, "loja.db") == false){
                        (contexto as SplashActivity).createSemConexaoPrimeiraDialog()

                    // Se existir banco de Dados exibir a mensagem selecionada
                    }else {
                        (contexto as SplashActivity).createSemConexaoSegundaDialog()
                    }
                }

            }

            // Ação para resposta da conexão
            override fun onResponse(call: Call<List<Loja>>, response: Response<List<Loja>>) {

                // Deleta as informações do banco de dados
                DeleteAllLojaTask(database).execute()

                response.body()?.forEach {

                    // Insere as novas informações no banco de dados
                    InsertLojaTask(database, it).execute()
                }

                // Inicia a conexão com o servidor de produtos
                getProdutosServidor(contexto, contextoWrapper)
            }
        })
    }

    // Contador para limite de erros de conexão
    var contadorProdutos: Int = 0

    // Conexão com servidor e armazenamento interno dos dados dos produtos
    fun getProdutosServidor(contexto: Context,  contextoWrapper: ContextWrapper) {

        // Instância do Banco de Dados
        var database: ProdutoDatabase? = ProdutoDatabase.getInstance(contexto)

        // Conexão com servidor
        val retrofitClient = NetworkUtils
            .getRetrofitInstance(enderecoConexao)
        val endpoint = retrofitClient.create(EndpointProduto::class.java)
        val callback = endpoint.getPosts()
        callback.enqueue(object : Callback<List<Produto>> {

            // Tratamento em caso de falha na conexão
            override fun onFailure(call: Call<List<Produto>>, t: Throwable) {

                // Se Contador menor que 3 é realizada uma nova chamada
                if (contadorProdutos<3){
                    getProdutosServidor(contexto, contextoWrapper)
                    contadorProdutos = contadorProdutos+1

                // Se contador maior que 3
                } else{

                    // Se não existir nenhum banco de dados exibir a mensagem selecionada
                    if (dataBaseExist(contextoWrapper, "produto.db") == false){
                        (contexto as SplashActivity).createSemConexaoPrimeiraDialog()

                    // Se existir banco de Dados exibir a mensagem selecionada
                    }else {
                        (contexto as SplashActivity).createSemConexaoSegundaDialog()
                    }
                }

            }

            // Ação para resposta da conexão
            override fun onResponse(call: Call<List<Produto>>, response: Response<List<Produto>>) {

                // Deleta as informações do banco de dados
                DeleteAllProdutoTask(database).execute()

                response.body()?.forEach {

                    // Insere as novas informações no banco de dados
                    InsertProdutoTask(database, it).execute()
                }

                // Inicia a recuperação da localização atual e abertura da Main
                obterLocalizacao(contexto)

            }
        })
    }


    /** LOCALIZAÇÃO E ABERTURA DA MAIN **/


    // Resposável por recuperar a ultima localização conhecida do aparelho e iniciar a tela Main
    @SuppressLint("MissingPermission")
    fun obterLocalizacao(contexto: Context){

        // Inicia os bancos de dados e trás todos os produtos
        var databaseProduto: ProdutoDatabase? = ProdutoDatabase.getInstance(contexto)
        GetAllProdutoTask(databaseProduto).execute()
        var databaseLoja: LojaDatabase? = LojaDatabase.getInstance(contexto)
        GetAllLojaTask(databaseLoja).execute()

        // Buscar a localização atual do dispositivo
        lateinit var fusedLocationClient: FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(contexto)

        // Recupera a Latitude e Longitude
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: android.location.Location? ->
                var latitude = location?.latitude!!
                var longitude = location?.longitude!!

                // Inicia a Main e envia as Coordenadas
                var intent = Intent(contexto, MainActivity::class.java)
                intent.putExtra("latitude", latitude)
                intent.putExtra("longitude", longitude)
                contexto.startActivity(intent)
                (contexto as SplashActivity).finish()

            }
    }


    /** BANCO DE DADOS **/

    // Verificar se já existe banco de dados criado
    fun dataBaseExist(contextoWrapper: ContextWrapper, dbName: String):Boolean{
        var dbFile: File = contextoWrapper.getDatabasePath(dbName)
        return  dbFile.exists()
    }

    /** CONTROLE DE VIEW **/

    // Exibe o componente
    fun showView(view: View) {
        View.VISIBLE
    }

    // Esconde o componente
    fun hideView(view: View) {
        View.INVISIBLE
    }




}