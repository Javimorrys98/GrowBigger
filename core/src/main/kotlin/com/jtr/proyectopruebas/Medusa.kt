import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.jtr.proyectopruebas.*

class Medusa: PezEnemigo(textura) {
    companion object{
        val textura= am.get<Texture>("medusa.png")
    }

    //Animación.
    private val animacion=construyeAnimacion()
    private val ancho=114
    private val alto=136
    private var tiempo=0f


    private fun construyeAnimacion(): Animation<TextureRegion> {
        val duracion=.5f
        val columnas=3
        val filas=1
        val temporal= TextureRegion.split(textura,114, 136)
        val fotogramas= Array<TextureRegion>()//     <-- CUIDADO!! Array de Gdx, no de Kotlin (ver imports)
        var posicion=0
        for(i in 0 until filas){
            for(j in 0 until columnas)
                fotogramas.add(temporal[i][j])
            posicion++
        }
        return Animation<TextureRegion>(duracion,fotogramas)
    }

    override fun actualizar(delta:Float):Boolean{
        //Para la animación.
        tiempo+=delta
        setRegion(animacion.getKeyFrame(tiempo,true))
        setBounds(x,y,ancho.toFloat(),alto.toFloat())
        //Para movimiento diaognal y rebote en las "Paredes".
        x+=direccion.x*velocidad*delta
        if (y<=0f) direccion.y++
        if (y>=ALTO.toFloat()-height) direccion.y--
        y+=direccion.y*velocidad*delta
        mascara.set(x,y,width*tamanio,height*tamanio)
        return true
    }

    override fun recolocar() {
        setOrigin(0f,0f)
        tamanio=(Math.random()*0.5).toFloat()
        setScale(tamanio)
        if (aleatorio<0.5){
            setPosition(ANCHO+width+100f,(Math.random()*(ALTO -height)).toFloat())
            direccion.x--
            if(aleatorio<0.5){
                direccion.y++
            }else{
                direccion.y--
            }
        }
        else {
            setPosition(0-width-100f,(Math.random()*(ALTO -height)).toFloat())
            direccion.x++
            if(aleatorio<0.5){
                direccion.y--
            }else{
                direccion.y++
            }
        }
        velocidad= VELOCIDAD_BASE_ENEMIGO
    }
}