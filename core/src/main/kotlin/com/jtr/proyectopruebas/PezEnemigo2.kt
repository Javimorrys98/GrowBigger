package com.jtr.proyectopruebas

import com.badlogic.gdx.graphics.Texture

class PezEnemigo2: PezEnemigo(textura) {
    companion object{
        val textura= am.get<Texture>("pezEnemigo2.png")
    }

    override fun actualizar(delta: Float): Boolean {
        x+=direccion.x*velocidad*delta
        mascara.set(x,y,width*tamanio,height*tamanio)
        return true
    }
}