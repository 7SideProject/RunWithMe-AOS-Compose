package com.side.runwithme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.side.runwithme.R
import com.side.runwithme.ui.theme.Grey
import com.side.runwithme.ui.theme.MainGrey
import com.side.runwithme.ui.theme.RunWithMeTheme

@Preview(showBackground = true)
@Composable
fun EditTextPreview() {

    RunWithMeTheme {
        val state = remember {
            mutableStateOf("")
        }
        val passState = remember {
            mutableStateOf(true)
        }
        Column() {
            TextInput(
                valueState = state,
                labelId = "TextInput",
                enabled = true
            )
            EmailInput(
                emailState = state,
                labelId = "EmailInput",
                enabled = true
            )
            PasswordInput(
                passwordState = state,
                labelId = "PasswordInput",
                enabled = true,
                passwordVisibility = passState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String? = null,
    placeholder: String? = null,
    enabled: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    TextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = labelId?.let { { Text(text = it, style = MaterialTheme.typography.bodySmall) } },
        placeholder = placeholder?.let {
            {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        singleLine = isSingleLine,
        textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
            .focusRequester(focusRequester = focusRequester)
            .onFocusChanged {
                if (!it.hasFocus) {
                    keyboardController?.hide()
                }
            },
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    TextField(
        value = emailState.value,
        onValueChange = { emailState.value = it },
        label = { Text(text = labelId, style = MaterialTheme.typography.bodySmall) },
        singleLine = isSingleLine,
        textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .focusRequester(focusRequester = focusRequester)
            .onFocusChanged {
                if (!it.hasFocus) {
                    keyboardController?.hide()
                }
            },
        enabled = enabled,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = imeAction),
        keyboardActions = onAction,
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    passwordState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>? = null,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester by remember { mutableStateOf(FocusRequester()) }

    val visualTransformation = passwordVisibility?.let {
        if (it.value) VisualTransformation.None else
            PasswordVisualTransformation()
    } ?: PasswordVisualTransformation()

    TextField(
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId, style = MaterialTheme.typography.bodySmall) },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .focusRequester(focusRequester = focusRequester)
            .onFocusChanged {
                if (!it.hasFocus) {
                    keyboardController?.hide()
                }
            },
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            passwordVisibility?.let {
                PasswordVisibility(passwordVisibility = it)
            }
        },
        keyboardActions = onAction,
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(
        onClick = { passwordVisibility.value = !visible }) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = if (visible) painterResource(id = R.drawable.visibility_off)
            else painterResource(
                id = R.drawable.visibility_on
            ),
            contentDescription = "",
            tint = MainGrey
        )
    }
}