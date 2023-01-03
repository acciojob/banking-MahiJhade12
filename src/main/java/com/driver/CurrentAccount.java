package com.driver;

import jdk.jshell.spi.ExecutionControl;

import java.util.Comparator;
import java.util.PriorityQueue;

/*public class CurrentAccount extends BankAccount{
  String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<500)
            throw new UserException("Insufficient Balance" );

        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(!check(tradeLicenseId)){
            String NewId= makeDifferId(tradeLicenseId);
            if(NewId.length()==0)
                throw new UserException("Valid License can not be generated");
            else
                tradeLicenseId=NewId;
        }



    }
    public boolean check(String id){

        for(int i=0;i<id.length()-1;i++){
            if(id.charAt(i)==id.charAt(i+1))
                return false;
        }
        return true;
    }

    public String makeDifferId(String S){

        int[] counts = new int[26];
        for (char ch : S.toCharArray()) {
            counts[ch - 'a'] += 1;
        }

        Comparator<int[]> comp = new Comparator<int[]>() {
            public int compare(int[] a1, int[] a2) {
                return a2[0] - a1[0];
            }
        };
        int limit = (S.length()+ 1)/2;
        PriorityQueue<int[]> pq = new PriorityQueue(comp);
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (count > limit)
                return "";
            if(count > 0)
                pq.add(new int[] {count, 'a' + i});
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            //in this case we use most frequent char
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != (char)cur[1]) {
                sb.append((char)cur[1]);
                if (cur[0] > 1) {
                    cur[0] -= 1;
                    pq.add(cur);
                }
                //if most frequent one has been used previously - use next one from the min heap
            } else {
                int[] p = pq.poll();
                sb.append((char)p[1]);
                if (p[0] > 1) {
                    p[0] -= 1;
                    pq.add(p);
                }
                //put back most frequent one for next iterations
                pq.add(cur);
            }
        }
        return sb.toString();
    }
}*/
class ValidLicense extends Exception{
    ValidLicense(){
        super("Valid License can not be generated");
    }
}
public class CurrentAccount extends BankAccount {
    String tradeLicenseId;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
        if(balance<5000){
            throw new UserException("Insufficient Balance" );
        }
        this.tradeLicenseId=tradeLicenseId;
    }
    private boolean consecutive() {
        for(int i=0;i<this.tradeLicenseId.length()-1;i++){
            if(this.tradeLicenseId.charAt(i)==this.tradeLicenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }
    public char getMaxCountChar(int[] count){
        int max = 0;
        char ch = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > max) {
                max = count[i];
                ch = (char)((int)'a' + i);
            }
        }
        return ch;
    }
    public String rearrangeString(String S) {
        int N = S.length();
        if (N == 0)
            return "";
        int[] count = new int[26];
        for (int i = 0; i < 26; i++) {
            count[i] = 0;
        }
        for (char ch : S.toCharArray()) {
            count[(int)ch - (int)'a']++;
        }
        char ch_max = getMaxCountChar(count);
        int maxCount = count[(int)ch_max - (int)'a'];
        if (maxCount > (N + 1) / 2)
            return "";
        String res = "";
        for (int i = 0; i < N; i++) {
            res += ' ';
        }
        int ind = 0;
        while (maxCount > 0) {
            res = res.substring(0, ind) + ch_max
                    + res.substring(ind + 1);
            ind = ind + 2;
            maxCount--;
        }
        count[(int)ch_max - (int)'a'] = 0;
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                ind = (ind >= N) ? 1 : ind;
                res = res.substring(0, ind)
                        + (char)((int)'a' + i)
                        + res.substring(ind + 1);
                ind += 2;
                count[i]--;
            }
        }
        return res;
    }
    public void validateLicenseId() throws Exception {
        if(!consecutive()) {
            this.tradeLicenseId  = rearrangeString(this.tradeLicenseId.toLowerCase()).toUpperCase();
            if(this.tradeLicenseId==null || this.tradeLicenseId.length()==0){
                throw new ValidLicense();
            }
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}