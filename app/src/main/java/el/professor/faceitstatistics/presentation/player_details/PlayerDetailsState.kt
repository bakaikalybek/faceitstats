package el.professor.faceitstatistics.presentation.player_details

import el.professor.faceitstatistics.domain.model.PlayerDetails

data class PlayerDetailsState(
    val playerDetails: PlayerDetails? = null,
    val isLoading: Boolean = false,
    val message: String = "",
    val error: String? = ""
)