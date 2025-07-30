import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  hint: String,
  icon: ImageVector? = null,
  maxLength: Int = Int.MAX_VALUE,
  maxLines: Int = 1,
  obscureText: Boolean = false,
  prefixIcon: @Composable (() -> Unit)? = null,
  suffixIcon: @Composable (() -> Unit)? = null,
  textAlign: TextAlign = TextAlign.Start,
  hintStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
  textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
  padding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
  transparentBackground: Boolean = false,
  onTap: (() -> Unit)? = null,
  textInputAction: ImeAction = ImeAction.Done,
  keyboardType: KeyboardType = KeyboardType.Text,
  autoFocus: Boolean = false,
  fillColor: Color = MaterialTheme.colorScheme.surfaceVariant,
  borderColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f),
  onSubmitted: (String) -> Unit = {},
  borderRadius: Dp = 10.dp,
  validator: ((String) -> String?)? = null,
  enabledBorderColor: Color = MaterialTheme.colorScheme.outline.copy(alpha = 0.1f),
  isError: Boolean = false,
  errorMessage: String? = null,
  singleLine: Boolean = true,
  leadingIconSize: Dp = 24.dp,
  leadingIconPadding: Dp = 0.dp,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val focusManager = LocalFocusManager.current
    val isFocused by interactionSource.collectIsFocusedAsState()

    val showError = isError || (validator?.invoke(value) != null && value.isNotEmpty())
    val actualErrorMessage = errorMessage ?: validator?.invoke(value)

    val shape = RoundedCornerShape(borderRadius)

    val actualVisualTransformation = if (obscureText) PasswordVisualTransformation() else visualTransformation

    val leadingIcon = if (icon != null) {
        {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(leadingIconSize),
                tint = if (isFocused) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    } else {
        prefixIcon
    }

    Column {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= maxLength) {
                    onValueChange(newValue)
                }
            },
            modifier = modifier.fillMaxWidth(),
            textStyle = textStyle,
            singleLine = singleLine,
            maxLines = maxLines,
            isError = showError,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = textInputAction
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSubmitted(value)
                    focusManager.clearFocus()
                }
            ),
            visualTransformation = actualVisualTransformation,
            shape = shape,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = enabledBorderColor,
                errorBorderColor = MaterialTheme.colorScheme.error,
                containerColor = if (transparentBackground) Color.Transparent else fillColor,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorLeadingIconColor = MaterialTheme.colorScheme.error,
            ),
            placeholder = {
                Text(
                    text = hint,
                    style = hintStyle,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            leadingIcon = leadingIcon,
            trailingIcon = suffixIcon,
            interactionSource = interactionSource
        )

        if (showError && actualErrorMessage != null) {
            Text(
                text = actualErrorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}