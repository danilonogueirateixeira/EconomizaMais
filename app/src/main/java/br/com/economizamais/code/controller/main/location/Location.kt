package br.com.economizamais.code.controller.main.location

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil

class Location{

    // Recupera a distancia entre duas coordenadas
    fun distancia (latOrigem: Double, lgtOrigem: Double, latDestino: Double, lgtDestino: Double):Double{

        val posicaoInicial = LatLng(latOrigem, lgtOrigem)
        val posicaoFinal = LatLng(latDestino, lgtDestino)


    var distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaoFinal)

        distance = distance/1000

        // Adiciona 50% a mais na distância para o calculo ficar mais aproximado do real
        distance = distance + (distance*0.5)

        return distance
    }

}




