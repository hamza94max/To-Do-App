package com.hamza.todoapp.data.local.tasks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamza.todoapp.domain.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM Tasks WHERE isCompleted = 0 AND date >= :currentDate")
    fun getTasks(currentDate: String): Flow<List<Task>>

    @Query("SELECT * FROM Tasks WHERE isCompleted = 1")
    fun getCompletedTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Tasks WHERE isCompleted = 0 AND date < :currentDate")
    fun getOverdueTasks(currentDate: String): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("UPDATE Tasks SET isCompleted = 1 WHERE id = :taskID")
    fun completeTask(taskID: Int): Int


    @Query("DELETE FROM tasks")
    fun deleteAllTasks()
}
