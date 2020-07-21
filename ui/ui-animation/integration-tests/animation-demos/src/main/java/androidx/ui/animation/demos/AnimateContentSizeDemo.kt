/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.ui.animation.demos

import androidx.animation.tween
import androidx.compose.Composable
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.currentTextStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.corner.RoundedCornerShape
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.remember
import androidx.compose.setValue
import androidx.ui.animation.animateContentSize
import androidx.ui.core.Modifier
import androidx.ui.graphics.Color
import androidx.ui.material.Button
import androidx.ui.unit.dp

@Composable
fun AnimateContentSizeDemo() {
    Column(
        Modifier.wrapContentHeight()
            .padding(50.dp)
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(50.dp)
    ) {
        text()
        Spacer(Modifier.height(20.dp))
        button()
        Spacer(Modifier.height(20.dp))
        image()
    }
}

@Composable
fun text() {
    val shortText = "Click me"
    val longText = "Very long text\nthat spans across\nmultiple lines"
    var short by remember { mutableStateOf(true) }
    Box(modifier = Modifier
        .background(Color.Blue, RoundedCornerShape(15.dp))
        .clickable { short = !short }
        .padding(20.dp)
        .wrapContentSize()
        .animateContentSize()
    ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },
            style = currentTextStyle().copy(color = Color.White)
        )
    }
}

@Composable
fun button() {
    val shortText = "Short"
    val longText = "Very loooooong text"
    var short by remember { mutableStateOf(true) }
    Button(
        { short = !short }
    ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },
            style = currentTextStyle().copy(color = Color.White),
            modifier = Modifier.animateContentSize()
        )
    }
}

@Composable
fun image() {
    var portraitMode by remember { mutableStateOf(true) }
    Box(
        Modifier.clickable { portraitMode = !portraitMode }
            .sizeIn(maxWidth = 300.dp, maxHeight = 300.dp)
            .background(if (portraitMode) Color(0xFFfffbd0) else Color(0xFFe3ffd9))
            .animateContentSize(tween(500))
            .aspectRatio(if (portraitMode) 3 / 4f else 16 / 9f)
    ) {
        Text(
            if (portraitMode) {
                "3 : 4"
            } else {
                "16 : 9"
            },
            style = currentTextStyle().copy(color = Color.Black)
        )
    }
}