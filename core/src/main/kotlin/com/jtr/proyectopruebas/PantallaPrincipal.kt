package com.jtr.proyectopruebas

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2

class PantallaPrincipal: Pantalla() {
    //Fuente
    val fuente=am.get<BitmapFont>("edo-64.fnt")
    //Botones
    val start=am.get<Texture>("start.png")
    val quit= am.get<Texture>("quit.png")
    //Musica
    val musica= am.get<Music>("mainMenu.ogg")
    //Fondo
    val fondo= am.get<Texture>("Fondo.png")

    //Click
    private val puntoClick= Vector2(0f,0f)

    override fun leerEntrada(delta: Float) {
        //Lee donde clickamos.
        if(Gdx.input.justTouched()){
            puntoClick.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
            vista.unproject(puntoClick)
        }
    }

    override fun actualizar(delta: Float) {
        //Si pulsamos start empieza la partida.
        if((puntoClick.x>=270f && puntoClick.x<=270f+start.width) && (puntoClick.y>=220f && puntoClick.y<=220f+start.height)){
            Juego.instancia.screen.dispose()
            Juego.instancia.screen=PantallaJuego()
        }
        //Si pulsamos quit salimos del juego.
        else if((puntoClick.x>=270f && puntoClick.x<=270f+quit.width) && (puntoClick.y>=100f && puntoClick.y<=100f+quit.height)){
            Gdx.app.exit()
        }
    }

    override fun dibujar(delta: Float) {
        sb.draw(fondo,0f,0f,0,0,ANCHO, ALTO)
        fuente.draw(sb,"GROW BIGGER!",180f,400f)
        sb.draw(start,270f,220f)
        sb.draw(quit,270f,100f)
    }

    override fun depurar(delta: Float) {
    }

    override fun show() {
        //Empieza la música del menu principal.
        musica.play()
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
        //Para la música y descargamos los assets que no volveremos a usar.
        musica.stop()
        am.unload("mainMenu.ogg")
        am.unload("edo-64.fnt")
        am.unload("start.png")
        am.unload("quit.png")
    }
}