package com.jtr.proyectopruebas

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

class PezJugador: PezGenerico(textura) {
    companion object {
        val textura = am.get<Texture>("jugador.png")
    }

    val mascara = Rectangle()
    var puntuacionPantalla = 0f
    //Si inmune=true, no te matan los peces enmigos más grandes que el jugador.
    var inmune = false
    //Para calcular la duración de las bonificaciones.
    var timerBonus: Float = 0f
    //Determina si tienes el efecto de una bonificación activo en este momento.
    var bonus = false

    init {
        tamanio = TAMANIO_BASE
        velocidad = VELOCIDAD_BASE
        setPosition(ANCHO / 2f - width / 2, ALTO / 2f - height / 2)
        setOrigin(0f, 0f)
        setScale(tamanio)
    }

    fun leeTeclado() {
        //Lee la entrade de teclado para mover al pez por la pantalla.
        direccion.set(0f, 0f)
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && x > 0) direccion.x--
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && x < ANCHO - tamanio * 100) direccion.x++
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && y < ALTO - tamanio * 100) direccion.y++
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && y > 0) direccion.y--

        //Gira al pez cuando cambia de dirección.
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !isFlipX) flip(true, false)
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && isFlipX) flip(true, false)

        //Si el devMode está activo, el pezJugador crece rápidamente para poder hacer pruebas pulsando A.
        if (Gdx.input.isKeyJustPressed(Input.Keys.A) && PantallaJuego.devMode == true) {
            crecer(0.5f)
        }
    }

    fun actualizar(delta: Float) {
        x += direccion.x * velocidad * delta
        y += direccion.y * velocidad * delta
        mascara.set(x, y, width * tamanio, height * tamanio)

        //Empieza el contador de tiempo del bonus.
        if (bonus) timerBonus+= delta
        //Cuando llega al límite se desactiva.
        if (timerBonus>= DURACION_BONUS) desactivaBonus()

    }

    fun dibujar() {
        draw(sb)
    }


    fun crecer(puntEnemigo: Float) {
        val tamanioEnemigo = puntEnemigo / 4
        if (tamanio < 2) {
            tamanio += tamanioEnemigo
            puntuacionPantalla += tamanioEnemigo

            setScale(tamanio)
        }
        //Cuando llega al límite de tamaño, puede seguir ganando puntos pero no podrá crecer más con el fin de ajustar la dificultad del juego.
        else {
            tamanio = 2f
            puntuacionPantalla += tamanioEnemigo
        }

    }

    fun desactivaBonus(){
        timerBonus=0f
        bonus=false
        inmune=false
        velocidad= VELOCIDAD_BASE
    }

}



