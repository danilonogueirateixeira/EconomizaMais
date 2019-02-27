package br.com.economizamais.code.controller.detalhes_loja

import br.com.economizamais.code.model.entities.Produto

class DetalhesLojaController{



    // Filtra produtos pela loja
    fun filtrarProdutosLoja (listaOriginal: MutableList<Produto>, listaFiltrada: MutableList<Produto>, idloja: Int) : MutableList<Produto>{
        for (i in 0 until listaOriginal.size){
            if(listaOriginal[i].idLoja != idloja){
                listaFiltrada.add(listaOriginal[i])
            }


        }
        listaOriginal.removeAll(listaFiltrada)

        return listaOriginal
    }

}