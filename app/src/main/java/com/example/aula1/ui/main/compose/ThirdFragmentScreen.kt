package com.example.aula1.ui.main.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aula1.domain.model.SampleModel
import com.example.aula1.ui.main.MainViewModel
import com.example.aula1.R
import com.example.aula1.ui.model.SampleHelper

@Composable
fun ThirdFragmentScreen(
    viewModel: MainViewModel,
    onClickIcon: (sampleInfoHelper: SampleHelper) -> Unit
) {
    val screenState by viewModel.sampleDataScreen.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .wrapContentHeight()
        ) {
            screenState?.let {
                itemsIndexed(it) { _, comp ->
                    InfoTable(comp, onClickIcon)
                }
            }
        }
    }
}

@Composable
fun InfoTable(
    sample: SampleModel,
    onClickIcon: (sampleInfoHelper: SampleHelper) -> Unit
) {
    Row {
        Icon(
            modifier = Modifier
                .clickable {
                    sample.infoHelper?.let { onClickIcon.invoke(it) }
                }
                .size(48.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_baseline_drive_eta_24),
            contentDescription = null
        )
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            Text(
                text = sample.title,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = sample.body,
                textAlign = TextAlign.Start,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                ),
                maxLines = 3
            )
        }
    }
}