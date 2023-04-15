package el.professor.faceitstatistics.presentation.player_details

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import el.professor.faceitstatistics.R
import el.professor.faceitstatistics.domain.model.Player
import el.professor.faceitstatistics.ui.theme.DarkBlue
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun PlayerDetailsScreen(
    player: Player,
    navigator: DestinationsNavigator,
    viewModel: PlayerDetailsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    if (state.error == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            state.playerDetails?.let { playerDetails ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "backButton")
                    }
                    Text(text = "${playerDetails.nickname}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Image(
                        painter = rememberAsyncImagePainter(playerDetails.avatar),
                        contentDescription = "avatar",
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        viewModel.onEvent(
                                            PlayerDetailsEvents.OnPlayerSelectedFavorite(player = player)
                                        )
                                    }
                                )
                            },
                    )
                }

                val resId = when (playerDetails.games.csgo.level) {
                    1 -> R.drawable.faceit1
                    2 -> R.drawable.faceit2
                    3 -> R.drawable.faceit3
                    4 -> R.drawable.faceit4
                    5 -> R.drawable.faceit5
                    6 -> R.drawable.faceit6
                    7 -> R.drawable.faceit7
                    8 -> R.drawable.faceit8
                    9 -> R.drawable.faceit9
                    else -> R.drawable.faceit10
                }

                Image(
                    painter = painterResource(resId),
                    contentDescription = "level",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .padding(horizontal = 100.dp, vertical = 24.dp)
                )

                Text(text = "${playerDetails.games.csgo.elo} ELO", modifier = Modifier.padding(16.dp), fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatisticsItem(title = "K/D Ratio", value = playerDetails.lifetime.kdRatio)
                    StatisticsItem(title = "Winrate", value = "${playerDetails.lifetime.winrate}%")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatisticsItem(title = "Matches", value = playerDetails.lifetime.totalMatches)
                    StatisticsItem(title = "Headshots", value = "${playerDetails.lifetime.headshots}%")
                }
            }
        }
    }
    if (state.message.isNotBlank()) {
        Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        if(state.isLoading) {
            CircularProgressIndicator()
        } else if(state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }

}