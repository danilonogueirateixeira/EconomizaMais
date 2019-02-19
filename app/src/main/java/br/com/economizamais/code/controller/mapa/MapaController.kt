package br.com.economizamais.code.controller.mapa

import br.com.economizamais.code.model.entities.Loja
import java.io.UnsupportedEncodingException

class MapaController{

    // Chamada de API DIRECTIONS
    fun sendRequest(latitude: Double, longitude: Double, loja: Loja, contexto: DirectionFinderListener) {

        // Coordenadas de Origem
        val origin = "$latitude,$longitude"

        // Coordenadas de Destino
        val destination = "${loja.latitude},${loja.longitude}"

        // Chamada da API
        try {
            DirectionFinder(contexto , origin, destination).execute()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

    }
}