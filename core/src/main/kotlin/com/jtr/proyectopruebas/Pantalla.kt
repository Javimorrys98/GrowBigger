package com.jtr.proyectopruebas

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20


/** First screen of the application. Displayed after the application is created.  */
abstract class Pantalla : Screen {

    override fun render(delta: Float) {
        leerEntrada(delta)
        actualizar(delta)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin()
        dibujar(delta)
        sb.end()
        //Si el devMode está activado se muestran por pantalla las cajas de colisión.
        if (PantallaJuego.devMode) depurar(delta)
    }

    abstract fun leerEntrada(delta:Float)
    abstract fun actualizar(delta:Float)
    abstract fun dibujar(delta:Float)
    abstract fun depurar(delta:Float)

}