package com.side.runwithme.components

import android.graphics.Paint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.side.runwithme.R

@Preview(showBackground = true)
@Composable
fun MyToolBarPreview() {
    MyToolBar(title = stringResource(id = R.string.join),
        onBackClick = {
            // 뒤로가기
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyToolBarWithIconPreview() {
    MyToolBarWithIcon(
        title = stringResource(id = R.string.join),
        onBackClick = {
            // 뒤로가기
        }, icon = Icons.Default.PlayArrow,
        onIconClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolBar(
    title: String,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick.invoke() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "BackButton")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolBarWithIcon(
    title: String,
    onBackClick: () -> Unit,
    icon: ImageVector,
    onIconClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick.invoke() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "BackButton")
            }
        },
        actions = {
            // 액션 버튼들
            IconButton(onClick = { onIconClick.invoke() }) {
                Icon(icon, contentDescription = "IconButton")
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors()
    )
}