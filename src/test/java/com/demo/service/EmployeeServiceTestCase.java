package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.doamin.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class EmployeeServiceTestCase {

    @Autowired
    private EmployeeService employeeService;


    @Test
    void testSave(){
        Employee employee= new Employee();
        employee.setName("測試帳號名");
        employee.setPhoto("1234.jpg");
        employee.setSalary(1234D);
        employee.setBirthday(new Date(100,10,10));

        employeeService.save(employee);
    }

    @Test
    void testDelete(){
        employeeService.removeById(1);
    }

    @Test
    void testUpdate(){
        Employee employee = new Employee();
        employee.setId(7);
        employee.setName("測試新的員工名");
        employee.setPhoto("test.jpg");
        employee.setSalary(1236545D);
        employee.setBirthday(new Date(90,9,9));
        employeeService.updateById(employee);
    }

    @Test
    void testGetById(){
        System.out.println(employeeService.getById(4));
    }

    @Test
    void testGetAll(){
        employeeService.list();
    }

    @Test
    void testGetPage(){
        IPage<Employee> page = new Page<Employee>(0,5);
        employeeService.page(page);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }
}
