package com.fatserver.IncomingForms;

import java.sql.Timestamp;
import java.util.Set;

public class QuestionDTO {
    private Long id;
    private String title;
    private String discription;
    private Integer price;
    private Timestamp dateTime;
    private Set<SkillDTO> skills;


    public QuestionDTO(){
        this.id = null;
        this.title = "Title";
        this.dateTime = getDateTime();
    }

    public QuestionDTO(String title, String discription, Set<SkillDTO> skills, Integer price) {
        this.title = title;
        this.discription = discription;
        this.skills = skills;
        this.price = price;
    }

    public QuestionDTO(String title, String discription) {
        this.title = title;
        this.discription = discription;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }



    public Set<SkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDTO> skills) {
        this.skills = skills;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", discription='" + discription + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

}