package com.example.firebasenotes.views.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firebasenotes.viewModels.LoginViewModel
import com.example.firebasenotes.viewModels.NotesViewModel

@Composable
fun TabsView(navController: NavController, LoginVM: LoginViewModel){
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Iniciar Sesisón", "Registrarse")

    Column(
        modifier = Modifier.padding(top = 34.dp)
    ) {
        TabRow(
            selectedTabIndex = selectedTab,
            indicator = { tabPosition ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPosition[selectedTab])
                )
            }
        ) {
            tabs.forEachIndexed{ index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(text = title) }
                    )
            }
        }
        when(selectedTab){
            0 -> LoginView(navController, LoginVM)
            1 -> RegisterView(navController, LoginVM)
        }
    }
}