package com.softserve.mosquito.repo.api;

import com.softserve.mosquito.entities.Comment;

import java.util.List;

/**
 * Created by student on 5/25/18.
 */

public interface CommentRepo extends GenericCRUD<Comment> {

    List<Comment> getByTaskId(Long taskId);

}
