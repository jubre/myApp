package com.jubre

class Truco {
    static hasMany = [comentarios: Comentario]
    static belongsTo = Usuario

    List comentarios
    Date fecha
    String titulo
    String texto
    boolean denunciado

    static constraints = {
        titulo(size: 10..1000)
        texto(maxSize: 999999)
    }
}
