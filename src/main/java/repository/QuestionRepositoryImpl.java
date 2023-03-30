package repository;

import exceptions.SqlUpdateException;
import model.Question;
import repository.dao.QuestionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final Connection connection;

    public QuestionRepositoryImpl() {
        this.connection = ConnectionSingleton.getConnection() ;
    }

    private final String findById =
            """
                    SELECT * FROM questions WHERE id = ?
                    """;
    private final String findByTopic =
            """
                    SELECT * FROM questions WHERE topic = ?
                    """;
    private final String saveQuestion =
            """
                    INSERT INTO questions (id,text,topic)
                    VALUES (?,?,?)
                    """;

    private final String deleteQuestionById =
            """
                    DELETE FROM questions
                    WHERE id = ?
                    """;
    private final String getAllQuest =
            """
                    SELECT * FROM questions
                    """;
    private final String getRandomQuest =
            """
                    SELECT id,text,topic FROM questions
                    ORDER BY RANDOM() LIMIT 1
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
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }

    @Override
    public Question getRndmQuestion() {
        try {
            PreparedStatement statement = connection.prepareStatement(getRandomQuest);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return Question.builder()
                    .id(resultSet.getInt("id"))
                    .text(resultSet.getString("text"))
                    .topic(resultSet.getString("topic"))
                    .build();
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
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
            while (resultSet.next()) {
                question.add(Question.builder()
                        .id(resultSet.getInt("id"))
                        .text(resultSet.getString("text"))
                        .topic(resultSet.getString("topic"))
                        .build()
                );
            }
            return question;
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }

    @Override
    public void save(Question question) {
        try {
            PreparedStatement statement = connection.prepareStatement(saveQuestion);
            statement.setInt(1, question.getId());
            statement.setString(2, question.getText());
            statement.setString(3, question.getTopic());
            statement.execute();
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }
    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(deleteQuestionById);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new SqlUpdateException(e.getMessage());
        }
    }
}

