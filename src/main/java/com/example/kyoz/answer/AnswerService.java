package com.example.kyoz.answer;

import com.example.kyoz.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.kyoz.user.SiteUser;


import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
}
