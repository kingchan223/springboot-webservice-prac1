package com.webservice.springboot.web;
import com.webservice.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController//컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다.
public class HelloController{

    @GetMapping("/hello/dto")//HTTP Method인 Get요청을 받을 수 있는 API를 만들어준다.
    // 예전에는 @RequesatMapping(method=RequestMethod.GET)으로 사용.
    //여기서는 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 가지게 되었다.
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
