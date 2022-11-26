package com.gmail.aba.task2;


import lombok.Data;

@Data
public class TrafficViolation {
    private String date_time;
    private String first_name;
    private String last_name;
    private String type;
    private long fine_amount;
}
