package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello world!!");
        return "hello";
    }

    // http://localhost:8080/hello-mvc?name=hwyeom
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){

        return "hello " + name; //"hello hwyeom"
    }


    @GetMapping("hello-api")
    @ResponseBody
    // @ResponseBody : 기본 객체가 오면 json 방식으로 처리하겠다는 규칙
    // 'viewResolver' 대신에 'HttpMessageConverter'(JsonConverter, StringConverter) 기본으로 등록
    // 기본 문자처리 : 'StringHttpMessageConverter'
    // 기본 객체처리 : 'MappingJackson2HttpMessageConverter'
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
}
