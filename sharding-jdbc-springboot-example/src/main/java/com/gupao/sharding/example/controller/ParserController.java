package com.gupao.sharding.example.controller;

import org.example.ParserManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RestController
@RequestMapping("/parser")
public class ParserController {



    @GetMapping("/{type}")
    public String parser(@PathVariable("type") String type){
        try {
            return ParserManager.getParser(type).parse(new File(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "不支持该种解析方式";
    }
}
