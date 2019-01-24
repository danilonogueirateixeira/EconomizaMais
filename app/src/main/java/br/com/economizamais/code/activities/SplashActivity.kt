package br.com.economizamais.code.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import br.com.economizamais.R
import br.com.economizamais.code.activities.Main2Activity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            mostrarLogin()
        }, 2000)

    }



    private fun mostrarLogin() {
        val intent = Intent(
            this,
            Main2Activity::class.java
        )
        startActivity(intent)
        finish()
    }
}
