package com.shaycormac2062.cardapplication.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.shaycormac2062.cardapplication.ui.screen.info.CardInfoScreen
import com.shaycormac2062.cardapplication.ui.screen.input.CardDatabaseViewModel
import com.shaycormac2062.cardapplication.ui.screen.input.components.CardInputScreen

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        count = 2
    ) { index ->
        val viewModel: MainViewModel = hiltViewModel()
        val databaseViewModel: CardDatabaseViewModel = hiltViewModel()
        if (index == 0) {
            CardInputScreen(
                pagerState,
                viewModel,
                databaseViewModel
            )
        } else CardInfoScreen(
            pagerState,
            viewModel
        )
    }
}
