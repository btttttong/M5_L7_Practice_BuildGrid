package com.supakavadeer.lession7_buildagrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supakavadeer.lession7_buildagrid.data.DataSource
import com.supakavadeer.lession7_buildagrid.model.Topic
import com.supakavadeer.lession7_buildagrid.ui.theme.Lession7_BuildAGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lession7_BuildAGridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GridApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GridApp(modifier: Modifier = Modifier) {
    GridList(
        topicList = DataSource.topics, modifier = modifier
    )
}

@Composable
fun GridCard(
    modifier: Modifier = Modifier, topic: Topic
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier.size(68.dp, 68.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(topic.stringResourceId),
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = "just an image"
                    )
                    Text(
                        text = topic.number.toString(),
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }

            }
        }
    }
}

@Composable
fun GridList(
    topicList: List<Topic>, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(topicList.chunked(2)) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                rowItems.forEach { topic ->
                    GridCard(
                        topic = topic, modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    )
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GridPreview() {
    Lession7_BuildAGridTheme {
        GridApp()
    }
}