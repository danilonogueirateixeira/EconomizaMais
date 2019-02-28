package br.com.economizamais.code.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.multidex.MultiDex
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import br.com.economizamais.R
import br.com.economizamais.code.controller.splash.SplashController
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.activity_teste.*

class TesteActivity : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private val TAG = "MainActivity"
    private lateinit var mGoogleApiClient: GoogleApiClient
    private var mLocationManager: LocationManager? = null
    lateinit var mLocation: Location
    private var mLocationRequest: LocationRequest? = null
    private val listener: com.google.android.gms.location.LocationListener? = null
    private val UPDATE_INTERVAL = (2 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */

    lateinit var locationManager: LocationManager

    // Variáveis para  permissao
    private val ACCESS_FINE_LOCATION_RESULT_CODE = 111


    /** PERMISSÕES **/

    // Requisição de Permissões
    @SuppressLint("NewApi")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.i("TESTE PERMISSION", "4")

        when (requestCode) {
            ACCESS_FINE_LOCATION_RESULT_CODE -> {

                // Permissão aceita
                if (grantResults.isNotEmpty()
                    && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    Log.i("TESTE PERMISSION", "5")


                    startLocationUpdates();



                    // Opção de não mostrar novamente, selecionada
                } else if (!shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    createNeverAskAgainDialog()
                    Log.i("TESTE PERMISSION", "6")

                    // Permissão negada
                } else {
                    Log.i("TESTE PERMISSION", "7")

                    createMoreInfoDialog()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    override fun onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    override fun onConnectionSuspended(p0: Int) {

        Log.i("TESTE CONECT", "Connection Suspended");
        mGoogleApiClient.connect();
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        Log.i("TESTE CONECT", "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    override fun onLocationChanged(location: Location) {
        Log.i("TESTE CONECT","onlocation changed")

            var msg = "Updated Location: Latitude " + location.longitude.toString() + location.longitude;


        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

        Log.i("TESTE latitude",""+location.latitude)
        Log.i("TESTE longitude",""+location.longitude)

        onStop()

        Log.i("TESTE Stop latitude",""+location.latitude)
        Log.i("TESTE Stop longitude",""+location.longitude)



        // Inicia a Main e envia as Coordenadas
        if (location != null) {
            var intent = Intent(this, SplashActivity::class.java)
            intent.putExtra("latitude", location.latitude)
            intent.putExtra("longitude", location.longitude)
            this.startActivity(intent)
            this.finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onConnected(p0: Bundle?) {
        Log.i("TESTE CONECT","oN CONNECTED")

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.i("TESTE PERMISSION", "1")


            // Se a permissão já foinegada anteriomente
            if (this.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.i("TESTE PERMISSION", "2")

                createMoreInfoDialog()

                // Se a permissão ainda não foi negada
            } else {
                Log.i("TESTE PERMISSION", "3")

                requestMinhaLocalizacao(this)
            }

            // Se a permissão estiver liberada
        } else {

            // Inicia a conexão com o servidor de lojas


            startLocationUpdates();

            var fusedLocationProviderClient:
                    FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, OnSuccessListener<Location> { location ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        // Logic to handle location object
                        mLocation = location;


                        // Inicia a Main e envia as Coordenadas
//                        var intent = Intent(this, SplashActivity::class.java)
//                        intent.putExtra("latitude", mLocation.latitude)
//                        intent.putExtra("longitude", mLocation.longitude)
//                        this.startActivity(intent)
//                        this.finish()

                    }
                })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teste)

        MultiDex.install(this)

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        mLocationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        checkLocation()
    }

    private fun checkLocation(): Boolean {
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private fun isLocationEnabled(): Boolean {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun showAlert() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Ative a Localização")
            .setMessage("Suas configurações de localização estão desligadas.\nPor favor ative a Localização para utilizar este aplicativo")
            .setPositiveButton("Configurações de Localização", DialogInterface.OnClickListener { paramDialogInterface, paramInt ->
                val myIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(myIntent)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { paramDialogInterface, paramInt -> })
        dialog.show()
    }

    protected fun startLocationUpdates() {

        // Create the location request
        mLocationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL)
            .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
            mLocationRequest, this);
    }

    // Verifica a situação da solicitação de permissão
    fun requestMinhaLocalizacao(contexto: Context) {
        val ACCESS_FINE_LOCATION_RESULT_CODE = 111

        ActivityCompat.requestPermissions(
            (contexto as TesteActivity),
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
        (contexto as TesteActivity).finish()
    }

    // Cria a Caixa de Diálogo, para quando o usuário selecionar a opção de "Não Mostrar Novamente"
    fun createNeverAskAgainDialog() {
        android.app.AlertDialog.Builder(this).apply {
            setMessage(R.string.never_ask_again_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.go_to_settings) { _, _ -> goToAppDetailsSettings(this@TesteActivity) }
            setNegativeButton(R.string.nao) { d, _ -> finish()  }
        }.show()
    }

    // Cria a Caixa de Diálogo, para quando o usuário negar a permissão solicitada por padrão no App
    fun createMoreInfoDialog() {
        android.app.AlertDialog.Builder(this).apply {
            setMessage(R.string.more_info_dialog)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.sim) { _, _ -> requestMinhaLocalizacao(this@TesteActivity)}
            setNegativeButton(R.string.nao) { d, _ -> finish() }
        }.show()
    }
}