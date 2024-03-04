package el.professor.faceitstatistics.presentation.match

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.professor.faceitstatistics.domain.repository.PlayerRepository
import el.professor.faceitstatistics.presentation.player_details.PlayerDetailsState
import el.professor.faceitstatistics.util.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MatchStatsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlayerRepository
): ViewModel() {

    var state by mutableStateOf("")

    init {
        viewModelScope.launch {
            val matchId = savedStateHandle.get<String>("matchId") ?: return@launch
            repository.getMatchStatistics(matchId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        state = result.data ?: "Empty"
                    }
                    is Resource.Error -> {
                        state = result.message ?: "Error"
                    }
                    else -> {
                        state = "Loading"
                    }
                }
            }
        }
    }
}