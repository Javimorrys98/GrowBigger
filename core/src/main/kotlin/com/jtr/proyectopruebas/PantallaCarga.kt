package com.jtr.proyectopruebas

import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont

class PantallaCarga :Pantalla() {

    private lateinit var fondo: Texture
    private lateinit var barra: Texture
    private lateinit var piloto: Texture
    private lateinit var fuente: BitmapFont
    private var xBarra=ANCHO/2f-110
    private var yBarra=ALTO/2f

    override fun leerEntrada(delta: Float) {
    }

    override fun actualizar(delta: Float) {
        //Una vez se han cargado los assets en el AssetManager, pasamos al menú principal.
        if(am.update()){
            dispose()
            Juego.instancia.screen=PantallaPrincipal()
        }
    }

    override fun dibujar(delta: Float) {
        //Para la barra de carga.
        sb.draw(piloto,xBarra,yBarra,barra.width*am.progress,barra.height.toFloat())
        sb.draw(barra,xBarra,yBarra)
        //Para el mensaje cargando en pantalla.
        fuente.draw(sb,"CARGANDO...",xBarra+30,yBarra+80)
    }

    override fun depurar(delta: Float) {
    }

    override fun show() {
        //Cargamos los assets que vamos a usar en esta pantalla.
        am.load("BarraCarga.png",Texture::class.java)
        am.load("PilotoCarga.png",Texture::class.java)
        am.load("edo-32.fnt", BitmapFont::class.java)
        //Espera hasta que haya cargado de manera efectiva estos tres assets -> carga síncrona
        am.finishLoading()

        //Asigno esos assets que ya están cargados y los ubico
        barra=am.get("BarraCarga.png")
        piloto=am.get("PilotoCarga.png")
        fuente=am.get("edo-32.fnt")

        //El resto de assets del juego se cargarán en sucesivas pasadas del "render" -> carga asíncrona
        am.load("start.png",Texture::class.java)
        am.load("quit.png",Texture::class.java)
        am.load("Fondo.png",Texture::class.java)
        am.load("jugador.png",Texture::class.java)
        am.load("pezEnemigo1.png",Texture::class.java)
        am.load("pezEnemigo2.png",Texture::class.java)
        am.load("medusa.png",Texture::class.java)
        am.load("tiburon.png",Texture::class.java)
        am.load("mainMenu.ogg", Music::class.java)
        am.load("partida.ogg", Music::class.java)
        am.load("rip.mp3", Sound::class.java)
        am.load("gnom.mp3", Sound::class.java)
        am.load("edo-64.fnt", BitmapFont::class.java)
        am.load("edo-18.fnt", BitmapFont::class.java)
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
        am.unload("PilotoCarga.png")
        am.unload("BarraCarga.png")
    }
}