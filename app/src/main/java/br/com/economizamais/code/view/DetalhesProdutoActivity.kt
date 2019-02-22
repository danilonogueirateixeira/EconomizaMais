package br.com.economizamais.code.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
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
import kotlinx.android.synthetic.main.detalhes_produto_item.imageView_Adicionar
import kotlinx.android.synthetic.main.detalhes_produto_item.view.*
import java.io.Serializable

class DetalhesProdutoActivity : AppCompatActivity() {

    var listaProdutosTemp: MutableList<Produto> = arrayListOf()

    private val CALL_PHONE_RESULT_CODE = 111

    private var PHONE_NUMBER = ""


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_produto)

        // Esconde a ActionBar
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

        // Recupera a loja em questão
        var loja = DetalhesProdutoController().dadosLoja(produto.idLoja, lojas)

        // Insere os dados da Loja na tela
        textView_Endereco.text = loja.endereco+" "+loja.bairro
        textView_Cidade.text = loja.cidade
        textView_Estado.text = "- "+ loja.uf
        textView_Distancia.text = FormataDados().formataKm(loja.distancia)
        Glide.with(this).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+loja.image).into(imageView_LogoLoja)

        // Mostra insere os dados do produto na tela
        textView_Nome.text = produto.nome
        textView_Marca.text = produto.marca
        textView_Detalhes.text = produto.descricao
        textView_Preco.text = FormataDados().formatarReal(produto.preco)
        Glide.with(this).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+produto.image).into(imageView_Produto)

        // Recupera o telefone da loja para ligação
        PHONE_NUMBER = loja.telefone


        // Filtra lista para exibição do recyclerView de produtos semelhantes
        produtos = DetalhesProdutoController().filtrarProdutosLojas(produtos, listaProdutosTemp, produto.idLoja, produto.id, produto.nome) as ArrayList<Produto>

        // Ordena os produtos por preço
        val produtosOrdenados = produtos.sortedWith(compareBy(Produto::preco, Produto::preco))


        // Preenche a lista de Produtos Semelhantes
        val recyclerView = RecyclerView_ProdutosRelacionados
        if (produtosOrdenados != null) {
            recyclerView.adapter = DetalhesProdutoListAdapter(produtosOrdenados!!, this)
        }
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

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

        // Clique em ícone de Adicionar a lista
        imageView_AdicionarLista.setOnClickListener {

            Toast.makeText(this, produto.nome, Toast.LENGTH_LONG).show()
        }

        // Clique em botão de voltar
        imageView_Voltar.setOnClickListener {

            this.finish()
        }


    }

    // Verifica a permissão para ligação
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            CALL_PHONE_RESULT_CODE -> {
                if (grantResults.isNotEmpty()
                    && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    DetalhesProdutoController().makeCall(PHONE_NUMBER, this)
                } else if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                    createNeverAskAgainDialog()
                } else {
                }
            }
        }
    }

    // Caixa de diálogo com opção de não mostrar novamente
    fun createNeverAskAgainDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.never_ask_again_dialog_ligacao)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.go_to_settings) { _, _ -> DetalhesProdutoController().goToAppDetailsSettings(this@DetalhesProdutoActivity) }
            setNegativeButton(R.string.nao ) { d, _ -> d.dismiss() }
        }.show()
    }

    // Caixa de diálogo
    fun createMoreInfoDialog() {
        AlertDialog.Builder(this).apply {
            setMessage(R.string.more_info_dialog_ligacao)
            setTitle(R.string.permission_dialog_title)
            setPositiveButton(R.string.sim) { _, _ -> DetalhesProdutoController().requestFazerLicagao(this@DetalhesProdutoActivity) }
            setNegativeButton(R.string.nao) { d, _ -> d.dismiss() }
        }.show()
    }
}
