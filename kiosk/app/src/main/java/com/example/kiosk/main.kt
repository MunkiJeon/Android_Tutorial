package com.example.kiosk

/* lv0
[ 필요한 기능 ]
요구사항1: 메인 메뉴판 화면
: 메뉴 선택시 상세 메뉴화면으로 이동 [v]
> 메뉴판 ->
    01.버거->
        치킨 3.0
        포크 4.0
        비프 6.0
        치즈 2.5
    02.스낵&4이드->
        치즈스틱 2.0
        감자튀김 1.0
        치킨너겟 1.5
        스넥랩   2.5
    03.음료&커픠->
        사이다     1.0
        콜라       1.0
        아메리카노 1.5
        라떼       1.5
: 잘못된 번호 선택 시 예외처리[v]
: 프로그램 종료을 위한 번호 정의[v]
*/
fun main() {// 숫자 입력 및 화면 전환 관련


    print(Menu(0).strTrens(0))//초기화면
    var category = 0
    while (true){
        var orderNum = try { readLine()!!.toInt() }
        catch (e: NumberFormatException) {
            print("\n숫자로만 입력 해주세요\n번호 입력>> ")
            continue
        }
        catch (e: IndexOutOfBoundsException){
            print("\n제시된 숫자 중에 선택해 주세요\n번호 입력>> ")
            continue
        }

       when(orderNum as Int){
            0->{
                if (category == 0) {
                    print("키오스크를 종료 합니다.")
                    break
                }
                else{
                    category = 0
                    print( Menu(orderNum).strTrens(orderNum))
                }
            }
            1->{
                if (category == 0){
                    category = orderNum
                    print(Burger(category,orderNum).strTrens(orderNum))
                }
                else if (category == 99) {
                    Cart().payment()
                    break
                }
                else{ Burger(category,orderNum).addCart() }
            }
            2->{
                if (category == 0){
                    category = orderNum
                    print( Snack(category,orderNum).strTrens(orderNum))
                }
                else if (category == 99){
                    category = 0
                    print( Menu(orderNum).strTrens(orderNum))
                }
                else{ Snack(category,orderNum).addCart() }
            }
            3->{
                if (category == 0){
                    category = orderNum
                    print( Drink(category,orderNum).strTrens(orderNum))
                }
                else{ Drink(category,orderNum).addCart() }
            }
           4->{
               if (category == 0){
                   category = orderNum
                   print( Drink(category,orderNum).strTrens(orderNum))
               }
               else{ Drink(category,orderNum).addCart() }
           }
            99->{
                category = orderNum
                Cart().checkCart()
            }
           else->{print("\n제시된 숫자 중에 선택해 주세요\n번호 입력>> ")}
       }
    }
}
