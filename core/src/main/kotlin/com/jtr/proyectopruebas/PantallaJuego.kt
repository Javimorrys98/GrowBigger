package com.jtr.proyectopruebas

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class PantallaJuego: Pantalla() {
    //Fondo.
    val fondo= am.get<Texture>("Fondo.png")

    //PezJugador.
    val pezJugador=PezJugador()

    //PezEnemigo.
    var tiempoDesdeUltimoPez=0f
    var intervaloPeces=1.5f

    //Fuente.
    val fuente= am.get<BitmapFont>("edo-18.fnt")

    //Música.
    val musica= am.get<Music>("partida.ogg")

    //Efectos de sonido.
    val gnom= am.get<Sound>("gnom.mp3")

    //Para inicializar la bonificación.
    lateinit var bonificacion:Bonificacion

    //Para determinar cuando hay o no bonificaciones en pantalla.
    var bonusFlag: Boolean = false

    companion object {
        //Dev Mode
        var devMode = false
    }

    override fun leerEntrada(delta: Float) {
        //Activar y desactivar el devMode con la tecla Q.
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q))
            when (devMode) {
                true -> devMode=false
                false -> devMode=true
            }
        //Leemos la entrada de teclado para mover el pez.
        pezJugador.leeTeclado()
    }

    override fun actualizar(delta: Float) {
        //Determinamos cuando crear un nuevo pez.
        tiempoDesdeUltimoPez+=delta
        if(tiempoDesdeUltimoPez>=intervaloPeces){
            PezEnemigo.nueva()
            tiempoDesdeUltimoPez=0f
        }

        //Recalculamos la posición del pez del jugador.
        pezJugador.actualizar(delta)

        //Eliminamos peces sobrantes para crear nuevos.
        val iteradorPeces=PezEnemigo.lista.iterator()
        while(iteradorPeces.hasNext()){
            val actual=iteradorPeces.next()
            if(!actual.actualizar(delta)){
                PezEnemigo.reservar(actual)
                iteradorPeces.remove()
            }
        }

        //Tratamos las colisiones del jugador con otros peces y con las bonificaciones.
           for(pezEnemigo in PezEnemigo.lista){
            if(pezJugador.mascara.overlaps(pezEnemigo.mascara)){
                if(pezEnemigo.aleatorio>0.5f && pezEnemigo.tamanio<pezJugador.tamanio){
                    pezEnemigo.setPosition(ANCHO+300f,100f)
                    gnom.play()
                    pezJugador.crecer(pezEnemigo.tamanio)
                }
                else if (pezEnemigo.aleatorio<=0.5f && pezEnemigo.tamanio<pezJugador.tamanio){
                    pezEnemigo.setPosition(-300f,100f)
                    gnom.play()
                    pezJugador.crecer(pezEnemigo.tamanio)
                }
                else{
                    if (!pezJugador.inmune){
                        Juego.instancia.screen.dispose()
                        Juego.instancia.screen=PantallaFin(pezJugador.puntuacionPantalla*1000)
                    }
                }
            }

            //Creamos bonificaciones.
            if (!bonusFlag) {
                val aleatorio = (Math.random() * 100).toFloat()
                if (aleatorio < 0.1f) {
                    bonusFlag = true
                    bonificacion = Bonificacion()
                }
            }

            if(bonusFlag && pezJugador.mascara.overlaps(bonificacion.mascara)){
                bonusFlag=false
                bonificacion.setPosition(1000f,1000f)
                if (bonificacion.tipoBonificacion==TipoBonificacion.VELOCIDAD){
                    pezJugador.velocidad= VELOCIDAD_AUMENTADA
                    pezJugador.bonus=true
                }else if(bonificacion.tipoBonificacion==TipoBonificacion.ESCUDO){
                    pezJugador.inmune=true
                    pezJugador.bonus=true
                }
            }
        }
    }

    override fun dibujar(delta: Float) {
        sb.draw(fondo,0f,0f,0,0,ANCHO, ALTO)
        for (pezEnemigo in PezEnemigo.lista) pezEnemigo.dibujar()
        pezJugador.dibujar()
        fuente.draw(sb,"SCORE: "+((pezJugador.puntuacionPantalla*1000).toInt()).toString(),25f, ALTO-42f)

        if (bonusFlag) bonificacion.draw(sb)
    }

    override fun depurar(delta: Float) {
        //Si el devMode está activado, mostramos las colisiones.
        renderizador.begin(ShapeRenderer.ShapeType.Line)
        renderizador.rect(pezJugador.mascara)
        for (pezEnemigo in PezEnemigo.lista) renderizador.rect(pezEnemigo.mascara)
        renderizador.end()
    }

    override fun show() {
        //Empieza la música de la partida.
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
        am.unload("partida.ogg")
        am.unload("edo-18.fnt")
        am.unload("gnom.mp3")
    }
}