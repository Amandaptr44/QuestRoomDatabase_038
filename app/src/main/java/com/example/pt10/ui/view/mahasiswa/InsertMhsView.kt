package com.example.praktikumpertemuan10.ui.view.mahasiswa

import android.text.Editable.Factory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pt10.ui.costumwidget.TopAppBar
import com.example.pt10.ui.navigation.AlamatNavigasi
import com.example.pt10.ui.viewmodel.FormErrorState
import com.example.pt10.ui.viewmodel.MahasiswaEvent
import com.example.pt10.ui.viewmodel.MahasiswaViewModel
import com.example.pt10.ui.viewmodel.MhsUIState
import com.example.pt10.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiInsert : AlamatNavigasi {
    override val route: String = "insert_mhs"
}

@Composable
fun InserMhsView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MahasiswaViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ){
        val uiState = viewModel.uiState //Ambil UI state dari ViewModel
        val snackbarHostState = remember{SnackbarHostState()} //Snackbar state
        val coroutineScope = rememberCoroutineScope()

        // Obserasi perubahan snackbarMessage
        LaunchedEffect(uiState.snackbarMessage){
            uiState.snackbarMessage?.let {message ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message) //Tampilkan snackbar
                    viewModel.resetSnackBarMessage()
                }
            }
        }
    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) } //
    ){padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Mahasiswa"
            )
            //Isi Body
            InsertBodyMhs(
                uiState = uiState,
                onValueChange = {updateEvent ->
                    viewModel.updateState(updateEvent) //Update state di view
                },
                onClick = {
                    coroutineScope.launch {
                        viewModel.saveData() // Simpan Data
                    }
                    onNavigate()
                }
            )
        }
    }
}

