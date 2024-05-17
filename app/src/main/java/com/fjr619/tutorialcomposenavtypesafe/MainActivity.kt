package com.fjr619.tutorialcomposenavtypesafe

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fjr619.tutorialcomposenavtypesafe.ui.theme.TutorialComposeNavTypeSafeTheme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialComposeNavTypeSafeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Page1) {
                        composable<Page1> {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Column {
                                    Text(text = "Page1")
                                    Button(onClick = { navController.navigate(
                                        Page2(
                                            Person(
                                                name = "John",
                                                age = 21
                                            )
                                        )
                                    ) }) {
                                        Text(text = "Go to page2")
                                    }
                                }
                            }
                        }
                        composable<Page2>(
                            typeMap = mapOf(typeOf<Person>() to serializableType<Person>())
                        ) { entry ->
                            val arg = entry.toRoute<Page2>()
                            val name = arg.person.name
                            val age = arg.person.age
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Column {
                                    Text(text = "Page2 $name $age")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//val personType = object : NavType<Person>(
//    isNullableAllowed = false
//) {
//    val json = Json
//    override fun get(bundle: Bundle, key: String): Person? {
//        return bundle.getString(key)?.let { json.decodeFromString(it) }
//    }
//
//    override fun parseValue(value: String): Person {
//        return Json.decodeFromString<Person>(value)
//    }
//
//    override fun put(bundle: Bundle, key: String, value: Person) {
//        bundle.putString(key, json.encodeToString(value))
//    }
//
//    override fun serializeAsValue(value: Person): String {
//        return Json.encodeToString(value)
//    }
//
//}
