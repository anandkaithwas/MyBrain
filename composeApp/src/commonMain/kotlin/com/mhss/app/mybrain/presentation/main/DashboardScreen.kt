package com.mhss.app.mybrain.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mhss.app.presentation.CalendarDashboardWidget
import com.mhss.app.presentation.TasksDashboardWidget
import com.mhss.app.ui.Res
import com.mhss.app.ui.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

private val MindInk = Color(0xFF172923)
private val MindMint = Color(0xFF42B995)
private val MindDeepMint = Color(0xFF238C70)
private val MindCream = Color(0xFFFFFDF8)

@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: MainViewModel = koinViewModel()
) {
    Scaffold { paddingValues ->
        LaunchedEffect(true) { viewModel.onDashboardEvent(DashboardEvent.InitAll) }
        val dashboardTasks = viewModel.dashboardTasks.collectAsLazyPagingItems()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Column(Modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 14.dp)) {
                    Text(
                        text = "MyMind",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MindInk
                    )
                    Text(
                        text = "Your world, in sync.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            item { MiraWelcomeCard(onTalkToMira = { navController.navigate(Screen.AssistantScreen) }) }
            item {
                Text(
                    text = "Your day",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MindInk,
                    modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)
                )
            }
            item {
                CalendarDashboardWidget(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp).aspectRatio(1.5f),
                    events = viewModel.uiState.dashBoardEvents,
                    onClick = { navController.navigate(Screen.CalendarScreen) },
                    onPermission = { viewModel.onDashboardEvent(DashboardEvent.ReadPermissionChanged(it)) },
                    onAddEventClicked = { navController.navigate(Screen.CalendarEventDetailsScreen()) },
                    onEventClicked = { navController.navigate(Screen.CalendarEventDetailsScreen(it.id)) }
                )
            }
            item {
                TasksDashboardWidget(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp).aspectRatio(1.5f),
                    tasks = dashboardTasks,
                    onCheck = { task, completed -> viewModel.onDashboardEvent(DashboardEvent.CompleteTask(task, completed)) },
                    onTaskClick = { navController.navigate(Screen.TaskDetailScreen(it.id)) },
                    onAddClick = { navController.navigate(Screen.TasksScreen(addTask = true)) },
                    onClick = { navController.navigate(Screen.TasksScreen()) }
                )
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun MiraWelcomeCard(onTalkToMira: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MindCream),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().background(
                Brush.horizontalGradient(listOf(Color(0xFFE6F6EF), Color(0xFFFFF7E9)))
            ).padding(18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier.weight(0.85f).aspectRatio(0.78f)
                    .background(
                        Brush.verticalGradient(listOf(Color(0xFFBCE9D7), Color(0xFF75C9AA))),
                        RoundedCornerShape(22.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("M", style = MaterialTheme.typography.displayLarge, color = MindDeepMint, fontWeight = FontWeight.Bold)
            }
            Column(Modifier.weight(1.5f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text("Mira", style = MaterialTheme.typography.titleLarge, color = MindInk, fontWeight = FontWeight.Bold)
                Text("Your personal assistant", style = MaterialTheme.typography.labelMedium, color = MindDeepMint, fontWeight = FontWeight.SemiBold)
                Text(
                    "A calmer day starts with a clear mind. I’m here to help you stay on track.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MindInk
                )
                Text("YOUR WORLD, IN SYNC", style = MaterialTheme.typography.labelSmall, color = MindDeepMint, fontWeight = FontWeight.Bold)
                androidx.compose.material3.FilledTonalButton(
                    onClick = onTalkToMira,
                    shape = RoundedCornerShape(18.dp),
                    colors = androidx.compose.material3.ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(0xFF238C70),
                        contentColor = Color.White
                    )
                ) {
                    Text("🎙  Talk to Mira", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
