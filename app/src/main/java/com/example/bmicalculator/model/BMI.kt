package com.example.bmicalculator.model


class BMI() {
    var id: Int? = null
    var name: String? = null
    var resultBmi: String? = null

    constructor(
        id: Int,
        name: String,
        resultBmi: String
    ) : this() {
        this.id = id
        this.name = name
        this.resultBmi = resultBmi

    }

}


