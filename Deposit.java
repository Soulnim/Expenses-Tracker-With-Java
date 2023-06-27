public class Deposit extends Transaction
{
    // test
    private String depositType, destination;
    
    // Why this one don't have void eh?
    public Deposit(String type, double value, String desc, String depositType, String destination) {
        super(type, value, desc);
        this.depositType=depositType;
        this.destination=destination;
    }
    
    public void setDepositType(String set) { this.depositType=set; }
    public void setDestination(String destination) { this.destination=destination; }
    
    public String getDepositType() { return depositType; }
    public String getDestination() { return destination; }
    
    public String toString() {
        return (super.toString() + "| Deposit Type : " + depositType +
            "| From Account : " + destination);
    }
}
