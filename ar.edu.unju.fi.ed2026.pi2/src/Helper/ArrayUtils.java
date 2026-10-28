package Helper;
import java.util.ArrayList;

/**
 * Clase que contiene metodos utilitarios para trabajar con arreglos de caracteres
 */
public class ArrayUtils {
    
    /**
     * Metodo que carga un arreglo de caracteres desde la consola
     * @param arrayListCharacter
     * @param message
     */
    public static void loadArrayListCharacterFromConsole(ArrayList<Character> arrayListCharacter, String message) {
        Character character, continueCharacter;
        while (true) {
            character = Helper.nexCharacter(message);  
            arrayListCharacter.add(character);

            continueCharacter = Helper.nexCharacter("Desea continuar ingresando caracteres? (s/n)");
            if (continueCharacter == 'n') {
                break;
            }
        }

    }

    /**
     * Metodo que muestra los elementos de un arreglo de caracteres
     * @param arrayListCharacter 
     * @param message mensaje a mostrar antes de los elementos
     */
    public static void showArrayListCharacter(ArrayList<Character> arrayListCharacter, String message) {
        System.out.println(message);
        for (Character character : arrayListCharacter) {
            System.out.print("["+ character + "] ");
        }
    }

    /**
     * Metodo que muestra los elementos de un arreglo de caracteres y su cantidad de repeticiones
     * @param arrayListCharacter arreglo de caracteres
     * @param arrayListInteger arreglo de enteros que representa la cantidad de repeticiones
     * @param message mensaje a mostrar antes del caracter
     * @param message2 mensaje a mostrar antes de la cantidad de repeticiones
     */
     public static void showArrayListCharacterAndInteger(ArrayList<Character> arrayListCharacter, ArrayList<Integer> arrayListInteger, String message, String message2) {
        for (int i = 0; i < arrayListCharacter.size(); i++) {
            System.out.print(message+ arrayListCharacter.get(i) + message2 + arrayListInteger.get(i) );
        }
    }

    /**
     * Metodo que muestra los elementos de un arreglo de enteros
     * @param arrayListInteger arreglo de enteros
     * @param message mensaje a mostrar antes de los elementos
     */
    public static void showArrayListInteger(ArrayList<Integer> arrayListInteger, String message) {
        System.out.println(message);
        for (Integer integer : arrayListInteger) {
            System.out.print("["+ integer + "] ");
        }
    }

    /**
     * Metodo que muestra los elementos de un arreglo de enteros
     * @param array arreglo de enteros
     * @param message mensaje a mostrar antes de los elementos
     */
    public static void showArrayOneDimensionInt(int[] array, String message) {
        System.out.println(message);
        for (int i = 0; i < array.length; i++) {
            System.out.print("["+ array[i] + "] ");
        }
    }

    /**
     * Metodo que muestra los elementos de un arreglo de caracteres
     * @param array arreglo de caracteres
     * @param message mensaje a mostrar antes de los elementos
     */
    public static <Element> void showArrayOneDimensionGeneric(Element[] array, String message) {
        System.out.println(message);
        for (int i = 0; i < array.length; i++) {
            System.out.print("["+ array[i] + "] ");
        }
    }



}
