package com.example.surgeryskillapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onNavigateToLogin: () -> Unit,
    onSignUpSuccess: (String) -> Unit // <--- 1. This expects a String
) {
    // State management for input fields
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // --- Header Section ---
        Text(
            text = "Welcome",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF001D3D)
        )
        Text(
            text = "Sign in to continue",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )

        // --- Login / Sign Up Toggle ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .background(Color(0xFFF1F1F1), RoundedCornerShape(27.dp))
                .padding(4.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Login Tab (Navigates back to Login)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable { onNavigateToLogin() },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Login", color = Color.Gray)
                }
                // Sign Up Tab (Active)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.White, RoundedCornerShape(23.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Sign Up", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // --- Full Name Field ---
        SignUpInputField(
            label = "Full Name",
            value = fullName,
            onValueChange = { fullName = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Email Field ---
        SignUpInputField(
            label = "Email",
            value = email,
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- Password Field ---
        SignUpInputField(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- Create Account Button ---
        Button(
            onClick = {
                // <--- 2. THE FIX: We now pass 'fullName' here
                onSignUpSuccess(fullName)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0097B2)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Create Account",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // --- Footer ---
        Text(
            text = "Protected by industry-grade security",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Composable
fun SignUpInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.Black
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F6F8),
                unfocusedContainerColor = Color(0xFFF5F6F8),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
    }
}