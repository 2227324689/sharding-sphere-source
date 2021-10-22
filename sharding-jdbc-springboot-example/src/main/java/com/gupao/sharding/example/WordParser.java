package com.gupao.sharding.example;

import org.example.Parser;

import java.io.File;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class WordParser implements Parser {
    @Override
    public String parse(File file) throws Exception {
        return "我是基于word解析";
    }

    @Override
    public String getType() {
        return "word";
    }
}
