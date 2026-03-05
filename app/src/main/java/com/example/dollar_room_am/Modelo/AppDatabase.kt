package com.example.dollar_room_am.Modelo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DollarTransaction::class], version = 1)
//Una abstract class (clase abstracta) en Kotlin o Java es una clase que sirve como plantilla y no se puede instanciar directamente. 🧩
//Se usa para definir propiedades y métodos que otras clases heredarán.

abstract class AppDatabase : RoomDatabase() {

    //dao para acceder a las operaciones de la tabla

    abstract fun dollarTransactionDao(): DollarTransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            //SI YA EXISTE  SE CREA LA BASE DE DATOS
            //SI NO EXISTE SE CREA LA BASE DE DATOS
            return INSTANCE ?: synchronized(this) {
                //crear la base de datos
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance



            }



        }
    }
}


