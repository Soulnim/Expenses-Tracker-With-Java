public class Deposit extends Transaction
{
    // test
    private String depositType, toAccount;
    
    // Why this one don't have void eh?
    public Deposit(String type, double value, String desc, String depositType, String toAccount) {
        super(type, value, desc);
        this.depositType=depositType;
        this.toAccount=toAccount;
    }
    
    public void setDepositType(String set) { this.depositType=set; }
    public void setToAccount(String toAccount) { this.toAccount=toAccount; }
    
    public String getDepositType() { return depositType; }
    public String getToAccount() { return toAccount; }
    
    public String toString() {
        return (super.toString() + "| Deposit Type : " + depositType +
            "| From Account : " + toAccount);
    }
}
