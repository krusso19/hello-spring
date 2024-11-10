package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
//@ResponseBody //removed in chapter 13
@RequestMapping("hello")
public class HelloController {
    //handle request at path http://localhost:8080/hello

//    @GetMapping("hello")
//    @ResponseBody
//    public String hello(){
//        return "Hello, Spring!";
//    }

    //handle request at path //http://localhost:8080/hello/goodbye
    @GetMapping("goodbye")
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    //handle requests of the form http://localhost:8080/hello?name=LaunchCode
    //Thymeleaf Part 2: Dynamic Templates
    @RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
    public String helloWithQueryParam(@RequestParam String name, Model model) { //model is where you put variable you want passed into view
        String theGreeting = "Hello, " + name + "!";
        model.addAttribute("greeting", theGreeting); //first is name of variable, second is the object that contains the value that that variable should have
        //first matches variable name in template. 2nd is the local variable
        return "hello"; //returns hello template?
    }

    //handle requests of the form http://localhost:8080/hello/LaunchCode
    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name, Model model) {
        String theGreeting = "Hello, " + name + "!";
        model.addAttribute("greeting", theGreeting);
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public String createMessage(@RequestParam String name, @RequestParam String selectedLanguage) {
        String message = "eeek";
        if (selectedLanguage.equals("english")) {
            message = "Hello, " + name + "!";
        } else if (selectedLanguage == "french") {
            message = "Bonjour, " + name + "!";
        } else if (selectedLanguage == "german") {
            message = "Guten Tag, " + name + "!";
        } else if (selectedLanguage == "spanish") {
            message = "Hola, " + name + "!";
        } else if (selectedLanguage == "ib") {
            message = "Hibellibo, " + name + "!";
        }
        return message;
    }

    //handles requests for http://localhost:8080/hello/form
    @GetMapping("form")
    public String helloForm() {
        return "form";
//        return "<html>" +
//                "<body>" +
//                "<form action = '/hello' method = 'post'>" + // submit a request to /hello
//                "<input type = 'text' name = 'name' >" +
//                "<select name ='selectedLanguage'>" +
//                "<option value='english'>English</option>" +
//                "<option value='french'>French</option>" +
//                "<option value='german'>German</option>" +
//                "<option value='ib'>Ib</option>" +
//                "<option value='spanish'>Spanish</option>" +
//                "<input type = 'submit' value = 'Greet Me!' >" +
//                "</form>" +
//                "</body>" +
//                "</html>";
    }

    @GetMapping("names")
    public String helloNames(Model model){
        List<String> names = new ArrayList<>();
        names.add("LaunchCode");
        names.add("Java");
        names.add("JavaScript");
        model.addAttribute("names"/*variable that template sees*/, names/*value that the variable should have*/);//pass list of names to template
        return "hello-list"; //returns java code in hello-list.html file
        //uses hell0-list template. must pass in a list of actual names. construct java list.
    }


}