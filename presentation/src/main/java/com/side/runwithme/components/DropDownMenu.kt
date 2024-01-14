package com.side.runwithme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.side.runwithme.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntListDropDownMenu(
    modifier: Modifier = Modifier,
    expanded: MutableState<Boolean>,
    list: Array<Int>,
    selectedIndex: MutableState<Int>,
) {
    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded.value,
        onExpandedChange = {
            expanded.value = !expanded.value
        }) {

        TextField(
            readOnly = true,
            value = list[selectedIndex.value].toString(),
            onValueChange = {},
            label = {
                Text("${list[selectedIndex.value]} cm")
            },
            trailingIcon = {
                Icon(
                    Icons.Filled.ArrowDropDown, stringResource(
                        id = R.string.drop_down
                    )
                )
            }
        )

        ExposedDropdownMenu(expanded = expanded.value, onDismissRequest = {
            expanded.value = false
        }) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(text = {
                    Text(text = item.toString())
                }, onClick = {
                    selectedIndex.value = index
                    expanded.value = false

                })
            }
        }
    }
}