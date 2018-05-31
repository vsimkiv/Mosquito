package com.softserve.mosquito.services;

import com.softserve.mosquito.controllers.BoardController;
import com.softserve.mosquito.dtos.Board;
import com.softserve.mosquito.dtos.Card;
import com.softserve.mosquito.dtos.List;
import com.softserve.mosquito.dtos.TaskCreateDto;
import com.softserve.mosquito.entities.Task;
import com.softserve.mosquito.services.impl.TaskServiceImpl;

public class CardService {

    private BoardController boardController = new BoardController();


    public void getTasksFromTrello(){

        for (Board board : boardController.getAllBoards()){
            for (List list : boardController.getListByBoard(board.getId())){
                if (list.getName().equalsIgnoreCase("todo")){
                    createTasksFromCards(boardController.getCardsByList(list.getId()), "todo", board.getName());
                }
                if (list.getName().equalsIgnoreCase("doing")){
                    createTasksFromCards(boardController.getCardsByList(list.getId()), "doing", board.getName());
                }
                if (list.getName().equalsIgnoreCase("done")){
                    createTasksFromCards(boardController.getCardsByList(list.getId()), "done", board.getName());
                }
            }

        }
    }

    private void createTasksFromCards(Card[] cards, String status, String projectName){

        TaskServiceImpl taskService = new TaskServiceImpl();
        TaskCreateDto taskCreateDto = new TaskCreateDto();
        taskCreateDto.setName(projectName);
        taskCreateDto.setOwnerId(boardController.getUserID());
        taskCreateDto.setWorkerId(boardController.getUserID());
        Task task = taskService.createTask(taskCreateDto);

        for (Card card : cards){
            TaskCreateDto trelloTask = new TaskCreateDto();
            trelloTask.setName(card.getName());
            trelloTask.setWorkerId(boardController.getUserID());
            trelloTask.setOwnerId(boardController.getUserID());
            trelloTask.setParentId(task.getId());
            taskService.createTask(trelloTask);
        }

    }
}
