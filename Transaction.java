public class Transaction
{
    protected String type;
    protected double value;
    protected String description;
    
    public Transaction() {
        type="";
        value=0.0;
        description="Undefined";
    }
    
    public Transaction(String type, double value, String description) {
        this.type=type;
        this.value=value;
        this.description=description;
    }
    
    public void setTransaction(String type, double value, String description) {
        this.type=type;
        this.value=value;
        this.description=description;
    }
    
    public String getType() { return type; }
    public String getDesc() { return description; }
    public double getValue() { return value; }
    
    public void setType(String type) { this.type=type; }
    public void setDesc(String desc) { this.description=desc; }
    public void setValue(double value) { this.value=value; }
    
    public String toString() {
        return ("Type : " + type + "| Value : " + value + "| Description : " + description);
    }
}
