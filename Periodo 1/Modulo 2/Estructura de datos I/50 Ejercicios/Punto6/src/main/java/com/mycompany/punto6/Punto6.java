package com.mycompany.punto6;

public class Punto6 {
    public static void main(String[] args) {
        int cantidad23=0;
        int cantidad2=0;
        int cantidad3=0;
        for(int i=1;i<=100;i++){
            if (i%2==0 && i%3==0){
                cantidad23++;
                System.out.println(i+" es multiplo de 2 y de 3");
            }else if (i%2==0){
                cantidad2++;
                System.out.println(i+" es multiplo de 2");
            }else if (i%3==0){
                cantidad3++;
                System.out.println(i+" es multiplo de 3: ");
            }
        }
        System.out.println("El total de multiplos de 2 es: "+cantidad2);
        System.out.println("El total de multiplos de 3 es: "+cantidad3);
        System.out.println("El total de multiplos de 2 y de 3 es: "+cantidad23);
    }
}

    

