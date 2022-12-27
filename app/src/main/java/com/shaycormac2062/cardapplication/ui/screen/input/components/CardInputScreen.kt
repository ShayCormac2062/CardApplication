package com.shaycormac2062.cardapplication.ui.screen.input.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.shaycormac2062.cardapplication.R
import com.shaycormac2062.cardapplication.ui.screen.input.CardDatabaseViewModel
import com.shaycormac2062.cardapplication.ui.screen.main.MainViewModel
import com.shaycormac2062.cardapplication.utils.AppButton
import com.shaycormac2062.cardapplication.utils.AppText
import com.shaycormac2062.cardapplication.utils.appGradient
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun CardInputScreen(
    pagerState: PagerState,
    mainViewModel: MainViewModel,
    cardDatabaseViewModel: CardDatabaseViewModel
) {
    var openDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) Color.Black
                else Color.White
            ),
        horizontalAlignment = Alignment.End
    ) {
        AppButton(
            modifier = Modifier
                .width(210.dp)
                .height(40.dp)
                .background(appGradient()),
            text = "Request history"
        ) {
            openDialog = true
        }
        CardInput(
            pagerState = pagerState,
            mainViewModel = mainViewModel
        )
        if (openDialog) RequestHistoryAlertDialog(
            setShowDialog = { openDialog = it },
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                    mainViewModel.getCardInfo(it)
                }
            },
            cardDatabaseViewModel
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardInput(
    pagerState: PagerState,
    mainViewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = stringResource(R.string.card_number_enter),
            size = 24.sp,
        )
        CardNumberTextField(
            pagerState,
            mainViewModel
        )
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun CardNumberTextField(
    pagerState: PagerState,
    viewModel: MainViewModel
) {
    var cardNumber by remember { mutableStateOf("") }
    var isNumberWrong by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Card(
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp
            ),
        elevation = 1.dp
    ) {
        BasicTextField(
            value = cardNumber,
            onValueChange = {
                if (it.length <= 8) cardNumber = it
            },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            visualTransformation = CardNumberMask(" "),
            singleLine = true
        )
    }
    AppButton(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp)
            .background(appGradient()),
        text = stringResource(R.string.submit),
        onClick = {
            if (cardNumber.length == 8) {
                coroutineScope.launch {
                    viewModel.getCardInfo(cardNumber)
                    pagerState.animateScrollToPage(1)
                }
            } else isNumberWrong = true
        }
    )
    if (isNumberWrong) AppText(
        modifier = Modifier
            .padding(horizontal = 36.dp)
            .padding(top = 12.dp),
        text = stringResource(R.string.valid_card_notification),
        color = Color.Red
    )
}
