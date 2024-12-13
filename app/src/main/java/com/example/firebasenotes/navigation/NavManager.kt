package com.example.firebasenotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.firebasenotes.viewModels.LoginViewModel
import com.example.firebasenotes.viewModels.NotesViewModel
import com.example.firebasenotes.views.login.BlankView
import com.example.firebasenotes.views.login.TabsView
import com.example.firebasenotes.views.notes.AddNotesView
import com.example.firebasenotes.views.notes.EditNoteView
import com.example.firebasenotes.views.notes.HomeView

@Composable
fun NavManager(LoginVM : LoginViewModel, NotesVM : NotesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Blank"){
        composable("Blank"){
            BlankView(navController)
        }
        composable("Login"){
            TabsView(navController, LoginVM)
        }
        composable("Home"){
            HomeView(navController,NotesVM)
        }
        composable("AddView"){
            AddNotesView(navController, NotesVM)
        }
        composable("EditNoteView/{idDoc}", arguments = listOf(
            navArgument("idDoc"){ type = NavType.StringType }
        )){
            val idDoc = it.arguments?.getString("idDoc") ?: ""
            EditNoteView(navController, NotesVM, idDoc)
        }
    }
}