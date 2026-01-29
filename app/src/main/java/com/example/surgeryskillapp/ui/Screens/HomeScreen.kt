package com.example.surgeryskillapp.ui.Screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// FIX 1: Added 'userName: String' here so the function accepts the name
fun HomeScreen(userName: String) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("SurgiSkill AI", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /* Open Drawer */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            // --- Welcome Section ---
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Welcome", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF001D3D))
                    Text("back,", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF001D3D))

                    // FIX 2: Now this variable works because we added it to the function top
                    Text(userName, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0097B2))
                }
                Surface(
                    color = Color(0xFFE0F7FA),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "Phase: Advanced Training",
                        color = Color(0xFF006064),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Text(
                "Here's your training progress overview",
                color = Color.Gray,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- Stats Cards ---
            StatCard(title = "Skill Score", value = "91", subtext = "+12% this week", iconColor = Color(0xFFB2EBF2))
            StatCard(title = "Average Deviation", value = "2.3mm", subtext = "-0.5mm improved", subtextColor = Color(0xFF00C853), iconColor = Color(0xFFE0F2F1))
            StatCard(title = "Motion Smoothness", value = "87%", subtext = "+5% this week", iconColor = Color(0xFFE1F5FE))
            StatCard(title = "Accuracy", value = "94%", subtext = "+3% this week", iconColor = Color(0xFFE0F7FA))

            Spacer(modifier = Modifier.height(24.dp))

            // --- Chart Section ---
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Skill Improvement Over Time", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    // Simple custom drawing for the line chart
                    SimpleLineChart(modifier = Modifier.fillMaxWidth().height(150.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // --- Today's Focus Section ---
            Text("Today's Focus", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF001D3D))
            Spacer(modifier = Modifier.height(16.dp))

            FocusCard(title = "Suture Technique", desc = "Practice consistent tension and spacing", tag = "Intermediate", tagColor = Color(0xFFFFCC00))
            FocusCard(title = "Tool Positioning", desc = "Maintain optimal ergonomic positioning", tag = "Advanced", tagColor = Color(0xFFFF5252))
            FocusCard(title = "Motion Efficiency", desc = "Reduce unnecessary movements", tag = "Beginner", tagColor = Color(0xFF69F0AE))

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

// --- Helper Composables ---

@Composable
fun StatCard(
    title: String,
    value: String,
    subtext: String,
    subtextColor: Color = Color(0xFF00C853), // Green by default
    iconColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp), // Shadow
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(title, color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(value, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF001D3D))
                Spacer(modifier = Modifier.height(4.dp))
                Text(subtext, color = subtextColor, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
            // Icon Placeholder Box
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(iconColor, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                // You can add distinct Icons here later
                Text("Icon", fontSize = 10.sp, color = Color(0xFF006064))
            }
        }
    }
}

@Composable
fun FocusCard(title: String, desc: String, tag: String, tagColor: Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF001D3D))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, tagColor),
                    color = Color.White
                ) {
                    Text(
                        text = tag,
                        color = tagColor,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(desc, color = Color.Gray, fontSize = 14.sp)
        }
    }
}

@Composable
fun SimpleLineChart(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val path = Path()
        val width = size.width
        val height = size.height

        // Draw Axes
        drawLine(
            start = Offset(0f, 0f),
            end = Offset(0f, height),
            color = Color.LightGray,
            strokeWidth = 2f
        )
        drawLine(
            start = Offset(0f, height),
            end = Offset(width, height),
            color = Color.LightGray,
            strokeWidth = 2f
        )

        // Draw Line Data (Fake Data Points)
        path.moveTo(0f, height * 0.8f) // Start low
        path.cubicTo(
            width * 0.3f, height * 0.7f,
            width * 0.6f, height * 0.4f,
            width, height * 0.1f // End high
        )

        drawPath(
            path = path,
            color = Color(0xFF00BCD4), // Cyan chart line
            style = Stroke(width = 5.dp.toPx(), cap = StrokeCap.Round)
        )

        // Draw Dots
        drawCircle(Color(0xFF00BCD4), radius = 6.dp.toPx(), center = Offset(0f, height * 0.8f))
        drawCircle(Color(0xFF00BCD4), radius = 6.dp.toPx(), center = Offset(width * 0.5f, height * 0.55f))
        drawCircle(Color(0xFF00BCD4), radius = 6.dp.toPx(), center = Offset(width, height * 0.1f))
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    // FIX 3: Updated the preview to pass a dummy name
    HomeScreen(userName = "Dr. John Doe")
}