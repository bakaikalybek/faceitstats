package el.professor.faceitstatistics.presentation.players_list

import el.professor.faceitstatistics.domain.model.Player

data class PlayersListState(
    val players: List<Player> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)
