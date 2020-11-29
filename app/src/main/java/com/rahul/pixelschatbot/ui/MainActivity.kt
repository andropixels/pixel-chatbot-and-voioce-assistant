package com.rahul.pixelschatbot.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.pixelschatbot.R
import com.rahul.pixelschatbot.data.messege
import com.rahul.pixelschatbot.utils.botresponces
import com.rahul.pixelschatbot.utils.constants.RECIEVE_ID
import com.rahul.pixelschatbot.utils.constants.SEND_ID
import com.rahul.pixelschatbot.utils.time
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*




class MainActivity : AppCompatActivity() {



    lateinit var adptr:messgingadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

              title="PIXEL"

        adptr= messgingadapter()
        recyclerview.adapter= adptr
        recyclerview.layoutManager=LinearLayoutManager(applicationContext)

        typemessege.setOnLongClickListener {

           val intent=Intent(this,speak::class.java)
            startActivity(intent)
            return@setOnLongClickListener true
        }

        customsg("hey i am pixo , how can i help you")



      button.setOnClickListener {
          sendmessege()

      }





        typemessege.setOnClickListener {
            GlobalScope.launch {
                delay(1000)
                withContext(Dispatchers.Main){

                    recyclerview.scrollToPosition(adptr.itemCount- 1)
                }
            }
        }

    }



    private fun sendmessege(){

        val messege=  typemessege.text.toString()
        val  timestamp=time.timestamp()

           if (messege.isNotEmpty()){

               typemessege.setText("")
               adptr.insertmessegr(messege(messege,SEND_ID,timestamp))
               recyclerview.scrollToPosition(adptr.itemCount-1)
               botresponce(messege)

           }



    }

    private fun botresponce(messs: String) {
             val timestamp=time.timestamp()
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val responce=botresponces.responces(messs)
            adptr.insertmessegr(messege(responce, RECIEVE_ID,timestamp))
                recyclerview.scrollToPosition(adptr.itemCount- 1)
            }
        }

    }

    private fun customsg(messege: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val timestamp= time.timestamp()
                adptr.insertmessegr(messege(messege,RECIEVE_ID,timestamp))
                recyclerview.scrollToPosition(adptr.itemCount- 1)
            }
        }

    }
}