package de.jprojekt.utils;

public class Checks {

    public static boolean isNull(Object j){
        if(j == null){
            return true;
        }
        return false;
    }

    public static boolean isName(String j){
        if(isNull(j)){
            return false;
        }
        return j.matches("[a-zA-Z]+");
    }

    public static boolean isPLZ(int j){
        if(j>1000 && j<99999){
            return true;
        }
        return false;
    }
    
    public static boolean isPLZ(String plz) {
    	 return plz.matches("[0-9]+");
    }

    public static boolean isPassword(String j){

        if(isNull(j)){
            return false;
        }

        int passwordLength=8, upChars=0, lowChars=0;
        int special=0, digits=0;
        char ch;

        int total = j.length();
        if(total<passwordLength) {
            return false;
        }else {
            for(int i=0; i<total; i++) {
                ch = j.charAt(i);
                if(Character.isUpperCase(ch)) {
                    upChars = 1;
                }else if(Character.isLowerCase(ch)) {
                    lowChars = 1;
                }else if(Character.isDigit(ch)) {
                    digits = 1;
                }else{
                    special = 1;
                }
            }
        }
        if(upChars==1 && lowChars==1 && digits==1 && special==1) {
            return true;
        }else {
            return false;
        }
    }

}