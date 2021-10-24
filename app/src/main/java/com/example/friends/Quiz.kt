package com.example.friends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_quiz.*

class Quiz : AppCompatActivity() {
    var myUrl =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // on click of each quiz button display the corresponding quiz in the webView using the URL
        quiz1.setOnClickListener{
            myUrl = "https://play.howstuffworks.com/quiz/can-you-pass-this-friends-true-or-false-quiz?remorapos=3&rmalg=es&remorasrc=572bf541-fed8-11e6-99d2-080027b55128&remoraregion=rightrail"
            myWebView(myUrl)
        }
        quiz2.setOnClickListener{
            myUrl="https://play.howstuffworks.com/quiz/can-you-guess-the-friends-character-quiz?rmalg=es&remorapos=4&remorasrc=f19c11d615a846bf90709706c175bec3&remoraregion=rightrail"
            myWebView(myUrl)
        }
        quiz3.setOnClickListener{
            myUrl="https://play.howstuffworks.com/quiz/well-guess-how-many-times-youve-watched-friends-after-this-trivia-quiz?rmalg=es&remorapos=3&remorasrc=ab6227590e6f4f0dbcf163a465c02a81&remoraregion=rightrail"
            myWebView(myUrl)
        }
        quiz4.setOnClickListener{
            myUrl="https://play.howstuffworks.com/quiz/which-of-the-friends-said-it?rmalg=es&remorapos=3&remorasrc=7653a6adfe48406b9abd9aaa9fec30cb&remoraregion=rightrail"
            myWebView(myUrl)
        }
        //on click of back button , hide the webView and display the quiz page
        backwebButton.setOnClickListener{
            myWebView.loadUrl("about:blank")
            myScrollView.visibility = View.GONE
            myQuizLayout.visibility = View.VISIBLE
        }
       }

    fun myWebView(url : String){
        myWebView.settings.javaScriptEnabled = true
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
        //on click of quiz button , hide the quiz page and display the webView
        myQuizLayout.visibility = View.GONE
        myScrollView.visibility = View.VISIBLE
        myWebView.loadUrl(url)

    }
}