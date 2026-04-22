package model;

public class RepairRequest {

    private int rid;
    private String roadName;
    private String suburb;
    private String description;
    private String status;
    private int priority;   // ✅ NEW FIELD

    public RepairRequest(int rid, String roadName, String suburb,
                         String description, String status, int priority) {

        this.rid = rid;
        this.roadName = roadName;
        this.suburb = suburb;
        this.description = description;
        this.status = status;
        this.priority = priority;   // ✅ SET
    }

    public int getRid() { return rid; }
    public String getRoadName() { return roadName; }
    public String getSuburb() { return suburb; }
    public String getStatus() { return status; }
    public int getPriority() { return priority; }   // ✅ ADD THIS
}