package com.shaycormac2062.cardapplication.ui.screen.input.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.shaycormac2062.cardapplication.R
import com.shaycormac2062.cardapplication.domain.model.DatabaseModel
import com.shaycormac2062.cardapplication.ui.screen.input.CardDatabaseEvent
import com.shaycormac2062.cardapplication.ui.screen.input.CardDatabaseViewModel
import com.shaycormac2062.cardapplication.utils.AppButton
import com.shaycormac2062.cardapplication.utils.AppText
import com.shaycormac2062.cardapplication.utils.ProgressBar
import com.shaycormac2062.cardapplication.utils.appGradient

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RequestHistoryAlertDialog(
    setShowDialog: (Boolean) -> Unit,
    onClick: (String) -> Unit,
    viewModel: CardDatabaseViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    viewModel.getRequestHistory()
    AlertDialog(
        modifier = Modifier.clip(RoundedCornerShape(15.dp)),
        onDismissRequest = {
            setShowDialog(false)
        },
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(top = 44.dp),
                    text = stringResource(R.string.history),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                )
            }
        },
        text = {
            when(uiState) {
                is CardDatabaseEvent.CardDatabaseEmptyEvent ->
                    EmptyHistoryNotification()
                is CardDatabaseEvent.CardDatabaseSuccessEvent ->
                    History(
                        (uiState as CardDatabaseEvent.CardDatabaseSuccessEvent).cardInfo,
                        onClick
                    )
                else -> ProgressBar()
            }
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 1.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                AppButton(
                    modifier = Modifier
                        .width(210.dp)
                        .height(40.dp)
                        .background(appGradient()),
                    text = stringResource(R.string.close),
                    onClick = { setShowDialog(false) }
                )
            }
        }
    )
}

@Composable
fun History(
    models: List<DatabaseModel?>,
    onClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        content = {
            items(models.size) { item ->
                Block(models[item], onClick)
            }
        },
    )
}

@Composable
fun Block(
    databaseModel: DatabaseModel?,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
            .clickable {
                onClick(databaseModel?.name.toString())
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppText(
            text = databaseModel?.name.toString(),
            size = 20.sp
        )
        Image(
            painter = rememberVectorPainter(
                image = getImageVector(databaseModel?.isRight)
            ),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun getImageVector(isRight: Boolean?): ImageVector =
    ImageVector.vectorResource(
        id = if (isRight == true) {
            if (isSystemInDarkTheme()) {
                R.drawable.done_night
            } else R.drawable.done_day
        } else {
            if (isSystemInDarkTheme()) {
                R.drawable.cancel_night
            } else R.drawable.cancel_day
        }
    )

@Composable
fun EmptyHistoryNotification() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp),
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
            text = stringResource(R.string.empty_history),
            size = 16.sp
        )
    }
}
