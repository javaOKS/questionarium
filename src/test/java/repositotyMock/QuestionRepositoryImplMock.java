package repositotyMock;

import model.Question;
import repository.dao.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImplMock implements QuestionRepository {
    private List<Question> testQuestions ;
    public QuestionRepositoryImplMock(List<Question> questions) {
        this.testQuestions = questions;
    }

    public List<Question> getTestQuestions() {
        return testQuestions;
    }

    @Override
    public Question get(int id) {
        return null;
    }

    @Override
    public void save(Question question) {
        testQuestions.add(question);
    }

    @Override
    public void delete(int id) {
        for (Question q:testQuestions) {
            if(q.getId() == id){
                testQuestions.remove(q);
                return;
            }
        }
    }

    @Override
    public List<Question> getByTopic(String topic) {
        List<Question> result = new ArrayList<>();
        for(Question q:this.testQuestions){
            if(q.getTopic().equals(topic)){
                result.add(q);
            }
        }
        return result;
    }

    @Override
    public Question getRndmQuestion() {
        return null;
    }
}
