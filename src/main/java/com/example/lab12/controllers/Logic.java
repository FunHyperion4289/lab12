package com.example.lab12.controllers;
import com.example.lab12.entities.Result;
import org.springframework.stereotype.Component;
import static java.lang.Math.*;

@Component
public class Logic {

    public double tabulation(double x, double a, double b) {

        double eps = 0.0001;
        if (x<=0.7+eps) return 1;
        else if (x>1.4+eps) return Math.exp(a*x)*Math.cos(b*x);
        else return a*(x*x)*Math.log(x);

    }
    public int calculateSteps(double x1, double x2, double step) {

        return (int)round((x2 - x1) / step) + 1;
    }

    public double[] xArrayCreate(double x1, double x2, double step) {

        return new double[calculateSteps(x1, x2, step)];
    }


    public double[] yArrayFill(double[] xArray, double a, double b) {

        double[] yArray = new double[xArray.length];

        for (int i = 0; i < yArray.length; i++) {

            yArray[i] = tabulation(xArray[i], a, b);

        }
        return yArray;
    }

    public double[] xArrayFill(double x1, double x2, double step) {

        double[] xArray = xArrayCreate(x1, x2, step);

        for (int i = 0; i < xArray.length; i++) {

            xArray[i] = x1 + i * step;
        }

        return xArray;
    }

    public int getMinIndex(double[] yArray) {

        int minIndex = 0;
        double min = yArray[0];
        for (int i = 0; i < yArray.length; i++) {
            if (yArray[i] < min) {
                min = yArray[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public double getMinElement(double[] yArray) {
        int minNumber = getMinIndex(yArray);
        return yArray[minNumber];
    }

    public double getMinElementArgument(double[] yArray) {
        int mini = 0;
        double min = yArray[0];
        for (int i = 1; i < yArray.length; i++)
            if (yArray[i] < min) {
                min = yArray[i];
                mini = i;
            }
        return mini;
    }

    public int getMaxIndex(double[] yArray) {
        int maxIndex = 0;
        for (int i = 0; i < yArray.length; i++) {
            if (yArray[i] > yArray[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public double getMaxElement(double[] yArray) {
        int maxNumber = getMaxIndex(yArray);
        return yArray[maxNumber];
    }

    public double getMaxElementArgument(double[] yArray) {
        int maxi = 0;
        double max = yArray[0];
        for (int i = 1; i < yArray.length; i++)
            if (yArray[i] > max) {
                max = yArray[i];
                maxi = i;
            }
        return maxi;

    }

    public double getSum(double[] yArray) {
        double sum = 0;
        for (int i = 0; i < yArray.length; i++) {
            sum = sum + yArray[i];
        }
        return sum;
    }

    public double getAverage(double[] yArray) {
        double sum = getSum(yArray);
        for (int i = 0; i < yArray.length; i++)
            sum += yArray[i];
        return sum/yArray.length;

    }

    public Result[] convertToResult(double[] yValues, double[] xValues) {
        Result[] result = new Result[yValues.length];
        for (int i = 0; i < yValues.length; i++) {
            result[i] = new Result(yValues[i], xValues[i]);
        }
        return result;
    }
}