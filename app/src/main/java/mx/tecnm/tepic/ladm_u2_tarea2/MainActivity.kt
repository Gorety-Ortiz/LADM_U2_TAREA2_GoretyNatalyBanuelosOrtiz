package mx.tecnm.tepic.ladm_u2_tarea2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var hiloControl=Hilo(this) //mandar a llamar clase hilo mediante una variable en la clase mainactivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageButton.setOnClickListener {
            hiloControl?.start()//iniciar el hilo
        }

        imageButton2.setOnClickListener {
            hiloControl?.pausar()
        }

        imageButton3.setOnClickListener {
            hiloControl?.despausar()
        }

        imageButton4.setOnClickListener {
            hiloControl!!.detener()

        }

    }
}


class Hilo(p:MainActivity) : Thread(){
    val texto = arrayOf("Hola","cuando",
        "quieras","puedes","detenerla","ok","1","2","3","TAMARINDO")
    var iniciar = false
    var puntero = p
    var pausa = false
    var contador = 0

    override fun run() {
        super.run()
        //cambiamos iniciar a verdadera
        iniciar = true
        while (iniciar==true){
            sleep(600)
            if(pausa==false) {
                puntero.runOnUiThread {
                    puntero.textView.setText(texto[contador]).toString()
                    contador++
                    if (contador==10){contador=0}
                }
            }
        }
    }


    fun pausar(){
        pausa= true //ya no entrara al if(pausa==false) porque no cumple la condicion
    }

    fun despausar(){
        pausa = false //volvera a entrar el if(pausa==false) porque cumple la condicion
    }

    fun detener() {
        iniciar = false //ya no entra al while(iniciar==true) porque no cumple la condicion y este procede a detenerse
    }
}
