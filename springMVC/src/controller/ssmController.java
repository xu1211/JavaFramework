package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import src.server.UserService;

import java.util.List;

@Controller
public class ssmController {
    @Autowired
    UserService userService;

    @RequestMapping("/listCategory")
    public ModelAndView listUser(){
        ModelAndView mav = new ModelAndView();
        List cs= userService.list();

        // 放入转发参数
        mav.addObject("cs", cs);
        // 放入jsp路径
        mav.setViewName("listCategory");
        return mav;
    }
}
