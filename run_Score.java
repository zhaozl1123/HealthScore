import HealthScore.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class run_Score {
    public static void main(String[] arg) {
//        String args = "[{'weight':0.1,'indexScore':20},{'weight':0.2,'indexScore':2}]";
        JSONArray json_array = new JSONArray(arg[0]);
        int arrayLength = json_array.length();
        double[] pairs = new double[arrayLength*2];
        for (int i = 0;i<arrayLength;i++){
            JSONObject _obj = json_array.getJSONObject(i);
            pairs[2*i] = Double.parseDouble(_obj.get("weight").toString());
            pairs[2*i+1] = Double.parseDouble(_obj.get("indexScore").toString());
        }
        System.out.printf("%5.5f\n", new WeightingScore(pairs).score);
    }
}
