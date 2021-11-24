package HealthScore;

import java.util.Arrays;

public class CommonMethods {

    /**
     * 两个一维矩阵间的矩阵乘法，同位相乘
     *
     * @param  _array1 一维矩阵
     * @param  _array2 一维矩阵
     * @return  double[]
     */
    public static double[] matMultiply(double[] _array1,double[] _array2){
        int length = _array1.length;
        double[] res = new double[length];
        Arrays.fill(res, 0);
        for (int i=0;i<length;i++){
            res[i] = _array1[i] * _array2[i];
        }
        return res;
    }

    /**
     * 一维矩阵元素和
     *
     * @param  _array 一维矩阵
     * @return  double
     */
    public static double cumsum(double[] _array){
        int length = _array.length;
        double res = 0;
        for (int i=0;i<length;i++){
            res = res + _array[i];
        }
        return res;
    }
}
