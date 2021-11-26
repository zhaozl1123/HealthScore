import HealthScore.*;
import com.google.gson.Gson;


public class run_Membership_IndexScore_Cal {
    public run_Membership_IndexScore_Cal(){}

    public static void main(String[] args) {
        DegradeDegreeParams params = new Gson().fromJson(args[0], DegradeDegreeParams.class);

        double[] standardScores = new double[]{params.standardScores.normal, params.standardScores.attention, params.standardScores.abnormal, params.standardScores.critical};
        double[] degradeIndexes = new double[]{params.degradeIndexes.normal, params.degradeIndexes.attention, params.degradeIndexes.abnormal, params.degradeIndexes.critical};

        DegradeFunctionParams monoParam_airGapMiniAvg = new DegradeFunctionParams();
        DegradeFunctionParams centParam_airGapMiniAvg = new DegradeFunctionParams();

        int trigger = 0;

        try{
            monoParam_airGapMiniAvg.setMethodName("monotonic");
            monoParam_airGapMiniAvg.setExpressionParams(new double[]{
                    params.monotonic.optimalValue,
                    params.monotonic.upperLimit
            });
            monoParam_airGapMiniAvg.setParticipateCoef(params.monotonic.participateCoef);
            trigger += 1;
        } catch (Exception ignored) {}

        try{
            centParam_airGapMiniAvg.setMethodName("center");
            centParam_airGapMiniAvg.setExpressionParams(new double[]{
                    params.centerOptimal.lowerLimit,
                    params.centerOptimal.optimalLowerLimit,
                    params.centerOptimal.optimalUpperLimit,
                    params.centerOptimal.upperLimit
            });
            centParam_airGapMiniAvg.setParticipateCoef(params.centerOptimal.participateCoef);
            trigger += 2;
        } catch (Exception ignored) {}

        Method_CalculateDegradeIndex airGapMiniAvg_obj = new Method_CalculateDegradeIndex();

        switch (trigger){
            case 3:
                airGapMiniAvg_obj.calculate(params.measured, standardScores, degradeIndexes,
                        monoParam_airGapMiniAvg, centParam_airGapMiniAvg);
                break;
            case 2:
                airGapMiniAvg_obj.calculate(params.measured, standardScores, degradeIndexes, centParam_airGapMiniAvg);
                break;
            case 1:
                airGapMiniAvg_obj.calculate(params.measured, standardScores, degradeIndexes, monoParam_airGapMiniAvg);
                break;
        }

        System.out.printf("%5.5f, %5.5f, %5.5f, %5.5f, %5.5f", airGapMiniAvg_obj.indexScore,
                airGapMiniAvg_obj.membershipDegree_normal,
                airGapMiniAvg_obj.membershipDegree_attention,
                airGapMiniAvg_obj.membershipDegree_abnormal,
                airGapMiniAvg_obj.membershipDegree_critical);
    }
}
