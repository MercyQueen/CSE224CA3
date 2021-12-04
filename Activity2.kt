package com.example.ca3

import android.content.Context
import android.graphics.Color
import android.graphics.Color.GREEN
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.ca3.R.drawable
import com.google.android.material.snackbar.Snackbar

class Activity2 : AppCompatActivity() {

     var co:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)


        //Linear layout to display the items in the list
        val ll=findViewById<LinearLayout>(R.id.container)

        //Used for explicit Intent
        val namee=findViewById<TextView>(R.id.intro)

        //Bundle for explicit intent
        val bundle:Bundle?=intent.extras
        val n = bundle?.get("name")

        //Setting the textView present in this activity with values fetched from the first activity
        namee.text=n.toString()

        //Button to add new items
        val add=findViewById<ImageButton>(R.id.addbtn1)

        //function of add button

        add.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Add new item")

            //Custom Alert Dialog Box
             val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
             val dialogView = inflater.inflate(R.layout.dialog,null)

            //code for spinner inside dialog view
            val spin=dialogView.findViewById<Spinner>(R.id.spinner1)
            val cat = arrayOf("Stationary","Vegetable","Fruit","Others")
            if(spin !=null)
            {
                val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,cat)
                spin.adapter=adapter
                spin.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(parent: AdapterView<*>,view: View,position:Int,id:Long){
                        Toast.makeText(applicationContext,"selected Item"+" "+cat[position],Toast.LENGTH_LONG).show()

                        //If Else ladder for setting different background color on selection of different items

                         if(cat[position] == "Stationary")
                             co=Color.rgb(	131, 105, 83)
                         else if(cat[position]=="Vegetable")
                            co=Color.rgb(206,255,201)
                         else if(cat[position]=="Fruit")
                            co=Color.rgb(255,209,220)
                         else
                            co=Color.rgb(167, 199, 231)

                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {}

                }
            }
            builder.setView(dialogView)
            builder.setPositiveButton("Save"){dialogInterface,i->
                val inflater=getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                //rowView is the cardview that we add to the display when new item is added

                val rowView: View =inflater.inflate(R.layout.listitem,null)

                //fetching the realtive layout inside the rowView
                val rl=rowView.findViewById<RelativeLayout>(R.id.rl)
                rl.setBackgroundColor(co)

                //fetching the TextView inside the rowView to set its text
                val name=rowView.findViewById<TextView>(R.id.name)

                //Fetching the name of the item given by the user from dialog View
                val inp=dialogView.findViewById<EditText>(R.id.nameEdit)
                name.text=inp.text

                //Button to be clicked when item is purchased
                val delbtn = rowView.findViewById<ImageButton>(R.id.del)

                //Delete Button Function

                delbtn.setOnClickListener{

                    //Using Snackbar
                    val snackbar=Snackbar.make(it,"Successfully Purchased The ${inp.text}",Snackbar.LENGTH_LONG).setAction("Ok"){}
                    snackbar.setActionTextColor(Color.GREEN)
                    snackbar.setTextColor(Color.GRAY)
                    snackbar.setBackgroundTint(Color.WHITE)
                    snackbar.show()

                    //Removing the card
                    ll.removeView(rowView)
                }
                ll.addView(rowView)
            }
            .show()
        }
    }
}