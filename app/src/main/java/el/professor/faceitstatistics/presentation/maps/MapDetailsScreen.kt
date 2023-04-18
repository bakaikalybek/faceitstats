package el.professor.faceitstatistics.presentation.maps

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
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
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import el.professor.faceitstatistics.domain.model.Map
import el.professor.faceitstatistics.presentation.player_details.StatisticsItem
import el.professor.faceitstatistics.ui.theme.DarkBlue
import el.professor.faceitstatistics.ui.theme.GoogleSans

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun MapDetailsScreen(
    map: Map,
    navigator: DestinationsNavigator
) {
    val stats = map.mapStats
    Scaffold(
        topBar = {

            CenterAlignedTopAppBar(
                title = { Text(text = map.name, fontFamily = GoogleSans, fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() })
                    {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "backButton")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DarkBlue
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Image(
                painter = rememberAsyncImagePainter(map.imgRegular),
                contentDescription = "Map image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp, top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticsItem(title = "Matches", value = stats.matches, modifier = Modifier.weight(1f).padding(end = 8.dp))
                StatisticsItem(title = "Win Rate", value = "${stats.winrate}%", modifier = Modifier.weight(1f).padding(start = 8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticsItem(title = "Avg. Kills", value = stats.avgKills, modifier = Modifier.weight(1f).padding(end = 8.dp))
                StatisticsItem(title = "Avg. Deaths", value = stats.avgDeaths, modifier = Modifier.weight(1f).padding(start = 8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticsItem(title = "Avg. Assists", value = stats.avgAssists, modifier = Modifier.weight(1f).padding(end = 8.dp))
                StatisticsItem(title = "Avg. Headshots %", value = stats.avgHeadshots, modifier = Modifier.weight(1f).padding(start = 8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticsItem(title = "Avg. K/D Ratio", value = stats.avgKdRatio, modifier = Modifier.weight(1f).padding(end = 8.dp))
                StatisticsItem(title = "Avg. K/R Ratio", value = stats.avgKrRatio, modifier = Modifier.weight(1f).padding(start = 8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticsItem(title = "Triple Kills", value = stats.tripleKills, modifier = Modifier.weight(1f).padding(end = 8.dp))
                StatisticsItem(title = "Quadro Kills", value = stats.quadroKills, modifier = Modifier.weight(1f).padding(start = 8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatisticsItem(title = "Total Ace", value = stats.pentaKills, modifier = Modifier.weight(1f).padding(bottom = 16.dp))
            }

        }
    }
}