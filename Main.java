package com.univdep;

import javax.swing.*;

public class Main {

	public static void ClrScr() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

    public static void main(String[] args) {
		Money money = new Money();
		InputData input = new InputData();
		boolean flag = true;

		ClrScr();
		System.out.println("              :: GearBox. v. 0.4.10");
		do {
			if (money.isActive()) {
				int value = input.getValueInt("              :: Denominación a cambiar > ");
				if(value == 9)
					break;
				else if (money.containedValue(value)) {
                    money.GearBox(value);
                    if(money.isChange())
                        System.out.println(money.GearResult());
                    else
                        System.out.println(money.Message());
                }
				else
                    System.out.println(money.Message());
			} else {
                System.out.println(money.Message());
                flag = false;
            }
		} while(flag);
		System.out.println("              :: El sistema se cierra. \n" +
				           "              :: Actualizando el archivo de operaciones.");
    }
}
