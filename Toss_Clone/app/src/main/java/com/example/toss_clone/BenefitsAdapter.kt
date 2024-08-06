
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toss_clone.databinding.ItemBenefitsBinding
import java.text.DecimalFormat

class BenefitsAdapter(private val dataList: MutableList<String>, private val itemClickListener: (Int)-> Unit) : RecyclerView.Adapter<BenefitsAdapter.BenefitsViewHolder>() {
    fun replaseDecimalFormat(num: Int): String { //금액 포멧
        return DecimalFormat("#,###").format(num).toString()
    }

   inner class BenefitsViewHolder(val binding : ItemBenefitsBinding): RecyclerView.ViewHolder(binding.root){
       // 여기에 필요한 다른 뷰를 추가 가능

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitsViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: BenefitsViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}