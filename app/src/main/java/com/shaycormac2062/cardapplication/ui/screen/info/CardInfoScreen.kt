package com.shaycormac2062.cardapplication.ui.screen.info

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.shaycormac2062.cardapplication.R
import com.shaycormac2062.cardapplication.domain.model.CardInfoModel
import com.shaycormac2062.cardapplication.ui.screen.OpenDialer
import com.shaycormac2062.cardapplication.ui.screen.main.CardInfoEvent
import com.shaycormac2062.cardapplication.ui.screen.main.MainViewModel
import com.shaycormac2062.cardapplication.utils.*
import com.shaycormac2062.cardapplication.utils.ApplicationException.Companion.API_EXCEPTION
import com.shaycormac2062.cardapplication.utils.ApplicationException.Companion.NOT_FOUND_CARD_NUMBER
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardInfoScreen(
    pagerState: PagerState,
    viewModel: MainViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    when (uiState) {
        is CardInfoEvent.CardInfoSuccessEvent ->
            CardInfoScreen(
                pagerState = pagerState,
                info = (uiState as CardInfoEvent.CardInfoSuccessEvent).cardInfo
            )
        is CardInfoEvent.CardInfoEmptyEvent -> EmptyCardNotification()
        is CardInfoEvent.CardInfoLoadingEvent -> ProgressBar()
        is CardInfoEvent.CardInfoErrorEvent ->
            (uiState as CardInfoEvent.CardInfoErrorEvent).exception?.let { exception ->
                ErrorMessage(
                    exception = exception
                ) {
                    scope.launch {
                        if (
                            exception.message == NOT_FOUND_CARD_NUMBER ||
                            exception.message == API_EXCEPTION
                        ) {
                            pagerState.animateScrollToPage(0)
                            viewModel.resetData()
                        } else {
                            viewModel.getCardInfo(
                                (uiState as CardInfoEvent.CardInfoErrorEvent).errorNumber.toString()
                            )
                        }
                    }
                }
            }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardInfoScreen(
    pagerState: PagerState,
    info: CardInfoModel?,
) {
    var phoneOn by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Row(modifier = Modifier
            .padding(
                top = 18.dp,
                start = 12.dp
            )
            .clickable {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null
            )
            AppText(
                modifier = Modifier.padding(
                    start = 8.dp
                ),
                text = stringResource(R.string.go_back),
                size = 18.sp
            )
        }
        AppText(
            modifier = Modifier.padding(
                top = 42.dp,
                bottom = 32.dp,
                start = 14.dp
            ),
            text = stringResource(R.string.card_info),
            size = 35.sp
        )
        SingleInfo(
            name = stringResource(R.string.scheme_or_network),
            value = info?.scheme.toString()
        )
        SingleInfo(name = stringResource(R.string.brand), value = info?.brand.toString())
        MultipleInfo(
            baseName = stringResource(R.string.card_number),
            firstExtra = stringResource(R.string.length),
            firstValue = info?.number?.length.toString(),
            secondExtra = stringResource(R.string.luhn),
            secondEValue = if (info?.number?.luhn == true)
                stringResource(R.string.yes)
            else stringResource(R.string.no)
        )
        SingleInfo(
            name = stringResource(R.string.type),
            value = info?.type.toString()
        )
        SingleInfo(
            name = stringResource(R.string.prepaid),
            value = if (info?.prepaid == true)
                stringResource(R.string.yes)
            else stringResource(R.string.no)
        )
        MultipleInfo(
            baseName = stringResource(R.string.country),
            baseValue = "${info?.country?.alpha2} ${info?.country?.name}",
            firstExtra = stringResource(R.string.longitude),
            secondExtra = stringResource(R.string.latitude),
            firstValue = info?.country?.longitude.toString(),
            secondEValue = info?.country?.latitude.toString()
        )
        val uriHandler = LocalUriHandler.current
        BankInfo(
            info?.bank,
            onUrlClickListener = {
                var url = it
                if (!url.startsWith("http://") && !url.startsWith("https://"))
                    url = "http://$url"
                uriHandler.openUri(url)
            },
            onPhoneClickListener = { phoneOn = true }
        )
        if (phoneOn) {
            OpenDialer(info?.bank?.phone.toString())
        }
    }
}

@Composable
fun EmptyCardNotification() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberVectorPainter(
                image = ImageVector.vectorResource(
                    id = if (isSystemInDarkTheme()) {
                        R.drawable.ic_empty_card_night
                    } else R.drawable.ic_empty_card_day
                ),
            ),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        AppText(
            modifier = Modifier.padding(
                vertical = 8.dp,
                horizontal = 56.dp
            ),
            text = stringResource(R.string.empty_card_number),
            size = 19.sp
        )
    }
}
