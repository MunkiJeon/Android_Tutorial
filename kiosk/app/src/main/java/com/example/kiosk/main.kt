package com.example.kiosk

/* lv0
[ 필요한 기능 ]
요구사항1: 메인 메뉴판 화면
: 메뉴 선택시 상세 메뉴화면으로 이동 [v]
> 메뉴판 ->
    01.버거->
        치킨
        포크
        비프
        치즈
    02.스낵&4이드->
        치즈스틱
        감자튀김
        너겟
        스넥랩
    03.음료&커픠->
        사이다
        콜라
        아메리카노
        라떼
: 잘못된 번호 선택 시 예외처리[v]
: 프로그램 종료을 위한 번호 정의[v]
*/
fun main() {
    //lv1
    var orderPageNum = 0
    var order = 0

    var menu = Pair<Array<String>,Array<String>>(
        //메뉴
        arrayOf("----[메뉴판]----\n" +
                "[1] 버거버거\n" +
                "[2] 스낵&4이드\n" +
                "[3] 음료&커픠\n" +
                "---------------\n" +
//                "[99] 장바구니" +
                "[0] 취소" +
                "번호 입력>> "),
        //상세 메뉴
        arrayOf("[1]. 치킨 버거 | W 3.0\n" + "[2]. 포크 버거 | W 4.0\n" + "[3]. 비프 버거 | W 5.0\n" + "[4]. 치즈 버거 | W 2.0\n" + "[0]. 뒤로\n" +
                "번호 입력>> ",
            "[1]. 치즈스틱 | W 2.0\n" + "[2]. 감자튀김 | W 1.0\n" + "[3]. 스낵랩 | W 2.5\n" + "[4]. 치킨너겟 | W 1.5\n" + "[0]. 뒤로\n" +
                    "번호 입력>> ",
            "[1]. 사이다 | W 1.0\n" + "[2]. 콜라 | W 1.0\n" + "[3]. 아메리카노 | W 1.5\n" + "[4]. 카페라떼 | W 1.5\n" + "[0]. 뒤로\n" +
                    "번호 입력>> "))

    print(menu.first.toList()[order])
    while (true){
        try {
            order = readln().toInt()
            if (order-1 <0)  print(menu.first.toList()[0])
            else if()
            else print(menu.second.toList()[order-1])
        }
        catch (e: NumberFormatException) { print("\n숫자로만 입력 해주세요\n번호 입력>> ")}
        catch (e:IndexOutOfBoundsException){ print("\n제시된 숫자 중에 선택해 주세요\n번호 입력>> ")}
    }
}
