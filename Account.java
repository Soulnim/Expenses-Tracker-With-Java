import java.util.Scanner;

public class Account
{
    // Defining variable
    private String name, ID;
    private double value;
    
    // Default constructor
    public Account() {
        name = "Undefined";
        value = 0.0;
    }
    
    // Normal constructor
    public Account(String name, double value) {
        this.name = name;
        this.value = value;
    }
    
    // Setter method
    public void setName(String name) { this.name = name; };
    public void setValue(double value) { this.value = value; }
    public void setID(String ID) { this.ID = ID; }
    
    // Getter method
    public String getName() { return this.name; }
    public double getValue() { return this.value; }
    public String getID() { return this.ID; }
    
    // Generate ID
    public String generateID() {
        return ""; // complete this..
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
    
    public String toString() {
        return ("Account Name : " + name + "| Account value : " + value);
    }
}
