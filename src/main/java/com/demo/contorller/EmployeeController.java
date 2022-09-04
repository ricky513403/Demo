package com.demo.contorller;

import com.demo.doamin.Employee;
import com.demo.service.EmployeeService;
import com.demo.utils.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @Value("${photo.file.dir}")
    private String realpath;

    /**
     * 刪除員工資訊
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public String delete(Integer id){
        log.debug("刪除的員工id:{}",id);

        //找出員工的照片
        String photo = employeeService.getById(id).getPhoto();

        //刪除照片
        if(photo!=null){
        File file = new File(realpath, photo);
            file.delete();
        }
        //刪除數據
        employeeService.removeById(id);
        return "redirect:/employee/lists";//跳轉到員工列表
    }

    /**
     * 更新員工資訊
     * @param employee
     * @param img
     * @return
     */
    @RequestMapping("update")
    public String update(Employee employee,MultipartFile img) throws IOException {

        log.debug("更新之後的員工資訊ID:{},姓名{},工資{},生日{}", employee.getId(), employee.getName(), employee.getSalary(), employee.getBirthday());
        boolean notEmpty = !img.isEmpty();
        log.debug("是否更新照片:{}", notEmpty);

        if (notEmpty) {
            //刪除舊照片 根據id找出原本的照片名稱
            String oldPhoto = employeeService.getById(employee.getId()).getPhoto();
            //原本如有照片，則刪除該照片
            if(oldPhoto!=null) {
                File file = new File(realpath, oldPhoto);
                file.delete();
            }
            //上傳新照片
            String originalFileName = img.getOriginalFilename();
            String newFileName = uploadPhoto(img, originalFileName);
            employee.setPhoto(newFileName);
        }

        employeeService.updateById(employee);

        return "redirect:/employee/lists";//更新成功後跳轉回員工列表
    }

    //上傳照片
    private String uploadPhoto(MultipartFile img, String originalFileName) throws IOException {
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String fileNameSuffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = fileNamePrefix + fileNameSuffix;
        img.transferTo(new File(realpath,newFileName));
        return newFileName;
    }

    /**
     * 根據id查詢員工詳細資訊
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("detail")
    public String detail(Integer id,Model model){
        log.debug("當前查詢員工id:{}",id);

        Employee employee=employeeService.getById(id);
        model.addAttribute("employee",employee);

        return "updateEmp";
    }

    /**
     * 添加員工資訊
     * @param employee
     * @param img
     * @return
     * @throws IOException
     */
    @RequestMapping("save")
    public String save(Employee employee, MultipartFile img) throws IOException {

        log.debug("姓名:{},薪資:{},生日:{}",employee.getName(),employee.getSalary(),employee.getBirthday());

        String originalFilename=img.getOriginalFilename();
        log.debug("照片名稱:{}",originalFilename);
        log.debug("照片大小:{}",img.getSize());
        log.debug("上傳的路徑:{}",realpath);

        //處理上傳的照片，修改名稱
        if(img.getSize()!=0) {
            String newFileName = uploadPhoto(img, originalFilename);
            employee.setPhoto(newFileName);
        }
            //保存員工資訊

            employeeService.save(employee);

        return "redirect:/employee/lists";
    }

    /**
     * 員工列表
     * @return
     */
    @RequestMapping("lists")
    public String lists(Model model){
        log.debug("查詢所有員工資訊");

        List<Employee> employeeList = employeeService.list();
        model.addAttribute("employeeList",employeeList);

        return "emplist";
    }

    @RequestMapping("exportExcel")
    public void export(HttpServletResponse response){

        List<Employee> employeeList = employeeService.list();
        //導出excel
        ExcelUtils.exportExcel(employeeList,"員工總表","Employees",Employee.class,"員工總表.xls",response);
        log.debug("Excel導出成功");
    }

}
