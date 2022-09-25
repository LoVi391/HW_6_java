package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ParseService {


    public static void loadData() throws Exception {
        ParseService ps = new ParseService();
        ps.filler(ps.parseFile2());
    }

    List<String[]> parseFile2() throws Exception {
        List<String[]> forParse = new ArrayList<>();
        Path fileInPath = Paths.get(System.getProperty("user.dir"), "Homework06", "data", "notik.txt");
        BufferedReader br = new BufferedReader(new FileReader(fileInPath.toString()));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("i7") ||
                    line.contains("i5") ||
                    line.contains("i3") ||
                    line.contains("Ryzen") ||
                    line.contains("PQC")
            ) {
                forParse.add(line.split("</span><span>")[2].split(" "));
            }
        }
        return forParse;
    }

    void filler(List<String[]> inList) {
        DataService ds = new DataService();

        for (String[] record : inList) {
            Notebook curNtb = new Notebook();
            curNtb.setSerNum(String.format("%d", System.nanoTime()).substring(5));

            if (!record[0].equals("(RFB)")) {
                curNtb.setBrand(record[0]);
            } else {
                curNtb.setBrand(record[1]);
            }
            for (int i = 0; i < record.length; i++) {
                if (record[i].contains("i7") || record[i].contains("i5") || record[i].contains("i3")) {
                    curNtb.setProc(record[i]);
                } else if (record[i].contains("Ryzen")) {
                    curNtb.setProc(String.format("%s %s %s", record[i], record[i+1], record[i+2]));
                } else if (record[i].contains("PQC")) {
                    curNtb.setProc(String.format("%s %s", record[i], record[i+1]));
                } else if (record[i].contains("4Gb+4Gb") || record[i].contains("8Gb+8Gb")) {
                    String[] tmp = record[i].replace("+", "").split("Gb");
                    int ramVol = Integer.parseInt(tmp[0]) + Integer.parseInt(tmp[1]);
                    curNtb.setRamVol(ramVol);
                } else if (record[i].contains("Gb")) {
                    int ramVol = Integer.parseInt(record[i].split("Gb")[0]);
                    if (ramVol <= 32) {
                        curNtb.setRamVol(ramVol);
                    }
                } else if (record[i].equals("SSD") || record[i].equals("HDD")) {
                    if (record[i + 1].contains("Gb") || record[i + 1].contains("Tb"))
                        curNtb.setHddVol(Integer.parseInt(record[i + 1].substring(0, record[i + 1].length() - 2)));
                } else if (record[i].contains("Win") || record[i].contains("Lin")) {
                    curNtb.setOpSys(record[i]);
                }else if( record[i].contains("No") && record[i+1].contains("OS")){
                    curNtb.setOpSys(String.format("%s %s", record[i], record[i+1]));
                }
                else if (record[i].contains("ый") || record[i].contains("ий")) {
                    curNtb.setColor(record[i]);
                }

            }
            DataService.notebooks.add(curNtb);
        }
    }
}