package repository;

import model.Question;
import model.Question;
import org.junit.Assert;
import org.junit.Test;
import service.QuestionService;
import java.util.List;

public class QuestionRepositoryImplTest {
    private List<Question> testQuestions = List.of(
            Question.builder().id(5).text("something").topic("TestTopic").build(),
            Question.builder().id(6).text("something").topic("TestTopic").build(),
            Question.builder().id(7).text("something").topic("TestTopic").build());
    Question question = Question.builder()
            .id(5)
            .text("What is private")
            .topic("topic111")
            .build();

    @Test
    public void getRndmQuestionTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl();
        System.out.println(quest.getRndmQuestion());
    }
    @Test
    public void getTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl();
        quest.save(question);
        Question result = quest.get(question.getId());
        Assert.assertEquals(question,result);
        quest.delete(question.getId());
    }
    @Test
    public void getByTopicTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl();
        for (Question q:testQuestions) {
            quest.save(q);
        }
        List<Question> result = quest.getByTopic(testQuestions.get(1).getTopic());
        Assert.assertTrue(testQuestions.equals(result));
        for (Question q:testQuestions) {
            quest.delete(q.getId());
        }
    }
    @Test
    public void deleteTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl();
        quest.save(question);
        quest.delete(question.getId());
    }
    @Test
    public void saveTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl();
        quest.save(question);
        Question result = quest.get(question.getId());
        Assert.assertEquals(question,result);
        quest.delete(question.getId());

    }

}
