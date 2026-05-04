package com.example.main_townguide.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.main_townguide.ui.theme.Border
import com.example.main_townguide.ui.theme.CardBackground
import com.example.main_townguide.ui.theme.GoldDark
import com.example.main_townguide.ui.theme.HeadingFont
import com.example.main_townguide.ui.theme.PrimaryGold
import com.example.main_townguide.ui.theme.TextPrimary
import com.example.main_townguide.ui.theme.TextSecondary

@Composable
fun BrandHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("ПУТЕВОДИТЕЛЬ", fontFamily = HeadingFont, fontSize = 20.sp, letterSpacing = 0.sp, color = TextPrimary)
        Text("ОТКРОЙ РОССИЮ", fontSize = 11.sp, letterSpacing = 0.sp, color = TextSecondary)
        Spacer(Modifier.height(10.dp))
        Box(
            Modifier
                .width(44.dp)
                .height(2.dp)
                .clip(RoundedCornerShape(50))
                .background(PrimaryGold)
        )
    }
}

@Composable
fun IconButtonShell(
    icon: GuideIconType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(44.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        GuideIcon(icon, color = TextPrimary)
    }
}

@Composable
fun GoldButton(
    text: String,
    loading: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .shadow(12.dp, RoundedCornerShape(16.dp), ambientColor = PrimaryGold.copy(.18f), spotColor = PrimaryGold.copy(.24f))
            .clip(RoundedCornerShape(16.dp))
            .background(Brush.horizontalGradient(listOf(PrimaryGold, GoldDark)))
            .clickable(enabled = !loading, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator(modifier = Modifier.size(22.dp), color = Color.White, strokeWidth = 2.dp)
        } else {
            Text(text, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.sp)
        }
    }
}

@Composable
fun SocialButton(
    label: String,
    mark: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp), ambientColor = Color.Black.copy(.04f), spotColor = Color.Black.copy(.06f))
            .clip(RoundedCornerShape(16.dp))
            .background(CardBackground)
            .border(BorderStroke(1.dp, Border), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(mark, color = PrimaryGold, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.width(34.dp), textAlign = TextAlign.Center)
        Text(label, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SectionIntro(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(title, fontFamily = HeadingFont, fontSize = 32.sp, color = TextPrimary)
        Text(subtitle, fontSize = 15.sp, lineHeight = 22.sp, color = TextSecondary)
    }
}

