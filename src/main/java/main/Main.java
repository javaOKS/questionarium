package main;

import repository.ConnectionSingleton;
import repository.QuestionRepositoryImpl;
import service.QuestionService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\\n");
        QuestionService questionService = new QuestionService(new QuestionRepositoryImpl());
        int number,id;
        String text,topic;
        System.out.println("Enter 'yes' if you want add question,delete question or get a random question . ");
        while (scan.next().equals("yes")) {
            System.out.println("Enter 1 - to get a random question, \n" +
                    "2 - to get a random question by topic, \n" +
                    "3 - to add question, \n" +
                    "4 - to delete question.");
            number = scan.nextInt();
            switch (number) {
                case 1 -> System.out.println(questionService.getRandomQuestion());
                case 2 -> {
                    System.out.println("Enter topic name? ");
                    System.out.println(questionService.getRandomQuestionByTopic(scan.next()).getText());
                }
                case 3 -> {
                    System.out.println("Please enter id: ");
                    id = scan.nextInt();
                    System.out.println("Please enter text: ");
                    text = scan.next();
                    System.out.println("Please enter topic: ");
                    topic = scan.next();
                    questionService.addQuestion(id, text, topic);
                }
                case 4 -> {
                    System.out.println("Please enter id: ");
                    id = scan.nextInt();
                    questionService.deleteQuestion(id);
                }
                default -> System.out.println("Number you entered not valid.");
            }
            System.out.println("Enter 'no' to end or 'yes' to continue: ");
        }
    }
}
