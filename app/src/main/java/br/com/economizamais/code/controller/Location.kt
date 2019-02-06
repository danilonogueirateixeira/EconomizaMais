package br.com.economizamais.code.controller

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.json.JSONObject


class Location{


var distanciaFinal: Int? = null



//    LatLng  = new LatLng(latitude,longitude);
//    LatLng posicaiFinal = new LatLng(latitude,longitude);
//
//    double distance = SphericalUtil.computeDistanceBetween(posicaoInicial, posicaiFinal);
//    Log.i("LOG","A Distancia é = "+formatNumber(distance));
//
//    private String formatNumber(double distance) {
//        String unit = "m";
//        if (distance  1000) {
//            distance /= 1000;
//            unit = "km";
//        }
//
//        return String.format("%4.3f%s", distance, unit);
//    }
//
//    -15.941687241427971,-48.2416208222553



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


    fun testeGoogle(contexto: Context,latOrigem: Float, lgtOrigem: Float , latDestino: Float, lgtDestino: Float) = runBlocking{

        val chaveAPI = "AIzaSyD2g8sFgQ6MJFKIRbmTEpy1kRtNO_x6cmA"


        val urlDistance = "https://maps.googleapis.com/maps/api/distancematrix/json?&origins="+latOrigem+","+lgtOrigem+"&destinations="+latDestino+","+lgtDestino+"&key="+chaveAPI

       async {

          val directionsRequest = object : StringRequest(
              Request.Method.GET, urlDistance, com.android.volley.Response.Listener<String> {
                      response ->
                  val jsonResponse = JSONObject(response)
                  // Get routes
                  val routes = jsonResponse.getJSONArray("rows")
                  val legs = routes.getJSONObject(0).getJSONArray("elements")
                  val steps = legs.getJSONObject(0)
                  val distancia = steps.getJSONObject("distance")
                  val valor = distancia.get("value")



                  android.util.Log.i("TESTE LOCATION TESTE",  valor.toString())



              }, com.android.volley.Response.ErrorListener {

                      _ ->                android.util.Log.i("TESTE LOCATION ERROR",  "ERROR")


              }){



          }
          val requestQueue = Volley.newRequestQueue(contexto)
          requestQueue.add(directionsRequest)


           requestQueue.addRequestFinishedListener<Int> {

              // distanciaFinal = it.sequence
               android.util.Log.i("TESTE LOCATION REQUEST",   it.toString())


           }




      }.await()



    }




