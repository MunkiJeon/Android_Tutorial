package com.example.mycalculator
class SubstractOperation {
    var A = 0
    var B = 0
    constructor(numA: Number,numB: Number) {
        A = numA.toInt()
        B = numB.toInt()
    }
    fun subtract() :Int = A - B
}