package com.softserve.mosquito.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "priorities")
public final class Priority implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String title;

    @OneToMany(mappedBy = "priority", fetch = FetchType.LAZY)
    private List<Task> tasks = new ArrayList<>();


    public Priority(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}


