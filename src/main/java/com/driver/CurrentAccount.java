package com.driver;

import jdk.jshell.spi.ExecutionControl;

import java.util.Comparator;
import java.util.PriorityQueue;


public class CurrentAccount extends BankAccount {
    String tradeLicenseId; // consists of Uppercase English characters only
    public class CurrentAccount extends BankAccount{
        private String tradeLicenseId; //consists of Uppercase English characters only

        public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
            // minimum balance is 5000 by default. If balance is less than 5000, throw
            // "Insufficient Balance" exception
            super(name, balance, 5000);
            validateLicenseIdHelper(tradeLicenseId);
            // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

            super(name,balance,5000);

            if(balance < 5000)
                throw new Exception("Insufficient Balance");

        }

        public String getTradeLicenseId() {
            return tradeLicenseId;
        }

        public void validateLicenseId() throws Exception {
            // A trade license Id is said to be valid if no two consecutive characters are
            // same
            // If the license Id is valid, do nothing
            // If the characters of the license Id can be rearranged to create any valid
            // license Id
            // If it is not possible, throw "Valid License can not be generated" Exception
            try {
                if (tradeLicenseId == null) {
                    throw new Exception("Valid License can not be generated");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }

        String finalAnswer = null;

        public void validateLicenseIdHelper(String tradeLicenseId) throws Exception {

            try {
                for (int i = 0; i < tradeLicenseId.length(); i++) {
                    if (tradeLicenseId.charAt(i) >= 'A' && tradeLicenseId.charAt(i) <= 'Z') {
                        continue;
                    } else {
                        throw new Exception("Valid License can not be generated");
                    }
                }
                for (int i = 0; i < tradeLicenseId.length() - 1; i++) {
                    if (tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i + 1)) {
                        String answer = "";
                        rearrange(tradeLicenseId, answer);
                        if (finalAnswer != null) {
                            this.tradeLicenseId = finalAnswer;
                            return;
                        } else {
                            throw new Exception("Valid License can not be generated");
                        }
                    } else {
                        this.tradeLicenseId = tradeLicenseId;
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        public void rearrange(String tradeLicenseId, String answer) {
            if (tradeLicenseId.length() == 0) {
                for (int i = 0; i < answer.length() - 1; i++) {
                    if (answer.charAt(i) != answer.charAt(i + 1)) {
                        continue;
                    } else {
                        return;
                    }
                    boolean valid = true;
                    int n = tradeLicenseId.length();
                    for(int i=1; i<n; i++){
                        char c= tradeLicenseId.charAt(i);
                        if(c==tradeLicenseId.charAt(i-1)){
                            valid= false;
                            break;
                        }
                        finalAnswer = answer;
                    }
                    for (int i = 0; i < tradeLicenseId.length(); i++) {
                        if (finalAnswer != null)
                            return;
                        char ch = tradeLicenseId.charAt(i);
                        String left = tradeLicenseId.substring(0, i);
                        String right = tradeLicenseId.substring(i + 1);
                        String rest = left + right;
                        rearrange(rest, answer + ch);
                    }
                    if(!valid) throw new Exception("Valid License can not be generated");
                }

            }