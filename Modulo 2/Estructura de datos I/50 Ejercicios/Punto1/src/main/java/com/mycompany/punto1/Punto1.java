package com.mycompany.punto1;
//Se importa la libreria Scanner
import java.util.Scanner;
public class Punto1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Se define una variable de tipo entero y se asigna el valor ingresado por teclado
        System.out.print("Ingrese un numero: ");
        int num= sc.nextInt();
        //Se ponen las condiciones para imprimir el mensaje correcto por pantalla.
        if (num<0){
            System.out.println("el numero "+num+" es menor que 0");
        } else if (num>0){
            System.out.println("el numero "+num+" es mayor que 0");
        }else {
            System.out.println("el numero "+num+" es igual que 0");
        }
    }
}
