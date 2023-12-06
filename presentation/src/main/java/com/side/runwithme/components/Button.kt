package com.side.runwithme.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.side.runwithme.ui.theme.Black
import com.side.runwithme.ui.theme.Grey
import com.side.runwithme.ui.theme.MainColor
import com.side.runwithme.ui.theme.RunWithMeTheme
import com.side.runwithme.ui.theme.White


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    RunWithMeTheme {
        Row {
            RoundedWeakButton(label = "Button")
        }
    }
}

@Composable
fun RoundedStrongButton(
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    radius: Int = 8,
    textModifier: Modifier = Modifier
        .width(90.dp)
        .height(40.dp),
    onClick: () -> Unit = {}
) {

    Surface(
        modifier = modifier.clip(
            RoundedCornerShape(CornerSize(radius))
        ),
        color = MainColor
    ) {
        Column(
            modifier = textModifier.clickable {
                onClick.invoke()
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
        }
    }
}

@Composable
fun RoundedWeakButton(
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    radius: Int = 8,
    textModifier: Modifier = Modifier
        .width(90.dp)
        .height(40.dp),
    onClick: () -> Unit = {}
) {

    Surface(
        modifier = modifier.clip(
            RoundedCornerShape(CornerSize(radius))
        ),
        color = White,
        border = BorderStroke(2.dp, color = Grey)
    ) {
        Column(
            modifier = textModifier.clickable {
                onClick.invoke()
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                color = Grey
            )
        }
    }
}

@Composable
fun RoundedWhiteButton(
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    radius: Int = 8,
    textModifier: Modifier = Modifier
        .width(90.dp)
        .height(40.dp),
    onClick: () -> Unit = {}
) {

    Surface(
        modifier = modifier.clip(
            RoundedCornerShape(CornerSize(radius))
        ),
        color = White
    ) {
        Column(
            modifier = textModifier.clickable {
                onClick.invoke()
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                color = Black
            )
        }
    }
}