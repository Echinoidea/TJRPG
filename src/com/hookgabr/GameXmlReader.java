package com.hookgabr;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class GameXmlReader implements IXmlReader {

    @Override
        try {
            // Stuff to read the XML file.
            File inputFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("\nRoot element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("monster");

            // List of leveled Monsters relative to Player's level. When a battle starts, it will select a random index
            // and use that Monster.
            List<Monster> monsterLeveledList = new ArrayList<>();

            int monsterId;
            String monsterName;
            int monsterLevel;
            int monsterExpYield;
            int monsterGoldYield;

            // Go through each node in the XML file.
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    // If Player's level  + 3 is <= monster's level XOR, Player's level + 3 is >= monster's level
                    if (Integer.parseInt(eElement.getAttribute("level")) <= Player.level + 2 ^
                            Integer.parseInt(eElement.getAttribute("level")) >= Player.level + 2) {
                        // Add ONLY monsters that follow this to the monsterLeveledList.
                        Monster monsterTemp = new Monster();
                    }
                    System.out.println("Monster ID : " + eElement.getAttribute("id"));
                    System.out.println("Name : " + eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Level : " + eElement.getElementsByTagName("level").item(0).getTextContent());
                    System.out.println("Exp Yield : " + eElement.getElementsByTagName("exp-yield").item(0).getTextContent());
                    System.out.println("Gold Yield : " + eElement.getElementsByTagName("gold-yield").item(0).getTextContent());

                    String monsterName = eElement.getElementsByTagName("name").item(0).getTextContent();
                    int monsterLevel = Integer.parseInt(eElement.getElementsByTagName("level").item(0).getTextContent());
                    int monsterExp = Integer.parseInt(eElement.getElementsByTagName("exp-yield").item(0).getTextContent());
                    int monsterGold = Integer.parseInt(eElement.getElementsByTagName("gold-yield").item(0).getTextContent());


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getLeveledItem(String filepath) {

    }

    @Override
    public void getLeveledSpell(String filepath) {

    }
}
