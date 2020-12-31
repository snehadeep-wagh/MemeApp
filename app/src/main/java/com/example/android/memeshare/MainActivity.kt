package com.example.android.memeshare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.memeshare.Networking.Client
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApi()

    }

    fun shareMeme(view: View) {
        Intent(Intent.ACTION_SEND).also {
            it.putExtra(Intent.EXTRA_TEXT, "Hey, I got amazing meme from reddit $url")
            it.type = "text/plain"
            val chooser = Intent.createChooser(it, "Share this meme using...")
            startActivity(chooser)
        }
    }
    fun nextMeme(view: View) {
        callApi()
    }

    private fun callApi(): String?
    {
        progressBar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main) {
            val response = Client().api.getMeme()

            if(response.isSuccessful)
            {
                response.let {
                    url = response.body()?.url
                    Picasso.get().load(url).into(imageView, object: Callback{
                        override fun onSuccess() {
                            progressBar.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            progressBar.visibility = View.GONE
                        }

                    })
                }
            }
        }

        return url
    }
}