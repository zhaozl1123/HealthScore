public class DegradeDegreeParams {
    public StandardScores standardScores;
    public DegradeIndexes degradeIndexes;
    public double measured;
    public Monotonic monotonic;
    public CenterOptimal centerOptimal;

    public class Monotonic{
        double participateCoef;
        double optimalValue;
        double upperLimit;
    }

    public class CenterOptimal{
        double participateCoef;
        double lowerLimit;
        double optimalLowerLimit;
        double optimalUpperLimit;
        double upperLimit;
    }

    public class StandardScores{
        double normal;
        double attention;
        double abnormal;
        double critical;
    }

    public class DegradeIndexes{
        double normal;
        double attention;
        double abnormal;
        double critical;
    }
}
