package com.example.ca3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class CAproject : AppCompatActivity() {

    //Declaration of variable

    lateinit var getImage:ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caproject)

        //Image View
        val imageView = findViewById<ImageView>(R.id.imageView)

        //Image Upload Button
        val btnImgUp = findViewById<ImageButton>(R.id.imgup)

        //Next Activity Button
        val nxt=findViewById<Button>(R.id.nxt)

        //Function for picking image

        getImage=registerForActivityResult(ActivityResultContracts.GetContent(),
        ActivityResultCallback {  imageView.setImageURI(it) })

        //For app permission

        val requestMediaPermission = registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            if(it)
            {
                Toast.makeText(applicationContext,"Permission is Granted",Toast.LENGTH_LONG).show()
                getImage.launch("image/*")
            }
            else
                Toast.makeText(applicationContext,"Permission Denied",Toast.LENGTH_LONG).show()
        }

        //Image upload button function

        btnImgUp.setOnClickListener{
            requestMediaPermission.launch(android.Manifest.permission.CAMERA)
        }

        //Next Button function

        nxt.setOnClickListener{
            val intent =Intent(this,Activity2::class.java)
            val namee = findViewById<EditText>(R.id.uname)
            intent.putExtra("name","Hi, ${namee.text}")
            startActivity(intent)
        }
    }

}