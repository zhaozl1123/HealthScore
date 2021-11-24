package HealthScore;


/**
 * <p style="color: black">************************</p>
 * <p style="color: black">
 * 主要功能：<br>
 * 1.使用单调函数（越小越优型劣化度函数{@link HealthScore.DegradeIndex#monotonic}）计算劣化度；<br>
 * 2.使用非单调函数（中间最优型劣化度函数{@link HealthScore.DegradeIndex#centerOptimized}）计算劣化度；<br>
 * 3.使用一种或多种劣化度函数及其各自的参与系数计算指标劣化度。<br>
 * </p>
 * <p style="color: black">************************</p>
 */
public class DegradeIndex {
    
    public DegradeIndex() {
    }

    /**
     * 返回“使用单调函数表达劣化度的变量”的劣化度
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     *     1.在degradeIndex方法中，可以指定本劣化度的【参与系数】；
     *     2.通过计算该测点使用其它劣化度函数，如centerOptimized，并指定其参与系数，可以使用degradeIndex方法计算使用多种劣化度函数
     *     的情况下，该测点的【指标劣化度】
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param _measured 实测值
     * @param _optimalValue 最优值
     * @param _upperLimit 上限
     * @return double 劣化度（非"指标劣化度"）
     */
    public static double monotonic(double _measured, double _optimalValue, double _upperLimit) {
        double k = 1 / (_upperLimit - _optimalValue);
        double b = 1 - k * _upperLimit;
        return k * _measured + b;
    }

    /**
     * 返回“使用中间最优函数表达劣化度的变量”的劣化度
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     *     1.在degradeIndex方法中，可以指定本劣化度的【参与系数】；
     *     2.通过计算该测点使用其它劣化度函数，如monotonic，并指定其参与系数，可以使用degradeIndex方法计算使用多种劣化度函数
     *     的情况下，该测点的【指标劣化度】
     * </p>
     * <p style="color: black">************************</p>
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
     * 计算使用多种劣化度函数的情况下，结合其各自的参与系数以计算指标劣化度
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     *     1.劣化度与参与系数应当成对输入；<br>
     *     2.至少应输入一组(劣化度, 参与系数)<br>
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param  _degradeValue 劣化度函数的输出
     * @param  _participateCoef 参与系数
     * @return  double 指标劣化度
     */
    public static double degradeIndex(double _degradeValue, double _participateCoef, double ..._args){
        double _cache = 0;
        if (_args.length >= 2){
            for (int i=1; i<=_args.length-1; i++){
                _cache = _cache + _args[i-1] * _args[i];
            }
        }
        return _degradeValue * _participateCoef + _cache;

    }

    public static void main(String[] args) {
        double measuredValue = 17.1;
        double degrade_monotonic = monotonic(measuredValue, 16, 20);
        double degrade_centerOptimized = centerOptimized(measuredValue, 25, 30, 40, 45);
        double a = degradeIndex(degrade_monotonic, 0.5, degrade_centerOptimized, 1);
        System.out.println(a);
    }
}