package com.jtr.proyectopruebas

import Medusa
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

abstract class PezEnemigo(textura:Texture) : PezGenerico(textura){
    var aleatorio=Math.random().toFloat()
    val mascara= Rectangle()

    companion object{
        //Para guardar los peces que vamos creaando.
        val lista= mutableListOf<PezEnemigo>()
        val reserva= mutableListOf<PezEnemigo>()
        //Crear penes nuevos.
        fun nueva(){
            val nuevo:PezEnemigo
            if(reserva.size==0){//Si no hay en la reserva -> instancia nueva
                val opcion=(Math.random()*4).toInt()
                when(opcion){
                    0 -> nuevo=PezEnemigo1()
                    1 -> nuevo=PezEnemigo2()
                    2 -> nuevo=Tiburon()
                    else -> nuevo=Medusa()
                }
            }else{//Si hay en la reserva-> lo SACO de ahí
                nuevo=reserva.iterator().next()
                reserva.remove(nuevo)
            }
            nuevo.recolocar()
            lista.add(nuevo)
        }

        fun reservar(pezEnemigo:PezEnemigo){
            reserva.add(pezEnemigo)
        }
    }

    init{
        tamanio=(Math.random()*2.5).toFloat()
        recolocar()
        if (aleatorio<0.5) flip(true,false)
    }

    abstract fun actualizar(delta:Float):Boolean


    fun dibujar(){
        draw(sb)
    }

    open fun recolocar(){
        setOrigin(0f,0f)
        setScale(tamanio)
        if (aleatorio<0.5){
            setPosition(ANCHO+width+100f,(Math.random()*(ALTO-height)).toFloat())
            direccion.x--
        }
        else {
            setPosition(0-width-100f,(Math.random()*(ALTO-height)).toFloat())
            direccion.x++
        }
        velocidad=(VELOCIDAD_BASE_ENEMIGO+Math.random()*80).toFloat()
    }
}