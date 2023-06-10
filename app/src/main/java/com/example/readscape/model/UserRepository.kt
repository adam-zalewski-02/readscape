package com.example.readscape.model

class UserRepository(private val userDao: UserDao) {
    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }

    suspend fun getUserById(id: Long) : User? {
        return userDao.getUserById(id)
    }
    suspend fun getUserByEmail(email: String) : User? {
        return userDao.getUserByEmail(email)
    }
}