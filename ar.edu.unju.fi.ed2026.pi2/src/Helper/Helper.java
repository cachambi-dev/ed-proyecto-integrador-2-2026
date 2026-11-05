package Helper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase helper con metodos estaticos para la validacion de entradas
 * Helper
 */
public class Helper {
    /**
     * Scanner estatico para ser usado en toda la aplicacion
     */
    static Scanner scanner = new Scanner(System.in);

    public static Random random = new Random();

    /**
     * Metodo que valida el ingreso de caracteres
     * @param scanner de tipo Scanner
     * @param mensaje de tipo String
     * @return caracter de tipo Character
     */
     public static Character  nextCharacter(Scanner scanner,String mensaje){
        Character caracter;
        while (true) { 
         System.out.println(mensaje);
         if(scanner.hasNext(".")){
            caracter = scanner.next().charAt(0);
            return caracter;
         }
         System.out.println("[Error]: No ingresaste in caracter"); 
        }
    }

     /***
     * Metodo que valida el ingreso de valores enteros positivos
     */
    public static Integer nextInteger(Scanner scanner, String mensaje, int min) {
        int valor;
        while (true) {
            System.out.println(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor > min) {
                    scanner.nextLine();
                    return valor;
                }
                System.out.println("[Error] Valor invalido, debe ingresar valor positivo");
            } else {
                System.out.println("[Error] Valor invalido, debe ser un numero entero");
            }
        }
    }

    /**
     * Metodo que valida el ingreso de valores enteros positivos
     */
    public static Integer nextInteger(String mensaje, int min ){
        return nextInteger(Helper.scanner,mensaje, min);
    }

    public static Integer getInteger(String mensaje) {
        return nextInteger(Helper.scanner, mensaje);
    }

    /**
     * Metodo que valida el ingreso de caracteres
     */
    public static Character nexCharacter(String mensaje){
        return nextCharacter(Helper.scanner, mensaje);
    }

    public static Integer randomInteger(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
    }

    public static Integer nextInteger(Scanner scanner, String mensaje){
        return nextInteger(scanner, mensaje, 0);
    }

    /**
     * Metodo que valida el ingreso de valores decimales.
     */
    public static Double nextDouble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
            System.out.println("[Error] Valor invalido, debe ser un numero decimal");
            scanner.next();
        }
    }

    public static Double nextDouble(String mensaje) {
        return nextDouble(Helper.scanner, mensaje);
    }

     /**
     * Método que solicita al usuario un número entero y lo devuelve.
     * 
     * @param scanner      objeto de la clase Scanner
     * @param inputMessage mensaje que se muestra al usuario para solicitar el caracter
     * @param errorMessage mensaje que se muestra al usuario en caso de error
     * @return el número entero ingresado por el usuario
     */
    public static Integer nextInteger(Scanner scanner, String inputMessage, String errorMessage) {
        Integer integerValue = 0;
        while (true) {
            try {
                System.out.print(inputMessage);
                integerValue = Integer.parseInt(scanner.nextLine());
                return integerValue;
            } catch (Exception exception) {
                System.out.println(errorMessage);
            }
        }
    }

    public static Integer nextInteger(String inputMessage, String errorMessage) {
        return nextInteger(Helper.scanner, inputMessage, errorMessage);
    }

    /**
     * Método que solicita al usuario un número entero dentro de un rango y lo devuelve.
     * 
     * @param scanner      objeto de la clase Scanner
     * @param inputMessage mensaje que se muestra al usuario para solicitar el número
     * @param minValue     valor mínimo del rango
     * @param maxValue     valor máximo del rango
     * @return el número entero ingresado por el usuario
     */
     public static Integer nextInteger(Scanner scanner, String inputMessage,int minValue, int maxValue) {
        int valor;
        while (true) {
            System.out.println(inputMessage);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor >= minValue && valor <= maxValue) {
                    return valor;
                }
                System.out.println("[Error] Valor invalido, debe ingresar un numero entre " + minValue + " y " + maxValue);
            } else {
                System.out.println("[Error] Valor invalido, debe ser un numero entero");
            }
        }
    }

    /**
     * Método que solicita al usuario un número entero dentro de un rango y lo devuelve.
     * 
     * @param inputMessage mensaje que se muestra al usuario para solicitar el número
     * @param minValue     valor mínimo del rango
     * @param maxValue     valor máximo del rango
     * @return el número entero ingresado por el usuario
     */
    public static Integer nextInteger(String inputMessage,int minValue, int maxValue ) {
        return nextInteger(Helper.scanner, inputMessage, "Error: Ingrese un número entero válido.");
    }

    /**
     * Método que solicita al usuario un String y lo devuelve.
     * 
     * @param mensaje mensaje que se muestra al usuario para solicitar el String
     * @return el String ingresado por el usuario
     */
    public static String nextString(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    /**
     * Solicita una fecha con formato dd/MM/yyyy y la devuelve como Date.
     */
    public static Date nextDate(Scanner scanner, String mensaje) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        while (true) {
            System.out.println(mensaje + " (dd/MM/yyyy)");
            try {
                return dateFormat.parse(scanner.nextLine());
            } catch (ParseException exception) {
                System.out.println("[Error] Fecha invalida. Use el formato dd/MM/yyyy.");
            }
        }
    }

    public static Date nextDate(String mensaje) {
        return nextDate(Helper.scanner, mensaje);
    }


    // Método para validar enteros positivos
    public static int leerEnteroPositivo(Scanner scanner, String mensaje)
    {
        int numero = 0;
        boolean entradaValida = false;
        
        while ( !entradaValida )
        {
            System.out.print(mensaje);
            try
            {
                numero = scanner.nextInt(); 
                
                if (numero > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Error: El número debe ser mayor a cero.");
                }
                
            }
            catch (InputMismatchException e)
            {
                System.out.println("Error: Debe ingresar un valor numérico.");
                scanner.nextLine();
            }
        }
        return numero;
    }

}
