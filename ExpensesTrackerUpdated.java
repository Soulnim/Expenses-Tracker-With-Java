import java.util.*;
import java.io.*;
public class ExpenseTracker
{
    public static void main(String args[]) {
        // Define var
        String username="Undefined", password="Undefined"; // user data
        int totalAccount=0, totalTransaction=0, totalDeposit=0, totalWithdrawal=0;
        Account [] account=new Account[100]; // Define Account
        Transaction [] transaction = new Transaction[100]; // Define Transaction
        Deposit [] deposit = new Deposit[100]; // Define Deposit
        Withdrawal [] withdrawal = new Withdrawal[100]; // Define Withdrawal
        for (int i=0;i<100;i++) {
            transaction[i]=new Transaction();
            deposit[i]=new Deposit("Undefined", 0, "Undefined", "Undefined", "Undefined");
            withdrawal[i]=new Withdrawal("Undefined", 0, "Undefined", "Undefined", "Undefined");
            account[i]=new Account();
        }
        int assignedTransaction=0 ,assignedDeposit=0, assignedWithdrawal=0, assignedAccount=0;
        String [] depoTypeArr={"Income", "Gift", "Collection", "Lending", "Refunds", "Sale"}; // Deposit Type
        String [] withTypeArr={"Food And Drinks", "Shopping", "Housing", "Transportation", "Vehicle", "Life & Entertainment"}; // Withd type
        //-------------------------------------------------------------
        // Fetch user data from userdata.txt
        try { // start try
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st = null;
            String dataRow = br.readLine();
            boolean dataExist=false;
            // continue here
            while(dataRow!=null) {
                st=new StringTokenizer(dataRow, ";");
                String currentRead = st.nextToken();
                if (currentRead.equals("UserData")) {
                    dataExist=true;
                    username = st.nextToken();
                    password = st.nextToken();
                    totalAccount = Integer.parseInt(st.nextToken());
                    totalTransaction = Integer.parseInt(st.nextToken());
                    totalDeposit =  Integer.parseInt(st.nextToken());
                    totalWithdrawal = Integer.parseInt(st.nextToken());
                } else if (currentRead.equals("Account")) {
                    String accountName = st.nextToken();
                    double accountValue = Double.parseDouble(st.nextToken());
                    account[assignedAccount]=new Account(accountName,accountValue);
                    assignedAccount+=1;
                } else if (currentRead.equals("Deposit")) {
                    double depositValue = Double.parseDouble(st.nextToken());
                    String depositDesc = st.nextToken();
                    String depositType = st.nextToken();
                    String toAccount = st.nextToken();
                    deposit[assignedDeposit]=new Deposit(currentRead, depositValue, depositDesc, depositType, toAccount);
                    transaction[assignedTransaction]=new Transaction(currentRead, depositValue, depositDesc);
                    assignedTransaction+=1;
                    assignedDeposit+=1;
                } else if (currentRead.equals("Withdrawal")) {
                    double withdrawalValue = Double.parseDouble(st.nextToken());
                    String withdrawalDesc = st.nextToken();
                    String withdrawalType = st.nextToken();
                    String fromAccount = st.nextToken();
                    withdrawal[assignedWithdrawal]=new Withdrawal(currentRead, withdrawalValue, withdrawalDesc, withdrawalType, fromAccount);
                    transaction[assignedTransaction]=new Transaction(currentRead, withdrawalValue, withdrawalDesc);
                    assignedTransaction+=1;
                    assignedWithdrawal+=1;
                }
                dataRow=br.readLine();
            }
            if (dataExist==false) {
                Scanner scan=new Scanner(System.in);
                System.out.println("It seems like you are a new user!");
                System.out.println("Please enter username : ");
                System.out.println("+--------------------+");
                username=scan.nextLine();
                System.out.println("+--------------------+");
                account[0].setName(username);
                System.out.println("Please enter password : ");
                System.out.println("+--------------------+");
                password=scan.nextLine();
                System.out.println("+--------------------+");
                System.out.println("Data has been successfully saved!");
                System.out.println("You may login now");
                account[0].pressEnterToContinue();
            }
            //close file
            br.close();
        } // end try 
        catch(EOFException eof) //to display a message if an error related to file occur
        {    System.out.println("Problem: "+eof.getMessage());}
        catch(FileNotFoundException e)
        {    System.out.println("Problem: "+e.getMessage());}
        catch(IOException ioe)
        {    System.out.println("Problem: "+ioe.getMessage());}
        finally
        {}
        // main operation ------------------------------------------------
        Scanner inputText=new Scanner(System.in);
        Scanner inputNum=new Scanner(System.in);
        boolean passLogin = false;
        while (passLogin==false) {
            System.out.println("\u000C");
            System.out.println("+--------------------+");
            System.out.println("|       LOGIN        |");
            System.out.println("+--------------------+");
            System.out.println("Enter username : ");
            System.out.println("+--------------------+");
            String inputName = inputText.nextLine();
            System.out.println("+--------------------+");
            System.out.println("Enter password : ");
            System.out.println("+--------------------+");
            String inputPass = inputText.nextLine();
            System.out.println("+--------------------+");
            if (inputName.equals(username) && inputPass.equals(password)) {
                passLogin=true;
                System.out.println("Logged in successfully!");
                account[0].pressEnterToContinue();
            } else {
                System.out.println("Incorrect username or password");
                account[0].pressEnterToContinue();
            }
        }
        while (passLogin==true) {
            System.out.println("\u000C");
            System.out.println("+--------------------+");
            System.out.println("|     MAIN MENU      |");
            System.out.println("+--------------------+");
            System.out.println(" 1] Add Transaction");
            System.out.println(" 2] Accounts");
            System.out.println(" 3] Transactions Log");
            System.out.println(" 4] Analytics");
            System.out.println(" 5] Exit");
            System.out.println("+--------------------+");
            int choice_2 = inputNum.nextInt();
            System.out.println("+--------------------+");
            if (choice_2==1) {
                boolean isAdding=true;
                while (isAdding==true) {
                    System.out.println("\u000C");
                    System.out.println("+--------------------+");
                    System.out.println("|  ADD TRANSACTION   |");
                    System.out.println("+--------------------+");
                    System.out.println(" 1] Deposit");
                    System.out.println(" 2] Withdrawal");
                    System.out.println(" 3] Exit");
                    System.out.println("Choose operation : ");
                    System.out.println("+--------------------+");
                    int choice_3=inputNum.nextInt();
                    System.out.println("+--------------------+");
                    if (choice_3==1) {
                        System.out.println("\u000C");
                        System.out.println("ADD DEPOSIT");
                        System.out.println("Choose an account for operation : ");
                        System.out.println("+--------------------+");
                        for (int i=0;i<totalAccount;i++) {
                            System.out.println(" "+(i+1)+"] "+account[i].getName()+" | "+account[i].getValue());
                            System.out.println("+--------------------+");
                        }
                        int acc=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        System.out.println("Enter value : ");
                        System.out.println("+--------------------+");
                        double enterValue=inputNum.nextDouble();
                        System.out.println("+--------------------+");
                        System.out.println("Enter description : ");
                        System.out.println("+--------------------+");
                        String desc=inputText.nextLine();
                        System.out.println("+--------------------+");
                        System.out.println("Enter deposit type : ");
                        for (int i=0;i<6;i++) {
                            System.out.println("["+(i+1)+"] "+depoTypeArr[i]);
                        }
                        System.out.println("+--------------------+");
                        int typeChoice=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        String depoType=depoTypeArr[typeChoice-1];
                        transaction[totalTransaction]=new Transaction("Deposit", enterValue, desc);
                        deposit[totalDeposit].setType("Deposit");
                        deposit[totalDeposit].setValue(enterValue);
                        deposit[totalDeposit].setDesc(desc);
                        deposit[totalDeposit].setDepositType(depoType);
                        deposit[totalDeposit].setToAccount(account[acc-1].getName());
                        double currentVal = account[acc-1].getValue();
                        account[acc-1].setValue(currentVal+=enterValue);
                        totalTransaction++;
                        totalDeposit++;
                        System.out.println("Transactions had been successfully added!");
                        account[0].pressEnterToContinue();
                    } else if (choice_3==2) {
                        System.out.println("\u000C");
                        System.out.println("ADD WITHDRAWAL");
                        System.out.println("Choose an account for operation : ");
                        System.out.println("+--------------------+");
                        for (int i=0;i<totalAccount;i++) {
                            System.out.println(" "+(i+1)+"] "+account[i].getName()+" | "+account[i].getValue());
                            System.out.println("+--------------------+");
                        }
                        int acc=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        System.out.println("Enter value : ");
                        System.out.println("+--------------------+");
                        double enterValue=inputNum.nextDouble();
                        System.out.println("+--------------------+");
                        System.out.println("Enter description : ");
                        System.out.println("+--------------------+");
                        String desc=inputText.nextLine();
                        System.out.println("+--------------------+");
                        System.out.println("Enter withdrawal type : ");
                        for (int i=0;i<6;i++) {
                            System.out.println("["+(i+1)+"] "+withTypeArr[i]);
                        }
                        System.out.println("+--------------------+");
                        int typeChoice=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        String withType=withTypeArr[typeChoice-1];
                        transaction[totalTransaction]=new Transaction("Withdrawal", enterValue, desc);
                        withdrawal[totalWithdrawal].setType("Withdrawal");
                        withdrawal[totalWithdrawal].setValue(enterValue);
                        withdrawal[totalWithdrawal].setDesc(desc);
                        withdrawal[totalWithdrawal].setWithdrawType(withType);
                        withdrawal[totalWithdrawal].setFromAccount(account[acc-1].getName());
                        double currentVal = account[acc-1].getValue();
                        account[acc-1].setValue(currentVal-=enterValue);
                        totalTransaction++;
                        totalWithdrawal++;
                        System.out.println("Transactions had been successfully added!");
                        account[0].pressEnterToContinue();
                    } else if (choice_3==3) {
                        isAdding=false;
                    } else {
                        System.out.println("Invalid Key!");
                        account[0].pressEnterToContinue();
                    }
                }
            } else if (choice_2 == 2) {
                boolean isAccount=true;
                while (isAccount==true) {
                    System.out.println("\u000C");
                    System.out.println("+--------------------+");
                    System.out.println("|    ACCOUNT LIST    |");
                    System.out.println("+--------------------+");
                    if (totalAccount==0) {
                        System.out.println("It seems empty here...");
                        System.out.println(" 1] Add Account");
                        System.out.println(" 2] Back");
                        System.out.println("+--------------------+");
                        int intAccNotExist=inputNum.nextInt();
                        if (intAccNotExist==1) {
                            System.out.println("\u000C");
                            System.out.println("Add Account");
                            System.out.println("Enter new account's name :");
                            System.out.println("+--------------------+");
                            String newAccountName = inputText.nextLine();
                            System.out.println("+--------------------+");
                            System.out.println("Enter new account's value : ");
                            System.out.println("+--------------------+");
                            double newAccountValue = inputNum.nextDouble();
                            System.out.println("+--------------------+");
                            account[totalAccount]=new Account(newAccountName, newAccountValue);
                            System.out.println("Account has been added!");
                            account[0].pressEnterToContinue();
                            totalAccount+=1;
                            System.out.println("\u000C");
                            System.out.println("+--------------------+");
                            System.out.println("|    ACCOUNT LIST    |");
                            System.out.println("+--------------------+");
                        } else if (intAccNotExist==2) {
                            break;
                        } else {
                            System.out.println("Invalid Key!");
                        }
                    }
                    System.out.println(" 1] Add Account");
                    System.out.println(" 2] Edit Account");
                    System.out.println(" 3] Remove Account");
                    System.out.println(" 4] Back");
                    System.out.println("+--------------------+");
                    for (int i=0;i<totalAccount;i++) {
                        System.out.println(" "+(i+1)+"] "+account[i].getName()+" | "+account[i].getValue());
                        System.out.println("+--------------------+");
                    }
                    double totalAssets=0;
                    for (int i=0;i<totalAccount;i++) { totalAssets += account[i].getValue(); }
                    System.out.println("Total assets : RM " + totalAssets);
                    System.out.println("+--------------------+");
                    int choice_3 = inputNum.nextInt();
                    System.out.println("+--------------------+");
                    if (choice_3==1) {
                        System.out.println("\u000C");
                        System.out.println("Add Account");
                        System.out.println("Enter new account's name :");
                        System.out.println("+--------------------+");
                        String newAccountName = inputText.nextLine();
                        System.out.println("+--------------------+");
                        System.out.println("Enter new account's value : ");
                        System.out.println("+--------------------+");
                        double newAccountValue = inputNum.nextDouble();
                        System.out.println("+--------------------+");
                        account[totalAccount]=new Account(newAccountName, newAccountValue);
                        System.out.println("Account has been added!");
                        account[0].pressEnterToContinue();
                        totalAccount+=1;
                    } else if (choice_3==2) {
                        System.out.println("\u000C");
                        System.out.println("Edit Account");
                        for (int i=0;i<totalAccount;i++) {
                            System.out.println(" "+(i+1)+"] "+account[i].getName()+" | "+account[i].getValue());
                            System.out.println("+--------------------+");
                        }
                        System.out.println("Choose account to be edited : ");
                        System.out.println("+--------------------+");
                        int chooseAccount=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        System.out.println(" 1] Edit name");
                        System.out.println(" 2] Edit value");
                        System.out.println("+--------------------+");
                        int chooseEdit=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        if (chooseEdit==1) {
                            System.out.println("Enter new account's name :");
                            System.out.println("+--------------------+");
                            String newName=inputText.nextLine();
                            System.out.println("+--------------------+");
                            account[chooseAccount-1].setName(newName);
                        } else {
                            System.out.println("Enter new account's value :");
                            System.out.println("+--------------------+");
                            double newValue=inputNum.nextDouble();
                            System.out.println("+--------------------+");
                            account[chooseAccount-1].setValue(newValue);
                        }
                        System.out.println("Account has been edited!");
                        account[0].pressEnterToContinue();
                    } else if (choice_3==3) {
                        System.out.println("\u000C");
                        System.out.println("Remove Account");
                        for (int i=0;i<totalAccount;i++) {
                            System.out.println(" "+(i+1)+"] "+account[i].getName()+" | "+account[i].getValue());
                            System.out.println("+--------------------+");
                        }
                        System.out.println("Choose account to be removed : ");
                        System.out.println("+--------------------+");
                        int chooseAccount=inputNum.nextInt(); // Achievement lessgooooo
                        System.out.println("+--------------------+");
                        for (int i=0;i<totalAccount;i++) {
                            if (i>=chooseAccount-1) {
                                account[i].setName(account[i+1].getName());
                                account[i].setValue(account[i+1].getValue());
                            }
                        }
                        totalAccount-=1;
                        System.out.println("Account has been removed!");
                        account[0].pressEnterToContinue();
                    } else if (choice_3==4) {
                        isAccount=false;
                    } else {
                        System.out.println("Invalid key!");
                        account[0].pressEnterToContinue();
                    }
                }
            } else if (choice_2==3) {
                boolean isTransaction=true;
                while (isTransaction==true) {
                    System.out.println("\u000C");
                    System.out.println("+--------------------+");
                    System.out.println("|  TRANSACTIONS LOG  |");
                    System.out.println("+--------------------+");
                    if (totalTransaction==0) {
                        System.out.println("It seems empty here");
                        account[0].pressEnterToContinue();
                        break;
                    }
                    System.out.println(" 1] Remove");
                    System.out.println(" 2] Back");
                    System.out.println("+--------------------+");
                    System.out.println(" All Transactions -");
                    for (int i=0;i<totalTransaction;i++) {
                        System.out.println(" "+(i+1)+"] "+transaction[i].toString());
                    }
                    System.out.println("+--------------------+");
                    int choice_3=inputNum.nextInt();
                    System.out.println("+--------------------+");
                    if (choice_3==1) {
                        System.out.println("\u000C");
                        System.out.println("Remove Transaction");
                        for (int i=0;i<totalTransaction;i++) {
                            System.out.println(" "+(i+1)+"] "+transaction[i].toString());
                        }
                        System.out.println("Choose transaction to be removed : ");
                        System.out.println("+--------------------+");
                        int chooseTrans=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        String selectedDesc=transaction[chooseTrans-1].getDesc();
                        for (int i=0;i<totalTransaction;i++) {
                            if (i>=chooseTrans-1) {
                                transaction[i].setDesc(transaction[i+1].getDesc());
                                transaction[i].setValue(transaction[i+1].getValue());
                            }
                        }
                        totalTransaction-=1;
                        int index=0;
                        String transType="Undefined";
                        for (int i=0;i<totalDeposit;i++) {
                            if (deposit[i].getDesc().equals(selectedDesc)) {
                                transType="Deposit";
                                index=i;
                            }
                        }
                        for (int i=0;i<totalWithdrawal;i++) {
                            if (withdrawal[i].getDesc().equals(selectedDesc)) {
                                transType="Withdrawal";
                                index=i;
                            }
                        }
                        if (transType.equals("Deposit")) {
                            for (int i=0;i<totalDeposit;i++) {
                                if (i>=index) {
                                    deposit[i].setDesc(deposit[i+1].getDesc());
                                    deposit[i].setValue(deposit[i+1].getValue());
                                    deposit[i].setDepositType(deposit[i+1].getDepositType());
                                }
                            }
                            totalDeposit-=1;
                        } else {
                            for (int i=0;i<totalWithdrawal;i++) {
                                if (i>=index) {
                                    withdrawal[i].setDesc(withdrawal[i+1].getDesc());
                                    withdrawal[i].setValue(withdrawal[i+1].getValue());
                                    withdrawal[i].setWithdrawType(withdrawal[i+1].getWithdrawType());
                                }
                            }
                            totalWithdrawal=1;
                        }
                        System.out.println("Transaction has been removed!");
                        account[0].pressEnterToContinue();
                    } else if (choice_3==2) {
                        isTransaction=false;
                    } else {
                        System.out.println("Invalid key!");
                        account[0].pressEnterToContinue();
                    }
                }
            } else if (choice_2==4) {
                System.out.println("\u000C");
                System.out.println("+--------------------+");
                System.out.println("|     ANALYTICS      |");
                System.out.println("+--------------------+");
                double totalT=0, totalD=0, totalW=0; // trans, depo, withdraw
                double [] totalTypeDepo={0,0,0,0,0,0};
                double [] totalTypeWith={0,0,0,0,0,0};
                double [] percentDepo={0,0,0,0,0,0}; // 6 depo
                double [] percentWith={0,0,0,0,0,0}; // 6 withdraw
                String [] indicatorDepo={"","","","","",""};
                String [] indicatorWithd={"","","","","",""};
                int [] indicatorInt = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 120};
                String [] indicatorStr = {"|", "||", "|||", "||||", "|||||","||||||",
                    "|||||||", "||||||||", "|||||||||", "||||||||||", "Max"};
                Formatter fm=new Formatter(); // set precision for float num 
                for (int i=0;i<totalTransaction;i++) { totalT += transaction[i].getValue(); }
                for (int i=0;i<totalDeposit;i++) {
                    totalD += deposit[i].getValue();
                    for (int j=0;j<6;j++) {
                        if (depoTypeArr[j].equals(deposit[i].getDepositType())) {
                            totalTypeDepo[j]+=deposit[i].getValue();
                        }
                    }
                }
                for (int i=0;i<totalWithdrawal;i++) {
                    totalW += withdrawal[i].getValue();
                    for (int j=0;j<6;j++) {
                        if (withTypeArr[j].equals(withdrawal[i].getWithdrawType())) {
                            totalTypeWith[j]+=withdrawal[i].getValue();
                        }
                    }
                }
                for (int i = 0;i<6;i++) {
                    percentDepo[i]=Math.round(((totalTypeDepo[i]/totalD)*100));
                    for (int j=0;j<10;j++) {
                        if (percentDepo[i]>=indicatorInt[j]&&percentDepo[0]<indicatorInt[j+1]) {
                            indicatorDepo[i]=indicatorStr[j];
                        }
                    }
                    percentWith[i]=Math.round(((totalTypeWith[i]/totalW)*100));
                    for (int j=0;j<10;j++) {
                        if (percentWith[i]>=indicatorInt[j]&&percentWith[0]<indicatorInt[j+1]) {
                            indicatorWithd[i]=indicatorStr[j];
                        }
                    }
                }
                System.out.println("Overall -");
                System.out.println("Total transactions : RM " + totalT);
                System.out.println("Total deposit : RM " + totalD);
                System.out.println("Total withdrawal : RM " + totalW);
                System.out.println("+--------------------+");
                System.out.println("Deposit -");
                for (int i=0;i<6;i++) {
                    System.out.println("["+(i+1)+"] "+depoTypeArr[i]+" : RM "+totalTypeDepo[i]);
                    System.out.println("Percent : "+percentDepo[i]+"% , Frequency : "+indicatorDepo[i]);
                }
                System.out.println("+--------------------+");
                System.out.println("Withdrawal -");
                for (int i=0;i<6;i++) {
                    System.out.println("["+(i+1)+"] "+withTypeArr[i]+" : RM "+totalTypeWith[i]);
                    System.out.println("Percent : "+percentWith[i]+"% , Frequency : "+indicatorWithd[i]);
                }
                System.out.println("+--------------------+");
                account[0].pressEnterToContinue();
            } else if (choice_2==5) {
                System.out.println("\u000C");
                System.out.println("Logged out successfully");
                passLogin=false;
            }
        }
        // saving sessions data to related file (userdata.txt)
        try { // start try
            // saving user data
            FileWriter fwData=new FileWriter("userdata.txt");
            BufferedWriter bwData=new BufferedWriter(fwData);
            PrintWriter pwData=new PrintWriter(bwData);
            pwData.println("UserData;"+username+";"+password+";"+totalAccount+
                            ";"+totalTransaction+";"+totalDeposit+";"+totalWithdrawal);
            for (int i=0;i<totalAccount;i++) {
                pwData.println("Account;"+account[i].getName() + ";" + account[i].getValue());
            }
            for (int i=0;i<totalDeposit;i++) {
                pwData.println(deposit[i].getType()+";"+deposit[i].getValue()+";"+
                                deposit[i].getDesc()+";"+deposit[i].getDepositType()+";"+deposit[i].getToAccount());
            }
            for (int i=0;i<totalWithdrawal;i++) {
                pwData.println(withdrawal[i].getType()+";"+withdrawal[i].getValue()+";"+
                                withdrawal[i].getDesc()+";"+withdrawal[i].getWithdrawType()+";"+withdrawal[i].getFromAccount());
            }
            pwData.close();
        } // end try 
        catch(EOFException eof) //to display a message if an error related to file occur
        {    System.out.println("Problem: "+eof.getMessage());}
        catch(FileNotFoundException e)
        {    System.out.println("Problem: "+e.getMessage());}
        catch(IOException ioe)
        {    System.out.println("Problem: "+ioe.getMessage());}
        finally
        {}
    }
}
