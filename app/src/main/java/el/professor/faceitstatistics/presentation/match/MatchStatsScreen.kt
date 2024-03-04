package el.professor.faceitstatistics.presentation.match

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import el.professor.faceitstatistics.domain.model.Player
import el.professor.faceitstatistics.presentation.destinations.PlayerDetailsScreenDestination
import el.professor.faceitstatistics.ui.theme.DarkBlue
import el.professor.faceitstatistics.ui.theme.GoogleSans
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun MatchStatsScreen(
    matchId: String,
    navigator: DestinationsNavigator,
    viewModel: MatchStatsViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Match stats", fontFamily = GoogleSans, fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold) },
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
        if (state.error == null) {
            state.matchStatistics?.let {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState()),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = state.matchStatistics.teams[0].teamStats.team, maxLines = 1, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                            Text(text = state.matchStatistics.teams[0].teamStats.finalScore, fontSize = 22.sp, color = if (state.matchStatistics.teams[0].teamStats.teamWin == "1") Color.Green else Color.Red, fontWeight = FontWeight.Bold)
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = state.matchStatistics.roundStats.map, textAlign = TextAlign.Center)
                            Text(text = state.matchStatistics.gameMode, textAlign = TextAlign.Center)
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = state.matchStatistics.teams[1].teamStats.team, maxLines = 1, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                            Text(text = state.matchStatistics.teams[1].teamStats.finalScore, fontSize = 22.sp, color = if (state.matchStatistics.teams[1].teamStats.teamWin == "1") Color.Green else Color.Red, fontWeight = FontWeight.Bold)
                        }
                    }

                    Divider(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        color = Color.DarkGray
                    )

                    val teamOne = state.matchStatistics.teams[0]
                    val teamTwo = state.matchStatistics.teams[1]
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(650.dp)
                    ) {
                        item {
                            Row(Modifier.fillMaxWidth().background(Color.DarkGray)) {
                                TableCell(text = teamOne.teamStats.team, weight = 2.3f)
                                TableCell(text = "K-D", weight = 1f)
                                TableCell(text = "HS%", weight = 1f)
                                TableCell(text = "KD", weight = 1f)
                            }
                        }
                        items(teamOne.players.sortedByDescending { it.playerStats.kdRatio }) {player ->
                            Row(Modifier.fillMaxWidth()) {
                                TableCell(text = player.nickname, weight = 2.3f, modifier = Modifier
                                    .clickable {
                                        navigator.navigate(PlayerDetailsScreenDestination(
                                            player = Player(
                                                nickname = player.nickname,
                                                playerId = player.playerId,
                                                level = "1"))
                                        )
                                    })
                                TableCell(text = "${player.playerStats.kills}-${player.playerStats.deaths}", weight = 1f)
                                TableCell(text = player.playerStats.headshotsPercentage, weight = 1f)
                                TableCell(text = player.playerStats.kdRatio, weight = 1f)
                            }
                        }
                        item {
                            Row(Modifier.fillMaxWidth().background(Color.DarkGray)) {
                                TableCell(text = teamTwo.teamStats.team, weight = 2.3f)
                                TableCell(text = "", weight = 1f)
                                TableCell(text = "", weight = 1f)
                                TableCell(text = "", weight = 1f)
                            }
                        }
                        items(teamTwo.players.sortedByDescending { it.playerStats.kdRatio }) { player ->
                            Row(Modifier.fillMaxWidth()) {
                                TableCell(text = player.nickname, weight = 2.3f, modifier = Modifier
                                    .clickable {
                                        navigator.navigate(PlayerDetailsScreenDestination(
                                            player = Player(
                                                nickname = player.nickname,
                                                playerId = player.playerId,
                                                level = "1"))
                                        )
                                    })
                                TableCell(text = "${player.playerStats.kills}-${player.playerStats.deaths}", weight = 1f)
                                TableCell(text = player.playerStats.headshotsPercentage, weight = 1f)
                                TableCell(text = player.playerStats.kdRatio, weight = 1f)
                            }
                        }
                    }

//                    val teamTwo = state.matchStatistics.teams[1]
//                    LazyColumn(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(250.dp)
//                    ) {
//                        item {
//                            Row(Modifier.fillMaxWidth().background(Color.DarkGray)) {
//                                TableCell(text = teamTwo.teamStats.team, weight = 2.3f)
//                                TableCell(text = "K-D", weight = 1f)
//                                TableCell(text = "HS%", weight = 1f)
//                                TableCell(text = "KD", weight = 1f)
//                            }
//                        }
//                        items(teamTwo.players.sortedByDescending { it.playerStats.kdRatio }) {
//                            val player = it
//                            Row(Modifier.fillMaxWidth()) {
//                                TableCell(text = player.nickname, weight = 2.3f)
//                                TableCell(text = "${player.playerStats.kills}-${player.playerStats.deaths}", weight = 1f)
//                                TableCell(text = player.playerStats.headshotsPercentage, weight = 1f)
//                                TableCell(text = player.playerStats.kdRatio, weight = 1f)
//                            }
//                        }
//                    }
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
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
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