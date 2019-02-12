package br.com.economizamais.code.controller.adapter

import java.text.NumberFormat
import java.util.*

class AdapterUtils{



    fun formatarReal (double: Double):String{
        var localeBR: Locale = Locale("pt","BR")
        var real: NumberFormat = NumberFormat.getCurrencyInstance(localeBR)

        var preco: Double = double

        return real.format(preco)
    }
}