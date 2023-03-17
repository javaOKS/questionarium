package repository;

import model.Question;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private Connection connection;

    public QuestionRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    private String findById =
            """
                SELECT * FROM questions WHERE id = ?
            """;
    private String findByTopic =
            """
                SELECT * FROM questions WHERE topic = ?
            """;
    private String saveQuestion =
            """
                INSERT INTO questions (text,topic)
                 VALUES (?,?)
            """;
    private String updateQuestion =
            """
                UPDATE questions
                SET text=?, topic=?
                WHERE id = ?
                 """;
    private String deleteQuestion =
            """
                 DELETE FROM questions
                  WHERE id = ?
                    """;
    @Override
    public Question get(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(findById);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Question> getByTopic(String topic) {
        try {
            PreparedStatement statement = connection.prepareStatement(findByTopic);
            statement.setString(1, topic);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            List<Question> question = new ArrayList<>();
            while(resultSet.next()){
                question.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .text(resultSet.getString("text"))
                        .topic(resultSet.getString("topic"))
                        .build()
                );
            }
            return question;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public void save(Question question) {

    }

    @Override
    public void update(Question question) {

    }

    @Override
    public void delete(int id) {

    }

}
