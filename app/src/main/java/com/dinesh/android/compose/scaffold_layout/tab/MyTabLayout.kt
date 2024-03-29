package com.dinesh.android.compose.scaffold_layout.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        showTopAppBar = true,
        tab = ShowTab(showTab = true, tabPagerState = tabPagerState, tabCoroutineScope = tabCoroutineScope)
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyScaffoldLayout(showTopAppBar: Boolean = true, tab: ShowTab) {
//    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val isCollapsed by remember { derivedStateOf { (scrollBehavior.state.heightOffset) != 0F } }
    val surfaceColor = if (isCollapsed) MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp) else MaterialTheme.colorScheme.surface

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            if (showTopAppBar) {
                MyTopAppBar(scrollBehavior, surfaceColor)
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            if (tab.showTab){
                MyTabLayout(tabItems, tab.tabPagerState, tab.tabCoroutineScope, surfaceColor)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MyTopAppBar(scrollBehavior: TopAppBarScrollBehavior, surfaceColor: Color) {
    MediumTopAppBar(
        title = {
            Text(text = "Jetpack Compose TabLayout", maxLines = 1,
                overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.onSurface, style = MaterialTheme.typography.titleMedium)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = surfaceColor),
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
            }
        }, scrollBehavior = scrollBehavior
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun MyTabLayout(tabItems: List<TabItem>, tabPagerState: PagerState, tabCoroutineScope: CoroutineScope, surfaceColor: Color) {
    TabRow(selectedTabIndex = tabPagerState.currentPage,
        backgroundColor = surfaceColor,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
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
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(100) { index ->
            Text(text = "$text : $index", modifier = Modifier.padding(8.dp))
        }
    }
}

