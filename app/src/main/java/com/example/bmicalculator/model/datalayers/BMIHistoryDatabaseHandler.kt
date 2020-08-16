package com.example.bmicalculator.model.datalayers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.bmicalculator.model.*

class BMIHistoryDatabaseHandler(private var mContext: Context) : SQLiteOpenHelper(
    mContext, DATABASE_NAME, null, DAtABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_BMI_DATABASE = "CREATE TABLE $TABLE_NAME" +
                "($KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_NAME TEXT," +
                "$KEY_RESULT TEXT)"

        db!!.execSQL(CREATE_BMI_DATABASE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        //create new table
        onCreate(db)
    }


    //CRUD -> Create , READ , UPDATE , DELETE


    //create
    fun createBmi(bmi: BMI) {
        val db: SQLiteDatabase = writableDatabase

        var values = ContentValues()
        values.put(KEY_NAME, bmi.name)
        values.put(KEY_RESULT, bmi.resultBmi)

        db.insert(TABLE_NAME, null, values)
        db.close()

        Log.d("INSERT BMI", "SUCCESS")
    }

    //read
    fun read(): ArrayList<BMI> {
        val db: SQLiteDatabase = readableDatabase
        var list: ArrayList<BMI> = ArrayList()
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var bmi = BMI()
                bmi.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                bmi.name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                bmi.resultBmi = cursor.getString(cursor.getColumnIndex(KEY_RESULT))
                list.add(bmi)
            } while (cursor.moveToNext())
        }
        return list
    }

    //delete
    fun deleteBmi(id: Int) {
        val db: SQLiteDatabase = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(id.toString()))
        db.close()
        Log.d("DELETE BMI", "SUCCESS")

    }
}