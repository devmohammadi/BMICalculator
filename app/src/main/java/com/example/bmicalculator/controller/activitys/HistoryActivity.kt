package com.example.bmicalculator.controller.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bmicalculator.controller.adapter.Adapter
import com.example.bmicalculator.R
import com.example.bmicalculator.model.BMI
import com.example.bmicalculator.model.datalayers.BMIHistoryDatabaseHandler
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    lateinit var layoutManager: LinearLayoutManager
    var dbHandler: BMIHistoryDatabaseHandler? = null
    var bmiList: ArrayList<BMI>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        layoutManager = LinearLayoutManager(this)
        dbHandler = BMIHistoryDatabaseHandler(this)
        bmiList = ArrayList()
        getAllDtaFromDatabase()
    }

    private fun getAllDtaFromDatabase() {
        bmiList = dbHandler!!.read()
        bmiList!!.reverse()
        adapter = Adapter(this, bmiList!!)
        list_bmi.layoutManager = layoutManager
        list_bmi.setHasFixedSize(true)
        list_bmi.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }

    fun BackClick(view: View) {
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