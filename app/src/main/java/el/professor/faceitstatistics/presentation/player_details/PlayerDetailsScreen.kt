package el.professor.faceitstatistics.presentation.player_details

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import el.professor.faceitstatistics.R
import el.professor.faceitstatistics.domain.model.Player
import el.professor.faceitstatistics.presentation.destinations.MapDetailsScreenDestination
import el.professor.faceitstatistics.presentation.destinations.MatchStatsScreenDestination
import el.professor.faceitstatistics.presentation.player_details.composables.MapItem
import el.professor.faceitstatistics.presentation.player_details.composables.MatchItem
import el.professor.faceitstatistics.ui.theme.DarkBlue
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun PlayerDetailsScreen(
    player: Player,
    navigator: DestinationsNavigator,
    viewModel: PlayerDetailsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {  },
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
    ) { padding ->
        if (state.error == null) {
            state.playerDetails?.let { playerDetails ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                        .padding(padding)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.size(110.dp),
                            contentAlignment = BottomEnd
                        ) {
                            if (playerDetails.avatar == "") {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_person_24),
                                    contentDescription = "dummy avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .width(110.dp)
                                        .aspectRatio(1f)
                                )
                            } else {
                                Image(
                                    painter = rememberAsyncImagePainter(playerDetails.avatar),
                                    contentDescription = "avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .width(110.dp)
                                        .aspectRatio(1f)
                                )
                            }

                            val resId = when (playerDetails.games.cs.level) {
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
                                painter = painterResource(id = resId),
                                contentDescription = "level",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .width(35.dp)
                                    .aspectRatio(1f)
                            )
                            
                        }


                        Text(
                            text = playerDetails.nickname,
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onLongPress = {
                                            viewModel.onEvent(
                                                PlayerDetailsEvents.OnPlayerSelectedFavorite(player)
                                            )
                                        }
                                    )
                                },
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${playerDetails.games.cs.elo} ELO",
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        )

                    }


                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.DarkGray
                    )

                    Text(text = "Summary", color = Color.Gray, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatisticsItem(title = "K/D Ratio", value = playerDetails.lifetime.kdRatio, modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                        StatisticsItem(title = "Winrate", value = "${playerDetails.lifetime.winrate}%", modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StatisticsItem(title = "Matches", value = playerDetails.lifetime.totalMatches, modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                        StatisticsItem(title = "Headshots", value = "${playerDetails.lifetime.headshots}%", modifier = Modifier.weight(1f).padding(horizontal = 8.dp))
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        color = Color.DarkGray
                    )

                    Text(text = "Maps", color = Color.Gray, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 4.dp))

                    val orderedMaps = playerDetails.maps.sortedByDescending {
                        it.mapStats.winrate
                    }.filter {
                        it.mode == "5v5"
                    }
                    for (i in orderedMaps.indices) {
                        MapItem(map = orderedMaps[i], modifier = Modifier.clickable {
                            navigator.navigate(MapDetailsScreenDestination(map = orderedMaps[i]))
                        })
                    }

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        color = Color.DarkGray
                    )
                    val history = playerDetails.matches

                    Text(text = "Last ${history.size} matches", color = Color.Gray, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp, start = 16.dp, bottom = 4.dp))

                    for (i in history.indices) {
                        MatchItem(matchInfo = history[i], modifier = Modifier.clickable {
                            navigator.navigate(MatchStatsScreenDestination(matchId = history[i].matchId))
                        })
                    }
                }
            }
        }

        when {
            state.message.isNotBlank() -> {
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Center
                ) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }



    }


}