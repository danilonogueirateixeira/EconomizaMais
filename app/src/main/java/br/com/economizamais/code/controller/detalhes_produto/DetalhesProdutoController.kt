package br.com.economizamais.code.controller.detalhes_produto

import android.content.Context
import android.content.Intent
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto
import br.com.economizamais.code.view.DetalhesProdutoActivity
import br.com.economizamais.code.view.MainActivity

class DetalhesProdutoController{


    fun dadosLoja (idloja: Int, listaLojas: MutableList<Loja>): Loja{
        var loja: Loja? =  null
        for (i in 0 until listaLojas.size){
            if (listaLojas[i].id == idloja){
               loja = listaLojas[i]
            }
        }

        return loja!!
    }




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