package br.com.economizamais.code.view

import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import br.com.economizamais.code.controller.main.adapter.NoteListAdapter
import br.com.economizamais.code.controller.database.ProdutoDatabase
import br.com.economizamais.code.model.entities.Produto
import kotlinx.android.synthetic.main.activity_main.*
import br.com.economizamais.R
import br.com.economizamais.code.controller.main.location.Location
import br.com.economizamais.code.controller.main.MainController
import br.com.economizamais.code.controller.main.autocomplete.AutoCompleteAdapter
import br.com.economizamais.code.controller.main.adapter.LojasListAdapter
import br.com.economizamais.code.controller.database.LojaDatabase
import br.com.economizamais.code.model.entities.Loja


var listaProdutos: MutableList<Produto> = arrayListOf()
var listaProdutosTemp: MutableList<Produto> = arrayListOf()

var listaLojas: MutableList<Loja> = arrayListOf()
var listaLojasTemp: MutableList<Loja> = arrayListOf()


var listaNomeMarca: MutableList<String> = arrayListOf()
var listaNomeLoja: MutableList<String> = arrayListOf()




class MainActivity : AppCompatActivity() {

    /** NAVBAR **/

    // Controle da Barra de Navegação Inferior
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            // Produtos
            R.id.navigation_produtos -> {

                // Preenche a Lista
                val recyclerView = note_list_recyclerview
                if (listaProdutos != null) {
                    recyclerView.adapter = NoteListAdapter(listaProdutos!!, this)
                }
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.layoutManager = layoutManager

                // Prenche o AutoComplete
                val adapter = AutoCompleteAdapter(
                    this, // Context
                    android.R.layout.simple_dropdown_item_1line, // Layout
                    listaNomeMarca // Array
                )
                pesquisaprodutos_autocomplete.setAdapter(adapter)

                // Altera o texto padrão do AutoComplete
                pesquisaprodutos_autocomplete.setHint(R.string.pesquisar_produtos)

                return@OnNavigationItemSelectedListener true
            }

            // Lojas
            R.id.navigation_lojas -> {

                // Preenche a Lista
                val recyclerView = note_list_recyclerview
                if (listaLojas != null) {
                    recyclerView.adapter = LojasListAdapter(listaLojas!!, this)
                }
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.layoutManager = layoutManager

                // Prenche o AutoComplete
                val adapter = AutoCompleteAdapter(
                    this, // Context
                    android.R.layout.simple_dropdown_item_1line, // Layout
                    listaNomeLoja // Array
                )
                pesquisaprodutos_autocomplete.setAdapter(adapter)

                // Altera o texto padrão do AutoComplete
                pesquisaprodutos_autocomplete.setHint(R.string.pesquisar_lojas)

                return@OnNavigationItemSelectedListener true
            }

            // Listas
            R.id.navigation_listas -> {

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    /** ONCREATE **/

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recupera a navegação na NAVBAR
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Recebe os dados do Splash
        var intent: Intent = getIntent()
        var latitude: Double = intent.getSerializableExtra("latitude") as Double
        var longitude: Double = intent.getSerializableExtra("longitude") as Double

        // Recupera a distanca para todas as lojas
        for(i in 0 until  listaLojas!!.size ){
           listaLojas!![i].distancia =  Location()
               .distancia(latitude, longitude, listaLojas!![i].latitude,listaLojas!![i].longitude)
        }

        // Filtra as Lojas através da distancia
        listaLojas = MainController()
            .filtrarLojasDistancia(listaLojas, listaLojasTemp)

        // Filtra os Produtos através das lojas por distancia
        listaProdutos = MainController()
            .filtrarProdutosLojas(listaProdutos, listaProdutosTemp, listaLojasTemp)

        // Limpa a lista do AutoComplete
        listaNomeMarca.clear()

        // Aciciona os produtos selecionados a lista do AutoComplete
        for(i in 0 until  listaProdutos!!.size ){
            listaNomeMarca.add(listaProdutos!![i].nome+" "+ listaProdutos!![i].marca)
        }

        // Limpa a lista do AutoComplete
        listaNomeLoja.clear()
        // Aciciona as lojas selecionadas a lista do AutoComplete
        for(i in 0 until  listaLojas!!.size ){
            listaNomeLoja.add(listaLojas!![i].razaoSocial)
        }




        // Preenche a lista de Produtos na pagina inicial
        val recyclerView = note_list_recyclerview
        if (listaProdutos != null) {
            recyclerView.adapter = NoteListAdapter(listaProdutos!!, this)
        }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager


        // Preenche o AutoComplete de produtos na pagina inicial
        val adapter = AutoCompleteAdapter(
            this, // Context
            android.R.layout.simple_dropdown_item_1line, // Layout
            listaNomeMarca // Array
        )
        pesquisaprodutos_autocomplete.setAdapter(adapter)

        // Quantidade de digitos para abrir o AutoComplete
        pesquisaprodutos_autocomplete.threshold = 2

        // Clique em item no AutoComplete
        pesquisaprodutos_autocomplete.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            val selectedItem = parent.getItemAtPosition(position).toString()

            Toast.makeText(applicationContext,"$selectedItem",Toast.LENGTH_SHORT).show()
        }

        // Clique fora do AutoCompelte
//        pesquisaprodutos_autocomplete.setOnDismissListener {
//
//            Toast.makeText(applicationContext,"Clicou Fora",Toast.LENGTH_SHORT).show()
//        }


//        // Set a click listener for root layout
//        container.setOnClickListener{
//            val text = pesquisaprodutos_autocomplete.text
//            Toast.makeText(applicationContext,"Inputted : $text",Toast.LENGTH_SHORT).show()
//        }


        // Altera o foco para o AutoComplete
        pesquisaprodutos_autocomplete.onFocusChangeListener = View.OnFocusChangeListener{
                view, b ->
            if(b){
                pesquisaprodutos_autocomplete.showDropDown()
            }
        }
    }
}



/** BANCO DE DADOS **/

// Recupera todos os produtos do banco de dados
class GetAllProdutoTask (val database: ProdutoDatabase?): AsyncTask<Void, Void, Void?>(){
    override fun doInBackground(vararg params: Void?): Void? {

        listaProdutos = (database?.ProdutoDao()?.getAllProduto() as MutableList<Produto>?)!!

        return null
    }
}

// Recupera todas as lojas do banco de dados
class GetAllLojaTask (val database: LojaDatabase?): AsyncTask<Void, Void, Void?>(){
    override fun doInBackground(vararg params: Void?): Void? {

        listaLojas = (database?.LojaDao()?.getAllLoja() as MutableList<Loja>?)!!

        return null
    }
}









