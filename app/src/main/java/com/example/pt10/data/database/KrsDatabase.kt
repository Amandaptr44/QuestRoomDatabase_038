package com.example.pt10.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pt10.data.dao.MahasiswaDao
import com.example.pt10.data.entity.Mahasiswa

@Database(entities = [Mahasiswa::class], version = 1, exportSchema = false )
abstract class KrsDatabase : RoomDatabase(){
    // Mendefinisikan fungsi untuk mengakses data Mahasiswa
    abstract fun MahasiswaDao(): MahasiswaDao

    companion object{
        @Volatile // Memastikan nilai variabel instance selalu sama di semua
        private var Instance : KrsDatabase? = null

        fun getDatabase(context: Context) : KrsDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    KrsDatabase::class.java, //Class database
                    "KrsDatabase" //Nama database
                )
                    .build().also { Instance = it }
            })
        }
    }
}
