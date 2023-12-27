package com.mycompany.punto2;
import java.util.Scanner;
public class Punto2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite un numero : ");
        int num = sc.nextInt();
        int res = num%2;
        if (res==1){
            System.out.print("El numero es impar");
        }else {
            System.out.print("El numero es par");
        }
    }
}
