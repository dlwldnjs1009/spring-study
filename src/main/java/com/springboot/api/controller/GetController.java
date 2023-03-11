package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    //http://localhost:8080/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto){
        //return memberDto.getName() + " " + memberDto.getEmail() + " " + memberDto.getOrganization();
        LOGGER.info("memberDto 통해 들어온 값 : {}", memberDto.toString());
        return memberDto.toString();
    }
    //http://localhost:8080/api/v1/get-api/request2?key1=value&key2=value2
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        LOGGER.info("@RequestParam을 통해 들어온 값 : {}", sb.toString());
        return sb.toString();
    }

    //http://localhost:8080/api/v1/get-api/request1?name=value1&email=value2&organization=value3
    @ApiOperation(value = "Get 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization){
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}, {}, {}", name, email, organization);
        return name + " " + email + " " + organization;
    }

    //http://localhost:8080/api/v1/get-api/variable2/{String 값}
    //동일하게 맞추기 어려울 때
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", var);
        return var;
    }
    //http://localhost:8080/api/v1/get-api/variable1/{String 값}
    //@PathVariable을 활용한 GET 메서드 구현
    // @GetMapping 어노테이션과 @Pathvariable에 지정된 변수의 이름을 동일하게 맞춰야함
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    //http://localhost:8080/api/v1/get-api/name
    //매개변수가 없는 GET 메서드 구현
    @GetMapping(value = "/name")
    public String getName(){
        LOGGER.info("getName 메서드가 호출되었습니다.");
        return "Flature";
    }

    //http://localhost:8080/api/v1/get-api/hello
//    @RequestMapping으로 구현하기
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello(){
        LOGGER.info("getHello 메서드가 호출되었습니다.");
        return "Hello World!";
    }
}

