package el.professor.faceitstatistics.presentation.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = state)
        }
    }
}