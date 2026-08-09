package org.example;

import java.util.Random;
import java.util.HashSet;

public class createAccount {
    private String name;
    private String cnic;
    private String monthlySalary;
    private String password;
    private String accNumber;
    public double currentAmount;
    Random rand = new Random();

    public static HashSet<String> accountNumbers= new HashSet<>();

    public createAccount(String name,String cnic,String monthlySalary) {
    this.name=name;
    this.cnic=cnic;
    this.monthlySalary= String.valueOf(Double.parseDouble(monthlySalary));
    setInitialAmount(Double.parseDouble(monthlySalary));
    }

    private String generateUniqueNumber(){
        String temp;
        do{
            temp=cnic+rand.nextInt(2);
        }
        while(accountNumbers.contains(temp));
        if(checkAccountNumber(temp)){
            return"Account number already exists";
        }
        else {accountNumbers.add(temp);
        return temp;}
    }
    public boolean checkAccountNumber(String accountNumber){

        return accountNumbers.contains(accountNumber);
    }

    public void setAccNumber() {
        accNumber = generateUniqueNumber();
    }

    public String getName(){

        return name;
    }
    public double getSalary(){

        return Double.parseDouble(monthlySalary);
    }
    public void setInitialAmount(double amount){

        this.currentAmount=getSalary();
    }
    public double getCurrentAmount(){

        return currentAmount;
    }
    public String getAccNumber(){

        return accNumber;
    }

public void setSalaryForNextMonth(double amount){
        currentAmount= getSalary()+amount;
}

    public String getcnic(){
        return cnic;
    }
    public void setPin(String password){

        this.password=password;
    }
    public String getaccNumber(){
        return accNumber;

    }
    public String getPassword(){

        return password;
    }

    public String toString(){
        return "\nName:"+name+"\nCnic: "+cnic+"\nMonthly Salary: "+monthlySalary+"\nAcc Number: "+accNumber+"\nCurrent Amount: "+currentAmount;
    }
}
