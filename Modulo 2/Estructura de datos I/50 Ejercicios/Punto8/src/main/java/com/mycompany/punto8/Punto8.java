package com.mycompany.punto8;
import java.util.Scanner;
public class Punto8 {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.print("Digite un numero: ");
        int num1=sc.nextInt();
        System.out.print("Digite un numero: ");
        int num2=sc.nextInt();
        if(num1>num2){
            System.out.print(num2+" Es menor");
        }else{
            System.out.print(num1+" Es menor");
        }
    }
}
