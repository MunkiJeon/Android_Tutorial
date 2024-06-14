package com.example.kotlin_textrpg
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Random
import kotlin.concurrent.thread
class CashShop private constructor() {
    private val bowPrice = 150
    private val staffPrice = 120

    companion object {
        @Volatile private var instance: CashShop? = null
        @Volatile private var lottoStatus: String? = null
        @Volatile private var isFinish: Boolean? = null

        fun getInstance(): CashShop {
            // 외부에서 요청왔을때 instance가 null인지 검증
            if(instance == null) {
                // synchronized로 외부 쓰레드의 접근을 막음
                // 쓰레드는 다음챕터에서 소개합니다!
                // 쓰레드간의 객체상태 혼돈을 막기위해 사용한다고 이해해주세요
                synchronized(this) {
                    instance = CashShop()
                }
            }
            return instance!!
        }
    }

    fun purchaseWeapon(character:Character){
        if(character is Archer) {
            character?.run {
                if(money >= bowPrice) {
                    println("[구매 후 금액]: [${money} - ${bowPrice}] = ${money-bowPrice}")
                    money -= bowPrice
                    weapons.add("슈퍼 활")
                } else {
                    println("돈이 부족합니다.")
                }
            }
        } else if(character is Wizard) {
            character?.run {
                if(money >= staffPrice) {
                    println("[구매 후 금액]: [${money} - ${staffPrice}] = ${money-staffPrice}")
                    money -= staffPrice
                    weapons.add("슈퍼 스태프")
                } else {
                    println("돈이 부족합니다.")
                }
            }
        }
    }

