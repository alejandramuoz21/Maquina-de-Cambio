package com.univdep;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;

public class StockFile {
    private String path;
    private String error;
    private File file;
    private Scanner scanner;
    private boolean flag;

    public StockFile() {
        this.file = null;
        this.path = "/Users/pedro/Desarrollo/IdeaProjects/jEjemplo.Proyecto/out/production/jEjemplo.Proyecto/com/univdep/Values.txt";
        this.error = "";
    }

    public List<String> ReadValues() {
        List<String> data = new ArrayList<>();
        try {
            while (this.scanner.hasNextLine()) {
                String value = this.scanner.nextLine();
                data.add(value);
            }
            this.flag = true;
        } catch(Exception ex) {
            this.error = "Error 0x0F002 :: La lectura de los datos almacenados en el archivo de configuración \n" +
                         "              :: presenta un error. Se cierra el programa, vuelva a ejecutarlo.";
            this.flag = false;
        }
        return data;
    }

    public void OpenFile() {
        try {
            this.file = new File(this.path);
            this.scanner = new Scanner(this.file);
            this.flag = true;
        } catch(Exception ex) {
            this.error = "Error 0x0F001 :: El archivo no se puede leer. Revise que exista o no este dañado, \n" +
                         "              :: y vuelva a intentar a ejecutar el sistema.";
            this.flag = false;
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void CloseFile() {
        try {
            if(this.flag)
                this.scanner.close();
        } catch(Exception ex) {
            this.error = "Error 0x0F003 :: El archivo no se puede cerrar. Revise que otra aplicación no lo este usando \n" +
                         "              :: o no este dañado, e intentante ejecutar el cierre del mismo.";
        }
    }

    public String Message() {
        return this.error;
    }

    public boolean isProcessValid() {
        return this.flag;
    }
}
