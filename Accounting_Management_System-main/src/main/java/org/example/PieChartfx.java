package org.example;

import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;


public class PieChartfx {
    AddExpenditures addExp;

    public PieChartfx(AddExpenditures addExp){
        this.addExp = addExp;
    }

    public double getHealth(){
        double temp;
        temp = (addExp.getHealth() / addExp.totalUsage()) * 100;
        return temp;
    }
    public double getRent(){
        double temp;
        temp = (addExp.getRent() / addExp.totalUsage()) * 100;
        return temp;
    }
    public double getGrocery(){
        double temp;
        temp = (addExp.getGrocery() / addExp.totalUsage()) * 100;
        return temp;
    }
    public double getDailyuse(){
        double temp;
        temp = (addExp.getDailyuse() / addExp.totalUsage()) * 100;
        return temp;
    }
    public double getChildrenfee(){
        double temp;
        temp = (addExp.getChildrenfee() / addExp.totalUsage()) * 100;
        return temp;
    }
    public double getMisc(){
        double temp;
        temp = (addExp.getMisc() / addExp.totalUsage()) * 100;
        return temp;
    }
    public String printReport(){
        return "Health: "+addExp.getHealth()+" "+getHealth()+"%\nRent: "+addExp.getRent()+" "+getRent()+"%\nGrocery: "+addExp.getGrocery()+" "+getGrocery()+
                "%\nDailyUse="+addExp.getDailyuse()+" "+getDailyuse()+"%\nChildren Fee:"+addExp.getChildrenfee()+
                " "+getChildrenfee()+"%\nMisc:"+addExp.getMisc()+" "+getMisc();
    }

    public void printPieChartfx(){
        Stage stage = new Stage();
        stage.setTitle("Expenditure Pie Chart");

        PieChart pie = new PieChart();
        pie.setTitle("Expenditure Breakdown");

        pie.getData().add(new PieChart.Data("Health"+getHealth(), addExp.getHealth()));
        pie.getData().add(new PieChart.Data("Rent"+getRent(), addExp.getRent()));
        pie.getData().add(new PieChart.Data("Grocery"+getGrocery(), addExp.getGrocery()));
        pie.getData().add(new PieChart.Data("Daily Use"+getDailyuse(), addExp.getDailyuse()));
        pie.getData().add(new PieChart.Data("Children Fee"+getDailyuse(), addExp.getChildrenfee()));
        pie.getData().add(new PieChart.Data("Misc"+getMisc(), addExp.getMisc()));

        Scene scene = new Scene(pie, 500, 400);
        stage.setScene(scene);
        stage.show();
    }
}

