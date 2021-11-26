package HealthScore;

/**
 * Methods                                                                                                          <br>
 * ----------                                                                                                       <br>
 * * {@link HealthScore.DegradeIndex#monotonic(double, double, double)}                                             <br>
 * * {@link HealthScore.DegradeIndex#centerOptimized(double, double, double, double, double)}                       <br>
 * * {@link HealthScore.DegradeIndex#degradeIndex(double, double)}                                                  <br>
 * * {@link HealthScore.DegradeIndex#findMaxValueInArray(double[])}                                                 <br>
 * * {@link HealthScore.DegradeIndex#findMinValueInArray(double[])}                                                 <br>
 *
 * 主要功能                                                                                                          <br>
 * ----------                                                                                                       <br>
 * 1.劣化度函数库                                                                                                     <br>
 * 2.使用一种或多种劣化度函数及其各自的参与系数计算指标劣化度                                                                  <br>
 * 3.单劣化度函数的劣化度与参与系数的计算                                                                                  <br>
 */
public class DegradeIndex {
    
    public DegradeIndex() {
    }

    /**
     * 返回“使用单调函数表达劣化度的变量”的劣化度
     *
     * @param _measured 实测值
     * @param _optimalValue 最优值
     * @param _upperLimit 上限
     * @return double 劣化度（非"指标劣化度"）
     */
    public static double monotonic(double _measured, double _optimalValue, double _upperLimit) {
        double cache = findMaxValueInArray(new double[]{(_measured - _optimalValue) / (_upperLimit - _optimalValue), 0});
        return findMinValueInArray(new double[]{cache, 1});
    }

    /**
     * 返回“使用中间最优函数表达劣化度的变量”的劣化度
     *
     * @param _measured 实测值
     * @param _lowerLimit 下限
     * @param _optimalLowerLimit 最优下限
     * @param _optimalUpperLimit 最优上限
     * @param _upperLimit 上限
     * @return double 劣化度（非"指标劣化度"）
     */
    public static double centerOptimized(double _measured, double _lowerLimit, double _optimalLowerLimit, double _optimalUpperLimit, double _upperLimit) {
        double[] _cache_array = {(_measured - _lowerLimit) / (_optimalLowerLimit - _lowerLimit), 1, (_upperLimit - _measured) / (_upperLimit - _optimalUpperLimit)};
        double _res;
        _res = findMinValueInArray(_cache_array);
        _cache_array = new double[]{_res, 0};
        _res = findMaxValueInArray(_cache_array);
        return 1 - _res;
    }

    /**
     * 寻找以为Array数组中的最小值
     *
     * @param  _array 以为数组
     * @return  double 最小值
     */
    private static double findMinValueInArray(double[] _array) {
        double _cache = _array[0];
        for (int i = 1; i < _array.length; i++) {
            if (_array[i] < _cache) {
                _cache = _array[i];
            }
        }
        return _cache;
    }

    /**
     * 寻找以为Array数组中的最大值
     *
     * @param  _array 以为数组
     * @return  double 最大值
     */
    private static double findMaxValueInArray(double[] _array) {
        double _cache = _array[0];
        for (int i = 1; i < _array.length; i++) {
            if (_array[i] > _cache) {
                _cache = _array[i];
            }
        }
        return _cache;
    }

    /**
     * 根据单个劣化度及其参与系数计算其指标劣化度
     *
     * @param  _degradeValue 劣化度函数的输出
     * @param  _participateCoef 参与系数
     * @return  double 指标劣化度
     */
    public static double degradeIndex(double _degradeValue, double _participateCoef){
        return _degradeValue * _participateCoef;
    }

    public static void main(String[] args) {
        double measuredValue = 17.1;
        double degrade_monotonic = monotonic(measuredValue, 16, 20);
        double a = degradeIndex(degrade_monotonic, 0.5);
        System.out.println(a);
    }
}