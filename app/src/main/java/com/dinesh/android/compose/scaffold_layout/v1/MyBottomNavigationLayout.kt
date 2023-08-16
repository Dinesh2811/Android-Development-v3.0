package com.dinesh.android.compose.scaffold_layout.v1

import androidx.compose.ui.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.compose.material.icons.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.*


private val TAG = "log_MyScaffoldLayout"

@Composable
fun MyScreen(text: String){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = text)
    }
}
data class BottomNavItem(val title: String, val icon: ImageVector, val screen: @Composable () -> Unit)

val bottomNavItems = listOf(
    BottomNavItem(title = "Home", icon = Icons.Default.Home, screen = { MyScreen("Home Screen") }),
    BottomNavItem(title = "Favorite", icon = Icons.Default.Favorite, screen = { MyScreen("Favorite Screen") }),
    BottomNavItem(title = "Create", icon = Icons.Default.Create, screen = { MyScreen("Create Screen") }),
    BottomNavItem(title = "Settings", icon = Icons.Default.Settings, screen = { MyScreen("Settings Screen") })
)


@Preview(showBackground = true)
@Composable
fun MyScaffoldLayout() {
    var bottomNavItem by remember { mutableStateOf(bottomNavItems[0]) }

    Scaffold(
        bottomBar = {
            BottomNavigation(elevation = 8.dp, backgroundColor = MaterialTheme.colorScheme.surfaceContainer) {
                bottomNavItems.forEachIndexed { index, item ->
                    val selectedColor = if (bottomNavItem == item) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    val iconTint = rememberUpdatedState(selectedColor)
                    val imageVector = item.icon

                    BottomNavigationItem(
                        selected = bottomNavItem == item,
                        alwaysShowLabel = false,
                        icon = { Icon(imageVector = imageVector, contentDescription = item.title, tint = iconTint.value) },
                        label = { Text(text = item.title, color = selectedColor, style = MaterialTheme.typography.bodyMedium) },
                        onClick = { bottomNavItem = item }
                    )

                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            bottomNavItem.screen()
        }
    }
}



/*

@Preview(showBackground = true)
@Composable
fun MyScaffoldLayout() {
    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Favorite", Icons.Default.Favorite),
        BottomNavItem("Create", Icons.Default.Create),
        BottomNavItem("Settings", Icons.Default.Settings)
    )
    var selectedItem by remember { mutableStateOf(bottomNavItems[0]) }

    Scaffold(
        bottomBar = {
            BottomNavigation(elevation = 8.dp, backgroundColor = MaterialTheme.colorScheme.surfaceContainer) {
                bottomNavItems.forEach { item ->
                    val selectedColor = if (selectedItem == item) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    val iconTint = rememberUpdatedState(selectedColor)
                    val imageVector = item.icon

                    BottomNavigationItem(
                        selected = selectedItem == item,
                        alwaysShowLabel = false,
                        icon = { Icon(imageVector = imageVector, contentDescription = item.title, tint = iconTint.value) },
                        label = { Text(text = item.title, color = selectedColor, style = MaterialTheme.typography.bodyMedium) },
                        onClick = { selectedItem = item }
                    )

                }
            }
        }
    ) { innerPadding ->
        MyScreen(text = selectedItem.title, modifier = Modifier.padding(innerPadding))
    }
}

data class BottomNavItem(val title: String, val icon: ImageVector)

@Composable
fun MyScreen(text: String, modifier: Modifier) {
    Column(
        modifier = Modifier.fillMaxSize().then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = text)
    }
}

 */