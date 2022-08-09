package com.hamza.todoapp.Data.Dao

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.hamza.todoapp.Data.DataBase.TaskDataBase
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.Util.Priority
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDaoTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataBase: TaskDataBase
    private lateinit var dao: TaskDao

    @Before
    fun setup() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDataBase::class.java
        ).allowMainThreadQueries()
            .build()

        dao = dataBase.getTaskDao()
    }


    @After
    fun teardown() {
        dataBase.close()
    }


    @Test
    fun insertTask() = runBlockingTest {
        val task = Task(5, "title", "date", "time", Priority.HIGH, false)
        dao.insertTask(task)
        dao.insertTask(task)

        val allTasks = dao.getAllTasks().value
        Log.d("saeed ", allTasks?.size.toString())

        //assertThat(allTasks).isNotEmpty()


    }


}