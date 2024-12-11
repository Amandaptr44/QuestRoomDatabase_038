package com.example.pt10.repository

import com.example.pt10.data.dao.MahasiswaDao
import com.example.pt10.data.entity.Mahasiswa

class LocalRepositoryMhs (
    private val  mahasiswaDao: MahasiswaDao
): RepositoryMhs{
    override suspend fun insertMhs(mahasiswa: Mahasiswa){
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }
}
