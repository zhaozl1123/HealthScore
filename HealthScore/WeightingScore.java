package HealthScore;

import java.util.Arrays;

/**
 * 参见{@link HealthScore.WeightingScore#WeightingScore}  
 */
public class WeightingScore {
    public double score;

    /**
     * 计算指标加权得分
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     *     1.指标加权得分通过类属性score调用;<br>
     *     2._rawScore1 * _weight1 + _rawScore2 * _weight2 + ......
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param  _rawScore 原始得分
     * @param  _weight 权重
     * @param  args 成对出现的(原始得分, 权重)
     */
    public WeightingScore(double _rawScore, double _weight, double...args){
        score = _rawScore * _weight;
        if (args.length>=1){
            for (int i=1; i<args.length; i+=2){
                score = score + args[i-1] * args[i];
            }
        }
    }
    public WeightingScore(double[] _scores_and_weights){
        int length = _scores_and_weights.length;
        double res = 0;
        for (int i=0; i<length/2; i++){
            res += _scores_and_weights[2*i]*_scores_and_weights[2*i+1];
        }
        score = res;

    }

    public static void main(String[] args) {
        WeightingScore rotorScore = new WeightingScore(100, 0.6, 57.9, 0.4);
        System.out.println(rotorScore.score);
    }
}
