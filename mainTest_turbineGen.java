import HealthScore.*;

import java.util.Arrays;

public class mainTest_turbineGen {
    public static void main(String[] args) {
        /*
            气隙最小值平均
        */
        DegradeFunctionParams monoParam_airGapMiniAvg = new DegradeFunctionParams();
        monoParam_airGapMiniAvg.setMethodName("monotonic");
        monoParam_airGapMiniAvg.setExpressionParams(new double[]{24, 32});
        monoParam_airGapMiniAvg.setParticipateCoef(0);

        DegradeFunctionParams centParam_airGapMiniAvg = new DegradeFunctionParams();
        centParam_airGapMiniAvg.setMethodName("center");
        centParam_airGapMiniAvg.setExpressionParams(new double[]{24, 28, 30, 34});
        centParam_airGapMiniAvg.setParticipateCoef(1);

        double[] standardScores = new double[]{100, 70, 50, 30};
        double[] degradeIndexes = new double[]{0.2, 0.4, 0.6, 0.8};

        Method_CalculateDegradeIndex airGapMiniAvg_obj = new Method_CalculateDegradeIndex();
        airGapMiniAvg_obj.calculate(80, standardScores, degradeIndexes,
                monoParam_airGapMiniAvg, centParam_airGapMiniAvg);
        System.out.printf("气隙最小值平均,指标得分: %3.2f\n", airGapMiniAvg_obj.indexScore);

        /*
            气隙最小值偏差
        */
        Method_CalculateDegradeIndex airGapMiniBias_obj = new Method_CalculateDegradeIndex();
        airGapMiniBias_obj.calculate(0.5, new String[]{"monotonic"},
                new double[][][]{{{0, 0.96}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("气隙最小值偏差,指标得分: %3.2f\n", airGapMiniBias_obj.indexScore);
        /*
            转子得分
        */
        double weight_airGapMiniAvg = 0.6;
        double weight_airGapMiniBias = 0.4;
        WeightingScore rotor = new WeightingScore(
                airGapMiniAvg_obj.indexScore, weight_airGapMiniAvg,
                airGapMiniBias_obj.indexScore, weight_airGapMiniBias
        );
        System.out.printf("转子得分: %5.5f\n", rotor.score);
        /*
            转子各级隶属度
        */
        double[] rotorMembership_cache1 = WeightingMembershipCoef.mapReduceMultiply(0.6,
                airGapMiniAvg_obj.membershipDegree_normal, airGapMiniAvg_obj.membershipDegree_attention,
                airGapMiniAvg_obj.membershipDegree_abnormal, airGapMiniAvg_obj.membershipDegree_critical);
        double[] rotorMembership_cache2 = WeightingMembershipCoef.mapReduceMultiply(0.4,
                airGapMiniBias_obj.membershipDegree_normal, airGapMiniBias_obj.membershipDegree_attention,
                airGapMiniBias_obj.membershipDegree_abnormal, airGapMiniBias_obj.membershipDegree_critical);
        double[] rotorMemberships = WeightingMembershipCoef.mapReducePlus(rotorMembership_cache1, rotorMembership_cache2);
        System.out.println("转子各评级隶属度: " + Arrays.toString(rotorMemberships));
        /*
            集电环罩温度（平均值）
        */
        Method_CalculateDegradeIndex collectorCover_obj = new Method_CalculateDegradeIndex();
        collectorCover_obj.calculate(39, new String[]{"monotonic"},
                new double[][][]{{{30, 80}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("集电环罩温度（平均值）,指标得分: %3.2f\n", collectorCover_obj.indexScore);
        // 集电系统得分
        WeightingScore collectorSystemScore = new WeightingScore(collectorCover_obj.indexScore, 0.01);
        System.out.printf("集电系统得分: %5.5f\n", collectorSystemScore.score);
        // 集电系统各级隶属度
        double[] collectorSys_cache1 = WeightingMembershipCoef.mapReduceMultiply(0.01,
                collectorCover_obj.membershipDegree_normal, collectorCover_obj.membershipDegree_attention,
                collectorCover_obj.membershipDegree_abnormal, collectorCover_obj.membershipDegree_critical);
        double[] collectorSysMembership = WeightingMembershipCoef.mapReducePlus(collectorSys_cache1);
        System.out.println("集电系统各评级隶属度: " + Arrays.toString(collectorSysMembership));
        /*
            水轮发电机各级隶属度
        */
        double[] turbineGenerator_cache1 = WeightingMembershipCoef.mapReduceMultiply(0.2, rotorMemberships);
        double[] turbineGenerator_cache2 = WeightingMembershipCoef.mapReduceMultiply(0.08, collectorSysMembership);
        double[] turbineGeneratorMembership = WeightingMembershipCoef.mapReducePlus(turbineGenerator_cache1, turbineGenerator_cache2);
        System.out.println("水轮发电机各评级隶属度: " + Arrays.toString(turbineGeneratorMembership));

        /*
            水轮发电机指标得分值
        */
        double[] gradeScores = new double[]{100, 70, 50, 30};
        double[] cache = CommonMethods.matMultiply(gradeScores, turbineGeneratorMembership);
        double finalScore = CommonMethods.cumsum(cache);
        System.out.println("水轮发电机指标得分值: " + finalScore);
    }
}
