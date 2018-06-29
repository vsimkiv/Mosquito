
package com.softserve.mosquito.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_trello")
public class TrelloInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @Column(name = "username")
    @NonNull
    private String userTrelloName;

    @Column(name = "access_key")
    @NonNull
    private String userTrelloKey;

    @Column(name = "access_token")
    @NonNull
    private String userTrelloToken;
}

