import com.example.toss_clone.R
import java.time.LocalDateTime

data class Account(
    val aIcon: Int,//계좌 아이콘
    val aName: String,//계좌명
    val aNumber: String,//계좌번호
    val aBalance: Int,//총액
    val aList: MutableList<Triple<String, Int, LocalDateTime>>//거래처명,금액,날짜
)

var aaccountList = mutableListOf<Account>()
fun aaccountListInit() {
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
            Account(
                R.drawable.logo_toss_symbol_fill,
                "토스뱅크 통장",
                "123456789-123456789-1234",
                1573717,
                mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
            )
            )
    aaccountList.add(
            Account(
                R.drawable.logo_toss_symbol_fill,
                "토스뱅크 통장",
                "123456789-123456789-1234",
                1573717,
                mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
            )
            )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
    aaccountList.add(
        Account(
            R.drawable.logo_toss_symbol_fill,
            "토스뱅크 통장",
            "123456789-123456789-1234",
            1573717,
            mutableListOf(Triple("이화면", 100000, LocalDateTime.of(2023, 7, 1, 10, 10, 50))),
        )
    )
}