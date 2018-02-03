package com.yx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/hello"})
public class AnnoHelloController {
    @RequestMapping({"/myHandle"}) //对"/hello"进行窄化
    public ModelAndView myHello(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg","嘤嘤嘤");
        mv.setViewName("annoHello");
        return mv;
    }
    // GET POST 请求都可以处理，不写默认是GET
    @RequestMapping(value = {"/action"},method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView action(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("retAction","GET POST");
        mv.setViewName("annoHello");
        return mv;
    }
    // ANT风格参数
    @RequestMapping(value = {"/ANT/{username}/{password}"})
    public ModelAndView ant(
            @PathVariable("username") String username,
            @PathVariable("password") String password
    ){
        ModelAndView mv = new ModelAndView();
        mv.addObject("ANT",username+","+password);
        mv.setViewName("annoHello");
        return mv;
    }
    // 正则参数
    @RequestMapping(value = {"/regx/{x1:\\d+}-{x2:\\d+}"})
    public ModelAndView regx(
            @PathVariable("x1") String x1,
            @PathVariable("x2") String x2
    ){
        ModelAndView mv = new ModelAndView();
        mv.addObject("regx",x2+","+x2);
        mv.setViewName("annoHello");
        return mv;
    }
}
