package com.example.obm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.obm.question.Question;
import com.example.obm.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class ObmApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

}
