package com.vogella.android.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel extends ViewModel {
    private MutableLiveData<List<Task>> tasks;

    public LiveData<List<Task>> getTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<List<Task>>();
            loadTasks();
        }
        return tasks;
    }

    private void loadTasks() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Task> list = new ArrayList<>();
        Task task = Task.builder().setId(1).setSummary("Testing ViewModel").build();
        list.add(task);
        tasks.setValue(list);
        // do async operation to fetch users
    }
}