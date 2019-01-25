package br.com.economizamais.code.network_utils

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET

data class ProdutoServer(
    @SerializedName("id")
    var id : Int,
    @SerializedName("nome")
    var nome : String,
    @SerializedName("image")
    var image : String,
    @SerializedName("descricao")
    var descricao : String,
    @SerializedName("detalhe")
    var detalhe : String,
    @SerializedName("preco")
    var preco : Float,
    @SerializedName("marca")
    var marca : String,
    @SerializedName("loja")
    var loja : String,
    @SerializedName("estado")
    var estado : String,
    @SerializedName("cidade")
    var cidade : String,
    @SerializedName("latitude")
    var latitude : String,
    @SerializedName("longitude")
    var longitude : String

)

interface Endpoint {

    @GET("produtos")
    fun getPosts() : Call<List<ProdutoServer>>
}
