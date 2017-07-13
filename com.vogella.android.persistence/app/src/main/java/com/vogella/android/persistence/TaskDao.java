package com.vogella.android.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTask(Task task);

    @Query("select * from task")
    public List<Task> getAllTasks();

    @Query("select * from task where id = :id")
    public List<Task> getTask(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(Task task);

    @Query("delete from task")
    void removeAllTasks();
}