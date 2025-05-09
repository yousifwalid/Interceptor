package com.example.interceptorTask.controller;

import com.example.interceptorTask.model.Student;
import eu.bitwalker.useragentutils.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interceptor")
@Slf4j
public class interceptorController {

    @GetMapping("/getStudents")
    public List<Student> getStudents(@RequestAttribute(name = "isMobile") DeviceType deviceType) {
        if (DeviceType.MOBILE.equals(deviceType)) {
            log.warn("Request from Mobile");
            return List.of(new Student(1, "Ahmed - Mobile", deviceType),
                    new Student(2, "Mohamed - Mobile", deviceType),
                    new Student(3, "Ali - Mobile", deviceType));
        } else {
            log.warn("Request from another device");
            return List.of(new Student(1, "", deviceType),
                    new Student(2, "", deviceType),
                    new Student(3, "", deviceType));
        }
    }
}

//public List<Student> getStudents(@RequestHeader("X-Source") String sender) {