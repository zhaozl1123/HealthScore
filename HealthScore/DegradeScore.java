package HealthScore;

/**
 * Methods                                                                                                          <br>
 * ----------                                                                                                       <br>
 * * {@link DegradeScore#DegradeScore(double[], double[], double)}                                                  <br>
 * * {@link DegradeScore#min(double[])}                                                                             <br>
 * * {@link DegradeScore#max(double[])}                                                                             <br>
 * <p>
 * 主要功能                                                                                                          <br>
 * ----------                                                                                                       <br>
 * 1.隶属度计算                                                                                                       <br>
 * 2.指标得分计算                                                                                                     <br>
 */
public class DegradeScore {
    public double membershipDegree_normal;
    public double membershipDegree_attention;
    public double membershipDegree_abnormal;
    public double membershipDegree_critical;
    public double indexScore;

    /**
     * 根据定义的测点各状态下的得分及各状态的标准指标，计算指标得分                                                            <br>
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     * 1.指标得分使用类属性indexScore调用                                                                               <br>
     * 2.于"正常"状态下的隶属度使用类属性membershipDegree_normal调用                                                      <br>
     * 3.于"注意"状态下的隶属度使用类属性membershipDegree_attention调用                                                   <br>
     * 4.于"异常"状态下的隶属度使用类属性membershipDegree_abnormal调用                                                    <br>
     * 5.于"严重"状态下的隶属度使用类属性membershipDegree_critical调用                                                    <br>
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param _standardScores 按照["正常","注意","异常","严重"]进行排序的分值,如[100, 80, 60, 40]
     * @param _degradeIndexes 按照["正常","注意","异常","严重"]进行排序的指标值,如[0.2， 0.4， 0.6， 0.8]
     * @param _degradeIndex   使用{@link HealthScore.DegradeIndex#degradeIndex}计算得出的指标劣化度
     */
    public DegradeScore(double[] _standardScores, double[] _degradeIndexes, double _degradeIndex) {
        double score_normal = _standardScores[0];
        double score_attention = _standardScores[1];
        double score_abnormal = _standardScores[2];
        double score_critical = _standardScores[3];
        double dI_normal = _degradeIndexes[0];
        double dI_attention = _degradeIndexes[1];
        double dI_abnormal = _degradeIndexes[2];
        double dI_critical = _degradeIndexes[3];
        membershipDegree_normal = 1 - min(new double[]{max(new double[]{(_degradeIndex - dI_normal) / (dI_attention - dI_normal), 0}), 1});
        membershipDegree_attention = max(new double[]{min(new double[]{(_degradeIndex - dI_normal) / (dI_attention - dI_normal), (dI_abnormal - _degradeIndex) / (dI_attention - dI_normal)}), 0});
        membershipDegree_abnormal = max(new double[]{min(new double[]{(_degradeIndex - dI_attention) / (dI_abnormal - dI_attention), (dI_critical - _degradeIndex) / (dI_abnormal - dI_attention)}), 0});
        membershipDegree_critical = min(new double[]{max(new double[]{(_degradeIndex - dI_abnormal) / (dI_critical - dI_abnormal), 0}), 1});
        indexScore = score_normal * membershipDegree_normal +
                score_attention * membershipDegree_attention +
                score_abnormal * membershipDegree_abnormal +
                score_critical * membershipDegree_critical;

    }

    /**
     * 返回一维double数组最小值
     *
     * @param  _array 一维数组double[]
     * @return  数组最小值
     */
    private static double min(double[] _array) {
        double _cache = _array[0];
        for (int i = 1; i < _array.length; i++) {
            if (_array[i] < _cache) {
                _cache = _array[i];
            }
        }
        return _cache;
    }

    /**
     * 返回一维double数组最大值
     *
     * @param  _array 一维数组double[]
     * @return  数组最大值
     */
    private static double max(double[] _array) {
        double _cache = _array[0];
        for (int i = 1; i < _array.length; i++) {
            if (_array[i] > _cache) {
                _cache = _array[i];
            }
        }
        return _cache;
    }

    public static void main(String[] args) {
        DegradeScore cache = new DegradeScore(new double[]{100, 70, 50, 30},
                new double[]{0.2, 0.4, 0.6, 0.8}, 0.371);
        System.out.println(cache.indexScore);
    }
}

