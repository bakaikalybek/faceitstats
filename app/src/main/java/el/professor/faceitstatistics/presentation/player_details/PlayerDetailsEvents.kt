package el.professor.faceitstatistics.presentation.player_details

import el.professor.faceitstatistics.domain.model.Player

sealed class PlayerDetailsEvents {
    data class OnPlayerSelectedFavorite(val player: Player): PlayerDetailsEvents()
}
