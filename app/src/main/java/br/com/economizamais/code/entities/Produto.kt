package br.com.economizamais.code.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
data class Produto (
    @NonNull
    @PrimaryKey(autoGenerate = false)
    var id: Long?,
    var nome: String,
    var image: String,
    var descricao: String,
    var detalhe: String,
    var preco: Int,
    var marca: String,
    var loja: String,
    var estado: String,
    var cidade: String,
    var latitude: Int,
    var longitude: Int
)