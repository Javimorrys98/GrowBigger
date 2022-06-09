package com.jtr.proyectopruebas

import com.badlogic.gdx.graphics.Texture

class Tiburon: PezEnemigo(textura) {
    companion object{
        val textura= am.get<Texture>("tiburon.png")
    }

    override fun actualizar(delta:Float):Boolean{
        x+=direccion.x*velocidad*delta
        y+=direccion.y*velocidad*delta
        mascara.set(x,y,width*tamanio,height*tamanio)
        return true
    }

    override fun recolocar() {
        var aleatorio2=Math.random()
        setOrigin(0f,0f)
        tamanio=1f
        setScale(tamanio)
        if (aleatorio<0.5 && aleatorio2<0.5) {
            setPosition(ANCHO + width + 100f, ALTO + height + 100f)
            direccion.x--
            direccion.y--
        }
        else if(aleatorio<0.5 && aleatorio2>0.5){
            setPosition(ANCHO + width + 100f, 0-height-100f)
            direccion.x--
            direccion.y++
        }
        else if(aleatorio>0.5 && aleatorio2<0.5){
            setPosition(0-width-100f, ALTO+height+100f)
            direccion.x++
            direccion.y--
        }
        else{
            setPosition(0-width-100f, 0-height-100f)
            direccion.x++
            direccion.y++
        }
        velocidad=(VELOCIDAD_BASE_ENEMIGO+Math.random()*80).toFloat()
    }
}