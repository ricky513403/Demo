package com.demo.doamin;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Excel(name = "id",orderNum = "6", width = 20)
    private Integer id;
    @Excel(name = "姓名",orderNum = "6", width = 20)
    private String name;
    private String photo;//照片路徑
    @Excel(name = "薪水",orderNum = "6", width = 20)
    private Double salary;
    @Excel(name = "生日",orderNum = "6", width = 50)
    private Date birthday;

}
