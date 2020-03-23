package com.example.cityguide

class Prestador {

    var id: Int? = null
    var nome: String? = ""
    var latitude: String? = ""
    var longitude: String? = ""
    var horario: String? = ""
    var telefone: String? = ""
    var tipo: String? = ""

    override fun toString(): String {
        return "ID: $id NOME: $nome LATITUDE: $latitude LONGITUDE: $longitude HORÁRIO: $horario TELEFONE: $telefone"
    }


}