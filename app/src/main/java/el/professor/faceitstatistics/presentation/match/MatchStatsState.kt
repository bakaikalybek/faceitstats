package el.professor.faceitstatistics.presentation.match

import el.professor.faceitstatistics.domain.model.MatchStatistics

data class MatchStatsState(
    val matchStatistics: MatchStatistics? = null,
    val isLoading: Boolean = false,
    val message: String = "",
    val error: String? = ""
)