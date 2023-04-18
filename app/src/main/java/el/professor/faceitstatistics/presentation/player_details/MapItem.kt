package el.professor.faceitstatistics.presentation.player_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import el.professor.faceitstatistics.domain.model.Map

@Composable
fun MapItem(
    map: Map,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 4.dp,
                horizontal = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(map.imgRegular),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(50.dp)
                .aspectRatio(1f)
                .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
                .clip(RoundedCornerShape(4.dp))
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Text(text = map.name, fontWeight = FontWeight.Bold)
            Text(text = "${map.mapStats.winrate}% Win Rate", color = Color.Gray, fontSize = 12.sp)
        }

        Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "ArrowForward", tint = Color.Gray, modifier = Modifier.size(12.dp))
    }
}