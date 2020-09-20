package com.example.goaltracker.Service.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.goaltracker.Model.Evidence
import com.example.goaltracker.Model.Goal
import com.example.goaltracker.Model.Habit
import com.example.goaltracker.Service.Dao.EvidenceDao
import com.example.goaltracker.Service.Dao.GoalDao
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Evidence::class, Goal::class, Habit::class), version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {

    abstract fun evidenceDao(): EvidenceDao
    abstract fun goalDao(): GoalDao

    companion object{
        // singleton prevernts multiple instances of database opening at the same time.
        @Volatile
        private var instance: MainDatabase? = null

        fun getDatabaseInstance(context: Context, scope: CoroutineScope): MainDatabase{
            val temp1Instance = instance
            if(temp1Instance != null)
                return temp1Instance

            synchronized(this){
                val temp2Instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_database"
                )
//                    .addCallback(EvidenceDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                instance = temp2Instance
                return temp2Instance
            }

        }
    }

//    private class EvidenceDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {


//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            instance?.let { database ->
//                scope.launch {
//                    populateDatabase(database.evidenceDao())
//                }
//            }
//        }
//
//        suspend fun populateDatabase(evidenceDao: EvidenceDao){
//
//        }
//
//
//    }


}