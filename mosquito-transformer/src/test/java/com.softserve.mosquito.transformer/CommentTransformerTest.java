package com.softserve.mosquito.transformer;


import com.softserve.mosquito.dtos.CommentDto;
import com.softserve.mosquito.entities.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
 public class CommentTransformerTest {

    @Test
    public void toEntity() {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(10L);
        commentDto.setText("Test Comment");

        //Чи тут потрібно передавати Dto oбєкти при тестуванні
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
        Task task = Task.builder().name("Test task").estimation(estimation)
                .owner(user).worker(user).priority(priority).status(status).build();
        commentDto.setAuthorId(user.getId());
        commentDto.setTaskId(task.getId());
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setTask(new Task(commentDto.getTaskId()));
        comment.setAuthor(new User(commentDto.getAuthorId()));
        assertEquals(commentDto.getId(), comment.getId());
        assertEquals(commentDto.getText(), comment.getText());
        assertEquals(commentDto.getAuthorId(), comment.getAuthor().getId());

    }

    @Test
    public void toDTO() {
        Comment comment = new Comment();
        comment.setText("Test Comment Text");
        comment.setId(20L);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
        Task task = Task.builder().name("Test task").estimation(estimation)
                .owner(user).worker(user).priority(priority).status(status).build();
        comment.setAuthor(user);
        comment.setTask(task);
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setTaskId(comment.getTask().getId());
        commentDto.setAuthorId(comment.getAuthor().getId());

        assertEquals(comment.getId(), commentDto.getId());
        assertEquals(comment.getAuthor().getId(), commentDto.getAuthorId());
    }

    @Test
   public void toDTOList() {
        Comment comment1 = new Comment();
        comment1.setText("Test Comment Text");
        comment1.setId(20L);
        Estimation estimation = new Estimation();
        estimation.setTimeEstimation(25);
        estimation.setRemaining(25);
        User user = new User();
        user.setEmail("test_email");
        user.setPassword("test_password");
        user.setFirstName("test_name");
        user.setLastName("test_surname");
        user.setConfirmed(true);
        Priority priority = new Priority("middle");
        Status status = new Status("TODO");
        Task task = Task.builder().name("Test task").estimation(estimation)
                .owner(user).worker(user).priority(priority).status(status).build();
        comment1.setAuthor(user);
        comment1.setTask(task);

        Comment comment2 = new Comment();
        comment2.setText("New Test Comment Text");
        comment1.setId(30L);
        Estimation estimation1 = new Estimation();
        estimation1.setTimeEstimation(25);
        estimation1.setRemaining(25);
        User user1 = new User();
        user1.setEmail("test_email");
        user1.setPassword("test_password");
        user1.setFirstName("test_name");
        user1.setLastName("test_surname");
        user1.setConfirmed(true);
        Priority priority1 = new Priority("middle");
        Status status1 = new Status("TODO");
        Task task1 = Task.builder().name("Test task two").estimation(estimation1)
                .owner(user1).worker(user1).priority(priority1).status(status1).build();
        comment2.setAuthor(user1);
        comment2.setTask(task1);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment1);
        comments.add(comment2);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment commentt:comments) {
            commentDtos.add(CommentTransformer.toDTO(commentt));//Чи можна викликати toDto iз трансформера
        }


    }
}