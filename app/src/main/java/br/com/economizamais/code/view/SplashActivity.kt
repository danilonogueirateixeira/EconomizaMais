package br.com.economizamais.code.view

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Handler
import android.provider.Settings
import android.util.Log
import br.com.economizamais.R
import br.com.economizamais.code.controller.splash.SplashController
import kotlinx.android.synthetic.main.activity_splash.*



class SplashActivity : AppCompatActivity() {

    // Variáveis para  permissao
    private val ACCESS_FINE_LOCATION_RESULT_CODE = 111


    /** PERMISSÕES **/

    // Requisição de Permissões
    @SuppressLint("NewApi")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            ACCESS_FINE_LOCATION_RESULT_CODE -> {

                // Permissão aceita
                if (grantResults.isNotEmpty()
                    && grantResults.first() == PackageManager.PERMISSION_GRANTED) {



                    // Tenta acessar o servidor
                    SplashController().getLojasServidor(this@SplashActivity, this@SplashActivity, latitude, longitude)

                // Opção de não mostrar novamente, selecionada
                } else if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    createNeverAskAgainDialog()

                // Permissão negada
                } else {
                    createMoreInfoDialog()
                }
            }
        }
    }

    // Cria a Caixa de Diálogo, para quando o usuário selecionar a opção de "Não Mostrar Novamente"
    fun createNeverAskAgainDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.never_ask_again_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.go_to_settings) { _, _ -> SplashController().goToAppDetailsSettings(this@SplashActivity) }
            setNegativeButton(R.string.nao) { d, _ -> finish()  }
        }.show()
    }

    // Cria a Caixa de Diálogo, para quando o usuário negar a permissão solicitada por padrão no App
    fun createMoreInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.more_info_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.sim) { _, _ -> SplashController().requestMinhaLocalizacao(this@SplashActivity)}
            setNegativeButton(R.string.nao) { d, _ -> finish() }
        }.show()
    }


    /** CONEXÕES **/

    // Cria a Caixa de Diálogo, para abertura sem dados quando a conexão com o servidor não for possível
    fun createSemConexaoPrimeiraDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.conexao_primeira_abertura)
            setTitle(R.string.conexao_dialog_titulo)
            setPositiveButton(R.string.tentar_novamente) { _, _ -> SplashController().getLojasServidor(this@SplashActivity, this@SplashActivity, latitude, longitude) }
            setNegativeButton(R.string.sair) { d, _ -> finish() }
        }.show()
    }

    // Cria a Caixa de Diálogo, para abertura com dados quando a conexão com o servidor não for possível
    fun createSemConexaoSegundaDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.conexao_segunda_abertura)
            setTitle(R.string.conexao_dialog_titulo)
            setPositiveButton(R.string.tentar_novamente) { _, _ -> SplashController().getLojasServidor(this@SplashActivity, this@SplashActivity, latitude, longitude) }
            setNegativeButton(R.string.utilizar_dados_existentes) { d, _ ->




                // Inicia a abertura da main com os dados armazenados localmente
                SplashController().obterLocalizacao(this@SplashActivity, latitude, longitude)
            }
        }.show()
    }

    /** ONCREATE **/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.com.economizamais.R.layout.activity_splash)



        // Recebe os dados do Splash
        var intent: Intent = getIntent()
        latitude = intent.getSerializableExtra("latitude") as Double
        longitude  = intent.getSerializableExtra("longitude") as Double

        Log.i("TESTE Splash latitude",""+latitude)
        Log.i("TESTE Splash longitude",""+longitude)



        //Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

        //startActivityForResult(intent, 1);

        // Esconde a progressBar
        //SplashController().hideView(splash_progressbar)



        // Delay de 1 segundo
        Handler().postDelayed({

            // Verifica permissão
            SplashController().getMinhaLocalizacao(this, this, latitude, longitude)

        }, 1000)

        // Delay de 2 segundos
        Handler().postDelayed({

            // Mostra a progressBar
            //SplashController().showView(splash_progressbar)

        }, 2000)

    }

}



