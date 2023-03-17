package service;

import org.junit.Before;
import org.junit.Test;
import repository.QuestionRepositoryImpl;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class QuestionServiceTest {
    private String user = "postgres";
    private String password = "1234";
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    @Before
    public void init() throws SQLException {
        connection = DriverManager.getConnection(url,user,password);
    }
    @Test
    public void getRandomQuestionByTopicTest(){
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl(connection));
        System.out.println(questionService.getRandomQuestionByTopic("INCAPCULATION"));
    }
}
