package el.professor.faceitstatistics.presentation.match

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    modifier: Modifier = Modifier
) {
    Text(text = text,
        modifier = modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(horizontal = 8.dp, vertical = 16.dp),
        maxLines = 1)
}