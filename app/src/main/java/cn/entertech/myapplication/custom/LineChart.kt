package cn.entertech.myapplication.custom

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LineChart(dataPoints: List<Float>) {
    var maxValue by  remember { mutableStateOf(0f) }
    maxValue = dataPoints.maxOrNull() ?: 0f

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val xStep = size.width / (dataPoints.size - 1)
        val yStep = size.height / maxValue

        drawLine(
            start = Offset(0f, size.height - dataPoints.first() * yStep),
            end = Offset(size.width, size.height - dataPoints.last() * yStep),
            color = Color.Blue,
            strokeWidth = 2f
        )

        dataPoints.forEachIndexed { index, dataPoint ->
            drawCircle(
                color = Color.Blue,
                radius = 4.dp.toPx(),
                center = Offset(index * xStep, size.height - dataPoint * yStep),
                style = Stroke(2f)
            )
        }
    }
}

@Preview
@Composable
fun PreviewLineChart() {
    LineChart(dataPoints = listOf(10f, 20f, 15f, 30f, 25f, 40f,22f,12f,66f,48f,20f,22f,99f,00f,22f))
}
