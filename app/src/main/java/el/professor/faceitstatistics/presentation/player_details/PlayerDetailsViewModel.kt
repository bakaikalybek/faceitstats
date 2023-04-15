package el.professor.faceitstatistics.presentation.player_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import el.professor.faceitstatistics.domain.model.Player
import el.professor.faceitstatistics.domain.repository.PlayerRepository
import el.professor.faceitstatistics.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlayerDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PlayerRepository
): ViewModel() {

    var state by mutableStateOf(PlayerDetailsState())

    init {
        viewModelScope.launch {
            val player = savedStateHandle.get<Player>("player") ?: return@launch
            state = state.copy(isLoading = true)
            repository.getPlayersStatistics(playerId = player.playerId).collect { result ->
                Log.i("PlayerDetailsViewModel", "${result.data?.games?.csgo?.elo} - ${result.data?.games?.csgo?.playerNameInGame}")
                when(result) {
                    is Resource.Success -> {
                        state = state.copy(
                            playerDetails = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message,
                            playerDetails = null,
                        )
                    }
                    else -> {

                    }
                }
            }
        }
    }

    fun onEvent(event: PlayerDetailsEvents) {
        when (event) {
            is PlayerDetailsEvents.OnPlayerSelectedFavorite -> {
                addPlayerToFavoritesList(event.player)
            }
        }
    }

    private fun addPlayerToFavoritesList(player: Player) {
        viewModelScope.launch {
            repository.addOrDeletePlayer(player = player)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            state = state.copy(
                                isLoading = false,
                                message = result.data ?: "Success"
                            )
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = result.message ?: "Fail"
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }
}