package com.jubre

class Comentario {
    static belongsTo = Truco
    String autor
    String texto
    Date fecha

    static constraints = {
        autor(size: 3..50)
        texto(maxSize: 999999)
    }
}
