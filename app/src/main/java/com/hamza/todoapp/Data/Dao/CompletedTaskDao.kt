package com.hamza.todoapp.Data.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hamza.todoapp.Data.Models.Task

@Dao
interface CompletedTaskDao {

    @Query("SELECT * FROM Tasks")
    fun getAllCompletedTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletedTask(task: Task)

    @Delete
    suspend fun deleteCompletedTask(task: Task)

    @Query("DELETE FROM tasks")
    suspend fun deleteAllCompletedTasks()
}