    fun startLotto(character: Character, selectHorse: String) {
        var random = Random()
        val finalDst = 100
        isFinish = false
        thread(start = true) {
            var currentPosition = 0
            while(currentPosition < finalDst && isFinish == false) {
                currentPosition += (random.nextInt(5) + 1)

                println("1번말 현재 위치: ${currentPosition}m")
                runBlocking {
                    launch {
                        delay(1000)
                    }
                }
            }
            if(lottoStatus == null || lottoStatus != "two") {
                lottoStatus = "one"
                isFinish = true
                println("1등: ${lottoStatus}말")

                if(lottoStatus.equals(selectHorse)) {
                    println("축하합니다! 당첨!")
                    println("상금으로 1만원 지급")

                    // 왜 이렇게밖에 작성했는지 이유를 생각하고
                    // 코드를 개선하기
                    if(character is Archer) {
                        character?.run {
                            money += 10000
                        }
                    } else if(character is Wizard) {
                        character?.run {
                            money += 10000
                        }
                    }
                }
            }

        }

        thread(start = true) {
            var currentPosition = 0
            while(currentPosition < finalDst && isFinish == false) {
                currentPosition += (random.nextInt(10) + 1)

                println("2번말 현재 위치: ${currentPosition}m")
                runBlocking {
                    launch {
                        delay(1000)
                    }
                }
            }
            if(lottoStatus == null || lottoStatus != "one") {
                lottoStatus = "two"
                isFinish = true
                println("1등: ${lottoStatus}말")
                if(lottoStatus.equals(selectHorse)) {
                    println("축하합니다! 당첨!")
                    println("상금으로 1만원 지급")

                    // 왜 이렇게밖에 작성했는지 이유를 생각하고
                    // 코드를 개선하기
                    if(character is Archer) {
                        character?.run {
                            money += 10000
                        }
                    } else if(character is Wizard) {
                        character?.run {
                            money += 10000
                        }
                    }
                }
            }

        }
    }
}
fun main() {
    val worldName = "스코월드"

    var myName = inputMyInfo("name").toString()

    var myAge = inputMyInfo("age").toString().toInt()

    var myJob = inputMyInfo("job").toString()

    var myGender = inputMyInfo("gender").toString()

    var myMoney = inputMyInfo("money").toString().toInt()

    var myHp = inputMyInfo("hp").toString().toInt()

    var isNamePass = true
    var isAgePass = true
    var isJobPass = true

    var names = mutableListOf("참새", "꿩", "비둘기")
    for(name in names) {
        if(myName == name) {
            println("중복된 이름이 존재합니다.")
            isNamePass = false
            break
        }
    }

    if(myAge < 12) {
        println("12세 미만은 이용할 수 없습니다.")
        isAgePass = false
    }
    if(myJob == "전사") {
        println("일시적으로 전사를 이용할 수 없습니다.")
        isJobPass = false
    }

    // 모든 조건을 통과한 경우에만 환영
    if(isNamePass && isAgePass && isJobPass) {
        // 새로 이름 추가
        names.add(myName)
        displayInfo(worldName, myName, myAge, myJob)

        if(myJob == "마법사") {
            println("마법사는 초기 mp도 입력해주세요")
            var myMp = inputMyInfo("mp").toString().toInt()
            var myCharacter = Wizard(myName, myAge, myGender, myMoney, myHp, myMp)

            while(true) {
                println("[1] 슬라임동굴, [2] 좀비마을, [3] 캐쉬샵, [4] 종료")
                var selectNumber= inputMyInfo("selectNumber").toString().toInt()

                when(selectNumber) {
                    1 -> {
                        selectWorld(1, myCharacter)
                    }
                    2 -> {
                        selectWorld(2, myCharacter)
                    }
                    3 -> {
                        openCashShop(myCharacter)
                    }
                    4 -> {
                        println("게임 종료")
                        break
                    }
                    else -> {
                        break
                    }
                }
            }
        } else if(myJob == "궁수") {
            println("궁수를 선택했군요")
            var myCharacter = Archer(myName, myAge, myGender, myMoney, myHp)

            while(true) {
                println("[1] 슬라임동굴, [2] 좀비마을, [3] 캐쉬샵, [4] 로또 [5] 종료")
                var selectNumber= inputMyInfo("selectNumber").toString().toInt()

                when(selectNumber) {
                    1 -> {
                        selectWorld(1, myCharacter)
                    }
                    2 -> {
                        selectWorld(2, myCharacter)
                    }
                    3 -> {
                        openCashShop(myCharacter)
                    }
                    4 -> {
                        var selectHorse = inputMyInfo("selectHorse").toString()
                        startLotto(myCharacter, selectHorse)
                    }
                    5 -> {
                        println("게임 종료")
                        break
                    }
                    else -> {
                        break
                    }
                }
            }
        }
    }
}

fun displayInfo(worldName:String, myName:String, myAge:Int, myJob:String) {
    println("==================${worldName}에 오신것을 환영합니다==================")
    println("당신의 정보는 다음과 같습니다.")
    println("이름: ${myName}입니다.")
    println("나이: ${myAge}입니다.")
    println("직업: ${myJob}입니다.")
    println("모험을 떠나 볼까요?")
}

fun selectWorld(selectWorld:Int, myCharacter: Character) {
    if(selectWorld == 1) { // 슬라임 던전
        if(myCharacter is Archer) {
            var slime1 = Slime("초록슬라임", "초록", 30.2, 200, 10)
            slime1.attack()
            myCharacter.windArrow()
            slime1.poison()
        } else if(myCharacter is Wizard) {
            var slime1 = Slime("파랑슬라임", "파랑", 30.2, 200, 10)
            slime1.attack()
            myCharacter.fireBall()
            slime1.poison()
        }
    } else if(selectWorld == 2) { // 좀비 던전
        if(myCharacter is Archer) {
            var zombie1 = Zombie("파랑좀비", "파랑", 142.2, 500, 25)
            zombie1.virus()
            myCharacter.windJump("건물1")

        } else if(myCharacter is Wizard) {
            var zombie1 = Zombie("파랑좀비", "파랑", 142.2, 500, 25)
            zombie1.virus()
            myCharacter.teleport(10, 20)
        }

    }
}


