public class Transaction
{
    protected String type;
    protected double value;
    protected String description;
    protected String date;
    
    public Transaction() {
        type="";
        value=0.0;
        description="Undefined";
        date="Undefined";
    }
    
    public Transaction(String type, double value, String description, String date) {
        this.type=type;
        this.value=value;
        this.description=description;
        this.date=date;
    }
    
    public void setTransaction(String type, double value, String description) {
        this.type=type;
        this.value=value;
        this.description=description;
        this.date=date;
    }
    
    public String getType() { return type; }
    public String getDesc() { return description; }
    public double getValue() { return value; }
    public String getDate() { return date; }
    
    public void setType(String type) { this.type=type; }
    public void setDesc(String desc) { this.description=desc; }
    public void setValue(double value) { this.value=value; }
    public void setDate(String date) { this.date=date; }
    
    public String toString() {
        return ("Type : " + type + "| Value : " + value + "| Description : " + description + "| Date : "+date);
    }
}