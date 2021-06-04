package com.raquel.blocodenotas.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.data.User

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false))
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.apply {

            findViewById<TextView>(R.id.notesTitleItem).text = currentItem.notesTitleDB
            findViewById<TextView>(R.id.notesSubtitleItem).text = currentItem.notesSubtitleDB
            findViewById<TextView>(R.id.notesDateItem).text = currentItem.notesDate


         }
    }
    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}