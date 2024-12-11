package com.example.pt10.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.pt10.data.entity.Mahasiswa

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
}