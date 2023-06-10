package com.example.readscape

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.readscape.data.BookDatabase
import com.example.readscape.model.User
import com.example.readscape.model.UserDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: BookDatabase
    private val user = User(0, "test", "test")

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, BookDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() = db.close()

    @Test
    @Throws(Exception::class)
    suspend fun insertAndRetrieve() {
        userDao.insertUsers(user)
        val users = userDao.getAllUsers()
        assert(users.size == 1)
    }
}