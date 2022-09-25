/**
 * Подумать над структурой класса Ноутбук для магазина техники - выделить поля и
 * методы. Реализовать в java. Создать множество ноутбуков.
 * Реализовать методы фильтрации-сортировки ноутбуки их первоначального множества
 * и вывести проходящие по условиям.
 * Сделать так, как мы обсуждали на семинаре (M - V - C)
 */

import services.DataService;
import services.ViewService;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    static DataService ds = new DataService();
    static ViewService vs = new ViewService();
    static Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) {
        try {
            Path logPath = Paths.get(System.getProperty("user.dir"), "Homework06", "data", "homework06.log");
            FileHandler fh = new FileHandler(logPath.toString(), true);
            SimpleFormatter sFormat = new SimpleFormatter();
            fh.setFormatter(sFormat);
            logger.addHandler(fh);
        } catch (Exception exc) {
            logger.warning(exc.getMessage());
        }
        Scanner scn = new Scanner(System.in);
        vs.init();
        ds.init(logger);
        mainMenu(scn);
        scn.close();
    }

    static void mainMenu(Scanner localScn) {
        String command = "work";
        do {
            localScn.reset();
            vs.printMenu("mainMenu");
            command = localScn.next();
            switch (command) {
                case "P":
                case "p":
                    vs.printNotebookSet(ds.getNotebooks());
                    break;
                case "F":
                case "f":
                    filterMenu(localScn);
                    break;
                case "E":
                case "e":
                    System.out.println("Всего хорошего!");
                    command = "stop";
                    break;
                default:
                    System.out.println("Команда не распознана");
                    break;
            }
        } while (!command.equals("stop"));
    }

    static void filterMenu(Scanner localScn) {
        int choice = 0;
        String paramCat = null;
        String command;
        HashMap<Integer, String> selection = new HashMap<>();
        do {
            localScn.reset();
            vs.printMenu("filter");
            command = localScn.next();
            switch (command) {
                case "P":
                case "p":
                    System.out.println("Processor filter...");
                    selection = ds.createList("proc");
                    choice = vs.printFilterSelect(selection, localScn, logger);
                    paramCat = "proc";
                    command = "stop";
                    break;
                case "R":
                case "r":
                    System.out.println("RAM filter...");
                    selection = ds.createList("ramVol");
                    choice = vs.printFilterSelect(selection, localScn, logger);
                    paramCat = "ramVol";
                    command = "stop";
                    break;
                case "D":
                case "d":
                    System.out.println("Disk filter...");
                    selection = ds.createList("hddVol");
                    choice = vs.printFilterSelect(selection, localScn, logger);
                    paramCat = "hddVol";
                    command = "stop";
                    break;
                case "B":
                case "b":
                    System.out.println("Brand filter...");
                    selection = ds.createList("brand");
                    choice = vs.printFilterSelect(selection, localScn, logger);
                    paramCat = "brand";
                    command = "stop";
                    break;
                case "O":
                case "o":
                    System.out.println("Os filter...");
                    selection = ds.createList("opSys");
                    choice = vs.printFilterSelect(selection, localScn, logger);
                    paramCat = "opSys";
                    command = "stop";
                    break;
                case "C":
                case "c":
                    System.out.println("Color filter...");
                    selection = ds.createList("color");
                    choice = vs.printFilterSelect(selection, localScn, logger);
                    paramCat = "color";
                    command = "stop";
                    break;
                case "E":
                case "e":
                    command = "stop";
                    break;
                default:
                    System.out.println("Команда не распознана");
                    break;
            }
        } while (!command.equals("stop"));
        if (paramCat != null) {
            vs.printNotebookSet(ds.filter(paramCat, selection.get(choice)));
        }
    }
}