package com.univdep;

public class InputData {
    public int getValueInt(String message) {
        do {
            try {
                System.out.print(message);
                int value = (new java.util.Scanner(System.in)).nextInt();
                if(value > 0)
                    return value;
                System.out.println("Error 0x0A002 :: El valor ingresado no puede ser negativo o igual a \n" +
                                   "              :: cero. Vuelva a ingresarlo nuevamente.");
            } catch (Exception ex) {
                System.out.println("Error 0x0A001 :: El valor ingresado no corresponde al solicitado\n" +
                                   "              :: por el sistema. Vuelva a ingresarlo nuevamente.");

            }
        } while(true);
    }

    public boolean getValueBln(String message) {
        do {
            try {
                System.out.print(message);
                return (new java.util.Scanner(System.in)).nextBoolean();
            } catch (Exception ex) {
                System.out.println("Error 0x0A003 :: El valor ingresado no corresponde al solicitado por el sistema.\n" +
                                   "              :: Sólo puede ingresar [true ¦ false]. Vuelva a ingresarlo nuevamente.");
            }
        } while(true);
    }

    public String getValueStr(String message) {
        do {
            try {
                System.out.print(message);
                return (new java.util.Scanner(System.in)).nextLine();
            } catch (Exception ex) {
                System.out.println("Error 0x0A003 :: El valor ingresado no corresponde al solicitado por el sistema. Sólo\n" +
                                   "              :: puede ingresar palabras o letras. Vuelva a ingresarlo nuevamente.");
            }
        } while(true);
    }
}
