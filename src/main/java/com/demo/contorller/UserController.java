package com.demo.contorller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.doamin.User;
import com.demo.service.UserService;
import com.demo.utils.VerifyCodeUtils;
import com.demo.utils.exception.PassWordNotCorrectException;
import com.demo.utils.exception.UserNameBeRegisteredException;
import com.demo.utils.exception.VerifyCodeNotCorrectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 安全登出
     *
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();//清除session

        return "redirect:/employee/lists";
    }


    /**
     * 用戶登錄
     *
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password, HttpSession session) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        //判斷帳號是否存在
        if(userService.getOne(wrapper)==null) {
            log.debug("帳號不存在");
            return "redirect:/UserNameNotFoundException";
        }

        log.debug("本次登錄帳號:{}", username);
        log.debug("本次登錄密碼:{}", DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));

        //判斷密碼是否正確
        try {
            User user = userService.login(username, password);
            session.setAttribute("user", user);
        } catch (PassWordNotCorrectException e) {
            return "redirect:/PasswordNotCorrectException";
        }

        return "redirect:/employee/lists";
    }

    /**
     * 用戶註冊
     *
     * @return
     */
    @RequestMapping("register")
    public String register(User user, String code, HttpSession session) {
        log.debug("用戶名: {},真實姓名:{},密碼:{},性別:{}", user.getUsername(), user.getRealname(), user.getPassword(), user.getGender());
        log.debug("用戶輸入驗證碼:{}", code);

        //判斷輸入驗證碼  與存在session中的驗證碼是否一致
        try {
            String sessionCode = session.getAttribute("code").toString();
            if (!sessionCode.equalsIgnoreCase(code)){
                throw new VerifyCodeNotCorrectException("驗證碼輸入錯誤!");
            }
            userService.register(user);
        } catch (UserNameBeRegisteredException e) {
            e.printStackTrace();
            return "redirect:/UserNameBeRegisteredException"; //註冊失敗 回到註冊頁面
        }catch (VerifyCodeNotCorrectException e1){
            e1.printStackTrace();
            return "redirect:/VerifyCodeNotCorrectException";
        }

        return "redirect:/login"; //註冊成功 跳到登錄頁面
    }

    /**
     * 生成驗證碼
     */
    @RequestMapping("generateImageCode")
    public void generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {

        String code = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("code", code);
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        VerifyCodeUtils.outputImage(120, 60, os, code);

    }
}


