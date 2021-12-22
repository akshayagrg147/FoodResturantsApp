package com.meetSuccess.FoodResturant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.meetSuccess.FoodResturant.databinding.ActivityListItemAfterCategorySelectionBinding
import com.meetSuccess.FoodResturant.databinding.ActivitySignUpOtpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpOtp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpOtpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.enteredMobile.setOnClickListener{
//            binding.MobileNumberOutlinedTextField.visibility=View.GONE
//            binding.otpVerifyBtn.visibility=View.GONE
//            binding.otpLabel.setText( R.string.enter_otp)
//

      //  }
    }
}