package com.vogella.android.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

    @Query("select * from user")
    public List<User> getAllUser();

    @Query("select * from user where id = :userId")
    public List<User> getUser(long userId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Query("delete from user")
    void removeAllUsers();
}
