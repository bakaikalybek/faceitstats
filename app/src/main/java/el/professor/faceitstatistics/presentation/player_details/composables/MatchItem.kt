package el.professor.faceitstatistics.presentation.player_details.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import el.professor.faceitstatistics.domain.model.Map
import el.professor.faceitstatistics.domain.model.MatchInfo
import java.time.Instant
import java.time.format.DateTimeFormatter

@Composable
fun MatchItem(
    matchInfo: MatchInfo,
    modifier: Modifier
) {
    val formatter = DateTimeFormatter.ISO_INSTANT
    val date = formatter.format(Instant.ofEpochSecond(matchInfo.startedAt.toLong()))
    Box(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.DarkGray),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = matchInfo.title!!, fontWeight = FontWeight.Bold)
                Text(text = matchInfo.status!!, fontWeight = FontWeight.Light, color = if (matchInfo.status == "WIN") Color.Green else Color.Red)
            }
        }
    }
}