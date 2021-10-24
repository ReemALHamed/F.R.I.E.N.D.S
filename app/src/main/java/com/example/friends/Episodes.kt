package com.example.friends

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_episodes.*
import kotlinx.android.synthetic.main.episode_layout.*
import kotlinx.android.synthetic.main.info_layout.*
import kotlinx.android.synthetic.main.item_row.view.*

class Episodes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)

        randomThumbs()
        startEpisodesPicker.setOnClickListener{
            episodeDialog()
        }

    }

    private fun randomThumbs() {
        //change the gif (thumbs up) with each new episode suggestion
        var thumb = (1..3).random()
        when(thumb){
            1->thumbsGif.setBackgroundResource(R.drawable.thumbs1)
            2->thumbsGif.setBackgroundResource(R.drawable.thumbs2)
            3->thumbsGif.setBackgroundResource(R.drawable.thumbs3)
        }
    }

    fun episodeDialog() {//choose random season then pick a random episode

        var rand = (1..10).random()//choose a random season (1 .. 10)
        val jsonFileString = Utils.getJsonFromAssets(this, "s$rand.json")//assign the random variable to the json file name (s1.json .. s10.json)
        val gson = Gson()
        val listEpisodesType = object : TypeToken<List<EpisodePicker?>?>() {}.type
        val myEpisodes: List<EpisodePicker> = gson.fromJson(jsonFileString, listEpisodesType)
        var rand2 = (myEpisodes.indices).random()//pic a random episode based on the retrieved season indices
        var currentEpisode = myEpisodes[rand2]

        var myEpisodeDialog = Dialog(this)
        myEpisodeDialog.setContentView(R.layout.episode_layout)

        Glide.with(this)//use glide to upload the image into the imageview using URL
            .load(currentEpisode.image_url)
            .into(myEpisodeDialog.myepisodeImageView)
        // set the currentEpisode data into the corresponding views
        myEpisodeDialog.tvEpisodeTitle.text = currentEpisode.title
        myEpisodeDialog.tvEpisodeNumber.text = currentEpisode.no_in_season.toString()
        myEpisodeDialog.tvEpisodeID.text = currentEpisode.id
        myEpisodeDialog.tvSynopsis.text = currentEpisode.synopsis

        myEpisodeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myEpisodeDialog.show()
        myEpisodeDialog.closeEpisodeButton.setOnClickListener{
            //on close button click , dismiss the dialog and change the thumbs up gif
            myEpisodeDialog.dismiss()
            randomThumbs()
        }
    }
}