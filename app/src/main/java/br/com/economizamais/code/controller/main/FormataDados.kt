package br.com.economizamais.code.controller.main

import java.text.NumberFormat
import java.util.*

class FormataDados{

    // Formata um Double para formato da moeda Brasileira, o Real
    fun formatarReal (double: Double):String{
        var localeBR = Locale("pt","BR")
        var real: NumberFormat = NumberFormat.getCurrencyInstance(localeBR)

        var preco: Double = double

        return real.format(preco)
    }

    // Formata dados para exibir apenas 2 digitos
    fun formataKm (double: Double): String{
        val solution = String.format("%.1f", double)
        return solution + " km"
    }

}