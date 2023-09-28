package com.dinesh.android.v0

import android.view.View
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dinesh.android.R
import android.util.Log
import androidx.compose.ui.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.activity.compose.*
import androidx.compose.material.icons.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dinesh.android.ui.theme.Material3Theme
import com.dinesh.android.v0.data.ApolloCountryClient
import com.dinesh.android.v0.model.SimpleCountry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val TAG = "log_" + MainActivity::class.java.name.split(MainActivity::class.java.name.split(".").toTypedArray()[2] + ".").toTypedArray()[1]

class MainActivity : AppCompatActivity() {
    private val apolloClient = ApolloCountryClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var countries = listOf<SimpleCountry>()

        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                countries = apolloClient.getCountries()
                Log.e(TAG, "onCreate: $countries")
            }
        }

        setContent {
            Material3Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

                    Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Button(onClick = {
                            lifecycleScope.launch(Dispatchers.IO) {
                                val randomCountry = apolloClient.getCountry(countries.random().code)
                                Log.i(TAG, "onCreate: $randomCountry")
                            }
                        }) {
                            Text(text = "Get Country")
                        }
                    }

                }
            }
        }

    }
}