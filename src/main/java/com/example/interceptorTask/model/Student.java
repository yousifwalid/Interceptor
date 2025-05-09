package com.example.interceptorTask.model;

import eu.bitwalker.useragentutils.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private DeviceType deviceType;
}
