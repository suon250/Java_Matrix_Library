package util;

// purpose: when we're solving linear equation, we must determine
// whether the equation is satisfied <-> || Ax - b || = 0. (double).
// however, double is not very accurate, especially when you're doing
// boolean operation like 0.1 + 0.2 == 0.3(which will retrun false).

// Therefore, we would created a tolerance value to solve that accuracy problem
public final class NumericalUtils {

    // 默认数值容忍误差（Phase 1 足够）
    public static final double EPS = 1e-9;

    // 禁止实例化：工具类只用 static
    private NumericalUtils() {}

    // 
    public static boolean approxEqual(double a, double b) {
        return Math.abs(a - b) < EPS;
    }

    /**
     * 带自定义容忍误差的比较（给后续 Phase 2 / 测试用）
     */
    public static boolean approxEqual(double a, double b, double eps) {
        return Math.abs(a - b) < eps;
    }

    /**
     * 判断一个数是否“数值上为 0”
     * 在 pivot 判断、奇异矩阵判断中非常重要
     */
    public static boolean isZero(double x) {
        return Math.abs(x) < EPS;
    }

    /**
     * 判断一个数是否为有限值（防 NaN / Infinity）
     * 调试数值算法时非常有用
     */
    public static boolean isFinite(double x) {
        return !Double.isNaN(x) && !Double.isInfinite(x);
    }
}

