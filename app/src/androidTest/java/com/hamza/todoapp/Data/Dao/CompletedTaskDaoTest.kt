package com.hamza.todoapp.Data.Dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.hamza.todoapp.Data.DataBase.CompletedTaskDataBase
import com.hamza.todoapp.Data.Models.Task
import com.hamza.todoapp.Util.Priority
import com.hamza.todoapp.getOrAwaitValue
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
class CompletedTaskDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dataBase: CompletedTaskDataBase
    private lateinit var dao: CompletedTaskDao

    @Before
    fun setup() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CompletedTaskDataBase::class.java
        ).allowMainThreadQueries()
            .build()

        dao = dataBase.getCompletedTaskDao()
    }


    @After
    fun teardown() {
        dataBase.close()
    }


    @Test
    fun insertCompletedTask() = runBlockingTest {
        val task = Task(5, "title", "date", "time", Priority.HIGH, false)
        dao.insertCompletedTask(task)

        val allTasks = dao.getAllCompletedTasks().getOrAwaitValue()


        Truth.assertThat(allTasks).contains(task)
    }

    @Test
    fun deleteCompletedTask() = runBlockingTest {
        val task = Task(5, "title", "date", "time", Priority.HIGH, false)
        dao.insertCompletedTask(task)

        dao.deleteCompletedTask(task)
        val allTasks = dao.getAllCompletedTasks().getOrAwaitValue()


        Truth.assertThat(allTasks).doesNotContain(task)
    }


    @Test
    fun deleteAllCompletedTasks() = runBlockingTest {
        val task1 = Task(5, "title", "date", "time", Priority.HIGH, false)
        val task2 = Task(2, "title", "date", "time", Priority.HIGH, false)
        val task3 = Task(1, "title", "date", "time", Priority.HIGH, false)

        dao.insertCompletedTask(task1)
        dao.insertCompletedTask(task2)

        dao.insertCompletedTask(task3)

        dao.deleteAllCompletedTasks()

        val allTasks = dao.getAllCompletedTasks().getOrAwaitValue()


        Truth.assertThat(allTasks).isEmpty()
    }


}