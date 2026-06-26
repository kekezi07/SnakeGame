package com.example.snakegame

import android.content.Context
import android.media.MediaPlayer


object MusicManager {


    private var mediaPlayer: MediaPlayer? = null

    private var currentMusic: Int = 0


    fun play(context: Context, music: Int) {


        // Se já está tocando essa música, não reinicia
        if (mediaPlayer != null && currentMusic == music) {

            return

        }


        stop()


        currentMusic = music


        mediaPlayer = MediaPlayer.create(
            context,
            music
        )


        mediaPlayer?.isLooping = true

        mediaPlayer?.start()

    }



    fun stop(){

        mediaPlayer?.stop()

        mediaPlayer?.release()

        mediaPlayer = null

        currentMusic = 0

    }


}