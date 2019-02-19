package br.com.economizamais.code.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import br.com.economizamais.R
import br.com.economizamais.code.controller.detalhes_produto.DetalhesProdutoController
import br.com.economizamais.code.controller.detalhes_produto.DetalhesProdutoListAdapter
import br.com.economizamais.code.controller.main.FormataDados
import br.com.economizamais.code.controller.main.adapter.NoteListAdapter
import br.com.economizamais.code.controller.main.autocomplete.AutoCompleteAdapter
import br.com.economizamais.code.model.entities.Loja
import br.com.economizamais.code.model.entities.Produto
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detalhes_produto.*
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class DetalhesProdutoActivity : AppCompatActivity() {

    var listaProdutosTemp: MutableList<Produto> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_produto)

        getSupportActionBar()!!.hide()


        // Recebe os dados do Produto clicado
        var intent: Intent = getIntent()
        var produto: Produto = intent.getParcelableExtra("produto")
        var latitude: Double = intent.getSerializableExtra("latitude") as Double
        var longitude: Double = intent.getSerializableExtra("longitude") as Double

        // Recebe a lista de Produtos
        var extrasProduto: Bundle = getIntent().extras
        var produtos: ArrayList<Produto> = extrasProduto.getParcelableArrayList("produtos")

        // Recebe a lista de de Lojas
        var extrasLojas: Bundle = getIntent().extras
        var lojas: ArrayList<Loja> = extrasLojas.getParcelableArrayList("lojas")


        textView_Nome.text = produto.nome
        textView_Marca.text = produto.marca
        textView_Detalhes.text = produto.descricao
        textView_Preco.text = FormataDados().formatarReal(produto.preco)
        Glide.with(this).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+produto.image).into(imageView_Produto)



        var loja = DetalhesProdutoController().dadosLoja(produto.idLoja, lojas)

        textView_Endereco.text = loja.endereco+" "+loja.bairro
        textView_Cidade.text = loja.cidade
        textView_Estado.text = "- "+ loja.uf

        textView_Distancia.text = FormataDados().formataKm(loja.distancia)
        Glide.with(this).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+loja.image).into(imageView_LogoLoja)




        produtos = DetalhesProdutoController().filtrarProdutosLojas(produtos, listaProdutosTemp, produto.idLoja, produto.id, produto.nome) as ArrayList<Produto>


        // Preenche a lista de Produtos na pagina inicial
        val recyclerView = RecyclerView_ProdutosRelacionados
        if (produtos != null) {
            recyclerView.adapter = DetalhesProdutoListAdapter(produtos!!, this)
        }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager


        imageView_Rotas.setOnClickListener {


            // Inicia a Main e envia as Coordenadas
            var intent = Intent(this, Mapa::class.java)

            intent.putExtra("loja", loja)
            intent.putExtra("latitude", latitude)
            intent.putExtra("longitude", longitude)

            this.startActivity(intent)
        }


    }
}
