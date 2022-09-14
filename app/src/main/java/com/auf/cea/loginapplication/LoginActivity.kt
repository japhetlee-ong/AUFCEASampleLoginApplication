package com.auf.cea.loginapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import com.auf.cea.loginapplication.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)

        sharedPreferences = getSharedPreferences("MY_PREFERENCES", Context.MODE_PRIVATE)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_login) -> {

                if(binding.edtPassword.text.isEmpty()){
                    binding.edtPassword.error = "Required"
                    return
                }

                if(binding.edtUsername.text.isEmpty()){
                    binding.edtUsername.error = "Required"
                    return
                }

                val sUser = sharedPreferences.getString(USER_NAME,"")
                val sPassword = sharedPreferences.getString(USER_PASSWORD,"")

                if(sUser == binding.edtUsername.text.toString() && sPassword == binding.edtPassword.text.toString()){

                    if(binding.cbKeepMeLoggedIn.isChecked){
                        val editor = sharedPreferences.edit()
                        editor.putBoolean(KEEP_ME_LOGIN,true)
                        editor.apply()
                    }

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show()
                }

            }
            (R.id.btn_register) ->{
                val intent = Intent(this,RegisterUserActivity::class.java)
                startActivity(intent)
            }
        }
    }
}