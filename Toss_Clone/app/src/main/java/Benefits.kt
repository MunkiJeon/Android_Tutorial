import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Benefits(
    val icon: Int,
    val title: String,
    val comment: String,
    val unresolved: Int = 0,
) : Parcelable
