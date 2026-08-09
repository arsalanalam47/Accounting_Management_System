package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    ArrayList<createAccount> accounts = new ArrayList<>();


    @Override
    public void start(Stage stage) {

        stage.setTitle("Accounting Management System");
        stage.setScene(homeScene(stage));
        stage.show();
    }
    public Scene homeScene(Stage stage) {

        Button loginBtn = new Button("Login");
        Button signupBtn = new Button("Sign Up");
        TextField lABEL = new TextField();


        lABEL.editableProperty().setValue(false);
        VBox box = new VBox(10, new Label("Welcome!"), loginBtn, signupBtn
        );
        box.setPadding(new Insets(20));

        loginBtn.setOnAction(e -> stage.setScene(loginScene(stage)));
        signupBtn.setOnAction(e -> stage.setScene(signUpScene(stage)));

        return new Scene(box, 350, 300);
    }
    public Scene signUpScene(Stage stage) {

        TextField name = new TextField();
        TextField cnic = new TextField();
        TextField salary = new TextField();
        PasswordField password = new PasswordField();

        name.setPromptText("Enter Name");
        cnic.setPromptText("Enter CNIC");
        salary.setPromptText("Enter Monthly Income");
        password.setPromptText("Create Password");

        Button create = new Button("Create Account");
        Button back = new Button("Back");

        Label msg = new Label();

        create.setOnAction(e -> {
            if (name.getText().isEmpty() || cnic.getText().isEmpty()
                    || salary.getText().isEmpty() || password.getText().isEmpty()) {
                msg.setText(" Please fill all fields.");
                return;
            }


            createAccount acc = new createAccount(name.getText(), cnic.getText(), salary.getText());
            acc.setPin(password.getText());
            acc.setAccNumber();
            accounts.add(acc);

            msg.setText("✔ Account created!\nAccount Details: " + acc.toString());
        });

        back.setOnAction(e -> stage.setScene(homeScene(stage)));

        VBox box = new VBox(10, name, cnic, salary, password, create, back, msg);
        box.setPadding(new Insets(20));

        return new Scene(box, 350, 400);
    }

    public Scene loginScene(Stage stage) {

        TextField accNum = new TextField();
        PasswordField pass = new PasswordField();
        Button next = new Button("Login");
        Button back = new Button("Back");
        Label msg = new Label();

        accNum.setPromptText("Enter Account Number");
        pass.setPromptText("Enter Password");

        next.setOnAction(e -> {

            for (createAccount acc : accounts) {

                if (acc.getaccNumber().equals(accNum.getText()) &&
                        acc.getPassword().equals(pass.getText())) {

                    AddExpenditures exp = new AddExpenditures(acc);
                    calculateBalance bal = new calculateBalance(exp, acc);
                   // Savings sav = new Savings(bal, acc);

                    stage.setScene(userDashboard(stage, acc, exp, bal));
                    return;
                }
            }

            msg.setText(" Incorrect account or password.");
        });

        back.setOnAction(e -> stage.setScene(homeScene(stage)));

        VBox box = new VBox(10, accNum, pass, next, back, msg);
        box.setPadding(new Insets(20));

        return new Scene(box, 350, 300);
    }


    public Scene userDashboard(Stage stage, createAccount acc,
                               AddExpenditures exp,
                               calculateBalance bal) {

        Button addCredits = new Button("Add Credits");
        Button printReport = new Button("Print Report");
        Button nextMonth = new Button("Move to Next Month");
        Button checkBalance= new Button("Check Balance");
        Button addAmount=new Button("Add Amount");
        Button back = new Button("Log Out");

        Label header = new Label("Welcome, " + acc.getName());

        addCredits.setOnAction(e -> {
            stage.setScene(addCreditsScene(stage, acc, exp, bal));

        });
        printReport.setOnAction(e -> {
            PieChartfx chart = new PieChartfx(exp);
            Button pie = new Button("Print PieChart");
            Button reportButton = new Button("Print Report");
            Button backButton = new Button("Back");
            pie.setOnAction(e1->{
                chart.printPieChartfx();
            });
            reportButton.setOnAction(e1->{
                new Alert(Alert.AlertType.INFORMATION,chart.printReport()).show();
            });
            backButton.setOnAction(e1->{
                stage.setScene(userDashboard(stage,acc,exp,bal));
            });
            HBox box = new HBox(10, pie, reportButton, backButton);
            box.setPadding(new Insets(20));
            Scene  reportScene = new Scene(box, 350, 300);
            stage.setScene(reportScene);

            //chart.printPieChartfx();
           // new Alert(Alert.AlertType.INFORMATION,chart.printReport()).show();
        });
        checkBalance.setOnAction(e -> {

            new Alert(Alert.AlertType.INFORMATION, "---Remaining Amount---\nYour Current Amount is: "+acc.getCurrentAmount() ).show();
        });
        nextMonth.setOnAction(e -> {
            bal.updateSalary(bal.getBalance());
            new Alert(Alert.AlertType.INFORMATION, "Salary updated for next month!\nSalary="+acc.getSalary()+"\nPrevious Amount:"+(acc.getCurrentAmount()-acc.getSalary())+
                    "\nNew Amount="+acc.getCurrentAmount()).show();
        });
        addAmount.setOnAction(e -> {
            TextField amount = new TextField();
            amount.setPromptText("Enter Amount");
            Button add=new Button("Add");
            Button goBack=new Button("Back");
            TextArea output = new TextArea();
            output.setEditable(false);
            output.setPromptText("----OUTPUT SCREEN----");
            add.setOnAction(e1->{
                double temp;
                temp=Double.parseDouble(amount.getText());
                bal.addAmount(temp);
output.setText(temp+" added to Your Account Successfully\n"+"Your Current Amount is: "+bal.getBalance());
            });
            goBack.setOnAction(e1->{stage.setScene(userDashboard(stage,acc,exp,bal));});
            VBox box = new VBox(10, add, goBack, amount,output);
            box.setPadding(new Insets(20));
            Scene addAmountScene= new Scene(box, 350, 300);
            stage.setScene(addAmountScene);
        });
        back.setOnAction(e -> stage.setScene(homeScene(stage)));

        VBox box = new VBox(15, header, addCredits, printReport,checkBalance, nextMonth,addAmount, back);
        box.setPadding(new Insets(20));

        return new Scene(box, 350, 300);
    }

    public Scene addCreditsScene(Stage stage,
                                 createAccount acc,
                                 AddExpenditures exp,
                                 calculateBalance bal) {

        TextField amount = new TextField();
        amount.setPromptText("Enter Amount");

        ComboBox<String> category = new ComboBox<>();
        category.setPromptText("Category");
        category.getItems().addAll("Health", "Rent", "Grocery", "Daily Use", "Children Fee","Misc");

        Button submit = new Button("Add");
        Button back = new Button("Back");
        Label msg = new Label();

        submit.setOnAction(e -> {

            if (amount.getText().isEmpty() || category.getValue() == null) {
                msg.setText(" Please enter amount and select category.");
                return;
            }
            double amt = Double.parseDouble(amount.getText());
            if(amt>acc.getCurrentAmount()){
                msg.setText(" Invalid Input\nYour current amount is greater than your account balance.");
                return;
            }

            switch (category.getValue()) {
                case "Health": exp.addToHealth(amt); break;
                case "Rent": exp.addToRent(amt); break;
                case "Grocery": exp.addToGrocery(amt); break;
                case "Daily Use": exp.addToDailyuse(amt); break;
                case "Children Fee": exp.addToChildrenfee(amt); break;
                case "Misc":exp.addToMisc(amt); break;
            }

            msg.setText(amt+" added to "+category.getValue()+" successfully!"+"\n"+exp.checkMisc());
        });
        back.setOnAction(e -> stage.setScene(userDashboard(stage, acc, exp, bal)));

        VBox box = new VBox(10, amount, category, submit, back, msg);
        box.setPadding(new Insets(20));

        return new Scene(box, 350, 350);
    }

    public static void main(String[] args) {

        launch();
    }
}

