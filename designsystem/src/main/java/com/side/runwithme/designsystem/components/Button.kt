package com.side.runwithme.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.side.runwithme.designsystem.R
import com.side.runwithme.designsystem.theme.Black
import com.side.runwithme.designsystem.theme.Grey
import com.side.runwithme.designsystem.theme.MainColor
import com.side.runwithme.designsystem.theme.RunWithMeTheme
import com.side.runwithme.designsystem.theme.White


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    RunWithMeTheme {
        Column {
            RoundedWeakButton(label = "Button")
            RoundedStrongButton(label = "로그인")
            RoundedKakaoButton(label = "카카오 로그인")
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
                style = MaterialTheme.typography.titleMedium,
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
                style = MaterialTheme.typography.titleMedium,
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

@Composable
fun RoundedKakaoButton(
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
        color = Color(0xFFFEE500)
    ) {
        Row(
            modifier = textModifier
                .padding(horizontal = 10.dp)
                .clickable {
                    onClick.invoke()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.kakao_icon),
                tint = Black,
                contentDescription = stringResource(id = R.string.running_list)
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = Black
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}