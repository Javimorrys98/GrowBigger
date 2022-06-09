package com.jtr.proyectopruebas

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle


class Bonificacion( //Recibe un tipo aleatorio sacado del Enum.
                    val tipoBonificacion: TipoBonificacion = TipoBonificacion.values()[(Math.random() * TipoBonificacion.values().size).toInt()]
                    ) :Sprite(tipoBonificacion.texture) {

    val mascara= Rectangle()

    init
    {
        x=(Math.random()* ANCHO).toFloat()
        y=(Math.random()* ALTO).toFloat()
        mascara.set(x,y,width,height)
    }

    fun draw(batch: SpriteBatch) {
        batch.draw(this.texture, x, y)
    }
}