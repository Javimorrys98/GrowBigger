package com.jtr.proyectopruebas

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2

class PantallaFin(puntuacion:Float): Pantalla() {

    //Fuente
    val fuente=am.get<BitmapFont>("edo-32.fnt")
    //Sonidos
    val rip= am.get<Sound>("rip.mp3")
    val boton= am.get<Sound>("boton.mp3")
    //Texturas
    val fondo= am.get<Texture>("Fondo.png")
    val replay= am.get<Texture>("replay.png")
    //Recibimos la puntuación conseguida para mostrarla por pantalla.
    val puntos=puntuacion.toInt()
    //Punto click
    private val puntoClick= Vector2(0f,0f)

    override fun leerEntrada(delta: Float) {
        //Si pulsamos replay empieza la partida de nuevo.
        if(Gdx.input.justTouched()){
            puntoClick.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
            vista.unproject(puntoClick)
        }
    }

    override fun actualizar(delta: Float) {
        if((puntoClick.x>=ANCHO/2f-replay.width/2 && puntoClick.x<=ANCHO/2f+replay.width/2) && (puntoClick.y>=130f && puntoClick.y<=130f+replay.height)){
            boton.play()
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

    override fun dibujar(delta: Float) {
        sb.draw(fondo,0f,0f,0,0,ANCHO, ALTO)
        fuente.draw(sb,"HAS MUERTO", ANCHO/2f-75,ALTO/2f+100)
        fuente.draw(sb,"Tu puntuacion: "+puntos, ANCHO/2f-125,ALTO/2f+30)
        sb.draw(replay, ANCHO/2f-replay.width/2, 130f)
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
        rip.stop()
    }
}