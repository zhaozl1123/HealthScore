import HealthScore.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;


public class run_EachLevelMembership {
    public static void main(String[] arg) {
//        String[] args = new String[]{"[{'weight': 0.6, 'membership_normal':0.1,'membership_attention':0.2,'membership_abnormal':0.2,'membership_critical':0.3},{'weight': 0.4, 'membership_normal':0.1,'membership_attention':0.2,'membership_abnormal':0.2,'membership_critical':0.3}]"};
        JSONArray json_array = new JSONArray(arg[0]);
        int arrayLength = json_array.length();
        double[] res = new double[4];
        Arrays.fill(res, 0);
        for (int i = 0;i<arrayLength;i++){
            JSONObject _obj = json_array.getJSONObject(i);
            double[] cache = WeightingMembershipCoef.mapReduceMultiply(Double.parseDouble(_obj.get("weight").toString()),
                    Double.parseDouble(_obj.get("membership_normal").toString()),
                    Double.parseDouble(_obj.get("membership_attention").toString()),
                    Double.parseDouble(_obj.get("membership_abnormal").toString()),
                    Double.parseDouble(_obj.get("membership_critical").toString()));
            res = WeightingMembershipCoef.mapReducePlus(res, cache);
        }
        System.out.println(Arrays.toString(res));
    }
}
