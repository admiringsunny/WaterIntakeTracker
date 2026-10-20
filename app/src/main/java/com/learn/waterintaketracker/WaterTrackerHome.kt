package com.learn.waterintaketracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFFF4F8FD)
private val Blue = Color(0xFF2589F5)
private val DarkBlue = Color(0xFF1458A8)
private val LightGray = Color(0xFF718096)

@Preview(showBackground = true)
@Composable
fun WaterTrackerHome(
    name: String = "Water Tracker Home",
    modifier: Modifier = Modifier
) {
    var consumed by remember { mutableIntStateOf(0) }
    var lastAdded by remember { mutableIntStateOf(0) }
    var dailyGoal by remember { mutableIntStateOf(2000) }
    val progress = consumed.toFloat() / dailyGoal
    val percentage = (progress * 100).toInt()
    val remaining = dailyGoal - consumed

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "💧",
                fontSize = 34.sp,
                modifier = Modifier
                    .background(
                        color = Blue,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "AquaTrack",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue
                )

                Text(
                    text = "Your daily hydration companion",
                    fontSize = 13.sp,
                    color = LightGray
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            OutlinedButton(
                onClick = {
                    consumed = 0
                    lastAdded = 0
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Reset")
            }
        }

        Text(
            text = "Today's water intake",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )

        // Daily intake card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "DAILY GOAL",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = LightGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Circular progress indicator
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier.size(190.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        progress = { 1f },
                        modifier = Modifier.fillMaxSize(),
                        color = Color(0xFFE5F0FD),
                        strokeWidth = 12.dp,
                        trackColor = Color.Transparent
                    )

                    CircularProgressIndicator(
                        progress = { progress },
                        modifier = Modifier.fillMaxSize(),
                        color = Blue,
                        strokeWidth = 12.dp,
                        trackColor = Color.Transparent
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "💧",
                            fontSize = 30.sp
                        )

                        Text(
                            text = "$percentage%",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkBlue
                        )

                        Text(
                            text = "completed",
                            fontSize = 13.sp,
                            color = LightGray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "$consumed",
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "ml consumed",
                        fontSize = 14.sp,
                        color = LightGray,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }

                Text(
                    text = "Daily target: $dailyGoal ml",
                    fontSize = 14.sp,
                    color = LightGray
                )

                Spacer(modifier = Modifier.height(12.dp))

                LinearProgressIndicator(
                    progress = {
                        (consumed.toFloat() / dailyGoal).coerceIn(0f, 1f)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = Blue,
                    trackColor = Color(0xFFE5F0FD)
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (consumed >= dailyGoal) {
                    Text(
                        text = "🎉 Daily goal completed!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF16865C)
                    )
                } else {
                    Text(
                        text = "$remaining ml remaining",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Blue
                    )
                }
            }
        }

        Text(
            text = "Add your water",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(150, 250, 500).forEach { amount ->
                Button(
                    onClick = {
                        consumed += amount
                        lastAdded = amount
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 4.dp,
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = "+$amount ml",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }


        OutlinedButton(
            onClick = {
                consumed = (consumed - lastAdded).coerceAtLeast(0)
                lastAdded = 0
            },
            enabled = lastAdded > 0,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text("Undo last addition")
        }

        Text(
            text = "Daily Target",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = DarkBlue
        )

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf(1500, 2000, 2500, 3000).forEach { target ->
                FilterChip(
                    selected = dailyGoal == target,
                    onClick = {
                        dailyGoal = target
                    },
                    label = {
                        Text("${target / 100f}L")
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Text(
            text = "Your hydration journey starts here!",
            fontSize = 15.sp,
            color = LightGray
        )
    }
}