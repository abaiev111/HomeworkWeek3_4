package com.gmail.aba.task2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class JsonToXMLClass {

    public List<TrafficViolation> getListJsonObjectsFromFolder(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<List<TrafficViolation>> listsTrafficViolation = new ArrayList<>();
        List<TrafficViolation> trafficViolationsList = new ArrayList<>();

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                List<TrafficViolation> myObjects = objectMapper.readValue(new FileReader(path + file.getName()), new TypeReference<List<TrafficViolation>>(){});
                listsTrafficViolation.add(myObjects);
            }
        }
        for (List<TrafficViolation> v: listsTrafficViolation)
            for (TrafficViolation v2: v)
                trafficViolationsList.add(v2);
        return trafficViolationsList;
    }

    public Map<String, Long> getTotalAmountViolations(List<TrafficViolation> list) {
        Map<String, Long> map = new HashMap<>();

        for (TrafficViolation tv: list) {
            if (map.containsKey(tv.getType()) && !map.isEmpty())
                map.put(tv.getType(), map.get(tv.getType()) + tv.getFine_amount());
              else
                map.put(tv.getType(), tv.getFine_amount());

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
