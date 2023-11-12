package com.example.piedra_papel_tijeras

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //@SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Piedrapapeltijeras)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var contadorMaquina : TextView = findViewById(R.id.contadorMaquina)
        var contadorUsuario : TextView = findViewById(R.id.contadorYo)

        val piedra : ImageButton = findViewById(R.id.piedra)
        val papel : ImageButton = findViewById(R.id.papel)
        val tijeras : ImageButton = findViewById(R.id.tijeras)

        val movimientoMaquina : ImageView = findViewById(R.id.movimientoMaquina)
        val textoMovimientoMaquina : TextView = findViewById(R.id.textoMovimientoMaquina)
        val resultado : TextView = findViewById(R.id.resultado)
        val reinicio : Button = findViewById(R.id.reinicio)

        var cpuCounter = 0
        var userCounter = 0

        val onClickListener = View.OnClickListener{
            val eleccionMaquina = (1..3).random()
            textoMovimientoMaquina.text = "La máquina ha elegido..."
            movimientoMaquina.postDelayed({
                when(eleccionMaquina){
                    1 -> movimientoMaquina.setImageResource(R.drawable.piedra_slide)
                    2 -> movimientoMaquina.setImageResource(R.drawable.papel_slide)
                    3 -> movimientoMaquina.setImageResource(R.drawable.tijera_slide)
            }},1000)
            var opcionUsuario = it.id
            resultado.postDelayed({
                when{
                    (opcionUsuario == R.id.piedra && eleccionMaquina == 1) ||
                            (opcionUsuario == R.id.papel && eleccionMaquina == 2) ||
                            (opcionUsuario == R.id.tijeras && eleccionMaquina == 3)-> resultado.text = "Empate"

                    (opcionUsuario == R.id.piedra && eleccionMaquina == 3) ||
                            (opcionUsuario == R.id.papel && eleccionMaquina == 1) ||
                            (opcionUsuario == R.id.tijeras && eleccionMaquina == 2) -> {
                        resultado.setText("Has ganado")
                        userCounter++
                        contadorUsuario.text = "$userCounter"
                    }
                    else -> {
                        resultado.setText("Has perdido")
                        cpuCounter++
                        contadorMaquina.text = "$cpuCounter"
                    }
                }
            },1000)

        }
        piedra.setOnClickListener(onClickListener)
        papel.setOnClickListener(onClickListener)
        tijeras.setOnClickListener(onClickListener)

        reinicio.setOnClickListener(View.OnClickListener{
            cpuCounter = 0
            userCounter = 0
            contadorUsuario.text = "$userCounter"
            contadorMaquina.text = "$cpuCounter"
        })
    }
}