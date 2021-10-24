package com.example.friends

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.episode_layout.*
import kotlinx.android.synthetic.main.room_layout.*

class Map : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        kitchen.setOnClickListener{//on the click of the correct secret spot display the real life image of the same spot
            var myRoomDialog = Dialog(this)
            myRoomDialog.setContentView(R.layout.room_layout)
            myRoomDialog.tvroom.text = "Girls' Kitchen"
            myRoomDialog.roomView.setImageDrawable(getDrawable(R.drawable.kitchen))
            myRoomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myRoomDialog.show()
            myRoomDialog.closeRoomButton.setOnClickListener{
                myRoomDialog.dismiss()
            }
        }

        livingRoom.setOnClickListener{//on the click of the correct secret spot display the real life image of the same spot
            var myRoomDialog = Dialog(this)
            myRoomDialog.setContentView(R.layout.room_layout)
            myRoomDialog.tvroom.text = "Girls' LivingRoom"
            myRoomDialog.roomView.setImageDrawable(getDrawable(R.drawable.livingroom))
            myRoomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myRoomDialog.show()
            myRoomDialog.closeRoomButton.setOnClickListener{
                myRoomDialog.dismiss()
            }
        }

        boysKitchen.setOnClickListener{
            var myRoomDialog = Dialog(this)
            myRoomDialog.setContentView(R.layout.room_layout)
            myRoomDialog.tvroom.text = "Boys' Kitchen"
            myRoomDialog.roomView.setImageDrawable(getDrawable(R.drawable.boyskitchen))
            myRoomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myRoomDialog.show()
            myRoomDialog.closeRoomButton.setOnClickListener{
                myRoomDialog.dismiss()
            }
        }

        boysLivingroom.setOnClickListener{
            var myRoomDialog = Dialog(this)
            myRoomDialog.setContentView(R.layout.room_layout)
            myRoomDialog.tvroom.text = "Boys' LivingRoom"
            myRoomDialog.roomView.setImageDrawable(getDrawable(R.drawable.boyslivingroom))
            myRoomDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myRoomDialog.show()
            myRoomDialog.closeRoomButton.setOnClickListener{
                myRoomDialog.dismiss()
            }
        }
    }
}