package com.example.musicplayer

import android.content.Context
import android.media.AsyncPlayer
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.os.postDelayed
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()


    override fun onCreate(savedInstancesState: Bundle?) {
        super.onCreate(savedInstancesState)
        setContentView(R.layout.activity_main)


        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.music3)

        val btnPlay = findViewById<ImageView>(R.id.btnPlay)

        val seekBar = findViewById<SeekBar>(R.id.seekBar)
            seekBar.progress = 0
            seekBar.max = mediaPlayer.duration

        btnPlay.setOnClickListener {

            if(!mediaPlayer.isPlaying){
            mediaPlayer.start()
                btnPlay.setImageResource(R.drawable.baseline_pause_circle_filled_24)
            }else {
                mediaPlayer.pause()
                btnPlay.setImageResource(R.drawable.baseline_play_circle_filled_24)


            }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed) {
                    mediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)

        }
        handler.postDelayed(runnable, 1000)
        mediaPlayer.setOnCompletionListener {
            btnPlay.setImageResource(R.drawable.baseline_play_circle_filled_24)
            seekBar.progress = 0
        }
    }
}
}