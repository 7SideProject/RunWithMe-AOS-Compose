package com.side.runwithme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyToolBar(
    title: String,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp),
        title = {
             Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "BackButton",
                    modifier = Modifier.clickable {
                        onBackClick.invoke()
                    }
                )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
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
    icon: Painter,
    onIconClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp),
        title = {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "BackButton",
                        modifier = Modifier.clickable {
                            onBackClick.invoke()
                        }
                    )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Text(text = title, style = MaterialTheme.typography.headlineSmall)

                    Icon(
                        painter = icon,
                        contentDescription = "IconButton",
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clickable {
                                onIconClick.invoke()
                            }
                            .size(32.dp)
                    )

                }

        },
        colors = TopAppBarDefaults.mediumTopAppBarColors()
    )
}