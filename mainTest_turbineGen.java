import HealthScore.*;

import java.util.Arrays;

public class mainTest_turbineGen {
    public static void main(String[] args) {
        /*
            气隙最小值（平均）
        */
        // 根据所需劣化度函数计算劣化度
        double deg_mono_airGapMinAvg = DegradeIndex.monotonic(28.8, 24, 32);
        double deg_cent_airGapMinAvg = DegradeIndex.centerOptimized(28.8, 24, 28, 30, 34);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_mono_airGapMinAvg = 0.1;  // 越小越优型单调函数的劣化度参与系数
        double partCoef_cent_airGapMinAvg = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_Index_airGapMinAvg = DegradeIndex.degradeIndex(deg_mono_airGapMinAvg, partCoef_mono_airGapMinAvg, deg_cent_airGapMinAvg, partCoef_cent_airGapMinAvg);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_airGapMinAvg_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_Index_airGapMinAvg);
        // 输出
        System.out.println("气隙最小值(平均):");
        System.out.printf("\t越小越优型劣化度: %5.5f, 参与系数: %5.5f, 中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_mono_airGapMinAvg, partCoef_mono_airGapMinAvg, deg_cent_airGapMinAvg, partCoef_cent_airGapMinAvg,
                deg_Index_airGapMinAvg);
        System.out.printf("\t指标得分: %5.5f\n", degScore_airGapMinAvg_obj.score);
        /*
            气隙最小值偏差
        */
        // 根据所需劣化度函数计算劣化度
        double deg_mono_airGapMinBias = DegradeIndex.monotonic(0.5, 0, 0.96);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_mono_airGapMinBias = 1;  // 越小越优型单调函数的劣化度参与系数
        double deg_Index_airGapMinBias = DegradeIndex.degradeIndex(deg_mono_airGapMinBias, partCoef_mono_airGapMinBias);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_airGapMinBias_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_Index_airGapMinBias);
        // 输出
        System.out.println("气隙最小值偏差:");
        System.out.printf("\t越小越优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_mono_airGapMinBias, partCoef_mono_airGapMinBias, deg_Index_airGapMinBias);
        System.out.printf("\t指标得分: %5.5f\n", degScore_airGapMinBias_obj.score);
        /*
            转子得分
        */
        double weight_airGapMinAvg = 0.6;
        double weight_airGapMinBias = 0.4;
        WeightingScore rotorScore = new WeightingScore(degScore_airGapMinAvg_obj.score, weight_airGapMinAvg,
                degScore_airGapMinBias_obj.score, weight_airGapMinBias);
        System.out.printf("转子得分: %5.5f\n", rotorScore.score);
        /*
            转子各级隶属度
        */
        double[] rotorMembership_cache1 = WeightingMembershipCoef.mapReduceMultiply(0.6, degScore_airGapMinAvg_obj.membershipDegree_normal, degScore_airGapMinAvg_obj.membershipDegree_attention, degScore_airGapMinAvg_obj.membershipDegree_abnormal, degScore_airGapMinAvg_obj.membershipDegree_critical);
        double[] rotorMembership_cache2 = WeightingMembershipCoef.mapReduceMultiply(0.4, degScore_airGapMinBias_obj.membershipDegree_normal, degScore_airGapMinBias_obj.membershipDegree_attention, degScore_airGapMinBias_obj.membershipDegree_abnormal, degScore_airGapMinBias_obj.membershipDegree_critical);
        double[] rotorMemberships = WeightingMembershipCoef.mapReducePlus(rotorMembership_cache1, rotorMembership_cache2);
        System.out.println("转子各评级隶属度: " + Arrays.toString(rotorMemberships));

        /*
            集电环罩温度（平均值）
        */
        // 根据所需劣化度函数计算劣化度
        double deg_mono_collectorAvg = DegradeIndex.monotonic(39, 30, 80);
        double participateCoef_mono_collectorAvg = 1;
        double deg_index_collectorAvg = DegradeIndex.degradeIndex(deg_mono_collectorAvg, participateCoef_mono_collectorAvg);
        System.out.println("集电环罩温度（平均值）:");
        System.out.printf("\t越小越优型劣化度: %5.5f, 参与系数： %5.5f\n\t劣化度指标值: %5.5f %n",
                deg_mono_collectorAvg, participateCoef_mono_collectorAvg, deg_index_collectorAvg);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_collectorAvg_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_collectorAvg);
        System.out.printf("\t指标得分: %5.5f\n", degScore_collectorAvg_obj.score);
        // 集电系统得分
        WeightingScore collectorSystemScore = new WeightingScore(degScore_collectorAvg_obj.score, 0.01);
        System.out.printf("集电系统得分: %5.5f\n", collectorSystemScore.score);
        // 集电系统各级隶属度
        double[] collectorSys_cache1 = WeightingMembershipCoef.mapReduceMultiply(0.01, degScore_collectorAvg_obj.membershipDegree_normal, degScore_collectorAvg_obj.membershipDegree_attention, degScore_collectorAvg_obj.membershipDegree_abnormal, degScore_collectorAvg_obj.membershipDegree_critical);
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
        System.exit(0);
    }
}
