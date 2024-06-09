package com.example.mycalculator

import kotlin.math.roundToInt

fun main() {
    var run = true
    var numB = 0
    print("계산할 숫자 :")
    var numA = readLine()!!.toInt()
    while (run){
        println("\n$numA 에 어떤 연산 하시겠습니까? \n >> 1. + | 2. - | 3. * | 4. / | 5. % |  -1. 종료 <<")
        var operator = readLine()

        if (operator == "-1" || operator == "종료"){ run = false;}
        else{
            print("계산할 숫자 :")
            numB = readLine()!!.toInt()
        }

        when(operator){
            "1","+" ->{
//                println("= ${Operator().plus(numA,numB)}")
//                numA = Operator().plus(numA,numB)
                numA = AddOperation(numA,numB).plus()
                println("= ${numA}")
            }
            "2","-" ->{
//                println("= ${Operator().subtract(numA,numB)}")
//                numA = Operator().subtract(numA,numB)
                numA = SubstractOperation(numA,numB).subtract()
                println("= ${numA}")
            }
            "3","*" ->{
//                println("= ${Operator().multiply(numA,numB)}")
//                numA = Operator().multiply(numA,numB)
                numA = MultiplyOperation(numA,numB).multiply()
                println("= ${numA}")
            }
            "4","/" ->{
//                println("= ${Operator().divide(numA,numB)}")
//                numA = Operator().divide(numA,numB)
                numA = DivideOperation(numA,numB).divide().roundToInt()
                println("= ${numA}")
            }
            "5","%" ->{
                numA = Operator().remain(numA.toDouble(),numB.toDouble()).roundToInt()
                println("= ${numA}")
            }
            "-1","종료" ->{
                println("-------- 계산기를 종료 합니다 --------")
            }
            else ->{ println("잘못된 값을 입력 했습니다: ${operator}") }
        }
    }

}