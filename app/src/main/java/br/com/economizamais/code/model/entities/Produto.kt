package br.com.economizamais.code.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import java.sql.Date
import java.util.*

@Entity
data class Produto (
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @SerializedName("nome")
    var nome: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("descricao")
    var descricao: String,
    @SerializedName("detalhe")
    var detalhe: Boolean,
    @SerializedName("preco")
    var preco: Double,
    @SerializedName("marca")
    var marca: String,
    @SerializedName("validade")
    var validade: String,
    @SerializedName("idLoja")
    var idLoja: Int,
    @SerializedName("loja")
    var loja: String,
    @SerializedName("estado")
    var estado: String,
    @SerializedName("cidade")
    var cidade: String,
    @SerializedName("latitude")
    var latitude: Double,
    @SerializedName("longitude")
    var longitude: Double
)

interface EndpointProduto {
    @GET("precos/app")
    fun getPosts() : Call<List<Produto>>
}