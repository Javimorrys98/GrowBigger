package com.jtr.proyectopruebas

import com.badlogic.gdx.Game

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Juego : Game() {
    companion object{
        lateinit var instancia:Juego
    }


    override fun create() {
        setScreen(PantallaCarga())
        instancia=this
    }

    override fun resize(width: Int, height: Int) {
        vista.update(width, height,true)
    }
}