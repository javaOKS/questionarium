package service;

import model.Question;
import repository.dao.QuestionRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class QuestionService {
    private final Map<String,List<Question>> questionByTopic = new HashMap<>();
    private final QuestionRepository questionRepository;
    public QuestionService( QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getRandomQuestionByTopic(String topic){
        List<Question> questions = questionByTopic.computeIfAbsent(topic, questionRepository::getByTopic);
        questionByTopic.put(topic,questions);
        int randomNum = ThreadLocalRandom.current().nextInt(0,questions.size());
        return questions.get(randomNum);
    }
    public Question getRandomQuestion() {
        return questionRepository.getRndmQuestion();
    }
    public void addQuestion(int id,String text,String topic){
        Question question = Question.builder()
                .id(id)
                .text(text)
                .topic(topic)
                .build();
        questionRepository.save(question);
    }
    public void deleteQuestion(int id){
        questionRepository.delete(id);
    }

}
