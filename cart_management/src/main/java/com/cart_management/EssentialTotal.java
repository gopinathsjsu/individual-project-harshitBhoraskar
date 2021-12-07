package com.cart_management;

public class EssentialTotal {
    private static EssentialTotal essentialTotal = null;

     static int essentialCount=5;
     static int luxurySum=3;
     static int miscSum=6;



    public static int getEssentialCount() {
        return essentialCount;
    }

    public static int luxurySum() {
        return luxurySum;
    }

    public static int getMiscSum() {
        return miscSum;
    }
    public static void setEssentialCount(int essentialCount) {
        EssentialTotal.essentialCount = essentialCount;
    }

    public static void setLuxurySum(int luxurySum) {
        EssentialTotal.luxurySum = luxurySum;
    }

    public static void setMiscSum(int miscSum) {
        EssentialTotal.miscSum = miscSum;
    }

}
