package com.example.pt10.dependeciesinjection

import android.content.Context
import com.example.pt10.data.database.KrsDatabase
import com.example.pt10.repository.LocalRepositoryMhs
import com.example.pt10.repository.RepositoryMhs

interface InterfaceContainerApp {
    val repositoryMhs: RepositoryMhs
}

class ContainerApp (private val context: Context) : InterfaceContainerApp {
    override val repositoryMhs:RepositoryMhs by lazy {
        LocalRepositoryMhs(KrsDatabase.getDatabase(context).MahasiswaDao())
    }
}