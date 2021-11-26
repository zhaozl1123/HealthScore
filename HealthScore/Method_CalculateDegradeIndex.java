package HealthScore;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Methods                                                                                                          <br>
 * ----------                                                                                                       <br>
 * * {@link Method_CalculateDegradeIndex#calculate_index_degrade(String, double, double[], double)}                 <br>
 * * {@link Method_CalculateDegradeIndex#calculate_index_score(double[], double[], double[])}                       <br>
 * * {@link Method_CalculateDegradeIndex#calculate(double, String[], double[][][])}                                 <br>
 * * {@link Method_CalculateDegradeIndex#calculate(double, double[], double[], DegradeFunctionParams...)}           <br>
 * 主要功能                                                                                                           <br>
 * ----------                                                                                                       <br>
 * 1.计算单个劣化度函数所表达的劣化度                                                                                     <br>
 * 2.多劣化度函数的指标劣化度之和、劣化度得分和处于不同standardScores下的隶属度。                                               <br>
 * 3.计算指标劣化度、指标得分、对正常状态的隶属度、...                                                                       <br>
 */
public class Method_CalculateDegradeIndex {
    double participateCoefficient;
    double[] standardScores;
    double[] standardDegradeIndexes;

    public double indexDegrade;
    public double indexScore;
    public double membershipDegree_normal;
    public double membershipDegree_attention;
    public double membershipDegree_abnormal;
    public double membershipDegree_critical;

    public Method_CalculateDegradeIndex() {

    }

    /**
     * 根据所定义的劣化度函数、隶属度函数及其它参数计算某值的如下信息：                                                          <br>
     * 1.指标劣化度: indexGrade                                                                                        <br>
     * 2.指标得分: indexScore                                                                                          <br>
     * 3.对正常状态的隶属度: membershipDegree_normal                                                                     <br>
     * 4.对注意状态的隶属度: membershipDegree_attention                                                                  <br>
     * 5.对异常状态的隶属度: membershipDegree_abnormal                                                                   <br>
     * 6.对严重状态的隶属度: membershipDegree_critical                                                                   <br>
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     * calculate方法更新了类属性：                                                                                     <br>
     * indexDegrade: 对象如"气隙最小值（平均）"的指标劣化度                                                       <br>
     * indexScore: 对象如"气隙最小值（平均）"的指标得分                                                          <br>
     * membershipDegree_normal: 对象如"气隙最小值（平均）"于正常状态的隶属度                                       <br>
     * membershipDegree_attention: 对象如"气隙最小值（平均）"于注意状态的隶属度                                    <br>
     * membershipDegree_abnormal: 对象如"气隙最小值（平均）"于异常状态的隶属度                                     <br>
     * membershipDegree_critical: 对象如"气隙最小值（平均）"于严重状态的隶属度                                     <br>
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param _measured    对象实测值
     * @param _methodNames 需要使用的劣化度函数名称,数组如["monotonic", "center"]
     * @param _params      三维数组,<br></br>
     *                     如与["monotonic", "center"]相对应的:{ {{A1},{B1},{C1},{D1}}, {{A2},{B2},{C2},{D2}} }<br></br>
     *                     A1: {劣化度函数的最优值, 劣化度函数的上限值} <br></br>
     *                     B1: {劣化度函数参与系数} <br></br>
     *                     C1: {按照["正常","注意","异常","严重"]进行排序的分值,如[100, 80, 60, 40]} <br></br>
     *                     D1: {按照["正常","注意","异常","严重"]进行排序的指标值,如[0.2， 0.4， 0.6， 0.8]} <br></br>
     *                     A2: {劣化度函数的下限, 劣化度函数的最优下限, 劣化度函数的最优上限, 劣化度函数的上限} <br></br>
     *                     B2: {同上} <br></br>
     *                     C2: {同上} <br></br>
     *                     D2: {同上} <br></br>
     */
    public void calculate(double _measured, String[] _methodNames, double[][][] _params) {
        double[] sections;
        double[] index_degrades_withWeight = new double[_methodNames.length];
        Arrays.fill(index_degrades_withWeight, 99);
        for (int i = 0; i < _methodNames.length; i++) {
            sections = _params[i][0];
            participateCoefficient = _params[i][1][0];
            standardScores = _params[i][2];
            standardDegradeIndexes = _params[i][3];
            index_degrades_withWeight[i] = calculate_index_degrade(_methodNames[i], _measured, sections, participateCoefficient);
        }
        double[] cache = calculate_index_score(standardScores, standardDegradeIndexes, index_degrades_withWeight);

        indexDegrade = cache[0];
        indexScore = cache[1];
        membershipDegree_normal = cache[2];
        membershipDegree_attention = cache[3];
        membershipDegree_abnormal = cache[4];
        membershipDegree_critical = cache[5];
    }

    /**
     * 根据所定义的劣化度函数、隶属度函数及其它参数计算某值的如下信息：                                                        <br>
     * 1.指标劣化度: indexGrade                                                                                      <br>
     * 2.指标得分: indexScore                                                                                        <br>
     * 3.对正常状态的隶属度: membershipDegree_normal                                                                   <br>
     * 4.对注意状态的隶属度: membershipDegree_attention                                                                <br>
     * 5.对异常状态的隶属度: membershipDegree_abnormal                                                                 <br>
     * 6.对严重状态的隶属度: membershipDegree_critical                                                                 <br>
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     * 方法所接受的入参_params是DegradeFunctionParams对象(参考{@link HealthScore.DegradeFunctionParams})             <br><br>
     * calculate方法更新了类属性：                                                                                     <br>
     * indexDegrade: 对象如"气隙最小值（平均）"的指标劣化度                                                       <br>
     * indexScore: 对象如"气隙最小值（平均）"的指标得分                                                          <br>
     * membershipDegree_normal: 对象如"气隙最小值（平均）"于正常状态的隶属度                                       <br>
     * membershipDegree_attention: 对象如"气隙最小值（平均）"于注意状态的隶属度                                    <br>
     * membershipDegree_abnormal: 对象如"气隙最小值（平均）"于异常状态的隶属度                                     <br>
     * membershipDegree_critical: 对象如"气隙最小值（平均）"于严重状态的隶属度                                     <br>
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param _measured      对象实测值
     * @param standardScores 按照["正常","注意","异常","严重"]进行排序的分值,如[100, 80, 60, 40]
     * @param degradeIndexes 按照["正常","注意","异常","严重"]进行排序的指标值,如[0.2， 0.4， 0.6， 0.8]
     * @param _params        DegradeFunctionParams对象（参考{@link HealthScore.DegradeFunctionParams}）
     */
    public void calculate(double _measured, double[] standardScores, double[] degradeIndexes, DegradeFunctionParams... _params) {
        int paramsLength = _params.length;
        double[] index_degrades_withWeight = new double[paramsLength];
        Arrays.fill(index_degrades_withWeight, 99);
        for (int i = 0; i < paramsLength; i++) {
            double[] expressionParams = _params[i].expressionParams;
            String methodName = _params[i].methodName;
            participateCoefficient = _params[i].participateCoef;
            index_degrades_withWeight[i] = calculate_index_degrade(methodName, _measured, expressionParams, participateCoefficient);
        }
        double[] cache = calculate_index_score(standardScores, degradeIndexes, index_degrades_withWeight);
        indexDegrade = cache[0];
        indexScore = cache[1];
        membershipDegree_normal = cache[2];
        membershipDegree_attention = cache[3];
        membershipDegree_abnormal = cache[4];
        membershipDegree_critical = cache[5];
    }

    /**
     * 根据所选劣化度函数名称、实测值、劣化度函数参数、劣化度函数参与系数，最终计算单个劣化度函数所表达的劣化度<br></br>
     *
     * @param _methodName             劣化度函数名称,如"center"
     * @param _measured               实测值
     * @param _degradeFunctionParams  劣化度计算的参数,如["下限","最优下限","最优上限","上限"]=[5,10,20,35]
     * @param _participateCoefficient 所选劣化度函数的参与系数
     * @return (单个)劣化度函数与其参与系数的乘积
     */
    public static double calculate_index_degrade(String _methodName, double _measured,
                                                 double[] _degradeFunctionParams, double _participateCoefficient) {
        double deg = 0;
        double upperLimit;
        switch (_methodName) {
            case "center":
                // 根据所选择的劣化度函数计算劣化度
                double lowerLimit = _degradeFunctionParams[0];
                double optimalLowerLimit = _degradeFunctionParams[1];
                double optimalUpperLimit = _degradeFunctionParams[2];
                upperLimit = _degradeFunctionParams[3];
                deg = DegradeIndex.centerOptimized(_measured, lowerLimit, optimalLowerLimit, optimalUpperLimit, upperLimit);
                break;
            case "monotonic":
                // 根据所选择的劣化度函数计算劣化度
                double optimalValue = _degradeFunctionParams[0];
                upperLimit = _degradeFunctionParams[1];
                deg = DegradeIndex.monotonic(_measured, optimalValue, upperLimit);
                break;
        }
        // 根据(单个)劣化度及其参与系数计算指标劣化度
        return DegradeIndex.degradeIndex(deg, _participateCoefficient);
    }

    /**
     * 通过计算不同对象的加权劣化度之和作为多劣化度函数综合作用下的指标劣化度.进而计算：多劣化度函数的指标劣化度之和、劣化度得分
     * （参考{@link HealthScore.DegradeScore#DegradeScore}）和处于不同standardScores下的隶属度。
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     * 1.输入的index_degrades已经为叠加参与系数后的指标劣化度(可以为使用单个或多个劣化度的参与系数乘积之和)
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param standardScores            按照["正常","注意","异常","严重"]进行排序的分值,如[100, 80, 60, 40]
     * @param standardDegradeIndexes    按照["正常","注意","异常","严重"]进行排序的指标值,如[0.2， 0.4， 0.6， 0.8]
     * @param index_degrades_withWeight 使用{@link HealthScore.Method_CalculateDegradeIndex#calculate_index_degrade}
     *                                  计算得出的、使用了一种或多种劣化度函数及其参与系数的指标劣化度之和
     * @return double[]: [指标劣化度， 指标得分， 正常状态隶属度...严重状态隶属度]
     */
    public static double[] calculate_index_score(double[] standardScores, double[] standardDegradeIndexes, double[] index_degrades_withWeight) {
        double index_degrades_withWeight_sum = 0;  // 指标劣化度(多种劣化度函数的加权劣化度之和)
        for (double item : index_degrades_withWeight) {
            index_degrades_withWeight_sum += item;
        }
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_obj = new DegradeScore(standardScores, standardDegradeIndexes, index_degrades_withWeight_sum);
        return new double[]{index_degrades_withWeight_sum, degScore_obj.indexScore,
                degScore_obj.membershipDegree_normal, degScore_obj.membershipDegree_attention,
                degScore_obj.membershipDegree_abnormal, degScore_obj.membershipDegree_critical
        };

    }
}
