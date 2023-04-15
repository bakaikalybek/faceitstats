package el.professor.faceitstatistics.presentation.players_list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.professor.faceitstatistics.domain.repository.PlayerRepository
import el.professor.faceitstatistics.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayersListViewModel(
    private val repository: PlayerRepository
): ViewModel() {


    var state by mutableStateOf(PlayersListState())

    private var searchJob: Job? = null

    init {
        getPlayersList()
    }

    fun onEvent(event: PlayersListEvents) {
        Log.i("PlayersListViewModel", "Event: ")
        when (event) {
            is PlayersListEvents.OnSearchQueryChange -> {
                Log.i("PlayersListViewModel", "New Query: ${event.query}")
                state = state.copy(
                    searchQuery = event.query
                )
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(300)
                    getPlayersList()
                }
            }
        }
    }

    private fun getPlayersList(
        query: String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            repository.searchPlayer(query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { newData ->
                                state = state.copy(
                                    players = newData
                                )
                            }
                        }
                        is Resource.Error -> {
                            Log.e("PlayersListViewModel: getPlayersList", "Message: ${result.message}")
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}