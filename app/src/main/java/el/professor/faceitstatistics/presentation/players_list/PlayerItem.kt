package el.professor.faceitstatistics.presentation.players_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import el.professor.faceitstatistics.domain.model.Player
import el.professor.faceitstatistics.R

@Composable
fun PlayerItem(
    player: Player,
    modifier: Modifier = Modifier
) {
    val resId = when (player.level) {
        "1" -> R.drawable.faceit1
        "2" -> R.drawable.faceit2
        "3" -> R.drawable.faceit3
        "4" -> R.drawable.faceit4
        "5" -> R.drawable.faceit5
        "6" -> R.drawable.faceit6
        "7" -> R.drawable.faceit7
        "8" -> R.drawable.faceit8
        "9" -> R.drawable.faceit9
        else -> R.drawable.faceit10
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.hsl(34f, 0.92f, 0.7f)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(modifier = modifier) {
            Text(
                text = player.nickname,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.background,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(resId),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
        }
    }
//    Row(
//        modifier = modifier,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Column(
//            modifier = Modifier.weight(1f)
//        ) {
//            Row(modifier = Modifier.fillMaxWidth()) {
//                Text(
//                    text = player.nickname,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    color = MaterialTheme.colorScheme.onBackground,
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1,
//                    modifier = Modifier.weight(1f)
//                )
//                Spacer(modifier = Modifier.width(4.dp))
////                )
//                Image(
//                    painter = painterResource(resId),
//                    contentDescription = "avatar",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(24.dp)
//                        .clip(CircleShape)
//                )
//            }
//        }
//    }
}