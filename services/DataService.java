package services;

import java.util.*;
import java.util.logging.Logger;

public class DataService {

    static HashSet<Notebook> notebooks = new LinkedHashSet<>();

    public void init(Logger logger) {
        try {
            ParseService.loadData();
        } catch (Exception exc) {
            System.out.println("Error:");
            System.out.println(exc.getMessage());
        }
    }

    public HashMap<Integer, String> createList(String param) {
        HashMap<Integer, String> menuItems = new HashMap<>();
        Set<String> paramSet = new TreeSet<>();

        for (Notebook ntb : notebooks) {
            paramSet.add(ntb.getField(param));
        }
        int count = 0;
        for (String s : paramSet) {
            menuItems.put(++count, s);
        }
        return menuItems;
    }

    public HashSet<Notebook> filter(String paramCat, String param) {
        HashSet<Notebook> filtered = new HashSet<>();
        for (Notebook item : notebooks) {
            if(item.getField(paramCat).equals(param))
                filtered.add(item);
        }
        return filtered;
    }

    public HashSet<Notebook> getNotebooks() {
        return notebooks;
    }
}
