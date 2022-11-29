package com.gmail.aba.example;

import com.gmail.aba.task2.JsonToXMLClass;
import com.gmail.aba.task2.TrafficViolation;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        JsonToXMLClass2 jsonToXML = new JsonToXMLClass2();
        List<List<TrafficViolation>> trafficViolationList = jsonToXML.getListJsonObjectsFromFolder();
        Map<String, Long> map = jsonToXML.getTotalAmountViolations(trafficViolationList);
        jsonToXML.writeToXML(map);


    }
}
