package com.gmail.aba.task2;

import java.util.*;

public class Task2Main {
    public static void main(String[] args) throws Exception {
        String path = "/Users/mishaabaiev/Java/JavaProjects/HomeworkWeek3_4/src/main/java/com/gmail/aba/task2/json/";

        JsonToXMLClass jsonToXML = new JsonToXMLClass();
        List<TrafficViolation> trafficViolationList = jsonToXML.getListJsonObjectsFromFolder(path);
        Map<String, Long> map = jsonToXML.getTotalAmountViolations(trafficViolationList);
        jsonToXML.writeToXML(map);

        //jsonToXML.writeToXML(jsonToXML.getTotalAmountViolations(jsonToXML.getListJsonObjectsFromFolder(path)));
    }
}
