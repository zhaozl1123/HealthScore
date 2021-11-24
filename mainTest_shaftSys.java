import HealthScore.*;

import java.util.Arrays;

public class mainTest_shaftSys{
    public static void main(String[] args) {

        /*
        * 摆度得分
        * */
        /*上导摆度（峰峰，宽频）*/
        // 劣化度计算
        // 根据所需劣化度函数计算劣化度
        double deg_cent_upperGuideSwing = DegradeIndex.centerOptimized(13, 5, 10, 20, 35);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_upperGuideSwing = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_upperGuideSwing = DegradeIndex.degradeIndex(deg_cent_upperGuideSwing, partCoef_cent_upperGuideSwing);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_upperGuideSwing_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_upperGuideSwing);
        // 输出
        System.out.println("上导摆度（峰峰，宽频）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_upperGuideSwing, partCoef_cent_upperGuideSwing, deg_index_upperGuideSwing);
        System.out.printf("\t指标得分: %5.5f\n", degScore_upperGuideSwing_obj.score);

        /*下导摆度（峰峰，宽频）*/
        // 劣化度计算
        // 根据所需劣化度函数计算劣化度
        double deg_cent_lowerGuideSwing = DegradeIndex.centerOptimized(14, 5, 10, 20, 35);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_lowerGuideSwing = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_lowerGuideSwing = DegradeIndex.degradeIndex(deg_cent_lowerGuideSwing, partCoef_cent_lowerGuideSwing);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_lowerGuideSwing_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_lowerGuideSwing);
        // 输出
        System.out.println("下导摆度（峰峰，宽频）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_lowerGuideSwing, partCoef_cent_lowerGuideSwing, deg_index_lowerGuideSwing);
        System.out.printf("\t指标得分: %5.5f\n", degScore_lowerGuideSwing_obj.score);

        /*水导摆度（峰峰，宽频）*/
        // 劣化度计算
        // 根据所需劣化度函数计算劣化度
        double deg_cent_waterGuideSwing = DegradeIndex.centerOptimized(15, 5, 10, 20, 35);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_waterGuideSwing = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_waterGuideSwing = DegradeIndex.degradeIndex(deg_cent_waterGuideSwing, partCoef_cent_waterGuideSwing);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_waterGuideSwing_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_waterGuideSwing);
        // 输出
        System.out.println("水导摆度（峰峰，宽频）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_waterGuideSwing, partCoef_cent_waterGuideSwing, deg_index_waterGuideSwing);
        System.out.printf("\t指标得分: %5.5f\n", degScore_waterGuideSwing_obj.score);

        /*上导摆度变化率（稳态）*/
        // 劣化度计算
        // 根据所需劣化度函数计算劣化度
        double deg_cent_upperGuideSwingRatio = DegradeIndex.centerOptimized(12, -25, -10, 10, 25);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_upperGuideSwingRatio = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_upperGuideSwingRatio = DegradeIndex.degradeIndex(deg_cent_upperGuideSwingRatio, partCoef_cent_upperGuideSwingRatio);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_upperGuideSwingRatio_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_upperGuideSwingRatio);
        // 输出
        System.out.println("上导摆度变化率（稳态）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_upperGuideSwingRatio, partCoef_cent_upperGuideSwingRatio, deg_index_upperGuideSwingRatio);
        System.out.printf("\t指标得分: %5.5f\n", degScore_upperGuideSwingRatio_obj.score);

        /*下导摆度变化率（稳态）*/
        // 劣化度计算
        // 根据所需劣化度函数计算劣化度
        double deg_cent_lowerGuideSwingRatio = DegradeIndex.centerOptimized(8, -25, -10, 10, 25);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_lowerGuideSwingRatio = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_lowerGuideSwingRatio = DegradeIndex.degradeIndex(deg_cent_lowerGuideSwingRatio, partCoef_cent_lowerGuideSwingRatio);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_lowerGuideSwingRatio_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_lowerGuideSwingRatio);
        // 输出
        System.out.println("下导摆度变化率（稳态）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_lowerGuideSwingRatio, partCoef_cent_lowerGuideSwingRatio, deg_index_lowerGuideSwingRatio);
        System.out.printf("\t指标得分: %5.5f\n", degScore_lowerGuideSwingRatio_obj.score);

        /*水导摆度变化率（稳态）*/
        // 劣化度计算
        // 根据所需劣化度函数计算劣化度
        double deg_cent_waterGuideSwingRatio = DegradeIndex.centerOptimized(9, -25, -10, 10, 25);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_waterGuideSwingRatio = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_waterGuideSwingRatio = DegradeIndex.degradeIndex(deg_cent_waterGuideSwingRatio, partCoef_cent_waterGuideSwingRatio);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_waterGuideSwingRatio_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_waterGuideSwingRatio);
        // 输出
        System.out.println("下导摆度变化率（稳态）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_waterGuideSwingRatio, partCoef_cent_waterGuideSwingRatio, deg_index_waterGuideSwingRatio);
        System.out.printf("\t指标得分: %5.5f\n", degScore_waterGuideSwingRatio_obj.score);
        /*摆度得分*/
        double weight_upperGuideSwing = 0.25;
        double weight_lowerGuideSwing = 0.15;
        double weight_waterGuideSwing = 0.15;
        double weight_upperGuideSwingRatio = 0.18;
        double weight_lowerGuideSwingRatio = 0.15;
        double weight_waterGuideSwingRatio = 0.12;
        WeightingScore swing = new WeightingScore(
                degScore_upperGuideSwing_obj.score, weight_upperGuideSwing,
                degScore_lowerGuideSwing_obj.score, weight_lowerGuideSwing,
                degScore_waterGuideSwing_obj.score, weight_waterGuideSwing,
                degScore_upperGuideSwingRatio_obj.score, weight_upperGuideSwingRatio,
                degScore_lowerGuideSwingRatio_obj.score, weight_lowerGuideSwingRatio,
                degScore_waterGuideSwingRatio_obj.score, weight_waterGuideSwingRatio
        );
        System.out.printf("摆度得分: %5.5f\n", swing.score);
        /*摆度各级隶属度*/
        double[] swing_cache1 = WeightingMembershipCoef.mapReduceMultiply(0.25,
                degScore_upperGuideSwing_obj.membershipDegree_normal, degScore_upperGuideSwing_obj.membershipDegree_attention,
                degScore_upperGuideSwing_obj.membershipDegree_abnormal, degScore_upperGuideSwing_obj.membershipDegree_critical);
        double[] swing_cache2 = WeightingMembershipCoef.mapReduceMultiply(0.15,
                degScore_lowerGuideSwing_obj.membershipDegree_normal, degScore_lowerGuideSwing_obj.membershipDegree_attention,
                degScore_lowerGuideSwing_obj.membershipDegree_abnormal, degScore_lowerGuideSwing_obj.membershipDegree_critical);
        double[] swing_cache3 = WeightingMembershipCoef.mapReduceMultiply(0.15,
                degScore_waterGuideSwing_obj.membershipDegree_normal, degScore_waterGuideSwing_obj.membershipDegree_attention,
                degScore_waterGuideSwing_obj.membershipDegree_abnormal, degScore_waterGuideSwing_obj.membershipDegree_critical);
        double[] swing_cache4 = WeightingMembershipCoef.mapReduceMultiply(0.18,
                degScore_upperGuideSwingRatio_obj.membershipDegree_normal, degScore_upperGuideSwingRatio_obj.membershipDegree_attention,
                degScore_upperGuideSwingRatio_obj.membershipDegree_abnormal, degScore_upperGuideSwingRatio_obj.membershipDegree_critical);
        double[] swing_cache5 = WeightingMembershipCoef.mapReduceMultiply(0.15,
                degScore_lowerGuideSwingRatio_obj.membershipDegree_normal, degScore_lowerGuideSwingRatio_obj.membershipDegree_attention,
                degScore_lowerGuideSwingRatio_obj.membershipDegree_abnormal, degScore_lowerGuideSwingRatio_obj.membershipDegree_critical);
        double[] swing_cache6 = WeightingMembershipCoef.mapReduceMultiply(0.12,
                degScore_waterGuideSwingRatio_obj.membershipDegree_normal, degScore_waterGuideSwingRatio_obj.membershipDegree_attention,
                degScore_waterGuideSwingRatio_obj.membershipDegree_abnormal, degScore_waterGuideSwingRatio_obj.membershipDegree_critical);
        double[] swingMembership = WeightingMembershipCoef.mapReducePlus(swing_cache1, swing_cache2, swing_cache3, swing_cache4, swing_cache5, swing_cache6);
        System.out.println("摆度各评级隶属度: " + Arrays.toString(swingMembership));
        /*
        * 振动得分
        * */
        /*上机架水平振动（峰峰，最大）*/
        // 根据所需劣化度函数计算劣化度
        double deg_cent_upperFrameVib = DegradeIndex.centerOptimized(7, 0, 6, 9, 12);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_upperFrameVib = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_upperFrameVib = DegradeIndex.degradeIndex(deg_cent_upperFrameVib, partCoef_cent_upperFrameVib);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_upperFrameVib_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_upperFrameVib);
        // 输出
        System.out.println("上机架水平振动（峰峰，最大）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_upperFrameVib, partCoef_cent_upperFrameVib, deg_index_upperFrameVib);
        System.out.printf("\t指标得分: %5.5f\n", degScore_upperFrameVib_obj.score);
        /*下机架水平振动（峰峰，最大）*/
        // 根据所需劣化度函数计算劣化度
        double deg_cent_lowerFrameVib = DegradeIndex.centerOptimized(5, 0, 4, 9, 12);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_lowerFrameVib = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_lowerFrameVib = DegradeIndex.degradeIndex(deg_cent_lowerFrameVib, partCoef_cent_lowerFrameVib);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_lowerFrameVib_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_lowerFrameVib);
        // 输出
        System.out.println("下机架水平振动（峰峰，最大）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_lowerFrameVib, partCoef_cent_lowerFrameVib, deg_index_lowerFrameVib);
        System.out.printf("\t指标得分: %5.5f\n", degScore_lowerFrameVib_obj.score);
        /*顶盖水平振动（峰峰，最大）*/
        // 根据所需劣化度函数计算劣化度
        double deg_cent_headCoverVib = DegradeIndex.centerOptimized(6, 0, 5, 9, 35);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_headCoverVib = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_headCoverVib = DegradeIndex.degradeIndex(deg_cent_headCoverVib, partCoef_cent_headCoverVib);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_headCoverVib_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_headCoverVib);
        // 输出
        System.out.println("顶盖水平振动（峰峰，最大）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_headCoverVib, partCoef_cent_headCoverVib, deg_index_headCoverVib);
        System.out.printf("\t指标得分: %5.5f\n", degScore_headCoverVib_obj.score);
        /*上机架垂直振动（峰峰，最大）*/
        // 根据所需劣化度函数计算劣化度
        double deg_cent_upperFrameVerVib = DegradeIndex.centerOptimized(5, 0, 3, 7, 25);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_upperFrameVerVib = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_upperFrameVerVib = DegradeIndex.degradeIndex(deg_cent_upperFrameVerVib, partCoef_cent_upperFrameVerVib);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_upperFrameVerVib_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_upperFrameVerVib);
        // 输出
        System.out.println("上机架垂直振动（峰峰，最大）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_upperFrameVerVib, partCoef_cent_upperFrameVerVib, deg_index_upperFrameVerVib);
        System.out.printf("\t指标得分: %5.5f\n", degScore_upperFrameVerVib_obj.score);
        /*下机架垂直振动（峰峰，最大）*/
        // 根据所需劣化度函数计算劣化度
        double deg_cent_lowerFrameVerVib = DegradeIndex.centerOptimized(4, 0, 3, 7, 12);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_lowerFrameVerVib = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_lowerFrameVerVib = DegradeIndex.degradeIndex(deg_cent_lowerFrameVerVib, partCoef_cent_lowerFrameVerVib);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_lowerFrameVerVib_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_lowerFrameVerVib);
        // 输出
        System.out.println("下机架垂直振动（峰峰，最大）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_lowerFrameVerVib, partCoef_cent_lowerFrameVerVib, deg_index_lowerFrameVerVib);
        System.out.printf("\t指标得分: %5.5f\n", degScore_lowerFrameVerVib_obj.score);
        /*顶盖垂直振动（峰峰）*/
        // 根据所需劣化度函数计算劣化度
        double deg_cent_headCoverVerVib = DegradeIndex.centerOptimized(13, 0, 3, 7, 25);
        // 根据各劣化度及其参与系数计算指标劣化度
        double partCoef_cent_headCoverVerVib = 1;  // 中间最优型单调函数的劣化度参与系数
        double deg_index_headCoverVerVib = DegradeIndex.degradeIndex(deg_cent_headCoverVerVib, partCoef_cent_headCoverVerVib);
        // 根据指标劣化度计算指标得分
        DegradeScore degScore_headCoverVerVib_obj = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8,
                deg_index_headCoverVerVib);
        // 输出
        System.out.println("顶盖垂直振动（峰峰）:");
        System.out.printf("\t中间最优型劣化度: %5.5f, 参与系数: %5.5f\n\t劣化度指标值: %5.5f \n",
                deg_cent_headCoverVerVib, partCoef_cent_headCoverVerVib, deg_index_headCoverVerVib);
        System.out.printf("\t指标得分: %5.5f\n", degScore_headCoverVerVib_obj.score);
        /*振动得分*/
        double weight_upperFrameVib = 0.25;
        double weight_lowerFrameVib = 0.15;
        double weight_headCoverVib = 0.15;
        double weight_upperFrameVerVib = 0.12;
        double weight_lowerFrameVerVib = 0.18;
        double weight_headCoverVerVib = 0.15;
        WeightingScore vibration = new WeightingScore(
                degScore_upperFrameVib_obj.score, weight_upperFrameVib,
                degScore_lowerFrameVib_obj.score, weight_lowerFrameVib,
                degScore_headCoverVib_obj.score, weight_headCoverVib,
                degScore_upperFrameVerVib_obj.score, weight_upperFrameVerVib,
                degScore_lowerFrameVerVib_obj.score, weight_lowerFrameVerVib,
                degScore_headCoverVerVib_obj.score, weight_headCoverVerVib
        );
        System.out.printf("振动得分: %5.5f\n", vibration.score);

