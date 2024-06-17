package com.example.kiosk

object g_property{
    //금액 리스트
    var priseList = listOf<List<Double>>(
        listOf(3.0,4.0,6.0,2.5),//버거 가격
        listOf(2.0,1.0,1.5,2.5),//사이드 가격
        listOf(1.0,1.0,1.5,1.5),//음료 가격
    )
    //제품 리스트
    var productList = listOf<List<String>>(
        listOf("[1] 치킨 버거", "[2] 포크 버거", "[3] 비프 버거",   "[4] 치즈 버거"),//버거 제품명
        listOf("[1] 치즈스틱",  "[2] 감자튀김",  "[3] 치킨너겟",    "[4] 스낵랩"),//사이드 제품명
        listOf("[1] 슷포라잇t", "[2] 코-크콜라", "[3] A메리카노",   "[4] 카페라떼"),//음료 제품명
    )
    //메뉴 종류
    var categoryList = listOf<String>("[1] 버거버거","[2] 스낵&4이드","[3] 음료&커픠",)
    //장 바구니
    var cartList: MutableMap<String,Double> = mutableMapOf()

//    fun addCart(_order : Int , _categoryCode : Int) {
//        var prd : String = productList[_categoryCode][_order]
//        var pri : Double = priseList[_categoryCode][_order]
//        cartList += Pair(prd,pri)
//        cartList[0].second = cartList[0].second.toDouble() + pri
//        println("${prd} 를 장바구니에 담았습니다. \n가격:${pri}원 ${cartList[0]}")
//    }
}
val menuTitle = listOf(
            "------[ PRO기's Shop ]-----\n", "----------------\n",        //0 1
            "[99] 장바구니\n[0] 뒤로가기\n번호 입력>> ",         //2
            "결제하시겠습니까?\n[1] 결제 [2] 취소\n번호 입력>> ", //3
            "금액을 투입해 주세요\n금액 입력>> ", //4
            "결제가 완료 되었습니다. 감사합니다.", //5
            "금액이 부족합니다. 더 투입해 주세요.", //6
            )

open class Menu (val _category: Int) {
    //대 메뉴
    // - 메뉴 뜨우는 기능만 존재
    //필요한 프로퍼티: 제품메뉴 / 장바구니 / 취소
    var strMenus :String =""
    open fun strTrens(_category: Int) : String{
        strMenus += menuTitle[0]
        for (i in g_property.categoryList){ strMenus += i+"\n" }
        strMenus += menuTitle[1] + menuTitle[2]
        return strMenus
    }
}

open class SubMenu(_category: Int, var _orderNum: Int) : Menu(_category) {
     var prises : List<Double>  = g_property.priseList[_category-1]
     var products : List<String> = g_property.productList[_category-1]

    override fun strTrens(_category: Int) : String {
        super.strMenus += menuTitle[0]
        super.strMenus += g_property.categoryList[_category-1] +"\n"
        for ((index,i) in products.withIndex()){ super.strMenus += i +" | w "+ prises[index]+"천원\n" }
        super.strMenus += menuTitle[1] + menuTitle[2]
        return super.strMenus
    }
    open fun addCart() {
        var pri : Double = prises[_orderNum-1]
        var prd : String = products[_orderNum-1]
        if(g_property.cartList.containsKey(prd)){pri += g_property.cartList.get(prd)!!}
        g_property.cartList.put(prd, pri)
        print("${prd} 를 장바구니에 담았습니다. \n| 총 가격 : ${g_property.cartList.values.sum()}천원\n${menuTitle[1]}")
        //if(_category == 1){} 메뉴 추가
        print(strTrens(_category))
    }
}
//----------------------------------------------
class Burger(_category : Int, _orderNum: Int) : SubMenu(_category, _orderNum) {
//    fun setMenu(){
//        print("사이드 와 음료 추가 하시겠어요?\n [1] 예  [2] 아니요")
//        for (i in 2..3){
//            var orderNum = try { readLine()!!.toInt() }
//            catch (e: NumberFormatException) { print("\n숫자로만 입력 해주세요\n번호 입력>> ")}
//            catch (e: IndexOutOfBoundsException){ print("\n제시된 숫자 중에 선택해 주세요\n번호 입력>> ") }
//
//        }
//    }
}
class Snack(_category : Int, _orderNum: Int) : SubMenu(_category, _orderNum){
    init {
    }
    fun sauce(){

    }
}
class Drink(_category : Int, _orderNum: Int) : SubMenu(_category, _orderNum){
    init {
    }
    fun sizeUp(){

    }
}
class Cart (){
    fun checkCart(){
        var strCart = menuTitle[0] + "| 제품명       | 금액    |\n"
        for (i in g_property.cartList){
            strCart += "| "+i.key +"        | " + i.value +"  |\n"
        }
        strCart += "| 총금액   ${g_property.cartList.values.sum()}천원    |" + menuTitle[3]
        print(strCart)
    }
    fun payment(){
        var strCart = menuTitle[4]
        while (true){
            print(strCart)
            var cash = try { readLine()!!.toDouble() }
            catch (e: NumberFormatException) {
                print("\n숫자로만 입력 해주세요\n금액 입력>> ")
                continue
            }
            if (cash as Double >= g_property.cartList.values.sum()) {
                print(menuTitle[5] + "\n 거스름돈 : ${cash - g_property.cartList.values.sum()}천원")
                break
            }
            else menuTitle[6]
        }
    }
}
