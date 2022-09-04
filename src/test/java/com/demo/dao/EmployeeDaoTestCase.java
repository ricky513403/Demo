package com.demo.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.doamin.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;

@SpringBootTest
public class EmployeeDaoTestCase {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void testSave(){
        Employee employee=new Employee();
        employee.setName("新增員工1");
        employee.setSalary(103546D);
        employee.setBirthday(new Date());
        employee.setPhoto(null);
        employeeDao.insert(employee);
    }

    @Test
    void testDelete(){
        employeeDao.deleteById(6);
    }

    @Test
    void testUpdate(){
        Employee employee = new Employee();
        employee.setId(7);
        employee.setName("修改員工姓名");
        employee.setSalary(103547D);
        employee.setBirthday(new Date());
        employee.setPhoto(null);
        employeeDao.updateById(employee);
    }

    @Test
    void testGetById(){
        employeeDao.selectById(7);
    }

    @Test
    void testGetAll(){
        employeeDao.selectList(null);
    }

    @Test
    void testGetPage(){
        IPage page=new Page(1,5);
        employeeDao.selectPage(page,null);
    }

    @Test
    void testGetBy(){
        LambdaQueryWrapper<Employee> lqw=new LambdaQueryWrapper<>();
        lqw.like(Employee::getName,"2");
        employeeDao.selectList(lqw);
    }


}
