package com.example.firstproject

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {

    lateinit var password: EditText;
    lateinit var email: EditText;
    lateinit var signin: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout= findViewById<LinearLayout>(R.id.grad)//background as AnimationDrawable
        val animationDrawable = layout.background as AnimationDrawable
        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(2000)
            start()
        }
        val array1 = applicationContext.resources.getStringArray(R.array.Emails)
        val array2 = applicationContext.resources.getStringArray(R.array.Passwords)
        email = findViewById<EditText>(R.id.email)
        password = findViewById<EditText>(R.id.Password)
        signin = findViewById<Button>(R.id.signin)


        signin.setOnClickListener{
        if(array1.contains(email.text.toString())) {
            if(array2[array1.indexOf(email.text.toString())]==password.text.toString())
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
            else{

                password.setTextColor(Color.RED);
                email.setTextColor(Color.RED);
            }
        }
        else{
            password.setTextColor(Color.RED);
            email.setTextColor(Color.RED);
        }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("email", email.text.toString())
        outState.putString("password", password.text.toString())
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val emaill = savedInstanceState.getString("email")
        val passwordd = savedInstanceState.getString("password")
        email.setText(emaill)
        password.setText(passwordd)
    }
}