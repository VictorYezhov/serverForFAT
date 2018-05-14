package com.fatserver.controlller;

import com.fatserver.IncomingForms.CategotryDTO;
import com.fatserver.IncomingForms.SkillDTO;
import com.fatserver.comparators.DateComparator;
import com.fatserver.entity.Category;
import com.fatserver.entity.Question;
import com.fatserver.entity.Skill;
import com.fatserver.entity.User;
import com.fatserver.sendingForms.QuestionDTO;
import com.fatserver.service.CategotyService;
import com.fatserver.service.QuestionService;
import com.fatserver.service.SkillService;
import com.fatserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Victor on 06.03.2018.
 * Controller that processes requests for questions
 */

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    UserService userService;

    @Autowired
    SkillService skillService;

    @Autowired
    CategotyService categotyService;



    /**
     * Method returns all questions available in DB
     * @return
     */

    @GetMapping("/getAllQuestions")//TODO : Filltring and sending only latest questions
    public List<QuestionDTO> getAllQuestions(){
        List<QuestionDTO> questionDTOS = questionService.findAll();
        for (QuestionDTO qf:
                questionDTOS) {
            qf.getQuestion().setCommentsList(null);
        }
        Collections.sort(questionDTOS, new DateComparator());
        return questionDTOS;
    }

    @GetMapping("/getQuestionsByCategory{id}")
    public List<QuestionDTO> getQuestionsByCategory(@PathVariable("id") Long id){
        List<QuestionDTO> questionDTOS = questionService.findAllByCategoty(id);
        for (QuestionDTO qf:
                questionDTOS) {
            qf.getQuestion().setCommentsList(null);
        }
        Collections.sort(questionDTOS, new DateComparator());
        return questionDTOS;
    }

    @PostMapping(value = "/sendAskingQuestion{id}")
    public String askQuestion(@RequestBody com.fatserver.IncomingForms.QuestionDTO question, @PathVariable Long id){

        Question questionToSave = new Question(question);
        Long iid = question.getCategory().getId();
        Category category = categotyService.findOne(iid);
        questionToSave.setCategory(category);
        User user = userService.findOne(id);
        Skill skill = null;
        for (SkillDTO s:question.getSkills()) {
            skill = skillService.findByName(s.getName());
            questionToSave.getSkills().add(skill);
            skill.getQuestionList().add(questionToSave);

        }
        questionToSave.setUser(user);
        questionToSave.setDateTime(new Timestamp(System.currentTimeMillis()));
        user.getQuestions().add(questionToSave);
        questionService.save(questionToSave);
        skillService.save(skill);


        return "OK";
    }

    @GetMapping(value = "/getAllUsersQuestions{id}")
    public List<Question> sendToClientAllUsersQuestions(@PathVariable Long id){
        List<QuestionDTO> questionForms = questionService.findAll();
        List<Question> userQuestions = new ArrayList<>();


        for (QuestionDTO questionForm: questionForms){
            if(questionForm.getUserId().equals(id)){

                userQuestions.add(questionForm.getQuestion());
            }
        }

        return userQuestions;
    }

    @PostMapping(value = "/plusOneToViewCounter{id}")
    public String plusOneToViewCounter(@PathVariable Long id){

        Question q = questionService.findOne(id);
        q.setViews(q.getViews() + 1);
        questionService.update(q);

        return "OK";
    }
}
