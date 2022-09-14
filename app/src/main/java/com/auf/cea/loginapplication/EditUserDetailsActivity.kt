package com.auf.cea.loginapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.auf.cea.loginapplication.databinding.ActivityEditUserDetailsBinding

class EditUserDetailsActivity : AppCompatActivity(), View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityEditUserDetailsBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var genderIndex: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MY_PREFERENCES",Context.MODE_PRIVATE)
        val firstName = sharedPreferences.getString(USER_FIRST_NAME,"")
        val lastName  = sharedPreferences.getString(USER_LAST_NAME,"")
        val age = sharedPreferences.getString(USER_AGE,"")
        val gender = sharedPreferences.getInt(USER_GENDER,-1)

        val rbGender = binding.rgGender.getChildAt(gender) as RadioButton
        rbGender.isChecked = true

        binding.edtFirstName.setText(firstName)
        binding.edtLastName.setText(lastName)
        binding.edtAge.setText(age)

        binding.btnEditUser.setOnClickListener(this)
        binding.rgGender.setOnCheckedChangeListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            (R.id.btn_edit_user) ->{
                val editor = sharedPreferences.edit()
                editor.putString(USER_FIRST_NAME,binding.edtFirstName.text.toString())
                editor.putString(USER_LAST_NAME,binding.edtLastName.text.toString())
                editor.putString(USER_AGE,binding.edtAge.text.toString())
                editor.putInt(USER_GENDER,genderIndex)
                editor.apply()

                Toast.makeText(this,"Details has been edited",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val rb = binding.rgGender.findViewById<View>(p1)
        genderIndex = binding.rgGender.indexOfChild(rb)
    }
}