package com.example.firebasenotes.views.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.firebasenotes.components.CardNote
import com.example.firebasenotes.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, noteVM: NotesViewModel){

    LaunchedEffect(Unit) {
        noteVM.fetchNotes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Mis Notas") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            noteVM.signOut()
                            navController.popBackStack()
                                  },
                    ) {
                        Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("AddView")
                        },
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val datos by noteVM.notesData.collectAsState()
            LazyColumn {
                items(datos){item ->  
                    CardNote(title = item.title, note = item.note, date = item.date) {
                        navController.navigate("EditNoteView/${item.idDoc}")
                    }
                }
            }
        }
    }
}