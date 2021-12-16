package ru.evant.myquiz;

import ru.evant.myquiz.utils.Random;

import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {
    Scanner sc = new Scanner(System.in);
    String[][] questionsList = QuestionsList.questionsList;
    ArrayList<String> ans = new ArrayList<>(); // answers
    ArrayList<Question> listQuestions = new ArrayList<>(); // questions  Список вопросов

    int countCorrect = 0;
    int countIncorrect = 0;

    ArrayList<Integer> questionsIndex = new ArrayList<>();

    // Конструктор. Создает опрос.
    public Quiz() {
        // Создать список вопросов с вариантами ответов
        createQuestionList();
    }

    // В метод необходимо передать количество вопросов
    public void start(int n) {
        for (int i = 0; i < n; i++) {
            while (questionsIndex.size() < n) {
                int x = Random.randNumFromTo(0, listQuestions.size());
                if (!questionsIndex.contains(x)) questionsIndex.add(x);
            }
        }

        for (int i = 0; i < questionsIndex.size(); i++) {
            askQuestion(i);
            System.out.println();
        }

        sc.close();

        // статистика
        System.out.println("\nСтатистика: \nВерно: " + countCorrect + "\nНеверно: " + countIncorrect);

        // конец программы
        System.out.println("Конец программы");
    }

    // задать вопрос под принятым номером
    private void askQuestion(int numberQuestion) {
        // вывести вопрос с вариантами ответов
        printQuestion(numberQuestion);

        // выбрать вариант ответа, и проверить ответ
        chooseAnswer(numberQuestion);
    }

    // выбрать вариант ответа
    private void chooseAnswer(int numberQuestion) {
        // выбрать вариант ответа от 1 до 4
        System.out.print("ответ: ");
        int answer = sc.nextInt();

        // проверить ответ
        checkAnswer(answer, numberQuestion);
    }

    // проверить ответ
    private void checkAnswer(int indexAnswer, int numberQuestion) {
        String currentAnswer = ans.get(indexAnswer - 1);
        String correctAnswer = listQuestions.get(numberQuestion).getCorrectAnswer();

        if (currentAnswer.equals(correctAnswer)) {
            countCorrect++;
            System.out.println("Ваш ответ: " + currentAnswer + ". Это правильный ответ!");
        } else {
            countIncorrect++;
            System.out.println("Ваш ответ: " + currentAnswer + ". Это неправильный ответ!");
        }
    }

    // вывести вопрос с вариантами ответов
    private void printQuestion(int numberQuestion) {
        // вывод вопроса
        System.out.println(listQuestions.get(numberQuestion).getQuestion());

        // список вариантов ответов
        ans = getAnswers(numberQuestion);

        // вывод списка вариантов ответов
        printAnswers(ans);
    }

    // вывод списка вариантов ответов
    private void printAnswers(ArrayList<String> listAnswers) {
        int count = 1;
        for (String answer : listAnswers) {
            System.out.println(count + ". " + answer);
            count++;
        }
    }

    // список вариантов ответов
    private ArrayList<String> getAnswers(int numberQuestion) {
        ArrayList<String> answers = new ArrayList<>();
        answers.add(listQuestions.get(numberQuestion).getCorrectAnswer());
        for (int i = 0; i < 3; i++) {
            answers.add(listQuestions.get(numberQuestion).getIncorrectAnswer(i));
        }

        ArrayList<String> sortAnswers;
        sortAnswers = randomSort(answers);
        return sortAnswers;
    }

    // сортировать варианты ответов в случайном порядке
    private ArrayList<String> randomSort(ArrayList<String> listToSort) {
        ArrayList<String> sortedList = new ArrayList<>();
        while (sortedList.size() < listToSort.size()) {
            int tmp = Random.randNumFromTo(0, 4);
            if (!sortedList.contains(listToSort.get(tmp))) sortedList.add(listToSort.get(tmp));
        }
        return sortedList;
    }

    // создать список вопросов
    private void createQuestionList() {
        for (String[] strings : questionsList) {
            String[] answerArray = new String[3];

            System.arraycopy(strings, 2, answerArray, 0, 3);

            Question question = new Question(strings[0], strings[1], answerArray);
            listQuestions.add(question);
        }
    }
}


