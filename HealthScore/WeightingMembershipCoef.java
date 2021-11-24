package HealthScore;

import java.util.Arrays;

/**
 * 用以进行针对多个子系统所对应的隶属度向上一级系统所对应的隶属度的转化，转化过程需要赋予各子系统所对应的权重系数<br>
 * 比如：<br>
 * 气隙最小值（平均）  (0.6)  (1.000 	0.000	0.000	0.000)<br></br>
 * 气隙最小值偏差     (0.4)  (0.000 	0.396	0.604	0.000)<br></br>
 * 转子                    (0.600	0.158	0.242	0.000)<br></br>
 * 由于【气隙最小值】与【气隙最小值偏差】共同组成了对转子的隶属度的计算，故，0.6=1.0*0.6+0.0*0.4……
 */
public class WeightingMembershipCoef {

    /**
     * 将权重赋予每个评级对应的隶属度
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     *     1.需要通过进一步指定本劣化度的【参与系数】以计算该测点使用多种劣化度函数的情况下的【指标劣化度】
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param  _weight 权重
     * @param  coefs 系数数组，一般是针对不同的评级如“正常	注意	异常	严重”一对一地出现
     * @return double[]
     */
    public static double[] mapReduceMultiply(double _weight, double... coefs) {
        double[] res = new double[coefs.length];
        for (int i = 0; i < coefs.length; i++) {
            res[i] = coefs[i] * _weight;
        }
        return res;
    }

    /**
     * 根据“评级”叠加多个系统的隶属度。
     * 矩阵加法
     * @param  arrays 各“评级”的隶属度
     * @return double[]
     */
    public static double[] mapReducePlus(double[]... arrays) {
        int arrayQuant = arrays.length;
        int arrayLength = arrays[0].length;

        double[] res = new double[arrayLength];
        Arrays.fill(res, 0.0);

        for (int i=0;i<arrayQuant;i++){
            double[] array = arrays[i];
            for (int j=0;j<arrayLength;j++){
                res[j] = res[j] + array[j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        double[] cache1 = mapReduceMultiply(0.6, 1, 0, 0, 0);
        double[] cache2 = mapReduceMultiply(0.4, 0, 0.396, 0.604, 0);
        double[] cache3 = mapReducePlus(cache1, cache2);
        System.out.println(Arrays.toString(cache1) + ", " + Arrays.toString(cache2) + ", " + Arrays.toString(cache3));
    }
}
