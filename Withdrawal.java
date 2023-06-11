public class Withdrawal extends Transaction
{
    // test
    private String withdType, fromAccount;
    
    // Why this one don't have void eh?
    public Withdrawal(String type, double value, String desc, String withdType, String fromAccount) {
        super(type, value, desc);
        this.withdType=withdType;
        this.fromAccount=fromAccount;
    }
    
    public void setWithdrawType(String set) { this.withdType=set; }
    public void setFromAccount(String fromAccount) { this.fromAccount=fromAccount; }
    
    public String getWithdrawType() { return withdType; }
    public String getFromAccount() { return fromAccount; }
    
    public String toString() {
        return (super.toString() + "| Deposit Type : " + withdType +
            "| From Account : " + fromAccount);
    }
}
