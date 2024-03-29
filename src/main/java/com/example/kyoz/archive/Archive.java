package com.example.kyoz.archive;

//import com.example.kyoz.answer.Answer;

import com.example.kyoz.user.SiteUser;
import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


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

    @Column(columnDefinition = "TEXT")
    private String grade;

    private LocalDateTime createDate;



    @ManyToOne
    private SiteUser author;

//    @OneToMany(mappedBy = "archive", cascade = CascadeType.REMOVE)
//    private List<Answer> answerList;
}
