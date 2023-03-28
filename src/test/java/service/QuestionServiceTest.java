package service;

import model.Question;
import org.junit.Assert;
import org.junit.Test;
import repositotyMock.QuestionRepositoryImplMock;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceTest {
    private List<Question> questionList = List.of(
            Question.builder().id(5).text("something").topic("TestTopic").build(),
            Question.builder().id(6).text("something").topic("TestTopic").build(),
            Question.builder().id(7).text("something").topic("TestTopic").build());
    private  List<Question> questions = new ArrayList<>();
    private QuestionRepositoryImplMock questionRepository = new QuestionRepositoryImplMock(questionList);

    @Test
    public void getRandomQuestionByTopicTest() {
        QuestionService questionService = new QuestionService(questionRepository);
        Question randomQuestionByTopic = questionService.getRandomQuestionByTopic("TestTopic");
        Assert.assertTrue(questionRepository.getTestQuestions().contains(randomQuestionByTopic));
    }

    @Test
    public void getRandomQuestionTest() {
        QuestionService questionService = new QuestionService(questionRepository);
        Question randomQuestion = questionService.getRandomQuestion();
        Assert.assertTrue(questionList.contains(randomQuestion));
    }

    @Test
    public void addQuestionTest() {
        questions.addAll(questionList);
        QuestionService questionService1 = new QuestionService(new QuestionRepositoryImplMock(questions));
        Question actualQuestion = Question.builder().id(1).text("something from java").topic("TopicJAVA").build();
        questionService1.addQuestion(1, "something from java", "TopicJAVA");
        Assert.assertTrue(questions.contains(actualQuestion));
        questions.remove(actualQuestion);
    }
    @Test
    public void deleteQuestionTest() {
        questions.addAll(questionList);
        QuestionService questionService2 = new QuestionService(new QuestionRepositoryImplMock(questions));
        Question actualQuestion = Question.builder().id(1).text("something from java").topic("TopicJAVA").build();
        questionService2.addQuestion(1, "something from java", "TopicJAVA");
        questionService2.deleteQuestion(1);
        Assert.assertTrue(!questions.contains(actualQuestion));
    }
}
