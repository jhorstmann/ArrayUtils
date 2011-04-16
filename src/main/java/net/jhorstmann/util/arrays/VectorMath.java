package net.jhorstmann.util.arrays;

public class VectorMath {

    private static final float[] AXIS_X = create(1.0f, 0.0f, 0.0f);
    private static final float[] AXIS_Y = create(0.0f, 1.0f, 0.0f);
    private static final float[] AXIS_Z = create(0.0f, 0.0f, 1.0f);

    public static float[] create() {
        return new float[3];
    }

    public static float[] create(float x, float y, float z) {
        return new float[]{x, y, z};
    }

    public static float length(float[] v1, int o1) {
        float x = v1[o1];
        float y = v1[o1 + 1];
        float z = v1[o1 + 2];
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public static void normalize(float[] v1, int o1) {
        float l = length(v1, o1);
        if (l != 0.0f) {
            float s = 1.0f/l;
            v1[o1    ] *= s;
            v1[o1 + 1] *= s;
            v1[o1 + 2] *= s;
        }
    }

    public static void normalize(FloatArray v1, int o1) {
        normalize(v1.data, o1);
    }

    public static void interpolate(float[] v1, int o1, float[] v2, int o2, float[] dst, int dsto, float alpha) {
        float alpha1 = 1.0f - alpha;
        dst[dsto    ] = alpha * v1[o1] + alpha1 * v2[o2];
        dst[dsto + 1] = alpha * v1[o1 + 1] + alpha1 * v2[o2 + 1];
        dst[dsto + 2] = alpha * v1[o1 + 2] + alpha1 * v2[o2 + 2];
    }

    public static void cross(float[] v1, int o1, float[] v2, int o2, float[] dst, int dsto) {
        float x1 = v1[o1], y1 = v1[o1 + 1], z1 = v1[o1 + 2];
        float x2 = v2[o2], y2 = v2[o2 + 1], z2 = v2[o2 + 2];
        dst[dsto    ] = y1 * z2 - z1 * y2;
        dst[dsto + 1] = z1 * x2 - x1 * z2;
        dst[dsto + 2] = x1 * y2 - y1 * x2;
    }

    public static void cross(FloatArray v1, int o1, FloatArray v2, int o2, FloatArray dst, int dsto) {
        cross(v1.data, o1, v2.data, o2, dst.data, dsto);
    }

    public static float dot(float[] v1, int o1, float[] v2, int o2) {
        return v1[o1] * v2[o2] + v1[o1 + 1] * v2[o2 + 1] + v1[o1 + 2] * v2[o2 + 2];
    }

    public static float angleRad(float[] v1, int o1, float[] v2, int o2) {
        double dot = (double)v1[o1] * v2[o2] + v1[o1 + 1] * v2[o2 + 1] + v1[o1 + 2] * v2[o2 + 2];
        return (float) Math.acos(dot);
    }

    public static float angleDeg(float[] v1, int o1, float[] v2, int o2) {
        double dot = (double)v1[o1] * v2[o2] + v1[o1 + 1] * v2[o2 + 1] + v1[o1 + 2] * v2[o2 + 2];
        return (float) Math.toDegrees(Math.acos(dot));
    }

    public static float angleDegX(float[] v1, int o1) {
        return angleDeg(v1, o1, AXIS_X, 0);
    }

    public static float angleDegY(float[] v1, int o1) {
        return angleDeg(v1, o1, AXIS_Y, 0);
    }

    public static float angleDegZ(float[] v1, int o1) {
        return angleDeg(v1, o1, AXIS_Z, 0);
    }

    public static void sub(float[] v1, int o1, float[] v2, int o2, float[] dst, int dsto) {
        dst[dsto] = v1[o1] - v2[o2];
        dst[dsto + 1] = v1[o1 + 1] - v2[o2 + 1];
        dst[dsto + 2] = v1[o1 + 2] - v2[o2 + 2];
    }

    public static void sub(FloatArray v1, int o1, FloatArray v2, int o2, float[] dst, int dsto) {
        sub(v1.data, o1, v2.data, o2, dst, dsto);
    }

    public static void add(float[] v1, int o1, float[] v2, int o2, float[] dst, int desto) {
        dst[desto] = v1[o1] + v2[o2];
        dst[desto + 1] = v1[o1 + 1] + v2[o2 + 1];
        dst[desto + 2] = v1[o1 + 2] + v2[o2 + 2];
    }

    public static void add(FloatArray v1, int o1, FloatArray v2, int o2, FloatArray dst, int dsto) {
        add(v1.data, o1, v2.data, o2, dst.data, dsto);
    }

    public static float distance(float[] v1, int o1, float[] v2, int o2) {
        float dx = v1[o1]   - v2[o2];
        float dy = v1[o1+1] - v2[o2+1];
        float dz = v1[o1+2] - v2[o2+2];
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static float distance(FloatArray v1, int o1, FloatArray v2, int o2) {
        return distance(v1.data, o1, v2.data, o2);
    }

    public static void copy(float[] src, int srco, float[] dst, int dsto) {
        dst[dsto] = src[srco];
        dst[dsto + 1] = src[srco + 1];
        dst[dsto + 2] = src[srco + 2];
    }

    public static void copy(float[] src, int srco, FloatArray dst, int dsto) {
        copy(src, srco, dst.data, dsto);
    }

    public static void copy(FloatArray src, int srco, float[] dst, int dsto) {
        copy(src.data, srco, dst, dsto);
    }

    public static void copy(FloatArray src, int srco, FloatArray dst, int dsto) {
        copy(src.data, srco, dst.data, dsto);
    }

    public static boolean equals(float[] v1, int o1, float[] v2, int o2) {
        return v1[o1] == v2[o2] && v1[o1+1] == v2[o2+1] && v1[o1+2] == v2[o2+2];
    }

    public static boolean equals(FloatArray v1, int o1, FloatArray v2, int o2) {
        return equals(v1.data, o1, v2.data, o2);
    }
}
