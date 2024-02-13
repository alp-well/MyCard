package com.example.mycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.mycard.ui.theme.MyCardTheme
import com.example.mycard.ui.theme.body2
import com.example.mycard.ui.theme.header5

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column {
                        Spacer(modifier = Modifier.size(8.dp))
                        WrapContentHeightSolution()
                        Spacer(modifier = Modifier.size(8.dp))
                        OnSizeChangedSolution()
                    }
                }
            }
        }
    }
}

@Composable
fun OnGloballyPositionedSolution() {
    val localDensity = LocalDensity.current
    var columnHeightInDp by remember { mutableStateOf(0.dp) }
    Card(
        modifier =
            Modifier
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 12.dp)
                .fillMaxWidth(),
    ) {
        Row {
            Column(
                modifier =
                    Modifier
                        .onGloballyPositioned { coordinates ->
                            columnHeightInDp = with(localDensity) { coordinates.size.height.toDp() }
                        }
                        .weight(1f)
                        .padding(start = 16.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
                        .align(Alignment.CenterVertically),
            ) {
                HeaderText()
                BodyText()
            }

            Box(
                modifier =
                    Modifier
                        .size(100.dp, columnHeightInDp)
                        .align(Alignment.CenterVertically),
            ) {
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(100.dp, 160.dp)
                            .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
fun OnSizeChangedSolution() {
    val localDensity = LocalDensity.current
    var columnHeightInDp by remember { mutableStateOf(0.dp) }
    Card(
        modifier =
            Modifier
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 12.dp)
                .fillMaxWidth(),
    ) {
        Row {
            Column(
                modifier =
                    Modifier
                        .onSizeChanged { size ->
                            columnHeightInDp = with(localDensity) { size.height.toDp() }
                        }
                        .weight(1f)
                        .padding(start = 16.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
                        .align(Alignment.CenterVertically),
            ) {
                HeaderText()
                BodyText()
            }

            Box(
                modifier =
                    Modifier
                        .size(100.dp, columnHeightInDp)
                        .align(Alignment.CenterVertically),
            ) {
                Image(
                    painter = painterResource(R.drawable.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier =
                        Modifier
                            .size(100.dp, 160.dp)
                            .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
fun WrapContentHeightSolution() {
    // Unfinished
    Card(
        modifier =
            Modifier
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 12.dp)
                .fillMaxWidth(),
    ) {
        Row(
            modifier =
                Modifier
                    .graphicsLayer { clip = true },
        ) {
            Column(
                modifier =
                    Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
                        .align(Alignment.CenterVertically),
            ) {
                HeaderText()
                BodyText()
            }

            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier =
                    Modifier
                        .width(100.dp)
                        .wrapContentHeight(unbounded = true, align = Alignment.CenterVertically),
            )
        }
    }
}

@Composable
fun LayoutSolution() {
    Card(
        modifier =
            Modifier
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 12.dp)
                .fillMaxWidth(),
    ) {
        Layout(
            content = {
                Column(
                    modifier =
                        Modifier.padding(
                            start = 16.dp,
                            end = 12.dp,
                            bottom = 16.dp,
                            top = 16.dp,
                        ),
                ) {
                    HeaderText()
                    BodyText()
                }
                Box(
                    modifier = Modifier.width(100.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.image),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .align(Alignment.Center),
                    )
                }
            },
        ) { measurables, constraints ->
            val columnConstraints =
                constraints.copy(
                    maxWidth = constraints.maxWidth - 100.dp.roundToPx(),
                )
            val column = measurables[0].measure(columnConstraints)
            val boxConstraints =
                columnConstraints.copy(
                    maxWidth = 100.dp.roundToPx(),
                    maxHeight = column.height,
                )
            val box = measurables[1].measure(boxConstraints)
            layout(constraints.maxWidth, column.height) {
                column.placeRelative(x = 0, y = 0)
                val imageX = constraints.maxWidth - box.width
                box.placeRelative(x = imageX, y = 0)
            }
        }
    }
}

@Composable
fun ConstraintLayoutSolution() {
    Card(
        modifier =
            Modifier
                .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 12.dp)
                .fillMaxWidth(),
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (column, image) = createRefs()

            Column(
                modifier =
                    Modifier
                        .constrainAs(column) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        }
                        .padding(start = 16.dp, end = 112.dp, top = 16.dp, bottom = 16.dp),
            ) {
                HeaderText()
                BodyText()
            }

            Image(
                painter = painterResource(R.drawable.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .constrainAs(image) {
                            width = Dimension.value(100.dp)
                            height = Dimension.value(160.dp)
                            top.linkTo(parent.top, margin = (-30).dp)
                            bottom.linkTo(parent.bottom, margin = (-30).dp)
                            end.linkTo(parent.end)
                        },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutPrev() {
    LayoutSolution()
}

@Preview(showBackground = true)
@Composable
fun WrapContentHeightSolutionPrev() {
    WrapContentHeightSolution()
}

@Preview(showBackground = true)
@Composable
fun ConstrainLayoutPrev() {
    ConstraintLayoutSolution()
}

@Preview(showBackground = true)
@Composable
fun OnGloballyPositionedSolutionPreview() {
    OnGloballyPositionedSolution()
}

@Preview(showBackground = true)
@Composable
fun OnSizeChangedSolutionPreview() {
    OnSizeChangedSolution()
}

@Composable
fun HeaderText() {
    Text(
        text = "Lorem ipsum",
        style =
            MaterialTheme.typography.header5.copy(
                color = Color.Black,
            ),
    )
}

@Composable
fun BodyText() {
    Text(
        modifier = Modifier.padding(top = 4.dp),
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
        style =
            MaterialTheme.typography.body2.copy(
                color = Color.Black,
            ),
    )
}
