package br.com.economizamais.code.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import br.com.economizamais.code.controller.detalhes_loja.DetalhesLojaController
import br.com.economizamais.code.controller.detalhes_loja.DetalhesLojaListAdapter
import br.com.economizamais.code.controller.detalhes_produto.DetalhesProdutoController
import br.com.economizamais.code.controller.main.FormataDados
import br.com.economizamais.code.controller.main.MainController
import br.com.economizamais.code.controller.main.autocomplete.AutoCompleteAdapter
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detalhes_loja.*
import android.R
import android.support.v7.widget.RecyclerView


class DetalhesLojaActivity : AppCompatActivity() {

    var listaProdutosTemp: MutableList<Produto> = arrayListOf()
    var listaNomeMarca: MutableList<String> = arrayListOf()



    private var PHONE_NUMBER = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.com.economizamais.R.layout.activity_detalhes_loja)

        // Esconde a ActionBar
        getSupportActionBar()!!.hide()

        // Recebe os dados do Produto clicado
        var intent: Intent = getIntent()
        var loja: Loja = intent.getParcelableExtra("loja")
        var latitude: Double = intent.getSerializableExtra("latitude") as Double
        var longitude: Double = intent.getSerializableExtra("longitude") as Double

        // Recebe a lista de Produtos
        var extrasProduto: Bundle = getIntent().extras
        var produtos: ArrayList<Produto> = extrasProduto.getParcelableArrayList("produtos")

        // Recebe a lista de de Lojas
        var extrasLojas: Bundle = getIntent().extras
        var lojas: ArrayList<Loja> = extrasLojas.getParcelableArrayList("lojas")

        // Insere os dados da Loja na tela
        textView_Endereco.text = loja.endereco+" "+loja.bairro
        textView_Cidade.text = loja.cidade
        textView_Estado.text = "- "+ loja.uf
        textView_Distancia.text = FormataDados().formataKm(loja.distancia)
        Glide.with(this).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+loja.image).into(imageView_LogoLoja)


        // Recupera o telefone da loja para ligação
        PHONE_NUMBER = loja.telefone

        // Clique para Rotas
        imageView_Rotas.setOnClickListener {

            // Inicia o Mapa e envia as Coordenadas
            var intent = Intent(this, Mapa::class.java)
            intent.putExtra("loja", loja)
            intent.putExtra("latitude", latitude)
            intent.putExtra("longitude", longitude)

            this.startActivity(intent)
        }

        // Clique para Ligar
        imageView_Ligar.setOnClickListener {

            // Realiza Ligação
            DetalhesProdutoController().fazerLigacao(this, this, loja.telefone)
        }

        // Clique em botão de voltar
        imageView_Voltar.setOnClickListener {

            this.finish()
        }



        // Filtra lista para exibição do recyclerView de produtos semelhantes
        var produtosLoja = DetalhesLojaController().filtrarProdutosLoja(produtos, listaProdutosTemp, loja.id) as ArrayList<Produto>


        Log.i("TESTE ORDENAMENTO", produtosLoja.toString())

        // Ordena os produtos por preço
        val produtosOrdenados = produtosLoja.sortedWith(compareBy(Produto::nome, Produto::nome))


        Log.i("TESTE ORDENAMENTO", produtosOrdenados.toString())


        // Limpa a lista do AutoComplete
        listaNomeMarca.clear()

        // Aciciona os produtos selecionados a lista do AutoComplete
        for(i in 0 until  produtosOrdenados!!.size ){
            listaNomeMarca.add(produtosOrdenados!![i].nome+" "+ produtosOrdenados!![i].marca+" "+ produtosOrdenados[i].descricao)
            Log.i("TESTE LISTAPRODUTOS " +i, produtosOrdenados[i].nome +" "+ produtosOrdenados[i].marca)
            Log.i("TESTE LISTANOMEMARCA " +i, produtosOrdenados[i].toString())

        }


        // Preenche o AutoComplete de produtos na pagina inicial
        val adapter = AutoCompleteAdapter(
            applicationContext, // Context
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

            MainController().cliqueProduto(listaProdutos, listaLojas, produto,
                br.com.economizamais.code.view.latitude,
                br.com.economizamais.code.view.longitude, this )
        }





        // Preenche a lista de Produtos Semelhantes
        val recyclerView = RecyclerView_ProdutosRelacionados
        if (produtosOrdenados != null) {
            recyclerView.adapter = DetalhesLojaListAdapter(produtosOrdenados!!, this)
        }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        recyclerView.addOnItemClickListener(object: DetalhesLojaActivity.OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {


                Log.i("TESTE POSITION", position.toString())
                Log.i("TESTE POSITION", produtosOrdenados[position].toString())


                MainController().cliqueProduto(listaProdutos, listaLojas, produtosOrdenados[position],
                    br.com.economizamais.code.view.latitude,
                    br.com.economizamais.code.view.longitude, this@DetalhesLojaActivity )

            }
        })




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

}
