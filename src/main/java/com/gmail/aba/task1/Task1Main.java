package com.gmail.aba.task1;

/*
1. Розробити програму, яка на вхід отримує xml-файл з тегами <person>, в яких є атрибути name і surname.
        Програма повинна створювати копію цього файлу, в якій значення атрибута surname об'єднане з name.
        Наприклад name="Тарас" surname="Шевченко" у вхідному файлі повинно бути замінене на name="Тарас Шевченко" (атрибут surname має бути видалений).
        Вхідний файл може бути великий, тому завантажувати його цілком в оперативну пам'ять буде поганою ідеєю.
        * Опціонально (на макс. бал): зробити так, щоб форматування вихідного файла повторювало форматування вхідного файлу (мабуть, xml-парсер в такому разі тут не підійде).
*/

public class Task1Main {
    public static void main(String[] args) {

        String path = "/Users/mishaabaiev/Java/JavaProjects/HomeworkWeek3_4/src/main/java/com/gmail/aba/task1/file.xml";

        ReadEndWriteClass readEndWrite = new ReadEndWriteClass();

        String textFromFile = readEndWrite.readFromFile(path);
        String correctionText = readEndWrite.correctionText(textFromFile);
        readEndWrite.writeToXMLFile(correctionText);

        //readEndWrite.writeToXMLFile(readEndWrite.correctionText(readEndWrite.readFromFile(path)));
    }
}