fun inputMyInfo(type:String): Any? {
    return when(type) {
        "name" -> {
            println("이름을 입력해주세요")
            while(true) {
                try {
                    var originName = readLine()
                    if(originName?.first() != '_' && originName?.first() != '!') {
                        return originName
                    } else {
                        println("이름을 다시 입력해주세요")
                    }
                } catch(e:Exception) {
                    println("이름을 다시 입력해주세요")
                }
            }
        }
        "age" -> {
            println("나이를 입력해주세요")
            while(true) {
                try {
                    var originAge:String? = readLine()
                    return originAge?.toInt() ?: -1
                } catch(e:Exception) {
                    println("나이를 다시 입력해주세요")
                }
            }
        }
        "job" -> {
            println("직업을 입력해주세요")
            while(true) {
                try {
                    var originName = readLine()
                    if(originName?.equals("궁수") == true || originName?.equals("마법사") == true) {
                        return originName
                    } else {
                        println("직업을 다시 입력해주세요")
                    }
                } catch(e:Exception) {
                    println("직업을 다시 입력해주세요")
                }
            }
        }
        "gender" -> {
            println("성별을 입력해주세요")
            while(true) {
                try {
                    var originGender = readLine()
                    if(originGender?.equals("남") == true || originGender?.equals("여") == true) {
                        return originGender
                    } else {
                        println("성별을 다시 입력해주세요")
                    }
                } catch(e:Exception) {
                    println("성별을 다시 입력해주세요")
                }
            }
        }
        "money" -> {
            println("초기자본을 입력해주세요")
            while(true) {
                try {
                    var originMoney:String? = readLine()
                    return originMoney?.toInt() ?: -1
                } catch(e:Exception) {
                    println("초기자본을 다시 입력해주세요")
                }
            }
        }
        "hp" -> {
            println("초기체력을 입력해주세요")
            while(true) {
                try {
                    var originHp:String? = readLine()
                    return originHp?.toInt() ?: -1
                } catch(e:Exception) {
                    println("초기체력을 다시 입력해주세요")
                }
            }
        }
        "mp" -> {
            println("초기마나를 입력해주세요")
            while(true) {
                try {
                    var originMp:String? = readLine()
                    return originMp?.toInt() ?: -1
                } catch(e:Exception) {
                    println("초기마나를 다시 입력해주세요")
                }
            }
        }
        "selectWorld" -> {
            println("월드를 선택해주세요")
            while(true) {
                try {
                    var selectWorld:String? = readLine()
                    return selectWorld?.toInt() ?: -1
                } catch(e:Exception) {
                    println("월드를 다시 선택해주세요")
                }
            }
        }
        "selectNumber" -> {
            println("번호를 선택해주세요")
            while(true) {
                try {
                    var selectNumber:String? = readLine()
                    return selectNumber?.toInt() ?: -1
                } catch(e:Exception) {
                    println("번호를 다시 선택해주세요")
                }
            }
        }
        "selectHorse" -> {
            println("말의 이름을 입력해주세요")
            while(true) {
                try {
                    var originName = readLine()
                    if(originName?.equals("one") == true || originName?.equals("two") == true) {
                        return originName
                    } else {
                        println("말의 이름을 다시 입력해주세요")
                    }
                } catch(e:Exception) {
                    println("말의 이름을 다시 입력해주세요")
                }
            }
        }
        else -> {
            return "no"
        }
    }
}

fun openCashShop(character: Character) {
    var cashShop = CashShop.getInstance()

    if(character is Archer) {
        println("구매전 무기: ${character.weapons}")
        cashShop.purchaseWeapon(character)
        println("구매후 무기: ${character.weapons}")
    } else if(character is Wizard) {
        println("구매전 무기: ${character.weapons}")
        cashShop.purchaseWeapon(character)
        println("구매후 무기: ${character.weapons}")
    }
}

fun startLotto(character: Character, horse: String) {
    var cashShop = CashShop.getInstance()

    cashShop.startLotto(character, horse)
}