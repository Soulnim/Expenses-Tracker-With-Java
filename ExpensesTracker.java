import java.util.*;
import java.io.*;
public class ExpenseTracker
{
    public static void main(String args[]) {
        // Fetch user data from userdata.txt
        User user=new User();
        String login_required="Undefined", username="Undefined", password="Undefined"; // user data
        // get total account from userdata.txt
        int totalAccount=0;
        // get total transaction, deposit and withdrawal from userdata.txt
        int totalTransaction=0;
        int totalDeposit=0;
        int totalWithdraw=0;
        try { // start try
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st = null;
            String dataRow = br.readLine();
            boolean dataExist=false;
            while(dataRow!=null) {
                st=new StringTokenizer(dataRow, ";");
                username=st.nextToken();
                password=st.nextToken();
                totalAccount=Integer.parseInt(st.nextToken());
                totalTransaction=Integer.parseInt(st.nextToken());
                totalDeposit=Integer.parseInt(st.nextToken());
                totalWithdraw=Integer.parseInt(st.nextToken());
                // read new line of data
                dataExist=true;
                dataRow=br.readLine();
            }
            if (dataExist==false) {
                Scanner scan=new Scanner(System.in);
                System.out.println("It seems like you are a new user!");
                System.out.println("Please enter username : ");
                System.out.println("+--------------------+");
                username=scan.nextLine();
                System.out.println("+--------------------+");
                user.setName(username);
                System.out.println("Please enter password : ");
                System.out.println("+--------------------+");
                password=scan.nextLine();
                System.out.println("+--------------------+");
                user.setPass(password);
                System.out.println("Data has been successfully saved!");
                System.out.println("You may login now");
                user.pressEnterToContinue();
                // write new data to file userdata.txt
                //FileWriter fw=new FileWriter("userdata.txt");
                //BufferedWriter bw=new BufferedWriter(fw);
                //PrintWriter pw=new PrintWriter(bw);
                //pw.println(user.getName() + ";" + user.getPass()+";0;0;0;0");
                //pw.close();
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
        // Define user, based on the fetched data
        user.setUser(username, password, totalAccount, totalTransaction, totalDeposit, totalWithdraw);
        //------------------------------
        // fetch account data from account.txt
        Account [] account=new Account[100]; // declare Account
        for (int i=0;i<100;i++) {
            account[i]=new Account();
        }
        int counterU=0; // to iterate through account's array
        try { // start try
            FileReader fr = new FileReader("account.txt");
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st = null;
            String dataRow = br.readLine();
            while(dataRow!=null) {
                st=new StringTokenizer(dataRow, ";");
                String name=st.nextToken();
                double value=Double.parseDouble(st.nextToken());
                
                // Assign data
                account[counterU]=new Account(name,value);
                // read new line of data
                counterU+=1;
                dataRow=br.readLine();
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
        // fetch transaction data -------------------------
        // assign transaction data
        Transaction [] transaction = new Transaction[100]; // Define Transaction
        Deposit [] deposit = new Deposit[100]; // Define Deposit
        Withdrawal [] withdrawal = new Withdrawal[100]; // Define Withdrawal
        for (int i=0;i<100;i++) {
            transaction[i]=new Transaction();
        }
        for (int i=0;i<100;i++) {
            deposit[i]=new Deposit("Undefined", 0, "Undefined", "Undefined", "Undefined");
        }
        for (int i=0;i<100;i++) {
            withdrawal[i]=new Withdrawal("Undefined", 0, "Undefined", "Undefined", "Undefined");
        }
        int counterT=0;
        int assignedDepo=0, assignedWithdraw=0;
        try { // start try
            FileReader fr = new FileReader("transactions.txt");
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer st = null;
            String dataRow = br.readLine();
            while(dataRow!=null) {
                st=new StringTokenizer(dataRow, ";");
                String type = st.nextToken();
                double transVal = Double.parseDouble(st.nextToken());
                String transDesc = st.nextToken();
                String transType = st.nextToken();
                String transToFrom = st.nextToken();
                // Assign data (Transactions[])
                transaction[counterT]=new Transaction(type, transVal, transDesc);
                // Differentiate Deposit and Withdrawal
                if (type.equalsIgnoreCase("Deposit")) {
                    deposit[assignedDepo]=new Deposit(type, transVal, transDesc, transType, transToFrom);
                    assignedDepo+=1;
                } else {
                    withdrawal[assignedWithdraw]=new Withdrawal(type, transVal, transDesc, transType, transToFrom);
                    assignedWithdraw+=1;
                }
                // read new line of data
                counterT+=1;
                dataRow=br.readLine();
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
            if (inputName.equals(user.getName()) && inputPass.equals(user.getPass())) {
                passLogin=true;
                System.out.println("Logged in successfully!");
                user.pressEnterToContinue();
            } else {
                System.out.println("Incorrect username or password");
                user.pressEnterToContinue();
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
                        System.out.println(" 1] Income");
                        System.out.println(" 2] Gift");
                        System.out.println(" 3] Collection");
                        String [] depoTypeArr={"Income", "Gift", "Collection"};
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
                        user.pressEnterToContinue();
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
                        System.out.println(" 1] Food And Beverages");
                        System.out.println(" 2] Shopping");
                        System.out.println(" 3] Transportation");
                        String [] withTypeArr={"Food And Beverages", "Shopping", "Transportation"};
                        System.out.println("+--------------------+");
                        int typeChoice=inputNum.nextInt();
                        System.out.println("+--------------------+");
                        String withType=withTypeArr[typeChoice-1];
                        transaction[totalTransaction]=new Transaction("Withdrawal", enterValue, desc);
                        withdrawal[totalWithdraw].setType("Withdrawal");
                        withdrawal[totalWithdraw].setValue(enterValue);
                        withdrawal[totalWithdraw].setDesc(desc);
                        withdrawal[totalWithdraw].setWithdrawType(withType);
                        withdrawal[totalWithdraw].setFromAccount(account[acc-1].getName());
                        double currentVal = account[acc-1].getValue();
                        account[acc-1].setValue(currentVal-=enterValue);
                        totalTransaction++;
                        totalWithdraw++;
                        System.out.println("Transactions had been successfully added!");
                        user.pressEnterToContinue();
                    } else if (choice_3==3) {
                        isAdding=false;
                    } else {
                        System.out.println("Invalid Key!");
                        user.pressEnterToContinue();
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
                            user.pressEnterToContinue();
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
                        user.pressEnterToContinue();
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
                        user.pressEnterToContinue();
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
                        user.pressEnterToContinue();
                    } else if (choice_3==4) {
                        isAccount=false;
                    } else {
                        System.out.println("Invalid key!");
                        user.pressEnterToContinue();
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
                        user.pressEnterToContinue();
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
                        for (int i=0;i<totalWithdraw;i++) {
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
                            for (int i=0;i<totalWithdraw;i++) {
                                if (i>=index) {
                                    withdrawal[i].setDesc(withdrawal[i+1].getDesc());
                                    withdrawal[i].setValue(withdrawal[i+1].getValue());
                                    withdrawal[i].setWithdrawType(withdrawal[i+1].getWithdrawType());
                                }
                            }
                            totalWithdraw-=1;
                        }
                        System.out.println("Transaction has been removed!");
                        user.pressEnterToContinue();
                    } else if (choice_3==2) {
                        isTransaction=false;
                    } else {
                        System.out.println("Invalid key!");
                        user.pressEnterToContinue();
                    }
                }
            } else if (choice_2==4) {
                System.out.println("\u000C");
                System.out.println("+--------------------+");
                System.out.println("|     ANALYTICS      |");
                System.out.println("+--------------------+");
                double totalT=0, totalD=0, totalW=0; // trans, depo, withdraw
                double [] totalTypeDepo={0,0,0};
                double [] totalTypeWith={0,0,0};
                double [] percentDepo={0,0,0}; // 3 depo
                double [] percentWith={0,0,0}; // 3 withdraw
                for (int i=0;i<totalTransaction;i++) { totalT += transaction[i].getValue(); }
                for (int i=0;i<totalDeposit;i++) {
                    totalD += deposit[i].getValue(); 
                    if (deposit[i].getDepositType().equals("Income")) {
                        totalTypeDepo[0]+=deposit[i].getValue();
                    } else if (deposit[i].getDepositType().equals("Gift")) {
                        totalTypeDepo[1]+=deposit[i].getValue();
                    } else {
                        totalTypeDepo[2]+=deposit[i].getValue();
                    }
                }
                for (int i=0;i<totalWithdraw;i++) {
                    totalW += withdrawal[i].getValue(); 
                    if (withdrawal[i].getWithdrawType().equals("Food And Beverages")) {
                        totalTypeWith[0]+=withdrawal[i].getValue();
                    } else if (withdrawal[i].getWithdrawType().equals("Shopping")) {
                        totalTypeWith[1]+=withdrawal[i].getValue();
                    } else {
                        totalTypeWith[2]+=withdrawal[i].getValue();
                    }
                }
                for (int i = 0;i<3;i++) {
                    Formatter fm=new Formatter(); // set precision for float num
                    percentDepo[i]=Math.round(((totalTypeDepo[i]/totalD)*100));
                    percentWith[i]=Math.round(((totalTypeWith[i]/totalW)*100));
                }
                int [] indicatorInt = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 120};
                String [] indicatorStr = {"|", "||", "|||", "||||", "|||||","||||||",
                    "|||||||", "||||||||", "|||||||||", "||||||||||", "Max"};
                System.out.println("Overall -");
                System.out.println("Total transactions : RM " + totalT);
                System.out.println("Total deposit : RM " + totalD);
                System.out.println("Total withdrawal : RM " + totalW);
                System.out.println("+--------------------+");
                System.out.println("Deposit -");
                System.out.println("[1] Income : RM " + totalTypeDepo[0]);
                System.out.println("Percent : " + percentDepo[0] + "%");
                for (int i=0;i<10;i++) {
                    if (percentDepo[0]>=indicatorInt[i]&&percentDepo[0]<indicatorInt[i+1]) {
                        System.out.println("Indicator : " + indicatorStr[i]);
                    }
                }
                System.out.println("[2] Gift : RM " + totalTypeDepo[1]);
                System.out.println("Percent : " + percentDepo[1] + "%");
                for (int i=0;i<10;i++) {
                    if (percentDepo[1]>=indicatorInt[i]&&percentDepo[1]<indicatorInt[i+1]) {
                        System.out.println("Indicator : " + indicatorStr[i]);
                    }
                }
                System.out.println("[3] Collections : RM " + totalTypeDepo[2]);
                System.out.println("Percent : " + percentDepo[2] + "%");
                for (int i=0;i<10;i++) {
                    if (percentDepo[2]>=indicatorInt[i]&&percentDepo[2]<indicatorInt[i+1]) {
                        System.out.println("Indicator : " + indicatorStr[i]);
                    }
                }
                System.out.println("+--------------------+");
                System.out.println("Withdrawal -");
                System.out.println("[1] Food And Beverages : RM " + totalTypeWith[0]);
                System.out.println("Percent : " + percentWith[0] + "%");
                for (int i=0;i<10;i++) {
                    if (percentWith[0]>=indicatorInt[i]&&percentWith[0]<indicatorInt[i+1]) {
                        System.out.println("Indicator : " + indicatorStr[i]);
                    }
                }
                System.out.println("[2] Shopping : RM " + totalTypeWith[1]);
                System.out.println("Percent : " + percentWith[1] + "%");
                for (int i=0;i<10;i++) {
                    if (percentWith[1]>=indicatorInt[i]&&percentWith[1]<indicatorInt[i+1]) {
                        System.out.println("Indicator : " + indicatorStr[i]);
                    }
                }
                System.out.println("[3] Transportation : RM " + totalTypeWith[2]);
                System.out.println("Percent : " + percentWith[2] + "%");
                for (int i=0;i<10;i++) {
                    if (percentWith[2]>=indicatorInt[i]&&percentWith[2]<indicatorInt[i+1]) {
                        System.out.println("Indicator : " + indicatorStr[i]);
                    }
                }
                System.out.println("+--------------------+");
                user.pressEnterToContinue();
            } else if (choice_2==5) {
                System.out.println("\u000C");
                System.out.println("Logged out successfully");
                passLogin=false;
            }
        }
        // saving sessions data to related file (userdata.txt/account.txt/transactions.txt)
        try { // start try
            // saving accounts
            FileWriter fwAccount=new FileWriter("account.txt");
            BufferedWriter bwAccount=new BufferedWriter(fwAccount);
            PrintWriter pwAccount=new PrintWriter(bwAccount);
            for (int i=0;i<totalAccount;i++) {
                pwAccount.println(account[i].getName() + ";" + account[i].getValue());
            }
            pwAccount.close();
            // saving transactions
            FileWriter fwTrans=new FileWriter("transactions.txt");
            BufferedWriter bwTrans=new BufferedWriter(fwTrans);
            PrintWriter pwTrans=new PrintWriter(bwTrans);
            for (int i=0;i<totalDeposit;i++) {
                pwTrans.println(deposit[i].getType()+";"+deposit[i].getValue()+";"+
                                deposit[i].getDesc()+";"+deposit[i].getDepositType()+";"+deposit[i].getToAccount());
            }
            for (int i=0;i<totalWithdraw;i++) {
                pwTrans.println(withdrawal[i].getType()+";"+withdrawal[i].getValue()+";"+
                                withdrawal[i].getDesc()+";"+withdrawal[i].getWithdrawType()+";"+withdrawal[i].getFromAccount());
            }
            pwTrans.close();
            // saving user data
            FileWriter fwData=new FileWriter("userdata.txt");
            BufferedWriter bwData=new BufferedWriter(fwData);
            PrintWriter pwData=new PrintWriter(bwData);
            pwData.println(user.getName()+";"+user.getPass()+";"+totalAccount+
                            ";"+totalTransaction+";"+totalDeposit+";"+totalWithdraw);
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
