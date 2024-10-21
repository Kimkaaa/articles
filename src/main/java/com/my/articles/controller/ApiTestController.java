package com.my.articles.controller;

import com.my.articles.dto.LoginDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiTestController {
    @GetMapping("/api")
    public String api_form(){
        return "/test/api";
    }

    @PostMapping("/apiPostTest")
    public ResponseEntity<String> apiPostTest(
            @RequestBody LoginDTO loginDTO){
        String info = loginDTO.getUserId() + loginDTO.getPassword();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(info);
    }

    @RequestMapping(value = "/getResponse", method = RequestMethod.POST)
    @ResponseBody
    public LoginDTO getResponse(){
        return new LoginDTO("grape","1234");
    }

    @PostMapping("/apiPostArrayTest")
    @ResponseBody
    public Map<String, String> apiPostArrayTest(
            @RequestBody List<LoginDTO> dtos){

        System.out.println(dtos.toString());

        Map<String, String> map = new HashMap<>();
            map.put("userId", dtos.get(0).getUserId());
            map.put("password", dtos.get(0).getPassword());
        return null;
    }

    @DeleteMapping("/delete")
//    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(){
        return "Delete Mapping";
    }
}