import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sharkdroid.houseme.presentation.payment.component.models.PaymentImage

@Composable
fun PaymentImageRow(paymentImage: PaymentImage){
    Row(modifier = Modifier.padding(all = 2.dp).fillMaxWidth()) {
        Image(painter = painterResource(id = paymentImage.payImg),
            contentDescription = null,
            modifier = Modifier.height(70.dp).width(90.dp).clip(shape = RoundedCornerShape(12.dp))


        )
    }
}