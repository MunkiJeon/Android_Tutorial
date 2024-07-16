package com.example.applemarket

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applemarket.databinding.ActivityDetailBinding
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    fun replaseDecimalFormat(num :Int):String{
        return DecimalFormat("#,###").format(num).toString()
    }

    private lateinit var binding: ActivityDetailBinding
    private lateinit var userData: PostItem

    override fun onCreate(savedInstanceState: Bundle?) {
        userData = intent.getParcelableExtra("userData")?: throw Exception("유저 정보가 없습니다.")

        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailIvBack.setOnClickListener {
            finish()
        }
        binding.detailIvPostPic.setImageResource(userData.picture)
        binding.detailTvSeller.text = userData.seller
        binding.detailTvAddress.text = userData.address
        binding.detailTvSeller.text = userData.seller

        binding.detailTvTitle.text = userData.title
        binding.detailTvComment.text = userData.comment

        binding.detailTvCost.text = replaseDecimalFormat(userData.cost) + "원"
    }
}