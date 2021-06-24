package com.example.dogfacts_kt

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*

class RecyclerViewAdapter(
    private val actionListener: DogActionListener,
    private val dogFacts: ArrayList<String>,
    private val imageURLs: ArrayList<String>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val TAG = "RecyclerViewAdapter"

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: called.")


        //Leverage Picasso to receive image from image URL based on item position
        Picasso.get().load(imageURLs[position]).into(holder.image)

        //Set dog fact to the view
        holder.imageName.text = dogFacts[position]
        holder.parentLayout.setOnClickListener { view: View? ->
            actionListener.dogClicked(
                imageURLs[position], dogFacts[position]
            )
        }
    }

    override fun getItemCount(): Int {
        return dogFacts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var imageName: TextView
        var parentLayout: RelativeLayout

        init {
            image = itemView.findViewById(R.id.image)
            imageName = itemView.findViewById(R.id.image_name)
            parentLayout = itemView.findViewById(R.id.parent_layout)
        }
    }

}