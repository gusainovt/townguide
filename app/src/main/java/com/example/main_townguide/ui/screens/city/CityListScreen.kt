package com.example.main_townguide.ui.screens.city

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.main_townguide.data.model.City
import com.example.main_townguide.ui.components.BrandHeader
import com.example.main_townguide.ui.components.GuideIcon
import com.example.main_townguide.ui.components.GuideIconType
import com.example.main_townguide.ui.components.IconButtonShell
import com.example.main_townguide.ui.components.SectionIntro
import com.example.main_townguide.ui.theme.Background
import com.example.main_townguide.ui.theme.Border
import com.example.main_townguide.ui.theme.CardBackground
import com.example.main_townguide.ui.theme.PrimaryGold
import com.example.main_townguide.ui.theme.TextPrimary
import com.example.main_townguide.ui.theme.TextSecondary

@Composable
fun CityListScreen(viewModel: CityListViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(state.error) {
        state.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            viewModel.consumeError()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .padding(horizontal = 24.dp)
            .padding(top = 14.dp)
    ) {
        Box(Modifier.fillMaxWidth()) {
            IconButtonShell(icon = GuideIconType.Menu, onClick = {}, modifier = Modifier.align(Alignment.TopStart))
            BrandHeader(Modifier.align(Alignment.TopCenter))
            IconButtonShell(icon = GuideIconType.Search, onClick = {}, modifier = Modifier.align(Alignment.TopEnd))
        }
        Spacer(Modifier.height(56.dp))
        SectionIntro(
            title = "Выберите город",
            subtitle = "Погрузитесь в историю и культуру древних городов России"
        )
        Spacer(Modifier.height(26.dp))
        if (state.loading) {
            Box(Modifier.fillMaxWidth().padding(top = 50.dp), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = PrimaryGold)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.cities, key = { it.id }) { city ->
                    CityCard(city = city)
                }
                item { Spacer(Modifier.height(18.dp)) }
            }
        }
    }
}

@Composable
private fun CityCard(city: City) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(12.dp, RoundedCornerShape(16.dp), ambientColor = androidx.compose.ui.graphics.Color.Black.copy(.05f), spotColor = androidx.compose.ui.graphics.Color.Black.copy(.08f))
            .clip(RoundedCornerShape(16.dp))
            .background(CardBackground)
            .border(1.dp, Border.copy(alpha = .55f), RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(Background),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = city.photo,
                contentDescription = city.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, end = 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(city.name, color = TextPrimary, fontSize = 17.sp, fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(city.description, color = TextSecondary, fontSize = 13.sp, lineHeight = 18.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
        GuideIcon(GuideIconType.ArrowRight, color = PrimaryGold)
    }
}

