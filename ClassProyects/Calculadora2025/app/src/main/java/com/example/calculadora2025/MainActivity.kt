package com.example.calculadora2025

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora2025.databinding.ActivityMainBinding

class MainActivityBinding : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }  //Fin ViewCompat

        var resultado = 0.0 // creo una nueva variable quie se llama resultado y le asigno el valor de entrada 0.0
        //Creao cuatro banderas para las operaciones principales. La idea es que cuando un usuario elija una operación, la bandera de dicha operación se prenda
        var suma = false //Creo la bamdera para la suma y la inicializo como false
        var resta = false
        var multiplicacion = false
        var division = false

        binding.ceroButton.setOnClickListener {
            binding.salidaTextView.text=contador(0)
        }
        binding.unoButton.setOnClickListener {
            binding.salidaTextView.text=contador(1)
        }
        binding.dosButton.setOnClickListener {
            binding.salidaTextView.text=contador(2)
        }
        binding.tresButton.setOnClickListener {
            binding.salidaTextView.text=contador(3)
        }
        binding.cuatroButton.setOnClickListener {
            binding.salidaTextView.text=contador(4)
        }
        binding.cincoButton.setOnClickListener {
            binding.salidaTextView.text=contador(5)
        }
        binding.seisButton.setOnClickListener {
            binding.salidaTextView.text=contador(6)
        }
        binding.sieteButton.setOnClickListener {
            binding.salidaTextView.text=contador(7)
        }
        binding.ochoButton.setOnClickListener {
            binding.salidaTextView.text=contador(8)
        }
        binding.nueveButton.setOnClickListener {
            binding.salidaTextView.text=contador(9)
        }
        binding.acButton.setOnClickListener {
            binding.salidaTextView.text="0"
        }
        binding.puntoButton.setOnClickListener {
            if (!binding.salidaTextView.text.contains("."))
                binding.salidaTextView.text="${binding.salidaTextView.text}."
        }

        binding.masmenosButton.setOnClickListener {
            binding.salidaTextView.text=(binding.salidaTextView.text.toString().toDouble() * -1).toString()
        }

        binding.porcientoButton.setOnClickListener {
            binding.salidaTextView.text=(binding.salidaTextView.text.toString().toFloat() * 0.01).toString()
        }

        binding.masButton.setOnClickListener {
            resultado=binding.salidaTextView.text.toString().toDouble()
            binding.salidaTextView.text="0"
            suma=true
            resta = false
            multiplicacion=false
            division=false
            binding.masButton.visibility=View.INVISIBLE
        }

        binding.menosButton.setOnClickListener {
            resultado=binding.salidaTextView.text.toString().toDouble()
            binding.salidaTextView.text="0"
            suma = false
            resta = true
            multiplicacion = false
            division = false
            binding.menosButton.visibility=View.GONE
        }

        binding.porButton.setOnClickListener {
            resultado=binding.salidaTextView.text.toString().toDouble()
            binding.salidaTextView.text="0"
            suma = false
            resta = false
            multiplicacion = true
            division = false
        }

        binding.entreButton.setOnClickListener {
            resultado=binding.salidaTextView.text.toString().toDouble()
            binding.salidaTextView.text="0"
            suma = false
            resta = false
            multiplicacion = false
            division = true
        }

        binding.igualButton.setOnClickListener {
            when{
                suma -> {
                    resultado += binding.salidaTextView.text.toString().toDouble()
                    //binding.salidaTextView.text=resultado.toString()
                    binding.salidaTextView.text="$resultado"
                    suma = false
                    binding.masButton.visibility=View.VISIBLE
                }
                resta -> {
                    resultado -= binding.salidaTextView.text.toString().toDouble()
                    binding.salidaTextView.text="$resultado"
                    resta= false
                    binding.menosButton.visibility=View.VISIBLE
                }
                multiplicacion -> {
                    resultado *= binding.salidaTextView.text.toString().toDouble()
                    binding.salidaTextView.text="$resultado"
                    multiplicacion=false
                }
                division -> {
                    resultado /= binding.salidaTextView.text.toString().toDouble()
                    binding.salidaTextView.text="$resultado"
                    division=false
                }
            }
        }

    }   //Fin función OnCreate()

    //Creo una nueva función
    fun contador(nuevoNumero: Int) : String {
        //binding.salidaTextView.text="$textoActual$nuevoNumero"
        //binding.salidaTextView.text=textoActual.toString().plus(nuevoNumero.toString())
        //binding.salidaTextView.text="${binding.salidaTextView.text}$nuevoNumero"
        if(binding.salidaTextView.text=="0"){
            return "$nuevoNumero"
        }
        else{
            return "${binding.salidaTextView.text}$nuevoNumero"
        }

    }

}  //Fin Clase MainActivity

//Esto es un comentario
/* comentario multilinea 1
comentario multilinea 2
comentario multilinea 3
comentario multilinea 4
 */