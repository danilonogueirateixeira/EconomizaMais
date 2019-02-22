package br.com.economizamais.code.controller.detalhes_produto

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto
import br.com.economizamais.code.view.DetalhesProdutoActivity

class DetalhesProdutoController{


    /** PERMISSÕES **/

    // Resposável pela primeira verificação de permissões
    @SuppressLint("NewApi")
    fun fazerLigacao(contexto: Context, contextoWrapper: ContextWrapper, telefone: String) {

        // Se a permissão estiver negada
        if (ContextCompat.checkSelfPermission(contexto, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            // Se a permissão já foinegada anteriomente
            if ((contexto as DetalhesProdutoActivity).shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                contexto.createMoreInfoDialog()

                // Se a permissão ainda não foi negada
            } else {
                requestFazerLicagao(contexto)
            }

            // Se a permissão estiver liberada
        } else {

            // Inicia a conexão com o servidor de lojas
            makeCall(telefone, contexto)
        }
    }


    // Verifica a situação da solicitação de permissão
    fun requestFazerLicagao(contexto: Context) {
        val CALL_PHONE_RESULT_CODE = 111

        ActivityCompat.requestPermissions(
            (contexto as DetalhesProdutoActivity),
            arrayOf(Manifest.permission.CALL_PHONE),
            CALL_PHONE_RESULT_CODE)
    }


    // Abre as configurações do aplicativo para alterar as permissões manualmente
    fun goToAppDetailsSettings(contexto: Context) {
        val appSettings = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", contexto.packageName, null)
        }

        contexto.startActivity(appSettings)
    }

    // Realizar Ligação
    fun makeCall(number: String, contexto: Context) {
        val callIntent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
        contexto.startActivity(callIntent)
    }

    // Recupera os dados da loja
    fun dadosLoja (idloja: Int, listaLojas: MutableList<Loja>): Loja{
        var loja: Loja? =  null
        for (i in 0 until listaLojas.size){
            if (listaLojas[i].id == idloja){
               loja = listaLojas[i]
            }
        }

        return loja!!
    }

    // Filtra produtos pela loja
    fun filtrarProdutosLojas (listaOriginal: MutableList<Produto>, listaFiltrada: MutableList<Produto>, idloja: Int, produtoId: Int, produtoNome: String) : MutableList<Produto>{
        for (i in 0 until listaOriginal.size){
                if(listaOriginal[i].idLoja != idloja || listaOriginal[i].id == produtoId || listaOriginal[i].nome != produtoNome){
                    listaFiltrada.add(listaOriginal[i])
                }


        }
        listaOriginal.removeAll(listaFiltrada)

        return listaOriginal
    }

}