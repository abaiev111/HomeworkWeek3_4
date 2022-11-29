package com.gmail.aba.task2;

import java.util.*;

public class Task2Main {
    public static void main(String[] args) throws Exception {


        JsonToXMLClass jsonToXML = new JsonToXMLClass();
        List<List<TrafficViolation>> trafficViolationList = jsonToXML.getListJsonObjectsFromFolder();
        Map<String, Long> map = jsonToXML.getTotalAmountViolations(trafficViolationList);
        jsonToXML.writeToXML(map);

        //jsonToXML.writeToXML(jsonToXML.getTotalAmountViolations(jsonToXML.getListJsonObjectsFromFolder(path)));
    }
}
