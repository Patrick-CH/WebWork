package cyk.app.controller;

import cyk.app.bean.User;
import cyk.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session, HttpServletResponse response){
        try {
            PrintWriter out = response.getWriter();
            UserService userService = new UserService();
            User current_user = userService.findUser(user.getUsername());
            if (null == current_user) {
                out.println("<script>alert('用户不存在');</script>");
                return "index";
            }
            if (user.getPassword().equals(current_user.getPassword())) {
                session.setAttribute("username", user.getUsername());
                session.setAttribute("authorized", true);
                out.println("<script>alert('登录成功');</script>");
                return "redirect:/main";
            } else {
                out.println("<script>alert('密码错误');</script>");
                return "index";
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register_act(User user){
        try {
            UserService userService = new UserService();
            if(0==userService.addUser(user)){
                //注册成功
                return "redirect:/";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "register";
    }

    @RequestMapping(value = "/del_user", method = RequestMethod.GET)
    public String delUser(HttpSession session, HttpServletRequest request){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        try {
            String username = request.getParameter("username");
            System.out.println("deleting... username=" + username);
            UserService userService = new UserService();
            if(0==userService.removeUser(username)){
                System.out.println("username:" + username);
                return "redirect:/main";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/main";
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public String delUser(User user, HttpSession session){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        try {
            UserService userService = new UserService();
            if(0==userService.editUserInfo(user)){
                System.out.println("username:" + user.getUsername());
                return "redirect:/main";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/forget_pass", method = RequestMethod.POST)
    public String forgetUser(String username, String new_password, String new_password_again, HttpSession session, HttpServletResponse response){
        if (!new_password.equals(new_password_again)){
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.println("<script>alert('两次输入不一致');</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "forget";
        }
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(new_password);
            UserService userService = new UserService();
            if(0==userService.editUserPass(user)){
                System.out.println("username:" + user.getUsername());
                return "redirect:/";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/change_pass", method = RequestMethod.POST)
    public String changePass(User user, String new_password, String new_password_again, HttpSession session, HttpServletResponse response){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        if(!new_password.equals(new_password_again)){
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.println("<script>alert('两次输入不一致');</script>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "change_pass";
        }
        try {
            UserService userService = new UserService();
            user.setPassword(new_password);
            if(0==userService.editUserPass(user)){
                System.out.println("username:" + user.getUsername());
                return "redirect:/main";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
