package com.example.applemarket

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    //화면요소 바인딩
    private lateinit var binding: ActivityMainBinding

    //다이얼 로그 정의
    private fun materialNegativePositiveDialog() {
        MaterialAlertDialogBuilder(this@MainActivity)
            .setMessage("종료하시겠습니까??")
            .setNegativeButton("취소"){dialog, which -> Log.d("cansel","취소누름")}
            .setPositiveButton("확인") { dialog, which ->
                finish()
            }
            .show()
    }
    // 뒤로가기 버튼 눌렀을때 실행되는 콜백메소드
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로가기 실행시 실행할 동작코드 구현하기! (앱종료, 다이얼로그 띄우기 등등)
            materialNegativePositiveDialog()
        }
    }

    //알림 채널 만들기
    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notification(){

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val builder: NotificationCompat.Builder
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 26 버전 이상
            val channelId="apple-channel"
            val channelName="AppleMarket"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                // 채널에 다양한 정보 설정
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }
            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)

        }else { builder = NotificationCompat.Builder(this) }// 26 버전 이하

        val intent = Intent(this, DetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다.")
        }


        manager.notify(11, builder.build())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = mutableListOf<PostItem>().apply {
//            PostItem(var picture: Int, var title: String, var localInfo: String, var cost: Int)
            add(PostItem(R.drawable.sample1,"산진 한달된 선풍기 팝니다","서울 서대문구 창천동",1000, "대현동","이사가서 필요가 없어졌어요 급하게 내놓습니다",13,25))
            add(PostItem(R.drawable.sample2,"김치냉장고","인천 계양구 귤현동",20000, "안마담","이사로인해 내놔요",8,28))
            add(PostItem(R.drawable.sample3,"샤넬 카드지갑","수성구 범어동",10000, "코코유","고퀄지갑이구요\n사용감이 있어서 싸게 내어둡니다",23,5))
            add(PostItem(R.drawable.sample4,"금고","해운대구 우제2동",10000, "Nicole","금고\n떼서 가져가야함\n대우월드마크센텀\n미국이주관계로 싸게 팝니다",14,17))
            add(PostItem(R.drawable.sample5,"갤럭시Z플립3 팝니다","연제구 연산제8동",150000, "절명","갤럭시 Z플립3 그린 팝니다\n항시 케이스 씌워서 썻고 필름 한장챙겨드립니다\n화면에 살짝 스크래치난거 말고 크게 이상은없습니다!",22,9))
            add(PostItem(R.drawable.sample6,"프라다 복조리백","수원시 영통구 원천동",50000, "미니멀하게","까임 오염없고 상태 깨끗합니다\n정품여부모름",25,16))
            add(PostItem(R.drawable.sample7,"울산 동해오션뷰 60평 복층 펜트하우스 1일 숙박권 펜션 힐링 숙소 별장","남구 옥동",150000, "굿리치","울산 동해바다뷰 60평 복층 펜트하우스 1일 숙박권\n(에어컨이 없기에 낮은 가격으로 변경했으며 8월 초 가장 더운날 다녀가신 분 경우 시원했다고 잘 지내다 가셨습니다)\n1. 인원: 6명 기준입니다. 1인 10,000원 추가요금\n2. 장소: 북구 블루마시티, 32-33층\n3. 취사도구, 침구류, 세면도구, 드라이기 2개, 선풍기 4대 구비\n4. 예약방법: 예약금 50,000원 하시면 저희는 명함을 드리며 입실 오전 잔금 입금하시면 저희는 동.호수를 알려드리며 고객님은 예약자분 신분증 앞면 주민번호 뒷자리 가리시거나 지우시고 문자로 보내주시면 저희는 카드키를 우편함에 놓아 둡니다.\n5. 33층 옥상 야외 테라스 있음, 가스버너 있음\n6. 고기 굽기 가능\n7. 입실 오후 3시, 오전 11시 퇴실, 정리, 정돈 , 밸브 잠금 부탁드립니다.\n8. 층간소음 주의 부탁드립니다.\n9. 방3개, 화장실3개, 비데 3개\n10. 저희 집안이 쓰는 별장입니다.",142,54))
            add(PostItem(R.drawable.sample8,"샤넬 탑핸들 가방","동래구 온천제2동",180000, "난쉽","샤넬 트랜디 CC 탑핸들 스몰 램스킨 블랙 금장 플랩백 !\n \n색상 : 블랙n사이즈 : 25.5cm * 17.5cm * 8cm\n구성 : 본품더스트\n\n급하게 돈이 필요해서 팝니다 ㅠ ㅠ",31,7))
            add(PostItem(R.drawable.sample9,"4행정 엔진분무기 판매합니다."," 원주시 명륜2동",30000, "알뜰한", "3년전에 사서 한번 사용하고 그대로 둔 상태입니다. 요즘 사용은 안해봤습니다. 그래서 저렴하게 내 놓습니다. 중고라 반품은 어렵습니다.\n",7,28))
            add(PostItem(R.drawable.sample10,"4셀린느 버킷 가방.","중구 동화동",190000, "똑태현", "22년 신세계 대전 구매입니당\n셀린느 버킷백\n구매해서 몇번사용했어요\n까짐 스크래치 없습니다.\n타지역에서 보내는거라 택배로 진행합니당!",40,6))
        }

        var adapter = PostAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.itemClick = object : PostAdapter.ItemClick{
            override fun onClick(view: View, positon: Int) {
                var detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra("userData", dataList[positon])
                Log.d("title",">>누름<< ${dataList[positon].title}")
                startActivity(detailIntent)
            }
        }

        binding.mainIvNotification.setOnClickListener {
            notification()
        }

        // 뒤로가기를 onBackPressedDispatcher를 통해 등록
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}