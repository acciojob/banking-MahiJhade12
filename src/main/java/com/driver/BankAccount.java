package com.driver;

public class BankAccount {

        private String name;
        private double balance;
        private double minBalance;

        public BankAccount(String name, double balance, double minBalance) {
                this.name= name;
                this.balance= balance;
                this.minBalance= minBalance;
        }

        public String getName() {
                return name;
        }

        public double getBalance() {
                return balance;
        }

        public double getMinBalance() {
                return minBalance;
        }

        public void setBalance(double balance) {
                this.balance = balance;
        }

        public String generateAccountNumber(int digits, int sum) throws Exception{
                //Each digit of an account number can lie between 0 and 9 (both inclusive)
                //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
                //If it is not possible, throw "Account Number can not be generated" exception
                if(9*digits < sum){
                        throw new Exception("Account Number can not be generated");
                }
                else  return createAccountNumber(digits, sum);

        }

        public void deposit(double amount) {
                //add amount to balance
                balance = balance + amount;
        }

        public void withdraw(double amount) throws Exception {
                // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
                if(balance-amount < minBalance){
                        throw new Exception("Insufficient Balance");
                }
                else balance-= amount;
        }
        public String createAccountNumber(int digits, int sum){

                StringBuilder retNumber = new StringBuilder("");

                while(digits > 0){
                        if(sum > 9){
                                retNumber.append('9');
                                sum -= 9;
                        }
                        else{
                                retNumber.append((char)(sum+'0'));
                                sum= 0;
                        }
                }
                return String.valueOf(retNumber);
        }

}