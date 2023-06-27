public class Withdrawal extends Transaction
{
    // test
    private String withdType, source;
    
    // Why this one don't have void eh?
    public Withdrawal(String type, double value, String desc, String withdType, String source) {
        super(type, value, desc);
        this.withdType=withdType;
        this.source=source;
    }
    
    public void setWithdrawType(String set) { this.withdType=set; }
    public void setSource(String source) { this.source=source; }
    
    public String getWithdrawType() { return withdType; }
    public String getSource() { return source; }
    
    public String toString() {
        return (super.toString() + "| Deposit Type : " + withdType +
            "| From Account : " + source);
    }
}
