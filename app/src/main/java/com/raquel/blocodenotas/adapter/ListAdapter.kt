package com.raquel.blocodenotas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.raquel.blocodenotas.R
import com.raquel.blocodenotas.data.Priority
import com.raquel.blocodenotas.data.User
import com.raquel.blocodenotas.fragments.ListFragmentDirections

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

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
            findViewById<CardView>(R.id.cardview).setOnClickListener {
                val actionToPassDataToUpdateFragment = ListFragmentDirections.actionListFragment3ToUpdateFragment(userList[position])
                holder.itemView.findNavController().navigate(actionToPassDataToUpdateFragment)
            }

            when (currentItem.notesPriorityDB) {
                Priority.HIGH -> holder.itemView.findViewById<View>(R.id.notesPriorityItem)
                    .setBackgroundResource(R.drawable.red_shape)
                Priority.MEDIUM -> holder.itemView.findViewById<View>(R.id.notesPriorityItem)
                    .setBackgroundResource(R.drawable.yellow_shape)
                Priority.LOW -> holder.itemView.findViewById<View>(R.id.notesPriorityItem)
                    .setBackgroundResource(R.drawable.green_shape)
            }

        }
    }
    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}