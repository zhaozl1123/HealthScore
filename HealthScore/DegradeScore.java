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
     * @param  _normalScore "正常"分值
     * @param  _attentionScore "注意"分值
     * @param  _abnormalScore "异常"分值
     * @param  _criticalScore "严重"分值
     * @param  _degradeIndex_normal "正常"标准指标劣化度
     * @param  _degradeIndex_attention "注意"标准指标劣化度
     * @param  _degradeIndex_abnormal "异常"标准指标劣化度
     * @param  _degradeIndex_critical "严重"标准指标劣化度
     * @param  _degradeIndex 使用{@link HealthScore.DegradeIndex#degradeIndex}计算得出的指标劣化度
     */
    public DegradeScore(double _normalScore, double _attentionScore, double _abnormalScore, double _criticalScore,
                        double _degradeIndex_normal, double _degradeIndex_attention, double _degradeIndex_abnormal, double _degradeIndex_critical,
                        double _degradeIndex) {
        score_normal = _normalScore;
        score_attention = _attentionScore;
        score_abnormal = _abnormalScore;
        score_critical = _criticalScore;
        membershipDegree_normal = 1 - min(new double[]{max(new double[]{(_degradeIndex - _degradeIndex_normal) / (_degradeIndex_attention - _degradeIndex_normal), 0}), 1});
        membershipDegree_attention = max(new double[]{min(new double[]{(_degradeIndex - _degradeIndex_normal) / (_degradeIndex_attention - _degradeIndex_normal), (_degradeIndex_abnormal - _degradeIndex) / (_degradeIndex_attention - _degradeIndex_normal)}), 0});
        membershipDegree_abnormal = max(new double[]{min(new double[]{(_degradeIndex - _degradeIndex_attention) / (_degradeIndex_abnormal - _degradeIndex_attention), (_degradeIndex_critical - _degradeIndex) / (_degradeIndex_abnormal - _degradeIndex_attention)}), 0});
        membershipDegree_critical = min(new double[]{max(new double[]{(_degradeIndex - _degradeIndex_abnormal) / (_degradeIndex_critical - _degradeIndex_abnormal), 0}), 1});
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
        DegradeScore cache = new DegradeScore(100, 70, 50, 30,
                0.2, 0.4, 0.6, 0.8, 0.371);
        System.out.println(cache.score);
    }
}

