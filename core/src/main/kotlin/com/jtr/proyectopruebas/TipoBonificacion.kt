package com.jtr.proyectopruebas

import com.badlogic.gdx.graphics.Texture

enum class TipoBonificacion(val texture: Texture) {
    //Definimos los distintos tipos de bonificación en el enum y a cada uno le asignamos su textura.
    ESCUDO(Texture("escudo.png")),
    VELOCIDAD(Texture("velocidad.png"));
}