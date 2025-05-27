package com.example.basedatos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE phone = (:phone)")
    List<User> selectByPhone(String phone);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);
}