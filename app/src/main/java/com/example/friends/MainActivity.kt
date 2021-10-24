package com.example.friends

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friends.Utils.getJsonFromAssets
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_info_layout.*
import kotlinx.android.synthetic.main.room_layout.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //display the start peephole screen and fade out after few seconds
        peephole.visibility = View.VISIBLE
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = AccelerateInterpolator() //and this
        fadeOut.startOffset = 5000
        fadeOut.duration = 1000
        val animation = AnimationSet(false) //change to false
        animation.addAnimation(fadeOut)
        peephole.setAnimation(animation)

        var countdown_timer = object : CountDownTimer(6000, 1000) {
            override fun onFinish() {
                peephole.visibility= View.GONE
                val fadeIn = AlphaAnimation(0f, 1f)
                fadeIn.interpolator = DecelerateInterpolator() //add this
                fadeIn.duration = 1000
            }

            override fun onTick(p0: Long) {

            }
        }
        countdown_timer.start()

        //read the quotes from JSON file, shuffle them and display a random one in the catchphrase bar
        val jsonFileString = getJsonFromAssets(this, "quotes.json")
        val gson = Gson()
        val listQuotesType = object : TypeToken<List<quotes?>?>() {}.type
        val myQuotes: List<quotes> = gson.fromJson(jsonFileString, listQuotesType)
        var rand = (myQuotes.indices).random()
        var currentQuote = myQuotes[rand]
        tvQuote.text="\"${currentQuote.quote}\""

        //display the quotes' corresponding image
        when(currentQuote.character){
            "Monica"->charImageView.setImageResource(R.drawable.monica)
            "Ross"->charImageView.setImageResource(R.drawable.ross)
            "Joey"->charImageView.setImageResource(R.drawable.joey)
            "Phoebe"->charImageView.setImageResource(R.drawable.phoebe)
            "Rachel"->charImageView.setImageResource(R.drawable.rachel)
            "Chandler"->charImageView.setImageResource(R.drawable.chandler)
        }

        //get all the actors from the JSON file and display them in the recyclerview
        val jsonFileString2 = getJsonFromAssets(this, "actors.json")
        val listCastType = object : TypeToken<List<Cast?>?>() {}.type
        var myActors: List<Cast> = gson.fromJson(jsonFileString2, listCastType)
        myActors= myActors.shuffled()
        rvMain.adapter = RecyclerViewAdapter(this,myActors)
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //when recipe button is clicked move to the Recipes class to display recipes' PDF cookbook
        myRecipes.setOnClickListener{
            startActivity(Intent(this,Recipes::class.java))
            rvMain.adapter = RecyclerViewAdapter(this,myActors)
        }
        //when quiz button is clicked move to the Quiz class to display all online series quizzes
        myQuiz.setOnClickListener{
            startActivity(Intent(this,Quiz::class.java))
            rvMain.adapter = RecyclerViewAdapter(this,myActors)
        }
        //when episodes button is clicked move to the Episodes class to pick a random episode
        myEpisodesPicker.setOnClickListener{
            startActivity(Intent(this,Episodes::class.java))
            rvMain.adapter = RecyclerViewAdapter(this,myActors)
        }
        //when map button is clicked move to the Map class to display Friends' apartment map
        myMaps.setOnClickListener{
            startActivity(Intent(this,Map::class.java))
            rvMain.adapter = RecyclerViewAdapter(this,myActors)
        }
        //when info button is clicked open a dialog showing developer info & credits
        infoButton.setOnClickListener{
            var myInfoDialog = Dialog(this)
            myInfoDialog.setContentView(R.layout.app_info_layout)
            myInfoDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myInfoDialog.show()
            myInfoDialog.twitter.setOnClickListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/reemsaadhamed?t=sYwYCapOnm0ctje71SryYg&s=09"))
                this.startActivity(browserIntent)
            }
            myInfoDialog.github.setOnClickListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ReemALHamed?tab=repositories"))
                this.startActivity(browserIntent)
            }
            myInfoDialog.credit1.setOnClickListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/dmtrxw"))
                this.startActivity(browserIntent)
            }
            myInfoDialog.credit2.setOnClickListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/melanieseltzer"))
                this.startActivity(browserIntent)
            }
            myInfoDialog.closeInfoButton.setOnClickListener{
                myInfoDialog.dismiss()
            }

        }
    }
}