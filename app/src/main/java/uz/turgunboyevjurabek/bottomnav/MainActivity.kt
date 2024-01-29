@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.bottomnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.bottomnav.ui.theme.BottomNavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting()
                    WithScaffold()
                }

            }
        }
    }
}

@Composable
fun Greeting() {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()) {
        val sheetState= rememberModalBottomSheetState()
        var isSheetOpen  by rememberSaveable {
            mutableStateOf(false)
        }

        Button(
            onClick = {
                isSheetOpen=true
            })
        {
            Text(text = "Click me :)")
        }
      if (isSheetOpen){
          ModalBottomSheet(
              sheetState = sheetState,
              onDismissRequest = {isSheetOpen=false},
              windowInsets = WindowInsets(top = 20.dp)
          ) {
              Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription ="image" )
          }
      }
    }
}
@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    BottomNavTheme {
        WithScaffold()
    }
}

@Composable
fun WithScaffold() {
    val scaffoldState= rememberBottomSheetScaffoldState()
    val sheetState= rememberModalBottomSheetState()
    var isSheetOpen  by rememberSaveable {
        mutableStateOf(false)
    }
    val scope= rememberCoroutineScope()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        BottomSheetScaffold(
            scaffoldState=scaffoldState,
            sheetContent ={
                Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription ="allambalo" )
            }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(
                    Color.Cyan, Color.Red, Color.Green
                )))
                , contentAlignment = Alignment.Center){
                Button(onClick = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                }) {
                    Text(text = "Click me 😉", fontFamily = FontFamily.SansSerif)
                }
            }
        }

    }

}