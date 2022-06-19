package Operations;

import Structure.Cells.*;

import java.util.*;

/**
 * RankOperation contiene todas las operaciones que nuestro programa podrá realizar con uno o dos rangos
 * @author Pol Barco Martínez
 */

public class RankOperation {

    /**
     * Calcula la media de un rango de celdas
     * @param RankCell rango de celdas
     * @return cálculo de la media del rango de celdas "RankCell"
     */
    public double Average (LinkedList<Cell> RankCell) {
        double average;
        int n = RankCell.size();
        double v[] = new double[n];
        int aux = 0;
        for (Cell i: RankCell) {
            v[aux] = i.getNum();
            ++aux;
        }
        average = Mean(v, n);
        return average;
    }

    /**
     * Calcula la mediana de un rango de celdas
     * @param RankCell rango de celdas
     * @return cálculo de la mediana del rango de celdas "RankCell"
     */
    public double Median (LinkedList<Cell> RankCell) {
        double median;
        double v[] = new double[RankCell.size()];
        int aux = 0;
        for (Cell i: RankCell) {
            v[aux] = i.getNum();
            ++aux;
        }
        if (v.length == 1) median = v[0];
        else {
            Arrays.sort(v);
            if (v.length%2 == 0) {
                median = (v[(v.length/2)-1] + v[(v.length/2)]) / 2;
            }
            else {
                median = v[v.length/2];
            }
        }
        return median;
    }

    /**
     * Calcula la variancia de un rango de celdas
     * @param RankCell rango de celdas
     * @return cálculo de la variancia del rango de celdas "RankCell"
     */
    public double Variance (LinkedList<Cell> RankCell) {
        double variance;
        double average;
        double summation = 0;
        average = Average(RankCell);
        for (Cell i: RankCell) {
            summation += Math.pow((i.getNum() - average), 2);
        }
        variance = summation/RankCell.size();
        return variance;
    }

    /**
     * Calcula la covariancia de dos rangos de celdas
     * @param RankCellX primer rango de celdas
     * @param RankCellY segundo rango de celdas
     * @return cálculo de la covariancia de los rangos de celdas "RankCellX" y "RankCellY"
     */
    public double Covariance (LinkedList<Cell> RankCellX, LinkedList<Cell> RankCellY) {
        double covariance;
        int n = RankCellX.size();
        double x[] = new double[n];
        double y[] = new double[n];
        int aux = 0;

        for (Cell i: RankCellX) {
            x[aux] = i.getNum();
            ++aux;
        }
        aux = 0;
        for (Cell i: RankCellY) {
            y[aux] = i.getNum();
            ++aux;
        }

        double sum = 0;
        for (int j = 0; j < n; j++) {
            sum += x[j] * y[j];
        }
        double averageX = Average(RankCellX);
        double averageY = Average(RankCellY);

        covariance = (sum / n) - averageX * averageY;
        return covariance;
    }

    /**
     * Calcula la desviación estándard de un rango de celdas
     * @param RankCell rango de celdas
     * @return cálculo de la desviación estándard del rango de celdas "RankCell"
     */
    public double StandardDeviation (LinkedList<Cell> RankCell) {
        double variance = Variance(RankCell);
        double standardDeviation = Math.sqrt(variance);
        return standardDeviation;
    }

    /**
     * Calcula la correlación de Pearson de dos rangos de celdas
     * @param RankCellX primer rango de celdas
     * @param RankCellY segundo rango de celdas
     * @return cálculo de la correlación de Pearson de los rangos de celdas "RankCellX" y "RankCellY"
     */
    public double Pearson (LinkedList<Cell> RankCellX, LinkedList<Cell> RankCellY) {
        double pearson = 0;
        double covariance = Covariance(RankCellX, RankCellY);
        double standardDeviationX = StandardDeviation(RankCellX);
        double standardDeviationY = StandardDeviation(RankCellY);
        pearson = covariance/(standardDeviationX * standardDeviationY);
        return pearson;
    }

    /**
     * Calcula la media de un vector
     * @param v vector que contiene los valores
     * @param n tamaño del vector
     * @return cálculo de la media de los valores de "v"
     */
    private double Mean (double v[], int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            sum += v[i];
        }
        return sum / n;
    }
}
