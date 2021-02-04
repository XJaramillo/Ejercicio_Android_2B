package com.tutorialesprogramacionya.proyecto026

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var  mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mp = MediaPlayer.create(this, R.raw.numeros)
        var posicion = 0
        val boton1=findViewById<Button>(R.id.boton1)
        val boton2=findViewById<Button>(R.id.boton2)
        val boton3=findViewById<Button>(R.id.boton3)
        val boton4=findViewById<Button>(R.id.boton4)
        val boton5=findViewById<Button>(R.id.boton5)
        boton1.setOnClickListener {
            mp.start()
            if (boton5.text == "No reproducir en forma circular")
                mp.isLooping = false
            else
                mp.isLooping = true
        }

        boton2.setOnClickListener {
            if (mp.isPlaying()) {
                posicion = mp.getCurrentPosition()
                mp.pause()
            }
        }

        boton3.setOnClickListener {
            if (mp.isPlaying() == false) {
                mp.seekTo(posicion)
                mp.start()
            }
        }

        boton4.setOnClickListener {
            mp.pause()
            posicion = 0
            mp.seekTo(0)
        }

        boton5.setOnClickListener {
            if (boton5.text  == "No reproducir en forma circular")
                boton5.setText("Reproducir en forma circular")
            else
                boton5.setText("No reproducir en forma circular")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.release()
    }
}