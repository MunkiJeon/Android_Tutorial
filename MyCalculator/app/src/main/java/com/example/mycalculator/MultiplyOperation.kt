package com.example.mycalculator
class MultiplyOperation {
    var A = 0
    var B = 0
    constructor(numA: Number,numB: Number) {
        A = numA.toInt()
        B = numB.toInt()
    }
    fun multiply() :Int = A * B
}