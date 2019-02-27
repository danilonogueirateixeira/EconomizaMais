package br.com.economizamais.code.controller.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto
import br.com.economizamais.code.view.*

class MainController{


    // Filtra Lojas através da distância
    fun filtrarLojasDistancia (listaOriginal: MutableList<Loja>, listaFiltrada: MutableList<Loja>) : MutableList<Loja>{
        for (i in 0 until listaOriginal!!.size){
            if (listaOriginal[i].distancia > 50){

                listaFiltrada.add(listaOriginal[i])

            }

        }
        listaOriginal.removeAll(listaFiltrada)

        return listaOriginal
    }

    // Filtra os Produtos através das lojas filtradas
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

    // Recupera o ID do produto Clicado no Autocomplete
    fun recuperaId(produtoClicado: String):Produto{
        var produto: Produto? = null

        for (i in 0 until listaProdutos.size){

            if(produtoClicado.equals(listaProdutos[i].nome+" "+listaProdutos[i].marca+" "+ listaProdutos[i].descricao)){

                produto = listaProdutos[i]

            }
        }

        return produto!!
    }

    // Recupera o ID da Loja clicada no Autocomplete
    fun recuperaIdLoja(lojaClicada: String):Loja{
        var loja: Loja? = null

        for (i in 0 until listaLojas.size){

            if(lojaClicada.equals(listaLojas[i].razaoSocial)){

                loja = listaLojas[i]

            }
        }

        return loja!!
    }

    // Recupera o clique do produto na lista
    fun cliqueProduto(listaProdutos: MutableList<Produto>, listaLojas: MutableList<Loja>, produtoClicado: Produto,latitude: Double, longitude: Double, contexto: Context){

        for (i in 0 until listaProdutos.size){

            if(produtoClicado.equals(listaProdutos[i])){

                enviarDetalhes(contexto, listaProdutos[i],listaProdutos, listaLojas, latitude, longitude)

            }

        }


    }

    // Recupera o clique da Loja  na lista
    fun cliqueLoja(listaProdutos: MutableList<Produto>, listaLojas: MutableList<Loja>, lojaClicada: Loja,latitude: Double, longitude: Double, contexto: Context){

        for (i in 0 until listaLojas.size){

            if(lojaClicada.equals(listaLojas[i])){

                enviarDetalhesLoja(contexto, listaLojas[i],listaProdutos, listaLojas, latitude, longitude)

            }

        }

    }

    // Envia dados para tela e detalhes de produtos
    fun enviarDetalhesLoja(contexto: Context, loja: Loja, produtos: MutableList<Produto>, lojas: MutableList<Loja>, latitude: Double, longitude: Double){

        var listaP : ArrayList<Produto> = produtos as ArrayList<Produto>
        val produtos = Bundle()
        produtos.putParcelableArrayList("produtos", listaP)

        var listaL: ArrayList<Loja> = lojas as ArrayList<Loja>
        val lojas = Bundle()
        lojas.putParcelableArrayList("lojas", listaL)

        // Inicia a Main e envia as Coordenadas
        var intent = Intent(contexto, DetalhesLojaActivity::class.java)
        intent.putExtra("loja", loja)
        intent.putExtras(produtos)
        intent.putExtras(lojas)
        intent.putExtra("latitude", latitude)
        intent.putExtra("longitude", longitude)

        contexto.startActivity(intent)
    }


    // Envia dados para tela e detalhes de produtos
    fun enviarDetalhes(contexto: Context, produto: Produto, produtos: MutableList<Produto>, lojas: MutableList<Loja>, latitude: Double, longitude: Double){

        var listaP : ArrayList<Produto> = produtos as ArrayList<Produto>
        val produtos = Bundle()
        produtos.putParcelableArrayList("produtos", listaP)

        var listaL: ArrayList<Loja> = lojas as ArrayList<Loja>
        val lojas = Bundle()
        lojas.putParcelableArrayList("lojas", listaL)

        // Inicia a Main e envia as Coordenadas
        var intent = Intent(contexto, DetalhesProdutoActivity::class.java)
        intent.putExtra("produto", produto)
        intent.putExtras(produtos)
        intent.putExtras(lojas)
        intent.putExtra("latitude", latitude)
        intent.putExtra("longitude", longitude)

        contexto.startActivity(intent)
    }

}


