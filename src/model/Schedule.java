package model;

public class Schedule {
    private int id;
    private int rid;

    public Schedule(int id, int rid) {
        this.id = id;
        this.rid = rid;
    }

    public int getRid() { return rid; }
}