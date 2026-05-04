package com.example.main_townguide.ui.screens.registration

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.main_townguide.ui.components.BrandHeader
import com.example.main_townguide.ui.components.GoldButton
import com.example.main_townguide.ui.components.GuideIcon
import com.example.main_townguide.ui.components.GuideIconType
import com.example.main_townguide.ui.components.IconButtonShell
import com.example.main_townguide.ui.components.SectionIntro
import com.example.main_townguide.ui.components.SocialButton
import com.example.main_townguide.ui.theme.Background
import com.example.main_townguide.ui.theme.Border
import com.example.main_townguide.ui.theme.PrimaryGold
import com.example.main_townguide.ui.theme.TextPrimary
import com.example.main_townguide.ui.theme.TextSecondary

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel,
    onRegistered: () -> Unit,
    onLoginClick: () -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    AuthEffects(success = state.success, error = state.error, onSuccess = onRegistered, onConsumeError = viewModel::consumeError)

    AuthScaffold(onBack = onBack) {
        SectionIntro(
            title = "Регистрация",
            subtitle = "Создайте аккаунт, чтобы сохранять избранные места, маршруты и рекомендации."
        )
        Spacer(Modifier.height(28.dp))
        GuideTextField(state.login, viewModel::onLoginChange, "Логин", GuideIconType.User)
        Spacer(Modifier.height(14.dp))
        GuideTextField(state.password, viewModel::onPasswordChange, "Пароль", GuideIconType.Lock, isPassword = true)
        Spacer(Modifier.height(14.dp))
        GuideTextField(state.repeatPassword, viewModel::onRepeatPasswordChange, "Повторите пароль", GuideIconType.Lock, isPassword = true)
        Spacer(Modifier.height(20.dp))
        TermsRow(checked = state.acceptedTerms, onCheckedChange = viewModel::onTermsChange)
        Spacer(Modifier.height(26.dp))
        GoldButton(text = "Зарегистрироваться", loading = state.loading, onClick = viewModel::register)
        Spacer(Modifier.height(26.dp))
        DividerText()
        Spacer(Modifier.height(18.dp))
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SocialButton(label = "Продолжить с VK", mark = "VK", onClick = {})
            SocialButton(label = "Продолжить с Google", mark = "G", onClick = {})
            SocialButton(label = "Продолжить с Apple", mark = "A", onClick = {})
        }
        Spacer(Modifier.height(24.dp))
        AuthLink(prefix = "Уже есть аккаунт? ", action = "Войти", onClick = onLoginClick)
    }
}

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoggedIn: () -> Unit,
    onRegisterClick: () -> Unit,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    AuthEffects(success = state.success, error = state.error, onSuccess = onLoggedIn, onConsumeError = viewModel::consumeError)

    AuthScaffold(onBack = onBack) {
        SectionIntro(
            title = "Вход",
            subtitle = "Введите логин и пароль, чтобы продолжить работу с путеводителем."
        )
        Spacer(Modifier.height(28.dp))
        GuideTextField(state.login, viewModel::onLoginChange, "Логин", GuideIconType.User)
        Spacer(Modifier.height(14.dp))
        GuideTextField(state.password, viewModel::onPasswordChange, "Пароль", GuideIconType.Lock, isPassword = true)
        Spacer(Modifier.height(26.dp))
        GoldButton(text = "Войти", loading = state.loading, onClick = viewModel::login)
        Spacer(Modifier.height(24.dp))
        AuthLink(prefix = "Нет аккаунта? ", action = "Зарегистрироваться", onClick = onRegisterClick)
    }
}

@Composable
private fun AuthEffects(success: Boolean, error: String?, onSuccess: () -> Unit, onConsumeError: () -> Unit) {
    val context = LocalContext.current
    LaunchedEffect(success) {
        if (success) onSuccess()
    }
    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            onConsumeError()
        }
    }
}

@Composable
private fun AuthScaffold(onBack: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .padding(top = 14.dp, bottom = 28.dp)
    ) {
        Box(Modifier.fillMaxWidth()) {
            IconButtonShell(icon = GuideIconType.Back, onClick = onBack, modifier = Modifier.align(Alignment.TopStart))
            BrandHeader(Modifier.align(Alignment.TopCenter))
        }
        Spacer(Modifier.height(58.dp))
        content()
    }
}

@Composable
private fun GuideTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: GuideIconType,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Border, RoundedCornerShape(16.dp))
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GuideIcon(icon, color = TextSecondary)
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            cursorBrush = SolidColor(PrimaryGold),
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            textStyle = androidx.compose.ui.text.TextStyle(color = TextPrimary, fontSize = 15.sp),
            modifier = Modifier
                .weight(1f)
                .padding(start = 14.dp, end = 8.dp),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) Text(placeholder, color = TextSecondary.copy(alpha = .74f), fontSize = 15.sp)
                    innerTextField()
                }
            }
        )
        if (isPassword) {
            Box(
                Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { passwordVisible = !passwordVisible },
                contentAlignment = Alignment.Center
            ) {
                GuideIcon(GuideIconType.Eye, color = TextSecondary)
            }
        }
    }
}

@Composable
private fun TermsRow(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(if (checked) PrimaryGold else Color.Transparent)
                .border(1.dp, if (checked) PrimaryGold else Border, RoundedCornerShape(5.dp))
                .clickable { onCheckedChange(!checked) },
            contentAlignment = Alignment.Center
        ) {
            if (checked) Text("✓", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
        Text(
            text = buildAnnotatedString {
                append("Я принимаю условия ")
                withStyle(SpanStyle(color = PrimaryGold, fontWeight = FontWeight.Medium)) { append("Пользовательского соглашения") }
                append(" и ")
                withStyle(SpanStyle(color = PrimaryGold, fontWeight = FontWeight.Medium)) { append("Политики конфиденциальности") }
            },
            color = TextSecondary,
            fontSize = 13.sp,
            lineHeight = 19.sp,
            modifier = Modifier
                .padding(start = 12.dp)
                .clickable { onCheckedChange(!checked) }
        )
    }
}

@Composable
private fun DividerText() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.weight(1f).height(1.dp).background(Border))
        Text("или", color = TextSecondary, fontSize = 13.sp, modifier = Modifier.padding(horizontal = 16.dp))
        Box(Modifier.weight(1f).height(1.dp).background(Border))
    }
}

@Composable
private fun AuthLink(prefix: String, action: String, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(prefix, color = TextSecondary, fontSize = 14.sp)
        Text(
            action,
            color = PrimaryGold,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.clickable(onClick = onClick)
        )
    }
}
