package com.jtr.proyectopruebas

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2

abstract class PezGenerico(textura: Texture) :Sprite(textura){
    //Inicializamos las variables aunque luego en cada tipo de pez les asignemos nuevos valores.
    var tamanio=0f
    var velocidad=0f
    val direccion= Vector2(0f,0f)
}