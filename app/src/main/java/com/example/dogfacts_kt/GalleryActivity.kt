package com.example.dogfacts_kt

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class GalleryActivity : AppCompatActivity() {
    companion object{
        val imageURLkey = "image_url"
        val imageNameKey = "image_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        getIncomingIntent()
    }

    private fun getIncomingIntent(){
        if (intent.hasExtra(imageURLkey) && intent.hasExtra(imageNameKey)) {
            val imageUrl = intent.getStringExtra(imageURLkey)
            val imageName = intent.getStringExtra(imageNameKey)
            setImage(imageUrl!!, imageName!!)
        }
    }

    private fun setImage(imageUrl: String, imageName: String) {
        val name = findViewById<TextView>(R.id.image_description)
        name.text = imageName
        val image = findViewById<ImageView>(R.id.image)
        Picasso.get().load(imageUrl).into(image)
    }
}