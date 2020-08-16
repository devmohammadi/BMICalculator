package com.example.bmicalculator.controller.activitys

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.example.bmicalculator.model.BMI
import com.example.bmicalculator.R
import com.example.bmicalculator.model.datalayers.BMIHistoryDatabaseHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var height: Int = 150
    var weight: Int = 50
    var BMI: Float? = null
    var gender: String? = null
    var name: String? = null
    var dbHandler: BMIHistoryDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = BMIHistoryDatabaseHandler(this)
    }

    fun female(view: View) {
        btn_male.isChecked = false
        btn_male.setTextColor(Color.GRAY)
        btn_female.setTextColor(Color.WHITE)

    }

    fun male(view: View) {
        btn_female.isChecked = false
        btn_female.setTextColor(Color.GRAY)
        btn_male.setTextColor(Color.WHITE)
    }

    fun add(view: View) {
        view as Button
        when (view.id) {
            btn_height_plus.id -> {
                height = tv_height.text.toString().toInt()
                if (height != 250)
                    height += 1
                tv_height.text = height.toString()
            }
            btn_weight_plus.id -> {
                weight = tv_weight.text.toString().toInt()
                if (weight != 300)
                    weight += 1
                tv_weight.text = weight.toString()
            }
        }
        height = tv_height.text.toString().toInt()
        weight = tv_weight.text.toString().toInt()
    }

    fun min(view: View) {
        view as Button
        when (view.id) {
            btn_height_min.id -> {
                height = tv_height.text.toString().toInt()
                if (height != 50)
                    height -= 1
                tv_height.text = height.toString()
            }
            btn_weight_min.id -> {
                weight = tv_weight.text.toString().toInt()
                if (weight != 20)
                    weight -= 1
                tv_weight.text = weight.toString()
            }
        }
        height = tv_height.text.toString().toInt()
        weight = tv_weight.text.toString().toInt()
    }

    fun button_bmi(view: View) {
        name = tvName.text.toString()
        if ((btn_female.isChecked || btn_male.isChecked) && (!TextUtils.isEmpty(name))) {
            BMI = weight / (height.toFloat() * height.toFloat() / 10000)
            when (view.isClickable) {
                btn_female.isChecked -> gender = "female"
                btn_male.isChecked -> gender = "male"
            }
            var resultPage = Intent()
            resultPage = Intent(this, ResultPage::class.java)
            resultPage.putExtra("BMIResult", BMI!!)
            resultPage.putExtra("Gender", gender!!)

            var bmi = BMI()
            bmi.name = name
            bmi.resultBmi = BMI!!.toString()
            saveBmiToDatabase(bmi)

            startActivity(resultPage)
            finish()
        }

        if (!btn_male.isChecked && !btn_female.isChecked) {
            return Toast.makeText(this, "لطفا جنسیت خود را انتخاب کنید", Toast.LENGTH_SHORT).show()
        }
        if (TextUtils.isEmpty(name)) {
            return Toast.makeText(this, "لطفا نام خود را وارد کنید", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveBmiToDatabase(bmi: BMI) {
        dbHandler!!.createBmi(bmi)
    }

    fun historyOnclick(view: View) {
        view as ImageButton
        var historyIntent = Intent(this, HistoryActivity::class.java)
        startActivity(historyIntent)
        finish()
    }
}