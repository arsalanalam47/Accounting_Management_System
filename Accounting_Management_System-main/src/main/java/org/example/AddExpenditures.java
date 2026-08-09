package org.example;

public class AddExpenditures {
    createAccount account;
    private double health;
    private double rent;
    private double grocery;
    private double dailyuse;
    private double childrenfee;
    private double misc;
    public AddExpenditures(createAccount account){
        this.account=account;
        this.health=0;
        this.rent=0;
        this.grocery=0;
        this.dailyuse=0;
        this.childrenfee=0;
        this.misc=0;
    }
    public double calculateSavings(){
        return (account.currentAmount);
    }
    public double totalUsage(){
        return health+rent+grocery+dailyuse+childrenfee;
    }
    public void addToHealth(Double amount){

        health=health+amount;
        account.currentAmount=account.currentAmount-amount;
    }
    public void addToRent(Double amount){

        rent=rent+amount;
        account.currentAmount=account.currentAmount-amount;
    }
    public void addToGrocery(Double amount){

        grocery=grocery+amount;
        account.currentAmount=account.currentAmount-amount;
    }
    public void addToDailyuse(Double amount){

        dailyuse=dailyuse+amount;
        account.currentAmount=account.currentAmount-amount;
    }
    public void addToChildrenfee(Double amount){

        childrenfee=childrenfee+amount;
        account.currentAmount=account.currentAmount-amount;
    }
    public void addToMisc(Double amount){
        misc=misc+amount;
        account.currentAmount=account.currentAmount-amount;
    }
    public double getHealth(){

        return health;
    }
    public double getRent(){

        return rent;
    }
    public double getGrocery(){

        return grocery;
    }
    public double getDailyuse(){

        return dailyuse;
    }
    public double getChildrenfee(){

        return childrenfee;
    }
    public double getMisc(){
        return misc;
    }
    public String checkMisc(){
        if(misc/account.currentAmount>0.300000000){
            return "Miscellaneous expenditures are exceeding\nTry to get control over them";
        }
        else{
            return"";
        }
    }
    public String toString(){
        return "Health: "+getHealth()+"\nRent: "+getRent()+"\nDailyUse: "+getDailyuse()+"\nChildrenFee: "+getChildrenfee()+"\nMisc: "+getMisc();
    }
}
