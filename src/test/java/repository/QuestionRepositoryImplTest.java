package repository;

import model.Question;
import org.junit.Before;
import org.junit.Test;
import service.QuestionService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionRepositoryImplTest {
    private String user = "postgres";
    private String password = "1234";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url,user,password);
    }
    @Test
    public void getTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl(connection);
        System.out.println(quest.get(1));
    }
    @Test
    public void getByTopicTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl(connection);
        System.out.println(quest.getByTopic("OOP"));
    }
    @Test
    public void deleteTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl(connection);
        quest.delete(5);
    }
    @Test
    public void saveTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl(connection);
        Question question = Question.builder()
                .id(3)
                .text("What is private")
                .topic("topic111")
                .build();
        quest.save(question);
    }
    @Test
    public void updateTest(){
        QuestionRepositoryImpl quest =  new QuestionRepositoryImpl(connection);
        Question question = Question.builder()
                .id(7)
                .text("What is final")
                .topic("topicJava")
                .build();
        quest.update(question);
    }

}
