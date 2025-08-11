package com.mzdev.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.mzdev.newsapp.presentation.nvgraph.NavGraph
import com.mzdev.newsapp.presentation.onboarding.OnBoardingScreen
import com.mzdev.newsapp.presentation.onboarding.OnboardingViewModel
import com.mzdev.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }

        //enableEdgeToEdge()
        setContent {
            NewsAppTheme {
               /* val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Red,
                        darkIcons = !isSystemInDarkMode
                    )

                }*/
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NewsAppTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            val viewModel: OnboardingViewModel = hiltViewModel()
            OnBoardingScreen(
                event = viewModel::onEvent
            )
        }
    }
}