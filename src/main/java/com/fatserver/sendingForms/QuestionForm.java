package com.fatserver.sendingForms;

import com.fatserver.entity.Question;

import java.security.PrivateKey;

/**
 * Created by Victor on 06.03.2018.
 */
public class QuestionForm {

    private Question question;
    private Long userId;
    private String userName;
    private String userSurname;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}
