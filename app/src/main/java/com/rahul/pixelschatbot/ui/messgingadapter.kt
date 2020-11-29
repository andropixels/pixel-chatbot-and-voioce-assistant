package com.rahul.pixelschatbot.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.pixelschatbot.R
import com.rahul.pixelschatbot.data.messege
import com.rahul.pixelschatbot.utils.constants.RECIEVE_ID
import com.rahul.pixelschatbot.utils.constants.SEND_ID
import kotlinx.android.synthetic.main.messege_item.view.*



class messgingadapter():RecyclerView.Adapter<messgingadapter.messegeviewholder>() {

    var messegelist = mutableListOf<messege>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): messegeviewholder {

        val infalter =
            LayoutInflater.from(parent.context).inflate(R.layout.messege_item, parent, false)
        return messegeviewholder(infalter)
    }

    override fun getItemCount(): Int {
        return messegelist.size

    }

    override fun onBindViewHolder(holder: messegeviewholder, position: Int) {
        val curreemtmessege = messegelist[position]
        when(curreemtmessege.id){

            SEND_ID ->{
                                 holder.sendmessege.apply {
                                     text=curreemtmessege.messegs
                                     visibility=View.VISIBLE
                                 }
                        holder.recievemesege.visibility=View.GONE
            }
            RECIEVE_ID -> {

                holder.recievemesege.apply {
                    text=curreemtmessege.messegs
                    visibility=View.VISIBLE
                }
                holder.sendmessege.visibility=View.GONE
            }
        }
    }

    class messegeviewholder(view: View) : RecyclerView.ViewHolder(view) {

        val sendmessege = view.findViewById<TextView>(R.id.sendmesege)
        val recievemesege = view.findViewById<TextView>(R.id.recivemesege)


    }

    fun insertmessegr(messege: messege){

        messegelist.add(messege)//so messege list getsdata from here
        notifyItemInserted(messegelist.size)
    }
}