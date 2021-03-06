package br.com.economizamais.code.view

import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
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
import br.com.economizamais.code.controller.detalhes_produto.DetalhesProdutoController
import br.com.economizamais.code.controller.splash.SplashController
import br.com.economizamais.code.model.entities.Loja


var listaProdutos: MutableList<Produto> = arrayListOf()
var listaProdutosTemp: MutableList<Produto> = arrayListOf()

var listaLojas: MutableList<Loja> = arrayListOf()
var listaLojasTemp: MutableList<Loja> = arrayListOf()


var listaNomeMarca: MutableList<String> = arrayListOf()
var listaNomeLoja: MutableList<String> = arrayListOf()

var latitude: Double = 0.0
var longitude: Double = 0.0



class MainActivity : AppCompatActivity() {

    /** NAVBAR **/

    // Controle da Barra de Navegação Inferior
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            // Produtos
            R.id.navigation_produtos -> {

                // Mostra ou Esconde os Itens
                if (note_list_recyclerview.visibility == View.INVISIBLE) {
                    SplashController().showHide(note_list_recyclerview)
                    SplashController().showHide(lojas_list_recyclerview)
                }

                // Preenche a Lista
                val recyclerView = note_list_recyclerview
                if (listaProdutos != null) {
                    recyclerView.adapter = NoteListAdapter(listaProdutos!!, this)
                }
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.layoutManager = layoutManager

                // Adiciona Clique na lista
                recyclerView.addOnItemClickListener(object: OnItemClickListener {
                    override fun onItemClicked(position: Int, view: View) {

                        Log.i("TESTE POSITION", position.toString())
                        Log.i("TESTE POSITION", listaProdutos[position].toString())

                        MainController().cliqueProduto(listaProdutos, listaLojas, listaProdutos[position],latitude, longitude, this@MainActivity )
                    }
                })


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

                if (lojas_list_recyclerview.visibility == View.INVISIBLE) {

                    SplashController().showHide(lojas_list_recyclerview)
                    SplashController().showHide(note_list_recyclerview)
                }
                // Preenche a Lista
                val recyclerViewLojas = lojas_list_recyclerview
                if (listaLojas != null) {
                    recyclerViewLojas.adapter = LojasListAdapter(listaLojas!!, this)
                }
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                recyclerViewLojas.layoutManager = layoutManager

                recyclerViewLojas.addOnItemClickListenerLoja(object: OnItemClickListenerLoja {
                    override fun onItemClickedLoja(position: Int, view: View) {


                        Log.i("TESTE POSITION", position.toString())
                        Log.i("TESTE POSITION", listaLojas[position].toString())

                        //val selectedItem = listaProdutos[position].nome+" "+ listaProdutos[position].marca.toString()

                       // MainController().cliqueProduto(listaProdutos, listaLojas, listaProdutos[position],latitude, longitude, this@MainActivity )

                        //recyclerView.adapter!!.getItemId(position)

                        MainController().cliqueLoja(listaProdutos, listaLojas, listaLojas[position], latitude, longitude, this@MainActivity )

                    }
                })



                // Prenche o AutoComplete
                val adapter = AutoCompleteAdapter(
                    this, // Context
                    android.R.layout.simple_dropdown_item_1line, // Layout
                    listaNomeLoja // Array
                )
                pesquisaprodutos_autocomplete.setAdapter(adapter)

                // Altera o texto padrão do AutoComplete
                pesquisaprodutos_autocomplete.setHint(R.string.pesquisar_lojas)


                /////////////////////////////////////////////////////////////////////////////////////////////
                // Quantidade de digitos para abrir o AutoComplete
                pesquisaprodutos_autocomplete.threshold = 2

                // Clique em item no AutoComplete
                pesquisaprodutos_autocomplete.onItemClickListener = AdapterView.OnItemClickListener{
                        parent,view,position,id->
                    val selectedItem = parent.getItemAtPosition(position).toString()


                    var loja = MainController().recuperaIdLoja(selectedItem)

                    Log.i("TESTE LOJA", loja.toString())

                    MainController().cliqueLoja(listaProdutos, listaLojas, loja, latitude, longitude, this )

                }

                return@OnNavigationItemSelectedListener true
            }

            // Listas
            R.id.navigation_listas -> {


                    SplashController().showHide(note_list_recyclerview)
                    SplashController().showHide(lojas_list_recyclerview)


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
        latitude = intent.getSerializableExtra("latitude") as Double
        longitude  = intent.getSerializableExtra("longitude") as Double

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
            listaNomeMarca.add(listaProdutos!![i].nome+" "+ listaProdutos!![i].marca+" "+ listaProdutos[i].descricao)
            Log.i("TESTE LISTAPRODUTOS " +i, listaProdutos[i].nome +" "+ listaProdutos[i].marca)
            Log.i("TESTE LISTANOMEMARCA " +i, listaNomeMarca[i])

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



//        pesquisaprodutos_autocomplete.onItemClickListener = AdapterView.OnItemClickListener{
//                parent,view,position,id->
//            val selectedItem = parent.getItemAtPosition(position).toString()


        recyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {


                Log.i("TESTE POSITION", position.toString())
                Log.i("TESTE POSITION", listaProdutos[position].toString())

                val selectedItem = listaProdutos[position].nome+" "+ listaProdutos[position].marca.toString()

                MainController().cliqueProduto(listaProdutos, listaLojas, listaProdutos[position],latitude, longitude, this@MainActivity )

                //recyclerView.adapter!!.getItemId(position)
            }
        })

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

            var produto = MainController().recuperaId(selectedItem)

            Toast.makeText(applicationContext,"$selectedItem",Toast.LENGTH_SHORT).show()

            Log.i("TESTE POSITION", produto.toString())

            MainController().cliqueProduto(listaProdutos, listaLojas, produto, latitude, longitude, this )
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
//        pesquisaprodutos_autocomplete.onFocusChangeListener = View.OnFocusChangeListener{
//                view, b ->
//            if(b){
//                pesquisaprodutos_autocomplete.showDropDown()
//            }
//        }
    }







    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(p0: View) {
                p0?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(p0: View) {
                p0?.setOnClickListener({
                    val holder = getChildViewHolder(p0)
                    onClickListener.onItemClicked(holder.adapterPosition, p0)
                })
            }
        })
    }


    interface OnItemClickListenerLoja {
        fun onItemClickedLoja(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListenerLoja(onClickListenerLoja: OnItemClickListenerLoja) {
        this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(p0: View) {
                p0?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(p0: View) {
                p0?.setOnClickListener({
                    val holder = getChildViewHolder(p0)
                    onClickListenerLoja.onItemClickedLoja(holder.adapterPosition, p0)
                })
            }
        })
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








