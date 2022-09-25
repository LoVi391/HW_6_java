package services;

public class Notebook {
    private int ramVol = 0;
    private int hddVol = 0;
    private String brand = "NoName";
    private String proc = "NoName";
    private String serNum = "000000";
    private String opSys = "Clear";
    private String color = "Transparent";

    public Notebook(){

    }
    public Notebook(int ramVol, int hddVol, String proc,
                    String opSys, String color,
                    String serNum, String brand) {
        this.proc = proc;
        this.ramVol = ramVol;
        this.hddVol = hddVol;
        this.opSys = opSys;
        this.color = color;
        this.brand = brand;
        this.serNum = serNum;
    }

    public String getField(String param) {
        switch (param) {
            case "ramVol":return String.format("%d Gb", this.ramVol);
            case "hddVol":return this.hddVol < 10 ? String.format("%d Tb", this.hddVol) : String.format("%d Gb", this.hddVol);
            case "brand": return this.brand;
            case "proc":  return this.proc;
            case "serNum":return this.serNum;
            case "opSys": return this.opSys;
            case "color": return this.color;
            default: return null;
        }
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public void setRamVol(int ramVol) {
        this.ramVol = ramVol;
    }

    public void setHddVol(int hddVol) {
        this.hddVol = hddVol;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOpSys(String opSys) {
        this.opSys = opSys;
    }

    public int getHddVol() {
        return hddVol;
    }

    public int getRamVol() {
        return ramVol;
    }

    public String getBrand() {
        return brand;
    }

    public String getProc() {
        return proc;
    }

    public String getOpSys() {
        return opSys;
    }

    public String getColor() {
        return color;
    }

    public String getSerNum() {
        return serNum;
    }

    @Override
    public int hashCode() {
        return serNum.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        var cmp = (Notebook) obj;
        return cmp.hashCode() == this.hashCode();
    }

    @Override
    public String toString() {
        String hdd = "Not yet";
        if (this.hddVol < 10){
            hdd = String.format("%dTb", this.hddVol);
        } else {
            hdd = String.format("%dGb", this.hddVol);
        }
        return String.format("%s / %s / %sGb / %s / %s / %s / %s",
                this.brand, this.proc, this.ramVol, hdd, this.opSys,
                this.color, this.serNum);
    }
}
