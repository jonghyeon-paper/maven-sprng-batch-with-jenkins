package com.example.persistence.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.batchprocessing.Person;

public interface PersonMapper {

    @Select("SELECT FIRST_NAME, LAST_NAME FROM Z_TEST_PEOPLE")
    List<Person> findAll();
}
