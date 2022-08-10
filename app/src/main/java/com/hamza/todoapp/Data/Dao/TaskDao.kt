package com.hamza.todoapp.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamza.todoapp.Data.Models.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}
