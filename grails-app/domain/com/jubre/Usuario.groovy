package com.jubre

class Usuario {

    static hasMany = [trucos: Truco]
    String nombre
    String email
    Date fechaAlta
}
