package br.com.economizamais.code.controller

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil



class Location{

    fun distancia (latOrigem: Double, lgtOrigem: Double, latDestino: Double, lgtDestino: Double):Double{

        val posicaoInicial = LatLng(latOrigem, lgtOrigem)
        val posicaoFinal = LatLng(latDestino, lgtDestino)


    var distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaoFinal)

        distance = distance/1000
        Log.i("TESTE","A Distancia era = "+distance)


        distance = distance + (distance*0.5)

        Log.i("TESTE","A Distancia é = "+distance)


        return distance
    }

}




