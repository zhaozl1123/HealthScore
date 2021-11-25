package HealthScore;

import java.util.Arrays;

/**
 * 参见{@link HealthScore.Method_CalculateDegradeIndex#calculate}
 */
public class Method_CalculateDegradeIndex {
    double participateCoefficient;
    double[] standardScores;
    double[] standardDegradeIndexes;

    public double indexGrade;
    public double indexScore;
    public double membershipDegree_normal;
    public double membershipDegree_attention;
    public double membershipDegree_abnormal;
    public double membershipDegree_critical;

    public Method_CalculateDegradeIndex() {

    }

    /**
    * 根据所定义的劣化度函数、隶属度函数及其它参数计算某值的如下信息：<br></br>
    * 1.指标劣化度: indexGrade<br></br>
    * 2.指标得分: indexScore<br></br>
    * 3.对正常状态的隶属度: membershipDegree_normal<br></br>
    * 4.对注意状态的隶属度: membershipDegree_attention<br></br>
    * 5.对异常状态的隶属度: membershipDegree_abnormal<br></br>
    * 6.对严重状态的隶属度: membershipDegree_critical<br></br>
    *
    * @param  _measured 对象实测值
    * @param  _methodNames 需要使用的劣化度函数名称,数组如["monotonic", "center"]
    * @param  _params 三维数组,<br></br>
     *                 如与["monotonic", "center"]相对应的:{ {{A1},{B1},{C1},{D1}}, {{A2},{B2},{C2},{D2}} }<br></br>
     *                 A1: {劣化度函数的最优值, 劣化度函数的上限值} <br></br>
     *                 B1: {劣化度函数参与系数} <br></br>
     *                 C1: {按照["正常","注意","异常","严重"]进行排序的分值,如[100, 80, 60, 40]} <br></br>
     *                 D1: {按照["正常","注意","异常","严重"]进行排序的指标值,如[0.2， 0.4， 0.6， 0.8]} <br></br>
     *                 A2: {劣化度函数的下限, 劣化度函数的最优下限, 劣化度函数的最优上限, 劣化度函数的上限} <br></br>
     *                 B2: {同上} <br></br>
     *                 C2: {同上} <br></br>
     *                 D2: {同上} <br></br>
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

        indexGrade = cache[0];
        indexScore = cache[1];
        membershipDegree_normal = cache[2];
        membershipDegree_attention = cache[3];
        membershipDegree_abnormal = cache[4];
        membershipDegree_critical = cache[5];
    }

    /**
     * 根据所选择的劣化度计算方法名称,计算劣化度<br></br>
     * 计算如下信息:<br></br>
     * 1.劣化度: degradeDegree<br></br>
     * 2.指标劣化度: degradeIndex<br></br>
     * 3.指标得分: indexScore<br></br>
     * 4.对正常状态的隶属度: membershipDegree_normal<br></br>
     * 5.对注意状态的隶属度: membershipDegree_attention<br></br>
     * 6.对异常状态的隶属度: membershipDegree_abnormal<br></br>
     * 7.对严重状态的隶属度: membershipDegree_critical<br></br>
     *
     * @param _methodName 劣化度函数名称,如"center"
     * @param _measured 实测值
     * @param _degradeFunctionParams 劣化度计算的参数,如["下限","最优下限","最优上限","上限"]=[5,10,20,35]
     * @param _participateCoefficient 所选劣化度函数的参与系数
     */
    public static double calculate_index_degrade(String _methodName, double _measured, double[] _degradeFunctionParams, double _participateCoefficient) {
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
    * 通过计算不同对象的加权劣化度之和作为多劣化度函数综合作用下的指标劣化度.进而计算劣化度得分,参考{@link HealthScore.DegradeScore#DegradeScore}
    *
    * <p style="color: black">************************</p>
    * <p style="color: black">
    *     1.输入的index_degrades已经为叠加参与系数后的指标劣化度
    * </p>
    * <p style="color: black">************************</p>
    *
    * @param  standardScores 按照["正常","注意","异常","严重"]进行排序的分值,如[100, 80, 60, 40]
    * @param  standardDegradeIndexes 按照["正常","注意","异常","严重"]进行排序的指标值,如[0.2， 0.4， 0.6， 0.8]
    * @param  index_degrades 使用{@link HealthScore.DegradeIndex#degradeIndex}计算得出的指标劣化度
    * @return  double[]
    */
    public static double[] calculate_index_score(double[] standardScores, double[] standardDegradeIndexes, double[] index_degrades) {
        double index_grade = 0;  // 指标劣化度(多种劣化度函数的加权劣化度之和)
        for (double item: index_degrades){index_grade+=item;}
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_obj = new DegradeScore(standardScores, standardDegradeIndexes, index_grade);
        return new double[]{index_grade, degScore_obj.score,
                degScore_obj.membershipDegree_normal, degScore_obj.membershipDegree_attention,
                degScore_obj.membershipDegree_abnormal, degScore_obj.membershipDegree_critical
        };

    }
}
