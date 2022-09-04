package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.doamin.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeDao extends BaseMapper<Employee> {
}
