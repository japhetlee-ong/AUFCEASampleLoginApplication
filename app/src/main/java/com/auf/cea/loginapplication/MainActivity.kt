package com.auf.cea.loginapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.auf.cea.loginapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE)
        val firstName = sharedPreferences.getString(USER_FIRST_NAME,"")
        val lastName = sharedPreferences.getString(USER_LAST_NAME,"")

        binding.txtUserWelcome.text = String.format("Welcome %s %s", firstName,lastName)

        binding.btnLogoutUser.setOnClickListener(this)
        binding.btnEdtUserDetails.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_logout_user) ->{
                val editor = sharedPreferences.edit()
                editor.putBoolean(KEEP_ME_LOGIN,false)
                editor.apply()

                val intent = Intent(this,LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)

            }
            (R.id.btn_edt_user_details) ->{
                val intent = Intent(this,EditUserDetailsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}