        /*振动各级隶属度*/
        double[] vib_cache1 = WeightingMembershipCoef.mapReduceMultiply(0.25,
                degScore_upperFrameVib_obj.membershipDegree_normal, degScore_upperFrameVib_obj.membershipDegree_attention,
                degScore_upperFrameVib_obj.membershipDegree_abnormal, degScore_upperFrameVib_obj.membershipDegree_critical);
        double[] vib_cache2 = WeightingMembershipCoef.mapReduceMultiply(0.15,
                degScore_lowerFrameVib_obj.membershipDegree_normal, degScore_lowerFrameVib_obj.membershipDegree_attention,
                degScore_lowerFrameVib_obj.membershipDegree_abnormal, degScore_lowerFrameVib_obj.membershipDegree_critical);
        double[] vib_cache3 = WeightingMembershipCoef.mapReduceMultiply(0.15,
                degScore_headCoverVib_obj.membershipDegree_normal, degScore_headCoverVib_obj.membershipDegree_attention,
                degScore_headCoverVib_obj.membershipDegree_abnormal, degScore_headCoverVib_obj.membershipDegree_critical);
        double[] vib_cache4 = WeightingMembershipCoef.mapReduceMultiply(0.12,
                degScore_upperFrameVerVib_obj.membershipDegree_normal, degScore_upperFrameVerVib_obj.membershipDegree_attention,
                degScore_upperFrameVerVib_obj.membershipDegree_abnormal, degScore_upperFrameVerVib_obj.membershipDegree_critical);
        double[] vib_cache5 = WeightingMembershipCoef.mapReduceMultiply(0.18,
                degScore_lowerFrameVerVib_obj.membershipDegree_normal, degScore_lowerFrameVerVib_obj.membershipDegree_attention,
                degScore_lowerFrameVerVib_obj.membershipDegree_abnormal, degScore_lowerFrameVerVib_obj.membershipDegree_critical);
        double[] vib_cache6 = WeightingMembershipCoef.mapReduceMultiply(0.15,
                degScore_headCoverVerVib_obj.membershipDegree_normal, degScore_headCoverVerVib_obj.membershipDegree_attention,
                degScore_headCoverVerVib_obj.membershipDegree_abnormal, degScore_headCoverVerVib_obj.membershipDegree_critical);
        double[] vibMembership = WeightingMembershipCoef.mapReducePlus(vib_cache1, vib_cache2, vib_cache3, vib_cache4, vib_cache5, vib_cache6);
        System.out.println("振动各评级隶属度: " + Arrays.toString(vibMembership));

        /*
            轴系稳定性指标得分值
        */
        double[] swing_cache = WeightingMembershipCoef.mapReduceMultiply(0.6, swingMembership);
        double[] vibration_cache = WeightingMembershipCoef.mapReduceMultiply(0.4, vibMembership);
        double[] shaftMembership = WeightingMembershipCoef.mapReducePlus(swing_cache, vibration_cache);
        System.out.println("轴系稳定性各评级隶属度: " + Arrays.toString(shaftMembership));

        /*
            水轮发电机指标得分值
        */
        double[] gradeScores = new double[]{100, 70, 50, 30};
        double[] cache = CommonMethods.matMultiply(gradeScores, shaftMembership);
        double finalScore = CommonMethods.cumsum(cache);
        System.out.println("轴系稳定性指标得分值: " + finalScore);
    }
}
