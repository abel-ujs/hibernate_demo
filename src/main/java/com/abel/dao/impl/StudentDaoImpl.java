package com.abel.dao.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.abel.dao.StudentDao;
import com.abel.entity.Student;
import com.abel.utils.dao.DaoSupport;

@Repository
@Transactional
public class StudentDaoImpl extends DaoSupport<Student> implements StudentDao{
}
