package HealthScore;

import Interfaces.Interface_DegradeFunctionParams;


/**
 * 用以作为{@link HealthScore.Method_CalculateDegradeIndex#calculate(double, double[], double[], DegradeFunctionParams...)}
 * 中DegradeFunctionParams的参数
 *
 * <p style="color: black">************************</p>
 * <p style="color: black">
 *     1.使用了接口{@link Interface_DegradeFunctionParams}
 *     2.该类具有属性:<br></br>
 *              public String methodName 需要使用的劣化度函数的名称,如"monotonic"或"center"<br></br>
 *              public double[] expressionParams 所选劣化度函数的表达式<br></br>
 *              public double participateCoef 所选劣化度函数的参与系数<br></br>
 * </p>
 * <p style="color: black">************************</p>
 *
 */
public class DegradeFunctionParams implements Interface_DegradeFunctionParams {
    public String methodName;
    public double[] expressionParams;
    public double participateCoef;

    public DegradeFunctionParams(){

    }

    @Override
    public void setMethodName(String _name) {methodName = _name;}

    @Override
    public void setExpressionParams(double[] _args) {expressionParams = _args;}

    @Override
    public void setParticipateCoef(double _arg) {participateCoef = _arg;}

}
