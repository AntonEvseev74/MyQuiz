package ru.evant.myquiz;

/* Вопрос
    содержит:
    вопрос,
    правильный ответ,
    три неправильных ответа
 */

public class Question {
    private String question; // вопрос
    private String correctAnswer; // правильный ответ
    private String[] incorrectAnswer = new String[3]; // неправильные ответы, 3 ответа

    /* конструктор
        принимает:
        вопрос, правильный ответ, массив неправильных ответов (размер массива 3)
        инициализирует:
        принятые данные
     */
    public Question(String question, String correctAnswer, String[] incorrectAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }

    // возвращает вопрос
    public String getQuestion() {
        return question;
    }

    // возвращает правильный ответ
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    // возвращает массив неправильных ответов
    public String[] getIncorrectAnswer() {
        return incorrectAnswer;
    }

    // принимает: индекс неправильного ответа
    // возвращает: неправильный ответ под принятым индексом из массива неправильных ответов
    public String getIncorrectAnswer(int indexIncorrectAnswer) {
        return incorrectAnswer[indexIncorrectAnswer];
    }
}
