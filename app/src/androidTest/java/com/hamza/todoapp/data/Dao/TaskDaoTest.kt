package com.hamza.todoapp.data.Dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.hamza.todoapp.data.local.tasks.TaskDao
import com.hamza.todoapp.data.local.tasks.TasksDataBase
import com.hamza.todoapp.domain.models.Task
import com.hamza.todoapp.getOrAwaitValue
import com.hamza.todoapp.utils.Priority
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

    private lateinit var dataBase: TasksDataBase
    private lateinit var dao: TaskDao

    @Before
    fun setup() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TasksDataBase::class.java
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

        val allTasks = dao.getAllTasks().getOrAwaitValue()


        assertThat(allTasks).contains(task)
    }

    @Test
    fun deleteTask() = runBlockingTest {
        val task = Task(5, "title", "date", "time", Priority.HIGH, false)
        dao.insertTask(task)

        dao.deleteTask(task)
        val allTasks = dao.getAllTasks().getOrAwaitValue()


        assertThat(allTasks).doesNotContain(task)
    }


    @Test
    fun deleteAllTask() = runBlockingTest {
        val task1 = Task(5, "title", "date", "time", Priority.HIGH, false)
        val task2 = Task(2, "title", "date", "time", Priority.HIGH, false)
        val task3 = Task(1, "title", "date", "time", Priority.HIGH, false)

        dao.insertTask(task1)
        dao.insertTask(task2)

        dao.insertTask(task3)

        dao.deleteAllTasks()

        val allTasks = dao.getAllTasks().getOrAwaitValue()


        assertThat(allTasks).isEmpty()
    }


}