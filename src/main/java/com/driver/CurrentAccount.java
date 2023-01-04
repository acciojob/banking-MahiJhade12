package com.driver;

public class CurrentAccount extends BankAccount{
    private String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);

        if(balance < 5000)
            throw new Exception("Insufficient Balance");

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {

        boolean valid = true;
        int n = tradeLicenseId.length();
        for(int i=1; i<n; i++){
            char c= tradeLicenseId.charAt(i);
            if(c==tradeLicenseId.charAt(i-1)){
                valid= false;
                break;
            }
        }
        if(!valid) throw new Exception("Valid License can not be generated");
    }

}