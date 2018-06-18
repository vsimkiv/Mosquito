package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.Task;

import java.util.List;

public interface TaskRepo extends GenericCRUD<Task> {
    List<Task> getSubTasks(Long id);
    List<Task> getProjects();
    Task getByName(String name);
}
