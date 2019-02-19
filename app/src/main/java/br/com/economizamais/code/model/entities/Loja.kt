package br.com.economizamais.code.model.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.http.GET
import java.sql.Date
import java.util.*

@Parcelize
@Entity
data class Loja (
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,
    @SerializedName("razaoSocial")
    var razaoSocial: String,
    @SerializedName("eslogan")
    var eslogan: String,
    @SerializedName("telefone")
    var telefone: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("endereco")
    var endereco: String,
    @SerializedName("cnpj")
    var cnpj: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("bairro")
    var bairro: String,
    @SerializedName("cidade")
    var cidade: String,
    @SerializedName("uf")
    var uf: String,
    @SerializedName("cep")
    var cep: String,
    @SerializedName("latitude")
    var latitude: Double,
    @SerializedName("longitude")
    var longitude: Double,

    var distancia: Double
) : Parcelable

interface EndpointLoja {
    @GET("lojas")
    fun getPosts() : Call<List<Loja>>
}

