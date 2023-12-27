package com.mycompany.punto4;

public class Punto4 {

    public static void main(String[] args) {
        int res;
        for (int i=1; i<=100; i++){
            res=i%2;
            if(res==0){
                System.out.println(i);
            }
        }
    }
}
