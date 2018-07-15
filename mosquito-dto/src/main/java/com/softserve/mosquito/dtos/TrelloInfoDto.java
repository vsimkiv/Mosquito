package com.softserve.mosquito.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrelloInfoDto {

    private Long id;
    private UserDto userDto;
    private String userTrelloName;
    private String userTrelloKey;
    private String userTrelloToken;

}
