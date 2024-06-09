package com.example.mycalculator
class AddOperation {
    var A = 0
    var B = 0
    constructor(numA: Number,numB: Number) {
        A = numA.toInt()
        B = numB.toInt()
    }
    fun plus() :Int = A + B
}