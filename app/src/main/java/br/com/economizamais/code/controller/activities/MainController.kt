package br.com.economizamais.code.controller.activities

import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto

class MainController{


    fun filtrarLojasDistancia (listaOriginal: MutableList<Loja>, listaFiltrada: MutableList<Loja>) : MutableList<Loja>{
        for (i in 0 until listaOriginal!!.size){
            if (listaOriginal[i].distancia > 50){

                listaFiltrada.add(listaOriginal[i])

            }

        }
        listaOriginal.removeAll(listaFiltrada)

        return listaOriginal
    }

    fun filtrarProdutosLojas (listaOriginal: MutableList<Produto>, listaFiltrada: MutableList<Produto>, listaFiltro: MutableList<Loja>) : MutableList<Produto>{
        for (i in 0 until listaOriginal.size){
            for (j in 0 until listaFiltro.size){
                if(listaOriginal[i].idLoja == listaFiltro[j].id){
                    listaFiltrada.add(listaOriginal[i])
                }

            }
        }
        listaOriginal.removeAll(listaFiltrada)

        return listaOriginal
    }







}