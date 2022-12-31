package com.driver;

public class Main {
    public static void main(String[] args) {
       BankAccount b=new BankAccount("mahima",400,200);
        try {
            System.out.println(b.generateAccountNumber(9,9));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        CurrentAccount account1 = null;
        try {
            account1 = new CurrentAccount("Ram",10000.00,"34567890");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            account1.validateLicenseId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(account1.getTradeLicenseId());

        SavingsAccount account2 = new SavingsAccount("Sohan",5000.00,2000,15.6);
        //account2.withdraw(3000);
        //account2.withdraw(6000);
        try {
            account2.withdraw(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(account2.getSimpleInterest(2));
        System.out.println(account2.getCompoundInterest(3,1));

        StudentAccount account3 = new StudentAccount("Raj Kumar",1000,"AccioJob");

        BankAccount account4 = new BankAccount("Kyle",1000,500);
        try {
            System.out.println(account4.generateAccountNumber(6,16));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
 //////
    }
}
