package com.najeeb.income_expense_tracker.ui.screens
import CustomTextField
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.najeeb.income_expense_tracker.ui.theme.Income_expense_trackerTheme

@ExperimentalMaterial3Api
class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Income_expense_trackerTheme(
                content = {

                    Column {
                        Text(
                            "تسجيل الدخول",
                            style = TextStyle(
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        var textValue by remember { mutableStateOf("") } // Initial value is an empty string
                        CustomTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = textValue,
                            onValueChange = { newText ->
                                textValue = newText
                            },
                            hint = "Enter text"
                        )
                        Spacer(modifier = Modifier.size(40.dp))
                    }
                }
            )
        }

    }
}

