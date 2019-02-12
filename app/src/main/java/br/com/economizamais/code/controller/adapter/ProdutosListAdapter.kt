package br.com.economizamais.code.controller.adapter
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.economizamais.R
import br.com.economizamais.code.model.entities.Produto
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.produto_item.view.*
import java.text.NumberFormat
import java.util.*

class NoteListAdapter(private val produtos: List<Produto>,
                      private val context: Context
) : Adapter<NoteListAdapter.ViewHolder>() {




    //var localeBR: Locale = Locale("pt","BR")
    //var dinheiro: NumberFormat = NumberFormat.getCurrencyInstance(localeBR)

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val produto = produtos[position]
        p0?.let {
            it.nome.text = produto.nome
            it.marca.text = produto.marca
            it.loja.text = produto.loja

            //var preco: Double = produto.preco

           // it.preco.text = (dinheiro.format(preco))
            it.preco.text = AdapterUtils().formatarReal(produto.preco)

           // it.preco.text = ("R$ "+ produto.preco.toString())
            Glide.with(context).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+produto.image).into(it.imagem)


        }
         }


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.produto_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.listprodtext_nome
        val marca = itemView.listprodtext_marca
        val loja = itemView.listprodtext_loja
        val preco = itemView.listprodtext_preco
        val imagem = itemView.listprodimagem_img



    }



}

