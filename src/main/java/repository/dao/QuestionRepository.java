package repository.dao;

import model.Question;
import java.util.List;

public interface QuestionRepository {
    Question get(int id);
    void save(Question question);
    void delete(int id);
    List<Question> getByTopic(String topic);
    Question getRndmQuestion();

}
