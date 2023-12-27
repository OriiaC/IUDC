package com.mycompany.punto10;

public class Punto10 {

    public static void main(String[] args) {
        int suma=0;
        int total=0;
        for (int i=1;i<=15;i++){
            double numDouble=Math.random()*100+1;
            int numInt = (int) numDouble;
            System.out.println("el numero aleatorio #"+i+" es: "+numInt);
            total=numInt+suma;
            System.out.println(numInt+"+"+suma+"="+total);
            suma=suma+numInt;
        }
        System.out.println("El total de la suma de los 15 numeros aleatorios es: "+total);
    }
}
