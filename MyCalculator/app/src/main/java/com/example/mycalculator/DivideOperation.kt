package com.example.mycalculator
class DivideOperation {
    var A = 0.0
    var B = 0.0
    constructor(numA: Number,numB: Number) {
        A = numA.toDouble()
        B = numB.toDouble()
    }
    fun divide() :Double = A / B
}