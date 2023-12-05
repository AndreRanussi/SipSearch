package com.flexidevapps.sipsearch.data.api

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexidevapps.sipsearch.R

@Composable
fun OutlinedButtonCompose(
    text:String,
    onClick: () -> Unit)
{
    OutlinedButton(
        modifier = Modifier
            .height(40.dp)
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        onClick = onClick,
        border = BorderStroke(1.dp, Color.White),
        shape = RoundedCornerShape(10),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.OrangeTheme)
        ),
    ) {
        androidx.compose.material3.Text(text, modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
        )
    }
}

@Composable
fun OutlinedDropdownCompose(
    text:String,
    onClick: () -> Unit)
{
    OutlinedButton(
        modifier = Modifier
            .padding(0.dp, 7.dp, 0.dp, 0.dp)
            .height(47.dp),
        onClick = onClick,
        border = BorderStroke(1.dp, Color.White),
        shape = RoundedCornerShape(8),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.GreyTheme)
        ),
    ) {
        Text(text
            , modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 0.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
    }
}


@Composable
fun OutlinedTextFieldCompose(
    value:String,
    onValueChange: (it:String) -> Unit,
    trailingIcon:@Composable () -> Unit,
    labelText:String
) {
    OutlinedTextField(
        modifier = Modifier
            .height(55.dp)
        ,
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            fontSize = 12.sp
        ),
        label = { Text(
            text = labelText,
            modifier = Modifier
                .background(colorResource(id = R.color.GreyTheme))
                .padding(0.dp, 0.dp, 0.dp, 9.dp)
                .wrapContentHeight(align = Alignment.Top),
            fontSize = 12.sp,
        )},
        trailingIcon = trailingIcon,
        colors = OutlinedTextFieldDefaults.colors(
            //BoxC
            focusedContainerColor = colorResource(id = R.color.GreyTheme),
            unfocusedContainerColor = colorResource(id = R.color.GreyTheme),
            //Borders
            focusedBorderColor = colorResource(id = R.color.white),
            unfocusedBorderColor = colorResource(id = R.color.white),
            //Text
            focusedTextColor = colorResource(id = R.color.white),
            unfocusedTextColor = colorResource(id = R.color.white),
            //Label
            focusedLabelColor = colorResource(id = R.color.white),
            unfocusedLabelColor = colorResource(id = R.color.white),
            disabledLabelColor =  colorResource(id = R.color.white),

            )

    )

}