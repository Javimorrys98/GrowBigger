package com.jtr.proyectopruebas

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.viewport.FitViewport

///////////////////////// CONSTANTES GLOBALES ///////////////////////
//Dimensiones
const val ANCHO=752
const val ALTO=494
//PezJugador
const val VELOCIDAD_BASE=125f
const val VELOCIDAD_AUMENTADA=225f
const val TAMANIO_BASE=0.15f
//PezEnemigo
const val VELOCIDAD_BASE_ENEMIGO=30f
//Bonificaciones
const val DURACION_BONUS=5f

//Cámara
val camara=OrthographicCamera()
//Viewport
val vista=FitViewport(ANCHO.toFloat(), ALTO.toFloat(), camara)
//SpriteBatch
val sb=SpriteBatch()
//ShapeRenderer
val renderizador=ShapeRenderer()
//Asset Manager
val am=AssetManager()

//////////////////////////      EXTENSIONES     ////////////////////

//Para dibujar un rectángulo pasando directamente el propio rectángulo (nuestras collisionmaks son rectángulos)
fun ShapeRenderer.rect(r:Rectangle)=this.rect(r.x,r.y,r.width,r.height)



