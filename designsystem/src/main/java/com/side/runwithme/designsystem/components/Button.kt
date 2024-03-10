package com.side.runwithme.designsystem.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.side.runwithme.designsystem.R
import com.side.runwithme.designsystem.theme.DisableColor
import com.side.runwithme.designsystem.theme.RunWithMeTheme

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    RunWithMeTheme {
        Column {
            RoundedButton(label = "로그인")
            RoundedIconButton(
                label = "카카오 로그인",
                iconPainter = painterResource(id = R.drawable.kakao_icon),
                color = Color(0xFFFEE500)
            )
        }
    }
}

@SuppressLint("UnnecessaryComposedModifier")
@Composable
fun RoundedButton(
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    enabled: Boolean = true,
    radius: Int = 8,
    onClick: () -> Unit = {}
) {
    val minModifier = Modifier
        .defaultMinSize(minWidth = 90.dp, minHeight = 40.dp)
        .clickable { onClick.invoke() }

    Button(
        onClick = onClick,
        modifier = minModifier.composed { modifier },
        enabled = enabled,
        shape = RoundedCornerShape(radius)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@SuppressLint("UnnecessaryComposedModifier")
@Composable
fun RoundedColorButton(
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    enabled: Boolean = true,
    radius: Int = 8,
    color: Color,
    onClick: () -> Unit = {}
) {
    val minModifier = Modifier
        .defaultMinSize(minWidth = 90.dp, minHeight = 40.dp)
        .clickable { onClick.invoke() }

    // 다크 모드와 라이트 모드에 대한 버튼 컬러 정의
    val colors = ButtonDefaults.buttonColors(
        containerColor = color,
        disabledContainerColor = DisableColor
    )

    Button(
        onClick = onClick,
        modifier = minModifier.composed { modifier },
        enabled = enabled,
        colors = colors,
        shape = RoundedCornerShape(radius)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@SuppressLint("UnnecessaryComposedModifier")
@Composable
fun RoundedIconButton(
    label: String,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(56.dp),
    enabled: Boolean = true,
    radius: Int = 8,
    iconPainter: Painter,
    color: Color,
    onClick: () -> Unit = {}
) {

    val minModifier = Modifier
        .defaultMinSize(minWidth = 90.dp, minHeight = 40.dp)
        .clickable { onClick.invoke() }

    val colors = ButtonDefaults.buttonColors(
        containerColor = color,
        disabledContainerColor = DisableColor
    )

    Button(
        onClick = onClick,
        modifier = minModifier.composed { modifier },
        enabled = enabled,
        colors = colors,
        shape = RoundedCornerShape(radius)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = iconPainter,
                tint = Color.Black,
                contentDescription = stringResource(id = R.string.running_list)
            )
            Spacer(modifier = Modifier.weight(0.1f))
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(0.1f))
        }
    }
}