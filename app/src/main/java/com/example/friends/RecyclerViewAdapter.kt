package com.example.friends

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.info_layout.*
import kotlinx.android.synthetic.main.item_row.view.*


class RecyclerViewAdapter(val context: Context,private val myActors: List<Cast>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView: View):RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val myActor = myActors[position]

        holder.itemView.apply {
           tvTitle.text = myActor.name
            when(myActor.name){//display corresponding image based on the character's name
                "Monica"->myImageView.setImageResource(R.drawable.monica2)
                "Ross"->myImageView.setImageResource(R.drawable.ross2)
                "Joey"->myImageView.setImageResource(R.drawable.joey2)
                "Phoebe"->myImageView.setImageResource(R.drawable.phoebe2)
                "Rachel"->myImageView.setImageResource(R.drawable.rachel2)
                "Chandler"->myImageView.setImageResource(R.drawable.chandler2)
                "Huggsy"->myImageView.setImageResource(R.drawable.hugzy)
                "Duck & Chick"->myImageView.setImageResource(R.drawable.duckandchicken)
                "Gunther"->myImageView.setImageResource(R.drawable.gunther)
                "Janice"->myImageView.setImageResource(R.drawable.janice)
                "Mr.Heckles"->myImageView.setImageResource(R.drawable.heckles)
            }
        }

        holder.itemView.setOnClickListener {//on click of a single character card, display the information dialog
            infoDialog(myActor.name,holder.itemView.myImageView.drawable)
        }


    }
    override fun getItemCount() = myActors.size

    fun infoDialog(name: String?, drawable: Drawable) {
        //get all the characters from the (actorsInfo.json) file
        val gson = Gson()
        val jsonFileString = Utils.getJsonFromAssets(context, "actorsInfo.json")
        val listCastType = object : TypeToken<List<CastInfo?>?>() {}.type
        var myActorsInfo: List<CastInfo> = gson.fromJson(jsonFileString, listCastType)

        var myActorsDialog = Dialog(context)
        myActorsDialog.setContentView(R.layout.info_layout)

        //check if the clicked character matches the current (i) within the loop , then assign (i's) data in the views
        for (i in myActorsInfo){if (i.name==name){
            myActorsDialog.actorImageView2.background=drawable
            myActorsDialog.tvName.text=i.name
            myActorsDialog.tvNickname.text=i.nickname
            myActorsDialog.tvFakename.text=i.fakeName
            myActorsDialog.tvOccupation.text=i.occupation
            myActorsDialog.tvAbout.text=i.about
        }}

        myActorsDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myActorsDialog.show()
        myActorsDialog.backButton.setOnClickListener{
            myActorsDialog.dismiss()
        }
    }
}