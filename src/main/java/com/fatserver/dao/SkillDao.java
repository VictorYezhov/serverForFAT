package com.fatserver.dao;

import com.fatserver.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Victor on 16.02.2018.
 */
public interface SkillDao extends JpaRepository<Skill, Long> {

}
