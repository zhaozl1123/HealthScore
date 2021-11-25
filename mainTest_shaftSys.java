import java.util.Arrays;
import HealthScore.*;

public class mainTest_shaftSys{
    public static void main(String[] args) {
        /*
        * 摆度得分
        * */
        /*上导摆度（峰峰，宽频）*/
        Method_CalculateDegradeIndex upperGuideSwing_obj = new Method_CalculateDegradeIndex();
        upperGuideSwing_obj.calculate(50, new String[]{"center"},
                new double[][][]{{{5, 10, 20, 35}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("上导摆度（峰峰，宽频）,指标得分: %3.2f\n", upperGuideSwing_obj.indexScore);
        /*下导摆度（峰峰，宽频）*/
        Method_CalculateDegradeIndex lowerGuideSwing_obj = new Method_CalculateDegradeIndex();
        lowerGuideSwing_obj.calculate(33, new String[]{"center"},
                new double[][][]{{{5, 10, 20, 35}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("下导摆度（峰峰，宽频）,指标得分: %3.2f\n", lowerGuideSwing_obj.indexScore);
        /*水导摆度（峰峰，宽频）*/
        Method_CalculateDegradeIndex waterGuideSwing_obj = new Method_CalculateDegradeIndex();
        waterGuideSwing_obj.calculate(33, new String[]{"center"},
                new double[][][]{{{5, 10, 20, 35}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("水导摆度（峰峰，宽频）,指标得分: %3.2f\n", waterGuideSwing_obj.indexScore);
        /*上导摆度变化率（稳态）*/
        Method_CalculateDegradeIndex upperGuideSwingRatio_obj = new Method_CalculateDegradeIndex();
        upperGuideSwingRatio_obj.calculate(18, new String[]{"center"},
                new double[][][]{{{-25, -10, 10, 25}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("上导摆度变化率（稳态）,指标得分: %3.2f\n", upperGuideSwingRatio_obj.indexScore);
        /*下导摆度变化率（稳态）*/
        Method_CalculateDegradeIndex lowerGuideSwingRatio_obj = new Method_CalculateDegradeIndex();
        lowerGuideSwingRatio_obj.calculate(8, new String[]{"center"},
                new double[][][]{{{-25, -10, 10, 25}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("下导摆度变化率（稳态）,指标得分: %3.2f\n", lowerGuideSwingRatio_obj.indexScore);
        /*水导摆度变化率（稳态）*/
        Method_CalculateDegradeIndex waterGuideSwingRatio_obj = new Method_CalculateDegradeIndex();
        waterGuideSwingRatio_obj.calculate(9, new String[]{"center"},
                new double[][][]{{{-25, -10, 10, 25}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("水导摆度变化率（稳态）,指标得分: %3.2f\n", waterGuideSwingRatio_obj.indexScore);

        /*摆度得分*/
        double weight_upperGuideSwing = 0.25;
        double weight_lowerGuideSwing = 0.15;
        double weight_waterGuideSwing = 0.15;
        double weight_upperGuideSwingRatio = 0.18;
        double weight_lowerGuideSwingRatio = 0.15;
        double weight_waterGuideSwingRatio = 0.12;
        WeightingScore swing = new WeightingScore(
                upperGuideSwing_obj.indexScore, weight_upperGuideSwing,
                lowerGuideSwing_obj.indexScore, weight_lowerGuideSwing,
                waterGuideSwing_obj.indexScore, weight_waterGuideSwing,
                upperGuideSwingRatio_obj.indexScore, weight_upperGuideSwingRatio,
                lowerGuideSwingRatio_obj.indexScore, weight_lowerGuideSwingRatio,
                waterGuideSwingRatio_obj.indexScore, weight_waterGuideSwingRatio
        );
        System.out.printf("摆度得分: %5.5f\n", swing.score);
        /*摆度各级隶属度*/
        double[] swing_cache1 = WeightingMembershipCoef.mapReduceMultiply(weight_upperGuideSwing,
                upperGuideSwing_obj.membershipDegree_normal, upperGuideSwing_obj.membershipDegree_attention,
                upperGuideSwing_obj.membershipDegree_abnormal, upperGuideSwing_obj.membershipDegree_critical);
        double[] swing_cache2 = WeightingMembershipCoef.mapReduceMultiply(weight_lowerGuideSwing,
                lowerGuideSwing_obj.membershipDegree_normal, lowerGuideSwing_obj.membershipDegree_attention,
                lowerGuideSwing_obj.membershipDegree_abnormal, lowerGuideSwing_obj.membershipDegree_critical);
        double[] swing_cache3 = WeightingMembershipCoef.mapReduceMultiply(weight_waterGuideSwing,
                waterGuideSwing_obj.membershipDegree_normal, waterGuideSwing_obj.membershipDegree_attention,
                waterGuideSwing_obj.membershipDegree_abnormal, waterGuideSwing_obj.membershipDegree_critical);
        double[] swing_cache4 = WeightingMembershipCoef.mapReduceMultiply(weight_upperGuideSwingRatio,
                upperGuideSwingRatio_obj.membershipDegree_normal, upperGuideSwingRatio_obj.membershipDegree_attention,
                upperGuideSwingRatio_obj.membershipDegree_abnormal, upperGuideSwingRatio_obj.membershipDegree_critical);
        double[] swing_cache5 = WeightingMembershipCoef.mapReduceMultiply(weight_lowerGuideSwingRatio,
                lowerGuideSwingRatio_obj.membershipDegree_normal, lowerGuideSwingRatio_obj.membershipDegree_attention,
                lowerGuideSwingRatio_obj.membershipDegree_abnormal, lowerGuideSwingRatio_obj.membershipDegree_critical);
        double[] swing_cache6 = WeightingMembershipCoef.mapReduceMultiply(weight_waterGuideSwingRatio,
                waterGuideSwingRatio_obj.membershipDegree_normal, waterGuideSwingRatio_obj.membershipDegree_attention,
                waterGuideSwingRatio_obj.membershipDegree_abnormal, waterGuideSwingRatio_obj.membershipDegree_critical);
        double[] swingMembership = WeightingMembershipCoef.mapReducePlus(swing_cache1, swing_cache2, swing_cache3, swing_cache4, swing_cache5, swing_cache6);
        System.out.println("摆度各评级隶属度: " + Arrays.toString(swingMembership));

        /*
        * 振动得分
        * */
        /*上机架水平振动（峰峰，最大）*/
        Method_CalculateDegradeIndex upperFrameHoriVib_obj = new Method_CalculateDegradeIndex();
        upperFrameHoriVib_obj.calculate(7, new String[]{"center"},
                new double[][][]{{{0, 6, 9, 12}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("上机架水平振动（峰峰，最大）,指标得分: %3.2f\n", upperFrameHoriVib_obj.indexScore);
        /*下机架水平振动（峰峰，最大）*/
        Method_CalculateDegradeIndex lowerFrameHoriVib_obj = new Method_CalculateDegradeIndex();
        lowerFrameHoriVib_obj.calculate(5, new String[]{"center"},
                new double[][][]{{{0, 4, 9, 12}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("下机架水平振动（峰峰，最大）,指标得分: %3.2f\n", lowerFrameHoriVib_obj.indexScore);
        /*顶盖水平振动（峰峰，最大）*/
        Method_CalculateDegradeIndex headCoverHoriVib_obj = new Method_CalculateDegradeIndex();
        headCoverHoriVib_obj.calculate(6, new String[]{"center"},
                new double[][][]{{{0, 5, 9, 35}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("顶盖水平振动（峰峰，最大）,指标得分: %3.2f\n", headCoverHoriVib_obj.indexScore);
        /*上机架垂直振动（峰峰，最大）*/
        Method_CalculateDegradeIndex upperFrameVertVib_obj = new Method_CalculateDegradeIndex();
        upperFrameVertVib_obj.calculate(5, new String[]{"center"},
                new double[][][]{{{0, 3, 7, 25}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("上机架垂直振动（峰峰，最大）,指标得分: %3.2f\n", upperFrameVertVib_obj.indexScore);
        /*下机架垂直振动（峰峰，最大）*/
        Method_CalculateDegradeIndex lowerFrameVertVib_obj = new Method_CalculateDegradeIndex();
        lowerFrameVertVib_obj.calculate(4, new String[]{"center"},
                new double[][][]{{{0, 3, 7, 12}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("下机架垂直振动（峰峰，最大）,指标得分: %3.2f\n", lowerFrameVertVib_obj.indexScore);
        /*顶盖垂直振动（峰峰）*/
        Method_CalculateDegradeIndex headCoverVertVib_obj = new Method_CalculateDegradeIndex();
        headCoverVertVib_obj.calculate(15, new String[]{"center"},
                new double[][][]{{{0, 3, 7, 25}, {1}, {100, 70, 50, 30}, {0.2, 0.4, 0.6, 0.8}}});
        System.out.printf("顶盖垂直振动（峰峰）,指标得分: %3.2f\n", headCoverVertVib_obj.indexScore);
        /*振动得分*/
        double weight_upperFrameHoriVib = 0.25;
        double weight_lowerFrameHoriVib = 0.15;
        double weight_headCoverHoriVib = 0.15;
        double weight_upperFrameVertVib = 0.12;
        double weight_lowerFrameVertVib = 0.18;
        double weight_headCoverVertVib = 0.15;
        WeightingScore vibration = new WeightingScore(
                upperFrameHoriVib_obj.indexScore, weight_upperFrameHoriVib,
                lowerFrameHoriVib_obj.indexScore, weight_lowerFrameHoriVib,
                headCoverHoriVib_obj.indexScore, weight_headCoverHoriVib,
                upperFrameVertVib_obj.indexScore, weight_upperFrameVertVib,
                lowerFrameVertVib_obj.indexScore, weight_lowerFrameVertVib,
                headCoverVertVib_obj.indexScore, weight_headCoverVertVib
        );
        System.out.printf("振动得分: %5.5f\n", vibration.score);
        /*振动各级隶属度*/
        double[] vib_cache1 = WeightingMembershipCoef.mapReduceMultiply(weight_upperFrameHoriVib,
                upperFrameHoriVib_obj.membershipDegree_normal, upperFrameHoriVib_obj.membershipDegree_attention,
                upperFrameHoriVib_obj.membershipDegree_abnormal, upperFrameHoriVib_obj.membershipDegree_critical);
        double[] vib_cache2 = WeightingMembershipCoef.mapReduceMultiply(weight_lowerFrameHoriVib,
                lowerFrameHoriVib_obj.membershipDegree_normal, lowerFrameHoriVib_obj.membershipDegree_attention,
                lowerFrameHoriVib_obj.membershipDegree_abnormal, lowerFrameHoriVib_obj.membershipDegree_critical);
        double[] vib_cache3 = WeightingMembershipCoef.mapReduceMultiply(weight_headCoverHoriVib,
                headCoverHoriVib_obj.membershipDegree_normal, headCoverHoriVib_obj.membershipDegree_attention,
                headCoverHoriVib_obj.membershipDegree_abnormal, headCoverHoriVib_obj.membershipDegree_critical);
        double[] vib_cache4 = WeightingMembershipCoef.mapReduceMultiply(weight_upperFrameVertVib,
                upperFrameVertVib_obj.membershipDegree_normal, upperFrameVertVib_obj.membershipDegree_attention,
                upperFrameVertVib_obj.membershipDegree_abnormal, upperFrameVertVib_obj.membershipDegree_critical);
        double[] vib_cache5 = WeightingMembershipCoef.mapReduceMultiply(weight_lowerFrameVertVib,
                lowerFrameVertVib_obj.membershipDegree_normal, lowerFrameVertVib_obj.membershipDegree_attention,
                lowerFrameVertVib_obj.membershipDegree_abnormal, lowerFrameVertVib_obj.membershipDegree_critical);
        double[] vib_cache6 = WeightingMembershipCoef.mapReduceMultiply(weight_headCoverVertVib,
                headCoverVertVib_obj.membershipDegree_normal, headCoverVertVib_obj.membershipDegree_attention,
                headCoverVertVib_obj.membershipDegree_abnormal, headCoverVertVib_obj.membershipDegree_critical);
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
