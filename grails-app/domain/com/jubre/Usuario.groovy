package com.jubre

class Usuario {

    static hasMany = [trucos: Truco]
    String nombre
    String email
    Date fechaAlta

    static constraints = {
        nombre(size: 3..50)
        email(email: true)
    }
}
