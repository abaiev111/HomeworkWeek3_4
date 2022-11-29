package com.gmail.aba.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.aba.task2.TrafficViolation;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class JsonToXMLClass2 {

    public List<List<TrafficViolation>> getListJsonObjectsFromFolder() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<TrafficViolation>> listsTrafficViolation = new ArrayList<>();

        File folder = new File("/Users/mishaabaiev/Java/JavaProjects/HomeworkWeek3_4/src/main/java/com/gmail/aba/task2/json/");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                List<TrafficViolation> myObjects = objectMapper.readValue(new FileReader("/Users/mishaabaiev/Java/JavaProjects/HomeworkWeek3_4/src/main/java/com/gmail/aba/task2/json/" + file.getName()), new TypeReference<List<TrafficViolation>>(){});
                listsTrafficViolation.add(myObjects);
            }
        }
        return listsTrafficViolation;
    }

    public Map<String, Long> getTotalAmountViolations(List<List<TrafficViolation>> list) {
        Map<String, Long> map = new HashMap<>();
        for (List<TrafficViolation> v: list){
            for (TrafficViolation v2: v) {
                if (map.containsKey(v2.getType()) && !map.isEmpty())
                    map.put(v2.getType(), map.get(v2.getType()) + v2.getFine_amount());
                else
                    map.put(v2.getType(), v2.getFine_amount());
            }
        }
        Map<String,Long> sortedMap = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sortedMap;
    }

    public void writeToXML(Map<String, Long> map) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(map);
        xmlEncoder.close();

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("task2.xml"))) {
            bw.write(bos.toString());
        } catch (IOException ex) {
            System.out.println("Some exception");
        }
    }
}
