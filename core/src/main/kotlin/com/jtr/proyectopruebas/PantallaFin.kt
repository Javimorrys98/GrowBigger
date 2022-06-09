package com.jtr.proyectopruebas

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont

class PantallaFin(puntuacion:Float): Pantalla() {

    //Fuente
    val fuente=am.get<BitmapFont>("edo-32.fnt")
    //Sonidos
    val rip= am.get<Sound>("rip.mp3")
    //Texturas
    val fondo= am.get<Texture>("Fondo.png")
    //Recibimos la puntuación conseguida para mostrarla por pantalla.
    val puntos=puntuacion.toInt()

    override fun leerEntrada(delta: Float) {
        //Si clickamos en la pantalla, cerramos el juego.
        if(Gdx.input.justTouched()){
            //Gdx.app.exit()
            val iteradorPeces=PezEnemigo.lista.iterator()
            while(iteradorPeces.hasNext()){
                val actual=iteradorPeces.next()
                    PezEnemigo.reservar(actual)
                    iteradorPeces.remove()
            }
            Juego.instancia.screen.dispose()
            Juego.instancia.screen=PantallaCarga()
        }
    }

    override fun actualizar(delta: Float) {
    }

    override fun dibujar(delta: Float) {
        sb.draw(fondo,0f,0f,0,0,ANCHO, ALTO)
        fuente.draw(sb,"HAS MUERTO", ANCHO/2f-100,ALTO/2f+100)
        fuente.draw(sb,"Tu puntuacion: "+puntos, ANCHO/2f-135,ALTO/2f+30)
    }

    override fun depurar(delta: Float) {
    }

    override fun show() {
        //Reproducimos un sonido al final del juego.
        rip.play()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
    }
}