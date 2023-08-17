package com.dinesh.android.compose.scaffold_layout.v1

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.*
import androidx.compose.material.icons.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
data class ShowTab(
    val showTab: Boolean,
    val tabPagerState: PagerState,
    val tabCoroutineScope: CoroutineScope
)
data class ShowBottomNav(
    val showBottomNav: Boolean,
    val bottomNavItem: BottomNavItem,
    val onNavItemClicked: (BottomNavItem) -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun MyLayoutView() {
    val tabPagerState = rememberPagerState{ tabItems.size }
    val tabCoroutineScope = rememberCoroutineScope()

    var bottomNavItem by remember { mutableStateOf(bottomNavItems[0]) }

    MyScaffoldLayout(
        tab = ShowTab(showTab = true, tabPagerState = tabPagerState, tabCoroutineScope = tabCoroutineScope),
        bottomNav = ShowBottomNav(showBottomNav = true, bottomNavItem = bottomNavItem, onNavItemClicked = { bottomNavItem = it })
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout(tab: ShowTab, bottomNav: ShowBottomNav) {
    Scaffold(
        topBar = {
            if (tab.showTab){
                CenterAlignedTopAppBar(
                    title = { Text(text = "Jetpack Compose TabLayout", color = MaterialTheme.colorScheme.onSurface) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                )
            }
        }, bottomBar = {
            if (bottomNav.showBottomNav) {
                MyBottomNavigationLayout(
                    currentNavItem = bottomNav.bottomNavItem,
                    onNavItemClicked = bottomNav.onNavItemClicked
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (tab.showTab){
                MyTabLayout(tabItems, tab.tabPagerState, tab.tabCoroutineScope)
            }
            if (bottomNav.showBottomNav) {
                bottomNav.bottomNavItem.screen()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun MyTabLayout(tabItems: List<TabItem>, tabPagerState: PagerState, tabCoroutineScope: CoroutineScope) {
    TabRow(selectedTabIndex = tabPagerState.currentPage, backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                selected = tabPagerState.currentPage == index,
                text = { Text(text = item.title) },
                icon = { Icon(item.icon, "") },
                onClick = { tabCoroutineScope.launch { tabPagerState.animateScrollToPage(index) } }
            )
        }
    }

    HorizontalPager(state = tabPagerState) {
        tabItems[tabPagerState.currentPage].screen()
    }
}

@Composable
private fun MyBottomNavigationLayout(currentNavItem: BottomNavItem, onNavItemClicked: (BottomNavItem) -> Unit) {
    BottomNavigation(elevation = 8.dp, backgroundColor = MaterialTheme.colorScheme.surfaceContainer) {
        bottomNavItems.forEachIndexed { index, item ->
            val bottomNavColor = if (currentNavItem == item) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            BottomNavigationItem(
                selected = currentNavItem == item,
                alwaysShowLabel = false,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title, tint = bottomNavColor) },
                label = { Text(text = item.title, color = bottomNavColor, style = MaterialTheme.typography.bodyMedium) },
                onClick = { onNavItemClicked(item) }
            )
        }
    }
}


@Composable
fun MyScreen(text: String){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = text)
    }
}

data class TabItem(val title: String, val icon: ImageVector, val screen: @Composable () -> Unit)

val tabItems = listOf(
    TabItem(title = "Account", icon = Icons.Filled.AccountBox, screen = { MyScreen("Account Screen") }),
    TabItem(title = "Favorite", icon = Icons.Filled.Favorite, screen = { MyScreen("Favorite Screen") }),
    TabItem(title = "Place", icon = Icons.Filled.Place, screen = { MyScreen("Place Screen") })
)

data class BottomNavItem(val title: String, val icon: ImageVector, val screen: @Composable () -> Unit)

val bottomNavItems = listOf(
    BottomNavItem(title = "Home", icon = Icons.Default.Home, screen = { MyScreen("Home Screen") }),
    BottomNavItem(title = "Favorite", icon = Icons.Default.Favorite, screen = { MyScreen("Favorite Screen") }),
    BottomNavItem(title = "Create", icon = Icons.Default.Create, screen = { MyScreen("Create Screen") }),
    BottomNavItem(title = "Settings", icon = Icons.Default.Settings, screen = { MyScreen("Settings Screen") })
)

@Composable
private fun MyCustomLayout() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "MyCustomLayout without ScaffoldLayout")
    }
}


/*


@Preview(showBackground = true)
@Composable
fun MyLayout() {
    MyScaffoldLayout(
        showBottomNavigation = true,
        showToolbar = true
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout(showBottomNavigation: (Boolean), showToolbar: Boolean) {
    val tabPagerState = rememberPagerState{ tabItems.size }
    val tabCoroutineScope = rememberCoroutineScope()

    var bottomNavItem by remember { mutableStateOf(bottomNavItems[0]) }

    Scaffold(
        topBar = {
            if (showToolbar) {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Jetpack Compose TabLayout", color = MaterialTheme.colorScheme.onSurface) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                )
            }
        }, bottomBar = {
            if (showBottomNavigation) {
                MyBottomNavigationLayout(currentNavItem = bottomNavItem, onNavItemClicked = { bottomNavItem = it })
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (showToolbar) MyTabLayout(tabItems, tabPagerState, tabCoroutineScope)
            if (showBottomNavigation) { bottomNavItem.screen() } else { MyCustomLayout() }
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun MyTabLayout(tabItems: List<TabItem>, tabPagerState: PagerState, tabCoroutineScope: CoroutineScope) {
    TabRow(selectedTabIndex = tabPagerState.currentPage, backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
        contentColor = MaterialTheme.colorScheme.onSurface) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                selected = tabPagerState.currentPage == index,
                text = { Text(text = item.title) },
                icon = { Icon(item.icon, "") },
                onClick = { tabCoroutineScope.launch { tabPagerState.animateScrollToPage(index) } }
            )
        }
    }

    HorizontalPager(state = tabPagerState) {
        tabItems[tabPagerState.currentPage].screen()
    }
}

@Composable
private fun MyBottomNavigationLayout(currentNavItem: BottomNavItem, onNavItemClicked: (BottomNavItem) -> Unit) {
    BottomNavigation(elevation = 8.dp, backgroundColor = MaterialTheme.colorScheme.surfaceContainer) {
        bottomNavItems.forEachIndexed { index, item ->
            val bottomNavColor = if (currentNavItem == item) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            BottomNavigationItem(
                selected = currentNavItem == item,
                alwaysShowLabel = false,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title, tint = bottomNavColor) },
                label = { Text(text = item.title, color = bottomNavColor, style = MaterialTheme.typography.bodyMedium) },
                onClick = { onNavItemClicked(item) }
            )
        }
    }
}


@Composable
fun MyScreen(text: String){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = text)
    }
}

data class TabItem(val title: String, val icon: ImageVector, val screen: @Composable () -> Unit)

val tabItems = listOf(
    TabItem(title = "Account", icon = Icons.Filled.AccountBox, screen = { MyScreen("Account Screen") }),
    TabItem(title = "Favorite", icon = Icons.Filled.Favorite, screen = { MyScreen("Favorite Screen") }),
    TabItem(title = "Place", icon = Icons.Filled.Place, screen = { MyScreen("Place Screen") })
)

data class BottomNavItem(val title: String, val icon: ImageVector, val screen: @Composable () -> Unit)

val bottomNavItems = listOf(
    BottomNavItem(title = "Home", icon = Icons.Default.Home, screen = { MyScreen("Home Screen") }),
    BottomNavItem(title = "Favorite", icon = Icons.Default.Favorite, screen = { MyScreen("Favorite Screen") }),
    BottomNavItem(title = "Create", icon = Icons.Default.Create, screen = { MyScreen("Create Screen") }),
    BottomNavItem(title = "Settings", icon = Icons.Default.Settings, screen = { MyScreen("Settings Screen") })
)

@Composable
private fun MyCustomLayout() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "MyCustomLayout without ScaffoldLayout")
    }
}


 */
