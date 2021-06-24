package com.example.dogfacts_kt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogfacts_kt.api.DogAPI
import com.example.dogfacts_kt.api.DogFact
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), DogActionListener {
    //vars
    private val mFacts = ArrayList<String>()
    private val mImageUrls = ArrayList<String>()
    private val dogAPI = DogAPI()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initImageBitmaps()
    }

    private fun initImageBitmaps() {
        getDogImage(2)
        callDogFact(2)
    }

    private fun checkSet() {
        if (mImageUrls.isNotEmpty() && mFacts.isNotEmpty()) {
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerv_view)
        val adapter = RecyclerViewAdapter(this, mFacts, mImageUrls)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun callDogFact(factCount: Int) {
// Instantiate the RequestQueue.

        lifecycleScope.launch{
            val response : ArrayList<DogFact> = dogAPI.factService.getDogFacts(factCount)
            response.forEach { item ->
                mFacts.add(item.fact)
            }
            checkSet()
        }

    }

    private fun getDogImage(dogSize: Int) {
        lifecycleScope.launch {
           val response = dogAPI.imageService.getDogImages(dogSize)
            Log.i("status" , response.status)
           mImageUrls.addAll(response.urlsList)
            checkSet()
        }
    }

    override fun dogClicked(imageUrl: String, imageName: String) {
        Toast.makeText(this, "Dogs!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, GalleryActivity::class.java)
        intent.putExtra(GalleryActivity.imageURLkey, imageUrl)
        intent.putExtra(GalleryActivity.imageNameKey, imageName)
        startActivity(intent)
    }
}