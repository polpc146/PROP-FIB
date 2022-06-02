package Operations;
/**
 * NumberOperation contiene todas las operaciones que nuestro programa podrá realizar con números
 * @author Pol Barco Martínez
 */

public class NumberOperation {

    /**
     * Calcula la suma de dos valores
     * @param a primer valor de la suma
     * @param b segundo valor de la suma
     * @return la suma de los valor "a" y "b"
     */
    public double Sum (double a, double b) {
        return a + b;
    }

    /**
     * Calcula la resta de dos valores
     * @param a primer valor de la resta
     * @param b segundo valor de la resta
     * @return la resta de los valor "a" y "b"
     */
    public double Rest (double a, double b) {
        return a - b;
    }

    /**
     * Calcula la multiplicación de dos valores
     * @param a primer valor de la multiplicación
     * @param b segundo valor de la multiplicación
     * @return la multiplicación de los valor "a" y "b"
     */
    public double Multiplication (double a, double b) {
        return a * b;
    }

    /**
     * Calcula la división de dos valores
     * @param a primer valor de la división
     * @param b segundo valor de la división
     * @return la división de los valor "a" y "b"
     */
    public double Division (double a, double b) {
        return a / b;
    }

    /**
     * Conversió de entero a binario
     * @param a valor para la conversión
     * @return la conversión del valor "a" en binario
     */
    public String Binary (double a) {
        int b = (int)a;
        return Integer.toBinaryString(b);
    }

    /**
     * Conversió de entero a hexadecimal
     * @param a valor para la conversión
     * @return la conversión del valor "a" en hexadecimal
     */
    public String Hexadecimal (double a) {
        int h = (int)a;
        return Integer.toHexString(h);
    }

    /**
     * Trunca un valor
     * @param a valor "a" truncar
     * @return el valor "a" truncado
     */
    public double Truncate (double a) {
        int result = (int)a;
        return (double)result;
    }

    /**
     * Calcula el valor absoluto de un valor
     * @param a número del qual queremos saber su valor absoluto
     * @return el valor absoluto del valor del parámetro
     */
    public double Absolute (double a) {
        return (a < 0) ? -a : a;
    }

    /**
     * Incrementa en uno un valor
     * @param a número el qual incrementaremos
     * @return el valor "a" más uno
     */
    public double Increase (double a) {
        return a+1;
    }
}