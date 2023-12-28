package com.example.kyoz.archive;

//import com.example.kyoz.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String link;

    private LocalDateTime createDate;

//    @OneToMany(mappedBy = "archive", cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
}
