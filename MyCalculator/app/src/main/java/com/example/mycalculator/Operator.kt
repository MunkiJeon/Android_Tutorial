package com.example.mycalculator

class Operator{
    fun plus(numA :Double, numB :Double) :Double = numA + numB
    fun subtract(numA :Double, numB :Double) :Double = numA - numB
    fun multiply(numA :Double, numB :Double) :Double = numA * numB
    //나누기와 나머지는 연산후 실수로 변환
    fun divide(numA :Double, numB :Double) :Double = numA / numB
    fun remain(numA :Double, numB :Double) :Double = numA % numB
}