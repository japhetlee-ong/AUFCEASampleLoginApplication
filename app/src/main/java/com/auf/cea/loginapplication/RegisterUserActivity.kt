package com.auf.cea.loginapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import com.auf.cea.loginapplication.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityRegisterUserBinding
    private var genderIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterUser.setOnClickListener(this)
        binding.rgSex.setOnCheckedChangeListener(this)

    }

    override fun onClick(p0: View?) {

        if(binding.edtUsername.text.isEmpty()){
            binding.edtUsername.error = "Required"
            return
        }

        if(binding.edtPassword.text.isEmpty()){
            binding.edtPassword.error = "Required"
            return
        }

        if(binding.edtFirstName.text.isEmpty()){
            binding.edtFirstName.error = "Required"
            return
        }

        if(binding.edtLastName.text.isEmpty()){
            binding.edtLastName.error = "Required"
            return
        }

        if(binding.edtAge.text.isEmpty()){
            binding.edtAge.error = "Required"
            return
        }

        if(binding.edtPassword.text.toString() != binding.edtConfirmPassword.text.toString()){
            binding.edtPassword.error = "Password does not match"
            binding.edtConfirmPassword.error = "Password does not match"
            return
        }

        if(genderIndex == -1){
            Toast.makeText(this,"Please specify your sex",Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this,ConfirmUserDetailsActivity::class.java)
        intent.putExtra("username",binding.edtUsername.text.toString())
        intent.putExtra("password",binding.edtPassword.text.toString())
        intent.putExtra("firstname", binding.edtFirstName.text.toString())
        intent.putExtra("lastname",binding.edtLastName.text.toString())
        intent.putExtra("age", binding.edtAge.text.toString())
        intent.putExtra("genderIndex",genderIndex)
        startActivity(intent)


    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val rb = binding.rgSex.findViewById<View>(p1)
        genderIndex = binding.rgSex.indexOfChild(rb)
    }
}