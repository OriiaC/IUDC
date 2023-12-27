package com.mycompany.punto7;
import java.util.Scanner;
public class Punto7 {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.print("Digite un numero: ");
        int num1=sc.nextInt();
        System.out.print("Digite un numero: ");
        int num2=sc.nextInt();
        if(num1>num2){
            System.out.print(num1+" Es mayor");
        }else{
            System.out.print(num2+" Es mayor");
        }
    }
}
