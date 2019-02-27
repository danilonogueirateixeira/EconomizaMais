package br.com.economizamais.code.controller.detalhes_loja
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.economizamais.R
import br.com.economizamais.code.controller.main.FormataDados
import br.com.economizamais.code.model.entities.Produto
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detalhes_produto_item.view.*

class DetalhesLojaListAdapter(private val produtos: List<Produto>,
                              private val context: Context
) : Adapter<DetalhesLojaListAdapter.ViewHolder>() {


    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val produto = produtos[position]
        p0?.let {
            it.nome.text = produto.nome
            it.marca.text = produto.marca
            it.descricao.text = produto.descricao
            it.preco.text = FormataDados().formatarReal(produto.preco)
            Glide.with(context).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+produto.image).into(it.imagem)


            it.adicionar.setOnClickListener {
                Log.i("TESTE CLIQUE CARRO", produtos[position].toString())
            }
        }
         }


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.detalhes_produto_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.textView_Nome
        val marca = itemView.textView_Marca
        val descricao = itemView.textView_Descricao
        val preco = itemView.textView_Preco
        val imagem = itemView.imageView_Produto

        val adicionar = itemView.imageView_Adicionar



    }



}

