package br.com.economizamais.code.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.location.LocationListener
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.economizamais.code.controller.adapter.NoteListAdapter
import br.com.economizamais.code.controller.database.ProdutoDatabase
import br.com.economizamais.code.model.entities.Produto
import kotlinx.android.synthetic.main.activity_main.*
import br.com.economizamais.R
import br.com.economizamais.code.controller.Location
import br.com.economizamais.code.controller.adapter.LojasListAdapter
import br.com.economizamais.code.controller.database.LojaDatabase
import br.com.economizamais.code.model.entities.Loja




var listaProdutos: MutableList<Produto> = arrayListOf()
var listaProdutosTemp: MutableList<Produto> = arrayListOf()

var listaLojas: MutableList<Loja> = arrayListOf()
var listaLojasTemp: MutableList<Loja> = arrayListOf()


var listaNomeMarca = arrayListOf<String>()


class MainActivity : AppCompatActivity() {



    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_produtos -> {

                val recyclerView = note_list_recyclerview
                if (listaProdutos != null) {
                    recyclerView.adapter = NoteListAdapter(listaProdutos!!, this)
                }
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.layoutManager = layoutManager

                recyclerView.isAnimating
                recyclerView.animation

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_lojas -> {

                val recyclerView = note_list_recyclerview
                if (listaLojas != null) {
                    recyclerView.adapter = LojasListAdapter(listaLojas!!, this)
                }
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                recyclerView.layoutManager = layoutManager

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_listas -> {

               // showHide(note_list_recyclerview)
               // showHide(pesquisaprodutos_autocomplete)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun showHide(view:View) {
        view.visibility = if (view.visibility == View.VISIBLE){
            View.INVISIBLE
        } else{
            View.VISIBLE
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)





        var intent: Intent = getIntent()
        var latitude: Double = intent.getSerializableExtra("latitude") as Double
        var longitude:Double = intent.getSerializableExtra("longitude") as Double

        Log.i("TESTE MAIN",  latitude.toString())
        Log.i("TESTE MAIN",  longitude.toString())


        Log.i("TESTE MAIN",  "TESTANDO")


            Handler().postDelayed({
                Log.i("TESTE hundler OK",  "OK")
               // Log.i("TESTE Main Antes",  listaLojas!![0].toString())


            }, 5000)


        // Location().distancia(latitude, longitude, -15.945638, -48.260506)
        for(i in 0 until  listaLojas.size ){
            Log.i("TESTE Main Antes",  listaLojas!![i].toString())

        }


        for(i in 0 until  listaLojas!!.size ){
           listaLojas!![i].distancia =  Location().distancia(latitude, longitude, listaLojas!![i].latitude,listaLojas!![i].longitude)
        }

        fun teste (lista1: MutableList<Loja>, lista2: MutableList<Loja>){
            for (i in 0 until lista1!!.size){
                if (lista1!![i].distancia > 50){

                    lista2?.add(lista1!![i])

                }

            }
            lista1.removeAll(lista2)
        }

        teste(listaLojas, listaLojasTemp)

        for (i in 0 until listaProdutos.size){
            for (j in 0 until listaLojasTemp.size){
                if(listaProdutos[i].idLoja == listaLojasTemp[j].id){
                    listaProdutosTemp.add(listaProdutos[i])
                }

            }
        }
        listaProdutos.removeAll(listaProdutosTemp)



        for(i in 0 until  listaLojas!!.size ){
            Log.i("TESTE Main Depois",  listaLojas!![i].toString())
        }

        for(i in 0 until  listaProdutos!!.size ){
            Log.i("TESTE Main Depois",  listaProdutos!![i].toString())
        }



        val recyclerView = note_list_recyclerview
        if (listaProdutos != null) {
            recyclerView.adapter = NoteListAdapter(listaProdutos!!, this)
        }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager





        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)













        // Initialize a new array with elements
        for(i in 0 until  listaProdutos!!.size ){
            listaNomeMarca.add(listaProdutos!![i].nome+" "+ listaProdutos!![i].marca)
            Log.i("TESTE AUTOCOMPLETE",  listaNomeMarca[i])

        }





        // Initialize a new array adapter object
        val adapter = ArrayAdapter<String>(
            this, // Context
            android.R.layout.simple_dropdown_item_1line, // Layout
            listaNomeMarca // Array
        )


        // Set the AutoCompleteTextView adapter
        pesquisaprodutos_autocomplete.setAdapter(adapter)


        // Auto complete threshold
        // The minimum number of characters to type to show the drop down
        pesquisaprodutos_autocomplete.threshold = 1


        // Set an item click listener for auto complete text view
        pesquisaprodutos_autocomplete.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->
            val selectedItem = parent.getItemAtPosition(position).toString()
            // Display the clicked item using toast
            Toast.makeText(applicationContext,"Selected : $selectedItem",Toast.LENGTH_SHORT).show()
        }


        // Set a dismiss listener for auto complete text view
        pesquisaprodutos_autocomplete.setOnDismissListener {
            Toast.makeText(applicationContext,"Suggestion closed.",Toast.LENGTH_SHORT).show()
        }


        // Set a click listener for root layout
        container.setOnClickListener{
            val text = pesquisaprodutos_autocomplete.text
            Toast.makeText(applicationContext,"Inputted : $text",Toast.LENGTH_SHORT).show()
        }


        // Set a focus change listener for auto complete text view
        pesquisaprodutos_autocomplete.onFocusChangeListener = View.OnFocusChangeListener{
                view, b ->
            if(b){
                // Display the suggestion dropdown on focus
                pesquisaprodutos_autocomplete.showDropDown()
            }
        }
        //getDistancia(-15.941687241427971,-48.2416208222553, -15.941687241427971,-48.2416208222553)



        //getDistancia(-15.941687241427971,-48.2416208222553, -15.941687241427971,-48.2416208222553)



        //getDistanceInfo(getCurrentCoordinate(), "Cristo+Redentor")



    }










    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: android.location.Location) {
            Log.i("TESTE MAP Antigo",  location.latitude.toString())
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }







}







class GetAllProdutoTask (val database: ProdutoDatabase?): AsyncTask<Void, Void, Void?>(){


    override fun doInBackground(vararg params: Void?): Void? {


        listaProdutos = (database?.ProdutoDao()?.getAllProduto() as MutableList<Produto>?)!!

        return null
    }
}

class GetAllLojaTask (val database: LojaDatabase?): AsyncTask<Void, Void, Void?>(){


    override fun doInBackground(vararg params: Void?): Void? {


        listaLojas = (database?.LojaDao()?.getAllLoja() as MutableList<Loja>?)!!

        return null
    }
}









