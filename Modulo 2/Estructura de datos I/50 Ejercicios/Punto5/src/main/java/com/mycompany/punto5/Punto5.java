package com.mycompany.punto5;
import java.util.Scanner;
public class Punto5 {

    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        System.out.print("Ingrese una nota: ");
        float nota=sc.nextFloat();
        if(nota!=0){
            int totalNotas=0;
            float sumaNotas=0;
            while(nota!=0){
                totalNotas++;
                sumaNotas=sumaNotas+nota;
                System.out.print("Ingrese otra nota (si no hay mas notas ingrese 0): ");
                nota=sc.nextFloat();
            }
            float promedio=sumaNotas/totalNotas;
            System.out.println("Promedio: "+promedio);
        }else{
            System.out.println("No ingreso notas!");
            }
    }
}
