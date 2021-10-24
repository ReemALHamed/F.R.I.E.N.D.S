package com.example.friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recipes.*

class Recipes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        //upload the recipes' PDF cookbook from the assets folder into the PdfView
        activityMainPdfView.fromAsset("recipes.pdf").show()
    }
}