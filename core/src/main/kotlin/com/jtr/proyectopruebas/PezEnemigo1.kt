package com.jtr.proyectopruebas

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class PezEnemigo1: PezEnemigo(textura) {
    companion object{
        val textura= am.get<Texture>("pezEnemigo1.png")
    }

    //Animación
    private val animacion=construyeAnimacion()
    private val ancho=114
    private val alto=105
    private var tiempo=0f


    private fun construyeAnimacion(): Animation<TextureRegion> {
        val duracion=.1f
        val columnas=3
        val filas=1
        val temporal= TextureRegion.split(textura,114, 105)
        val fotogramas= Array<TextureRegion>()//     <-- CUIDADO!! Array de Gdx, no de Kotlin (ver imports)
        var posicion=0
        for(i in 0 until filas){
            for(j in 0 until columnas)
                fotogramas.add(temporal[i][j])
            posicion++
        }
        return Animation<TextureRegion>(duracion,fotogramas)
    }

    override fun actualizar(delta: Float): Boolean {
        //Para la animación.
        tiempo+=delta
        setRegion(animacion.getKeyFrame(tiempo,true))
        setBounds(x,y,ancho.toFloat(),alto.toFloat())
        //Dirección.
        if (aleatorio<0.5) flip(true,false)
        x+=direccion.x*velocidad*delta
        mascara.set(x,y,width*tamanio,height*tamanio)
        return true
    }

    override fun recolocar() {
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