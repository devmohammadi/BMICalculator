package com.example.bmicalculator.controller.activitys

import android.content.Intent
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageButton
import com.example.bmicalculator.R
import kotlinx.android.synthetic.main.activity_result_page.*

class ResultPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)

        var date = intent!!.extras!!
        if (date != null) {
            var BMI = date.getFloat("BMIResult")
            var gender = date.getString("Gender")
            tv_bmi.text = BMI.toString()
            if (BMI < 18.5) {
                tv_bmi.setTextColor(BLUE)
                tv_result.text = "شما دچار کاهش وزن هستید"
                tv_message.text = "عزیزم قصه نخور با ورزش حل میشه"
                if (gender == "female") image_result.setImageResource(R.drawable.underweightfemale)
                else image_result.setImageResource(R.drawable.underweightmale)

            } else if (BMI <= 18.5 || BMI < 25) {
                tv_bmi.setTextColor(GREEN)
                tv_result.text = "تبریک وزن شما نرمال است"
                tv_message.text = "بهتره ورزش رو روتین زندگیت کنی"
                if (gender == "female") image_result.setImageResource(R.drawable.normalfemale)
                else image_result.setImageResource(R.drawable.normalmale)

            } else if (BMI!! <= 25 || BMI!! < 30) {
                tv_bmi.setTextColor(YELLOW)
                tv_result.text = "شما اضافه وزن دارید"
                tv_message.text = "عزیزم بهتره از همین حالا ورزش رو شروع کنی"
                if (gender == "female") image_result.setImageResource(R.drawable.overweightfemale)
                else image_result.setImageResource(R.drawable.overweightmale)

            } else {
                tv_bmi.setTextColor(RED)
                tv_result.text = "شما دچار چاقی مفرط هستید"
                tv_message.text = "عزیزم بهتره زیر نظر پزشک یک رژیم مناسب به همراه ورزش روزانه داشته باشی"
                if (gender == "female") image_result.setImageResource(R.drawable.obesefemale)
                else image_result.setImageResource(R.drawable.obesemale)
            }
        }
    }

    fun BackOnClick(view: View) {
        view as ImageButton
        startMainActivity()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startMainActivity()
        }
        return super.onKeyDown(keyCode, event)
    }

    fun startMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
