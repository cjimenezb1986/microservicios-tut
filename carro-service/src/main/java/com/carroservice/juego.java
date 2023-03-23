package com.carroservice;

import java.util.Random;
import java.util.Scanner;

public class juego {
    public static void main(String[] args) {
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(100)+1;
        Scanner scanner = new Scanner(System.in);
        int intentos=0;
        int maxIntentos=10;

        System.out.println("Bienvenido al juego de adivinanza! Tienes que adivinar un número entre 1 y 100 en " + maxIntentos + " intentos o menos.");

        while (intentos< maxIntentos){
            System.out.println("Intento # "+(intentos+1)+": ");
            int intento= scanner.nextInt();
            intentos++;

            if(intento==numeroAleatorio){
                System.out.println("¡Felicidades! adivinaste en numero en "+intentos+" intentos ");
                System.out.println("┏┓┏┓┊┊┊┊┊┊┊┊┊┊┊┊\n" +
                                    "┃┗┛┣━━┳━━┳━━┳┓┏┓\n" +
                                    "┃┛┗┃╭╮┃┛┛┃┗┗┃╰┛┃\n" +
                                    "┃╰╯┃┗┛┃╰╯┃╰╯┣━╮┃\n" +
                                    "┃┏┓┃┏┓┃┏━┫┏┳┻━╯┃\n" +
                                    "┗┛┗┻┛┗┻┛┊┗┛┗━━━╯");

                break;
            } else if (intento<numeroAleatorio) {
                System.out.println("El número es mayor que " + intento);
            }else {
                System.out.println("El número es menor que " + intento);
            }
        }
        if(intentos==maxIntentos){
            System.out.println("Lo siento, has superado los " +maxIntentos+ " intentos permitidos. El numero era " +numeroAleatorio+"." );
            System.out.println("▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄\n" +
                                "████▌▄▌▄▐▐▌█████\n" +
                                "████▌▄▌▄▐▐▌▀████\n" +
                                "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
        }

    }
}
