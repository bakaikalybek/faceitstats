package el.professor.faceitstatistics.presentation.players_list

sealed class PlayersListEvents {
    data class OnSearchQueryChange(val query: String): PlayersListEvents()

}
