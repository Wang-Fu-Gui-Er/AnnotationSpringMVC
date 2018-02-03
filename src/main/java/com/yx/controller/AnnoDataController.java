package com.yx.controller;

import com.yx.po.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"data"})
@SessionAttributes({"myStar"})//会话
public class AnnoDataController {

    @RequestMapping(value = "form",method = RequestMethod.POST)
    // 把表单name字段传给po层User的同名属性
    // Map Model ModelMap 契约式编程，把键值对存到map里，前端jsp通过键就可以获取到值
    public String form(User user,
                       Map map,
                       Model model,
                       ModelMap modelMap
    ){
        map.put("username",user.getUsername());
        model.addAttribute("sex","男");
        modelMap.put("address","姑苏");
        return "annoData";
    }

    @RequestMapping("param")
    public String param1(@RequestParam(value = "myName") String name){
        System.out.println(name);
        return "annoData";
    }
    @RequestMapping("param2")
    public String param2(@RequestParam() String mySex){
        System.out.println(mySex);
        return "annoData";
    }
    @RequestMapping("param3")
    public String param3(@RequestParam("name") String [] names){//也可以用list接收
        System.out.println(names[0]+","+names[1]);
        return "annoData";
    }
    // cookie不能存中文
    @RequestMapping("cookie")
    public String cookie(@CookieValue(value = "myCookie", defaultValue = "0") Long value,
                         HttpServletResponse response
    ){
        value = 123456L;
        Cookie cookie = new Cookie("username",value.toString());
        response.addCookie(cookie);
        return "annoData";
    }

    //URL重写功能，为了防止一些用户把Cookie禁止无法使用Session而设置的功能，
    // jsessionid后面的一长串就是本地服务器的session的ID号，这样无需Cookie也可以使用Session.
    @RequestMapping("cookie2")
    public String cookie2(@CookieValue(value = "JSESSIONID", defaultValue = "") Cookie cookie,
                         HttpServletResponse response
    ){
        cookie.setValue("MDZZ");
        response.addCookie(cookie);
        return "annoData";
    }

    @RequestMapping(value = "session")
    public String session(Map map){
        map.put("myStar","蓝忘机");
        return "annoData";
    }

    @RequestMapping(value = "attr1")
    public String attr1(@ModelAttribute(value = "user") User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "annoData";
    }
    // 后端向前端传值，契约式编程，层级建目录，最后一级为文件名  jsp/data/attr2.jsp
    @RequestMapping(value = "attr2")
    public @ModelAttribute("user") User attr2(){
        User u = new User();
        u.setUsername("蓝忘机");
        u.setPassword("999");
        return u;
    }

    @RequestMapping(value = "attr3")
    public String attr3(@ModelAttribute User u, Model model){
        System.out.println(u.getUsername());
        //契约式编程，前端向后端传值的键,与commandName无关，
        // 如果@ModelAttribute指定value,则键是value,否则默认是po或vo层的类名首字母小写
        System.out.println(model.containsAttribute("user"));//false  true
        System.out.println(model.containsAttribute("user3"));//true  false
        model.addAttribute("txt","魔道祖师");
        return "annoData";
    }
    //契约式编程，在物理视图得到其值，只能使用泛型的类型名[首字母小写]+集合类名 userList
   @RequestMapping("list")
    public @ModelAttribute List<User> list(){
        return Arrays.asList(new User("蓝忘机","111"),
                new User("魏无羡","000"));
   }

    // 非契约式编程，在物理视图得到其值，就叫map
    @RequestMapping("myMap")
    public @ModelAttribute Map<String,User> myMap(){
        Map<String,User> m = new HashMap<String,User>();
        m.put("u1",new User("蓝忘机","111"));
        m.put("u2",new User("魏无羡","000"));
        return m;
    }
}
