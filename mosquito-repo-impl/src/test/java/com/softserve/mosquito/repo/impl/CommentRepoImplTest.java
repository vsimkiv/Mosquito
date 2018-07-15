package com.softserve.mosquito.repo.impl;

import com.softserve.mosquito.entities.Comment;
import com.softserve.mosquito.repo.api.CommentRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestRepoConfig.class})
public class CommentRepoImplTest {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    DataSource dataSource;

    @Test
    public void crudTest() {
        // create comment
        Comment comment = new Comment();
        Comment created = commentRepo.create(comment);
        Long id = created.getId();
        assertNotNull(id);

        //update comment
        created.setText("Test update");
        Comment updated = commentRepo.update(created);
        assertNotNull(updated);
        assertEquals("Test update", updated.getText());

        //read comment
        Comment gotComment = commentRepo.read(id);
        assertNotNull(gotComment);

        //delete comment
        commentRepo.delete(id);
        assertNull(commentRepo.read(id));

    }

}
