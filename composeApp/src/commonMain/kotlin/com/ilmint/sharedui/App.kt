package com.ilmint.sharedui

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import com.ilmint.sharedui.ui.screens.Navigation
import com.ilmint.sharedui.ui.screens.detail.DetailScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .crossfade(enable = true)
            //.logger(DebugLogger())
            .build()
    }

    Navigation()

}