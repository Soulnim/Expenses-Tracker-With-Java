import java.util.*;
import java.io.*;

public class User
{
    private String name, password;
    private int totalAcc, totalTrans, totalDepo, totalWithdraw;
    public User() {
        name = "Undefined";
        password = "Undefined";
        totalAcc = 0;
        totalTrans = 0;
        totalDepo = 0;
        totalWithdraw = 0;
    }
    
    public User(String name, String password, int totalTrans, int totalAcc, int totalDepo, int totalWithdraw) {
        this.name = name;
        this.password = password;
        this.totalAcc = totalAcc;
        this.totalDepo = totalDepo;
        this.totalWithdraw = totalWithdraw;
    }
    
    public void setUser(String name, String password, int totalTrans, int totalAcc, int totalDepo, int totalWithdraw) {
        this.name = name;
        this.password = password;
        this.totalAcc = totalAcc;
        this.totalTrans = totalTrans;
        this.totalDepo = totalDepo;
        this.totalWithdraw = totalWithdraw;
    }
    
    public void setName(String name) { this.name=name; }
    public void setPass(String password) { this.password=password; }
    public void setTotalAcc(int totalAcc) { this.totalAcc=totalAcc; }
    public void setTotalTrans(int totalTrans) { this.totalTrans=totalTrans; }
    public void setTotalDepo(int totalDepo) { this.totalDepo=totalDepo; }
    public void setTotalWithdraw(int totalWithdraw) { this.totalWithdraw=totalWithdraw; }
    
    public String getName() { return name; }
    public String getPass() { return password; }
    public int getTotalAcc() { return totalAcc; }
    public int getTotalTrans() { return totalTrans; }
    public int getTotalDepo() { return totalDepo; }
    public int getTotalWithdraw() { return totalWithdraw; }
    
    public String toString() {
        return ("Username : " + name + 
                "| Password : " + password +
                "| Total Account : " + totalAcc +
                "| Total Transactions : " + totalTrans +
                "| Total Deposit : " + totalDepo +
                "| Total Withdrawal : " + totalWithdraw);
    }
    
    public void pressEnterToContinue()
    { 
        System.out.println("Press Enter key to continue...");
        try
        {
            Scanner scanner=new Scanner(System.in);
            scanner.nextLine();
        }  
        catch(Exception e)
        {}  
    }
}
