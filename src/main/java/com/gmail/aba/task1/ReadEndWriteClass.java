package com.gmail.aba.task1;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadEndWriteClass {

    List<String> list = List.of("(n\\w*=\".*?)\" s\\w*=\"(.*?\")",
            "s\\w*=\"(.*?\") (n\\w*=\".*?)\"",
            "(n\\w* = \".*?)\"\\n *s\\w* = \"(.*?\")",
            "(n\\w*=\".*?)\"\\n *s\\w*=\"(.*?\")",
            "(n\\w*= \".*?)\" s\\w* = \"(.*?\")");

    public String readFromFile(String path)  {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException ex) {
            System.out.println("file not find");
        }
        return sb.toString();
    }

    public String correctionText(String text) {
        for(String string : list) {
            Pattern pattern1 = Pattern.compile(string);
            Matcher matcher1 = pattern1.matcher(text);

            for ( ;matcher1.find(); ) {
                if (string.equals("s\\w*=\"(.*?\") (n\\w*=\".*?)\"")) {
                    String name = matcher1.group(2);
                    String surname = matcher1.group(1);
                    text = text.replaceAll(matcher1.group(),name + " " + surname);
                }

                String name = matcher1.group(1);
                String surname = matcher1.group(2);
                text = text.replaceAll(matcher1.group(), name + " " + surname);
            }
        }
        return text;
    }

    public void writeToXMLFile(String text) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("task1.xml"))) {
            bw.write(text);
        } catch (IOException ex) {
            System.out.println("Some exception");
        }
    }
}
