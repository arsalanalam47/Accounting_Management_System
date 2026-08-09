package org.example;

public class calculateBalance {
    AddExpenditures addExp;
    createAccount account;
    Double balance;
    public calculateBalance(AddExpenditures addExp,createAccount account){
        this.addExp=addExp;
        this.account=account;

    }
    public void addAmount(double amount){
        account.currentAmount=account.currentAmount+amount;
    }
    public double getBalance(){
        balance =account.currentAmount;
        return balance;
    }


    public void updateSalary(double amount) {

        account.setSalaryForNextMonth(amount);
    }
}
