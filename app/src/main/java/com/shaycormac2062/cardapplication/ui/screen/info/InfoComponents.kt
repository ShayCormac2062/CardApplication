package com.shaycormac2062.cardapplication.ui.screen.info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shaycormac2062.cardapplication.R
import com.shaycormac2062.cardapplication.domain.model.BankModel
import com.shaycormac2062.cardapplication.utils.AppText

@Composable
fun SingleInfo(
    name: String,
    value: String,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        AppText(
            text = name,
            size = 16.sp,
            color = Color.LightGray
        )
        AppText(
            text = value,
            size = 28.sp
        )
    }
}

@Composable
fun BankInfo(
    bank: BankModel?,
    onUrlClickListener: (String) -> Unit,
    onPhoneClickListener: (String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        AppText(
            text = stringResource(R.string.bank),
            size = 16.sp,
            color = Color.LightGray
        )
        AppText(
            text = "${bank?.name}, ${bank?.city.toString()}",
            size = 24.sp
        )
        AppText(
            modifier = Modifier.clickable {
                onUrlClickListener(bank?.url.toString())
            },
            text = bank?.url.toString(),
            size = 24.sp,
            textDecoration = TextDecoration.Underline
        )
        AppText(
            text = bank?.phone.toString(),
            modifier = Modifier.clickable {
                onPhoneClickListener(bank?.phone.toString())
            },
            size = 24.sp,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun MultipleInfo(
    baseName: String,
    baseValue: String? = null,
    firstExtra: String,
    firstValue: String,
    secondExtra: String,
    secondEValue: String,
) {
    Column(
        Modifier
            .fillMaxWidth(.7f)
            .padding(32.dp)
    ) {
        AppText(
            text = baseName,
            size = 16.sp,
            color = Color.LightGray
        )
        baseValue?.let {
            AppText(
                text = it,
                size = 28.sp,
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = firstExtra,
                size = 14.sp,
                color = Color.LightGray
            )
            AppText(
                text = secondExtra,
                size = 14.sp,
                color = Color.LightGray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = firstValue,
                size = 28.sp,
            )
            AppText(
                text = secondEValue,
                size = 28.sp,
            )
        }
    }
}


