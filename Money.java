package com.univdep;
import java.util.ArrayList;
import java.util.List;

public class Money {
    /*
    Declaración de los atributos del objeto.
     */
    private List<Integer> denomination;
    private List<Integer> quantity;
    private boolean flag;
    private boolean exist;
    private String error;
    private StringBuilder result;

    /*
    Declaración del constructor.
     */
    public Money() {
        this.error = "";
        this.denomination = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.RecoveryData();
        this.result = new StringBuilder();
    }

    /*
    Declaración de los métodos privados del objeto.
     */

    private void StockData(StockFile data) {
        this.flag = true;
        for (String values:data.ReadValues()) {
            if(values.isEmpty()) {
                this.error = "Error 0x0E001 :: Existe una linea vacia en el archivo, corrija \n" +
                             "              :: el error en el archivo de configuración.";
                this.flag = false;
                break;
            }
            int position = values.indexOf("¦");
            if(position <= -1) {
                this.error = "Error 0x0E002 :: No es correcto el valor que esta escrito en el archivo, \n" +
                             "              :: el error esta presente en esta linea: " + values;
                this.flag = false;
                break;
            }
            else {
                String valueone = values.substring(0, position);
                if(valueone.isEmpty()) {
                    this.error = "Error 0x0E003 :: No se tiene el valor de la denominación en el archivo, \n" +
                                 "              :: el error esta presente en esta linea: " + values;
                    this.flag = false;
                    break;
                }
                String valuetwo = values.substring(position + 1);
                if(valuetwo.isEmpty()) {
                    this.error = "Error 0x0E004 :: No se tiene el valor de la cantidad en el archivo, \n" +
                                 "              :: el error esta presente en esta linea: " + values;
                    this.flag = false;
                    break;
                }
                try {
                    int one = Integer.parseInt(valueone);
                    if(one <= 0) {
                        this.error = "Error 0x0E006 :: No es correcto el valor que esta escrito en el archivo, \n" +
                                     "              :: el error esta presente en esta linea: " + values;
                        this.flag = false;
                        break;
                    }
                    int two = Integer.parseInt(valuetwo);
                    if(two < 0) {
                        this.error = "Error 0x0E006 :: No es correcto el valor que esta escrito en el archivo, \n" +
                                "              :: el error esta presente en esta linea: " + values;
                        this.flag = false;
                        break;
                    }
                    this.denomination.add(one);
                    this.quantity.add(two);
                } catch(Exception ex) {
                    this.error = "Error 0x0E005 :: No es correcto el valor que esta escrito en el archivo, \n" +
                                 "              :: el error esta presente en esta linea: " + values;
                    this.flag = false;
                    break;
                }
            }
        }
    }

    private void RecoveryData() {
        StockFile data = new StockFile();
        data.OpenFile();
        if(data.isProcessValid())
            this.StockData(data);
        else
            this.error = data.Message();
        data.CloseFile();
    }

    /*
    Declaración de los métodos públicos del objeto.
     */

    public boolean containedValue(int denomination) {
        boolean isExist = this.denomination.contains(denomination);
        if(!isExist)
            this.error = "Error 0x0E007 :: El valor ingresado no existe en la tabla de valores del \n" +
                         "              :: sistema. Vuelva a ingresarlo nuevamente.";
        return isExist;
    }

    public int getIndex(int denomination) {
        return this.denomination.indexOf(denomination);
    }

    public int getQuantity(int denomination) {
        return this.quantity.get(this.getIndex(denomination));
    }

    public String Message() {
        return this.error;
    }

    public boolean isActive() {
        return this.flag;
    }

    public boolean isChange() {
        return this.exist;
    }

    public void GearBox(int cash) {
        int index = this.getIndex(cash) + 1;
        if(index < this.denomination.size()) {
            this.result.append("              :: Posición: " + this.getIndex(cash) + "\n");
            this.result.append("              :: Cantidad: " + this.getQuantity(cash));
            this.exist = true;
        }
        else {
            this.error = "Error 0x08001 :: El valor ingresado no tiene menores denominaciones \n" +
                         "              :: para generar cambio. Vuelva a ingresar otro valor nuevamente.";
            this.exist = false;
        }
    }

    public String GearResult() {
        String values = this.result.toString();
        this.result.delete(0, this.result.length());
        return values;
    }
 }
