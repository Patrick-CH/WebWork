package cyk.app.controller;

import cyk.app.Static;
import cyk.app.bean.Info;
import cyk.app.bean.Passage;
import cyk.app.bean.User;
import cyk.app.service.PassageService;
import cyk.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;



@Controller
public class PageController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(HttpSession session, HttpServletRequest request){
        if(null == session.getAttribute("authorized")){
            return new ModelAndView("redirect:/");
        }
        String search = request.getParameter("search");
//        int page = request.getIntHeader("page");
        ModelAndView mad = new ModelAndView();
        mad.setViewName("main");
        try {
            UserService userService = new UserService();
            if (search == null) {
                List<User> users = userService.findAllUser();
                mad.addObject("users", users);
            } else {
                List<User> users = userService.findUsersLike(search);
                mad.addObject("users", users);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mad;
    }

    @RequestMapping(value = "/main/userinfo", method = RequestMethod.GET)
    public String userInfo(HttpSession session){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        return "userinfo";
    }

    @RequestMapping(value = "/main/change_pass", method = RequestMethod.GET)
    public String userPass(HttpSession session){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        return "change_pass";
    }

    @RequestMapping(value = "/main/change_other", method = RequestMethod.GET)
    public String userother(HttpSession session, HttpServletRequest request){
        session.setAttribute("user_to_change", request.getParameter("other"));
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        return "changeOthers";
    }

    @RequestMapping(value = "/passage", method = RequestMethod.GET)
    public ModelAndView passage(HttpSession session, HttpServletRequest request){
        if(null == session.getAttribute("authorized")){
            return new ModelAndView("redirect:/");
        }
        String search_username = request.getParameter("username");
        String search_title = request.getParameter("title");
        ModelAndView mad = new ModelAndView();
        mad.setViewName("passage");
        try {
            PassageService passageService = new PassageService();
            if (search_title == null && search_username == null) {
                List<Passage> passages = passageService.findAllPassages();
                mad.addObject("passages", passages);
            } else if (search_title != null && search_username == null){
                List<Passage> passages = passageService.findPassagesByTitle(search_title);
                mad.addObject("passages", passages);
            } else if (search_title == null){
                List<Passage> passages = passageService.findPassageByUsername(search_username);
                mad.addObject("passages", passages);
            } else {
                List<Passage> passages = passageService.findPassagesByUsernameAndTitle(search_username, search_title);
                mad.addObject("passages", passages);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mad;
    }

    @RequestMapping(value = "/main/new_passage", method = RequestMethod.GET)
    public String newPassage(HttpSession session, HttpServletRequest request){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        return "newPassage";
    }

    @RequestMapping(value = "/main/edit_passage", method = RequestMethod.GET)
    public String editPassage(HttpSession session, HttpServletRequest request){
        if(null == session.getAttribute("authorized")){
            return "redirect:/";
        }
        try {
            PassageService passageService = new PassageService();
            int id = Integer.parseInt(request.getParameter("id"));
            Passage original = passageService.findPassageById(id);
            session.setAttribute("passage_to_edit", original);
            if(null == session.getAttribute("authorized")){
                return "redirect:/";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "editPassage";
    }

    @RequestMapping(value = "/main/passage_statistic", method = RequestMethod.GET)
    public ModelAndView passageStatistic(HttpSession session){
        if(null == session.getAttribute("authorized")){
            return new ModelAndView("redirect:/");
        }
        ModelAndView mad = new ModelAndView("passageStatistic");
        try {
            List<Info> infos = new PassageService().findInfos();
            if (infos.size() > 5){
                infos = infos.subList(0, 5);
            }
            mad.addObject("infos", infos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mad;
    }

    @RequestMapping(value = "/main/manege_passage", method = RequestMethod.GET)
    public ModelAndView managePassage(HttpSession session, HttpServletRequest request){
        if(null == session.getAttribute("authorized")){
            return new ModelAndView("redirect:/");
        }
        ModelAndView mav = new ModelAndView("passageManage");
        try {
            PassageService passageService = new PassageService();
            String username = request.getParameter("username");
            List<Passage> passages = passageService.findPassageByUsername(username);
            mav.addObject("passages", passages);
            mav.addObject("user", new UserService().findUser(username));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mav;
    }
}
