package com.example.calculadoraimc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calcular();
    }

    /**
     * Calcular
     */
    private fun calcular() {
        val pesoK: EditText = findViewById(R.id.etKilos)
        val alturaE: EditText = findViewById(R.id.etEstatura)
        val calcular: Button = findViewById(R.id.btnCalcular)
        val imc: TextView = findViewById(R.id.tvIMC)
        val rango: TextView = findViewById(R.id.tvRange)

        calcular.setOnClickListener { view ->
            try {
                val peso: Double = pesoK.text.toString().toDouble()
                val estatura: Double = alturaE.text.toString().toDouble()

                val resultado = calcularIMC(estatura, peso)
                val formattedNumber = String.format("%.2f", resultado)
                imc.text = formattedNumber

                var salud: String
                var color: Int

                when {
                    resultado < 18.5 -> {
                        salud = "Bajo Peso"
                        color = R.color.colorRed
                    }

                    resultado in 18.5..24.9 -> {
                        salud = "Saludable"
                        color = R.color.colorGreenish
                    }

                    resultado in 25.0..29.9 -> {
                        salud = "Sobrepeso"
                        color = R.color.colorYellow
                    }

                    resultado in 30.0..34.9 -> {
                        salud = "Obesidad Grado 1"
                        color = R.color.colorOrange
                    }

                    resultado in 35.0..39.9 -> {
                        salud = "Obesidad Grado 2"
                        color = R.color.colorBrown
                    }

                    resultado >= 40 -> {
                        salud = "Obesidad Grado 3"
                        color = R.color.colorRed
                    }

                    else -> {
                        salud = "Error"
                        color = 0
                    }
                }

                rango.setBackgroundResource(color)
                rango.text = salud
            } catch (e: Exception) {
                imc.text = "Debe ingresar valores reales"
                println(e)
            }
        }
    }

    /**
     * Calcular IMC
     */
    private fun calcularIMC(estatura: Double, peso: Double): Double {
        return peso / (estatura * estatura)
    }
}