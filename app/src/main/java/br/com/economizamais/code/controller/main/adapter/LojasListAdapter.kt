package br.com.economizamais.code.controller.main.adapter
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.economizamais.R
import br.com.economizamais.code.model.entities.Loja
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.loja_item.view.*

class LojasListAdapter(private val lojas: List<Loja>,
                       private val context: Context
) : Adapter<LojasListAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val loja = lojas[position]
        p0?.let {
            it.razaoSocial.text = loja.razaoSocial
            it.eslogan.text = loja.eslogan
            it.uf.text = loja.uf
            Glide.with(context).load("http://res.cloudinary.com/hprhniuxo/image/upload/t_media_lib_thumb/"+loja.image).into(it.imagem)
        }
         }


    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.loja_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lojas.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val razaoSocial = itemView.listlojatext_nome
        val eslogan = itemView.listlojatext_eslogan
        val uf = itemView.listlojatext_uf
        val imagem = itemView.listlojadimagem_img

    }

}

