package repository;

import org.junit.Before;
import org.junit.Test;

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


}
