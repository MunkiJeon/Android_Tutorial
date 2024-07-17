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
import android.view.animation.AlphaAnimation
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    //화면요소 바인딩
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter

    // 뒤로가기 버튼 눌렀을때 실행되는 콜백메소드
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 뒤로가기 실행시 실행할 동작코드 구현하기! (앱종료, 다이얼로그 띄우기 등등)
            //다이얼 로그 정의
            MaterialAlertDialogBuilder(this@MainActivity)
                .setMessage("종료하시겠습니까??")
                .setNegativeButton("취소") { dialog, which -> Log.d("cansel", "취소누름") }
                .setPositiveButton("확인") { dialog, which -> finish() }
                .show()
        }
    }

    //알림 채널 만들기
    private fun createNotificationChannel(
        context: Context,
        importance: Int,
        showBadge: Boolean,
        name: String,
        description: String
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notification() {

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 26 버전 이상
            val channelId = "apple-channel"
            val channelName = "AppleMarket"
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

        } else {
            builder = NotificationCompat.Builder(this)
        }// 26 버전 이하

        val intent = Intent(this, DetailActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.drawable.icon_laugh)
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

        adapter = PostAdapter(PostData.dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val floatingFadeInAnimation = AlphaAnimation(0f, 1f).apply { duration = 500 }
        val floatingFadeOutAnimation = AlphaAnimation(1f, 0f).apply { duration = 500 }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d(
                    "scrolling",
                    "computeHorizontalScrollOffset() : ${recyclerView.computeVerticalScrollOffset()} mainBtnFloating.isVisible : ${binding.mainBtnFloating.isVisible}"
                )
                if (dy != 0 && recyclerView.computeVerticalScrollOffset() > 0 && !binding.mainBtnFloating.isVisible) {
                    binding.mainBtnFloating.isVisible = true
                    binding.mainBtnFloating.startAnimation(floatingFadeInAnimation)
                } else if (recyclerView.computeVerticalScrollOffset() == 0) {
                    binding.mainBtnFloating.isVisible = false
                    binding.mainBtnFloating.startAnimation(floatingFadeOutAnimation)
                }
            }
        })

        binding.mainBtnFloating.setOnClickListener {
            binding.recyclerView.scrollToPosition(0)
        }

        adapter.itemClick = object : PostAdapter.ItemClick {
            override fun onClick(view: View, postion: Int) {
                var detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra("userData", PostData.dataList[postion])
                Log.d("title", ">>누름<< ${PostData.dataList[postion].title}")
                startActivity(detailIntent)
            }

            override fun onPressed(view: View, postion: Int) {
                MaterialAlertDialogBuilder(this@MainActivity)
                    .setMessage("${PostData.dataList[postion].title} 삭제 하시겠습니까??")
                    .setNegativeButton("취소") { dialog, which -> Log.d("cansel", "취소누름") }
                    .setPositiveButton("확인") { dialog, which -> PostData.dataList.removeAt(postion); adapter.notifyDataSetChanged() }
                    .show()
            }
        }

        binding.mainIvNotification.setOnClickListener {
            binding.mainIvNotification
            notification()
        }

        // 뒤로가기를 onBackPressedDispatcher를 통해 등록
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}