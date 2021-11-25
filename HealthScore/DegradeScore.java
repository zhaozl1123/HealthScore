package HealthScore;


/**
 * 参见{@link HealthScore.DegradeScore#DegradeScore}
 */
public class DegradeScore {
    public double membershipDegree_normal;
    public double membershipDegree_attention;
    public double membershipDegree_abnormal;
    public double membershipDegree_critical;
    public double score_normal;
    public double score_attention;
    public double score_abnormal;
    public double score_critical;
    public double degradeIndex_normal;
    public double degradeIndex_attention;
    public double degradeIndex_abnormal;
    public double degradeIndex_critical;
    public double score;

    /**
     * 根据定义的测点各状态下的得分及各状态的标准指标劣化度，计算指标得分
     *
     * <p style="color: black">************************</p>
     * <p style="color: black">
     *     1.得分使用类属性score调用
     * </p>
     * <p style="color: black">************************</p>
     *
     * @param  _standardScores 按照["正常","注意","异常","严重"]进行排序的分值,如[100, 80, 60, 40]
     * @param  _degradeIndexes 按照["正常","注意","异常","严重"]进行排序的指标值,如[0.2， 0.4， 0.6， 0.8]
     * @param  _degradeIndex 使用{@link HealthScore.DegradeIndex#degradeIndex}计算得出的指标劣化度
     */
    public DegradeScore(double[] _standardScores, double[] _degradeIndexes, double _degradeIndex) {
        score_normal = _standardScores[0];
        score_attention = _standardScores[1];
        score_abnormal = _standardScores[2];
        score_critical = _standardScores[3];
        degradeIndex_normal = _degradeIndexes[0];
        degradeIndex_attention = _degradeIndexes[1];
        degradeIndex_abnormal = _degradeIndexes[2];
        degradeIndex_critical = _degradeIndexes[3];
        membershipDegree_normal = 1 - min(new double[]{max(new double[]{(_degradeIndex - degradeIndex_normal) / (degradeIndex_attention - degradeIndex_normal), 0}), 1});
        membershipDegree_attention = max(new double[]{min(new double[]{(_degradeIndex - degradeIndex_normal) / (degradeIndex_attention - degradeIndex_normal), (degradeIndex_abnormal - _degradeIndex) / (degradeIndex_attention - degradeIndex_normal)}), 0});
        membershipDegree_abnormal = max(new double[]{min(new double[]{(_degradeIndex - degradeIndex_attention) / (degradeIndex_abnormal - degradeIndex_attention), (degradeIndex_critical - _degradeIndex) / (degradeIndex_abnormal - degradeIndex_attention)}), 0});
        membershipDegree_critical = min(new double[]{max(new double[]{(_degradeIndex - degradeIndex_abnormal) / (degradeIndex_critical - degradeIndex_abnormal), 0}), 1});
        score = score_normal * membershipDegree_normal + score_attention * membershipDegree_attention + score_abnormal * membershipDegree_abnormal + score_critical * membershipDegree_critical;

    }


    private static double min(double[] _array) {
        double _cache = _array[0];
        for (int i = 1; i < _array.length; i++) {
            if (_array[i] < _cache) {
                _cache = _array[i];
            }
        }
        return _cache;
    }

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
                new double[] {0.2, 0.4, 0.6, 0.8}, 0.371);
        System.out.println(cache.score);
    }
}

