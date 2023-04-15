package el.professor.faceitstatistics.presentation.player_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsItem(
    title: String,
    value: String
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(170.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(18.dp))
            .background(color = Color.hsl(34f, 0.92f, 0.7f)),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.End
            )
        }
    }
}