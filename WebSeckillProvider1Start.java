package com.java.controller;

import com.java.exception.SeckillException;
import com.java.mapper.SeckillMapper;
import com.java.service.SeckillService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * date:2019-02-23
 * 16:16
 * description:WebSeckillProvider1Start
 * author:潘全科
 */
@SpringBootApplication(scanBasePackages = {"com.java.controller","com.java.service.impl"})
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan(basePackages = "com.java.mapper")
@Controller
public class WebSeckillProvider1Start {

    public static void main(String[] args) {
        SpringApplication.run(WebSeckillProvider1Start.class);
    }
    @Autowired
    private SeckillService seckillService;
    @RequestMapping("/processSeckill.do")
    public @ResponseBody
    Map<String,Object> processSeckill( Integer seckillId,  Integer userId) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            seckillService.doSeckill(userId,seckillId);
            resultMap.put("status",0);//成功
         return  resultMap;
        } catch (SeckillException e) {
            resultMap.put("status",1);//失败
            resultMap.put("msg",e.getMessage());
           return resultMap;
        }

    }
}
