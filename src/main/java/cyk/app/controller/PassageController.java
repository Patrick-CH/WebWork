package cyk.app.controller;

import cyk.app.bean.Passage;
import cyk.app.bean.User;
import cyk.app.service.PassageService;
import cyk.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;

@Controller
public class PassageController {

    @RequestMapping(value = "/del_passage", method = RequestMethod.GET)
    String delPassage(HttpServletRequest request, HttpSession session){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            PassageService passageService = new PassageService();
            passageService.removePassage(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/passage";
    }

    @RequestMapping(value = "/new_passage", method = RequestMethod.POST)
    String newPassage(Passage passage, HttpSession session, HttpServletResponse response){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        try {
            System.out.println(passage);
            PassageService passageService = new PassageService();
            passageService.addPassage(passage);
            PrintWriter out = response.getWriter();
            out.println("<script>alert('新建成功！'); window.location.href='http://localhost:8080/web_demo1/passage';</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "passage";
    }

    @RequestMapping(value = "/edit_passage", method = RequestMethod.POST)
    String editPassage(Passage passage, HttpSession session, HttpServletResponse response){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        try {
            System.out.println(passage);
            PassageService passageService = new PassageService();
            passageService.editPassageInfo(passage);
            PrintWriter out = response.getWriter();
            out.println("<script>alert('修改成功！'); window.location.href='http://localhost:8080/web_demo1/passage';</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "passage";
    }
}
