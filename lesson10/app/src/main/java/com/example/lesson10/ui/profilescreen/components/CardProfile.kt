package com.example.lesson10.ui.profilescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lesson10.R
import com.example.lesson10.ui.theme.LocalCustomColors
import com.example.lesson10.ui.theme.LocalCustomTypography

@Composable
fun CardProfile(
    firstName: String,
    lastName: String,
    patronymic: String,
    dateOfBirth: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LocalCustomColors.current.backgroundGray)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(68.dp),
                painter = painterResource(id = R.drawable.image),
                contentDescription = null
            )
            Card(
                modifier = Modifier
                    .padding(start = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(vertical = 12.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        CustomText(firstName)
                        CustomText(lastName)
                        CustomText(patronymic)
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 80.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CustomText(
                            stringResource(id = R.string.date_of_birth),
                            LocalCustomTypography.current.small
                        )
                        CustomText(
                            dateOfBirth,
                            LocalCustomTypography.current.small
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CardProfilePreview() {
    CardProfile(
        "Иван",
        "Иванов",
        "Иванович",
        "01.01.2001"
    )
}