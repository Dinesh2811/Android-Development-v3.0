package com.dinesh.android.compose.scaffold_layout.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
data class ShowTab(
    val showTab: Boolean,
    val tabPagerState: PagerState,
    val tabCoroutineScope: CoroutineScope
)

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun MyLayoutView() {
    val tabPagerState = rememberPagerState{ tabItems.size }
    val tabCoroutineScope = rememberCoroutineScope()

    MyScaffoldLayout(
        tab = ShowTab(showTab = true, tabPagerState = tabPagerState, tabCoroutineScope = tabCoroutineScope)
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout(tab: ShowTab) {
    Scaffold(
        topBar = {
            if (tab.showTab){
                CenterAlignedTopAppBar(
                    title = { Text(text = "Jetpack Compose TabLayout", color = MaterialTheme.colorScheme.onSurface) },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (tab.showTab){
                MyTabLayout(tabItems, tab.tabPagerState, tab.tabCoroutineScope)
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

val tabItems = listOf(
    TabItem(title = "Account", icon = Icons.Filled.AccountBox, screen = { MyScreen("Account Screen") }),
    TabItem(title = "Favorite", icon = Icons.Filled.Favorite, screen = { MyScreen("Favorite Screen") }),
    TabItem(title = "Place", icon = Icons.Filled.Place, screen = { MyScreen("Place Screen") })
)

data class TabItem(val title: String, val icon: ImageVector, val screen: @Composable () -> Unit)

@Composable
fun MyScreen(text: String){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = text)
    }
}



/*

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun MyLayoutView() {
    val tabPagerState = rememberPagerState{ tabItems.size }
    val tabCoroutineScope = rememberCoroutineScope()

    MyScaffoldLayout(tabItems = tabItems, tabPagerState = tabPagerState, tabCoroutineScope = tabCoroutineScope)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout(tabItems: List<TabItem>, tabPagerState: PagerState, tabCoroutineScope: CoroutineScope) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Jetpack Compose TabLayout", color = MaterialTheme.colorScheme.onSurface) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            MyTabLayout(tabItems, tabPagerState, tabCoroutineScope)
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

val tabItems = listOf(
    TabItem(title = "Account", icon = Icons.Filled.AccountBox, screen = { MyScreen("Account Screen") }),
    TabItem(title = "Favorite", icon = Icons.Filled.Favorite, screen = { MyScreen("Favorite Screen") }),
    TabItem(title = "Place", icon = Icons.Filled.Place, screen = { MyScreen("Place Screen") })
)

data class TabItem(val title: String, val icon: ImageVector, val screen: @Composable () -> Unit)

@Composable
fun MyScreen(text: String){
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = text)
    }
}


 */