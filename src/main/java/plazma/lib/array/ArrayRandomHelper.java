/*
 * Copyright (C) 2012-2023 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package plazma.lib.array;

import java.util.function.Function;

import plazma.lib.num.NumLib;

public class ArrayRandomHelper {

    private ArrayRandomHelper() {
    }

    static void checkLength(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length must be > 0");
        }
    }

    //// int

    // 100
    static int[] _randomIntArray(int length) {
        return _randomIntArray(100, length, 0, 0, null);
    }

    // 110
    static int[] _randomIntArray(int length, int max) {
        return _randomIntArray(110, length, 0, max, null);
    }

    // 120
    static int[] _randomIntArray(int length, int min, int max) {
        return _randomIntArray(120, length, min, max, null);
    }

    // 101
    static int[] _randomIntArray(int length, Function<Integer, Integer> mapper) {
        return _randomIntArray(101, length, 0, 0, mapper);
    }

    // 111
    static int[] _randomIntArray(int length, int max, Function<Integer, Integer> mapper) {
        return _randomIntArray(111, length, 0, max, mapper);
    }

    // 121
    static int[] _randomIntArray(int length, int min, int max, Function<Integer, Integer> mapper) {
        return _randomIntArray(121, length, min, max, mapper);
    }

    //

    static int[] _randomIntArray(int mode, int length, int min, int max, Function<Integer, Integer> mapper) {
        checkLength(length);

        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = _randomInt(mode, min, max, mapper);
        }
        return array;
    }

    //// float

    // 100
    static float[] _randomFloatArray(int length) {
        return _randomFloatArray(100, length, 0f, 0f, null);
    }

    // 110
    static float[] _randomFloatArray(int length, float max) {
        return _randomFloatArray(110, length, 0f, max, null);
    }

    // 120
    static float[] _randomFloatArray(int length, float min, float max) {
        return _randomFloatArray(120, length, min, max, null);
    }

    // 101
    static float[] _randomFloatArray(int length, Function<Float, Float> mapper) {
        return _randomFloatArray(101, length, 0f, 0f, mapper);
    }

    // 111
    static float[] _randomFloatArray(int length, float max, Function<Float, Float> mapper) {
        return _randomFloatArray(111, length, 0f, max, mapper);
    }

    // 121
    static float[] _randomFloatArray(int length, float min, float max, Function<Float, Float> mapper) {
        return _randomFloatArray(121, length, min, max, mapper);
    }

    //

    static float[] _randomFloatArray(int mode, int length, float min, float max, Function<Float, Float> mapper) {
        checkLength(length);

        float[] array = new float[length];
        for (int i = 0; i < length; i++) {
            array[i] = _randomFloat(mode, min, max, mapper);
        }
        return array;
    }

    //// double

    // 100
    static double[] _randomDoubleArray(int length) {
        return _randomDoubleArray(100, length, 0d, 0d, null);
    }

    // 110
    static double[] _randomDoubleArray(int length, double max) {
        return _randomDoubleArray(110, length, 0d, max, null);
    }

    // 120
    static double[] _randomDoubleArray(int length, double min, double max) {
        return _randomDoubleArray(120, length, min, max, null);
    }

    // 101
    static double[] _randomDoubleArray(int length, Function<Double, Double> mapper) {
        return _randomDoubleArray(101, length, 0d, 0d, mapper);
    }

    // 111
    static double[] _randomDoubleArray(int length, double max, Function<Double, Double> mapper) {
        return _randomDoubleArray(111, length, 0d, max, mapper);
    }

    // 121
    static double[] _randomDoubleArray(int length, double min, double max, Function<Double, Double> mapper) {
        return _randomDoubleArray(121, length, min, max, mapper);
    }

    //

    static double[] _randomDoubleArray(int mode, int length, double min, double max, Function<Double, Double> mapper) {
        checkLength(length);

        double[] array = new double[length];
        for (int i = 0; i < length; i++) {
            array[i] = _randomDouble(mode, min, max, mapper);
        }
        return array;
    }

    ////

    // 100 - ()
    // 110 - (max)
    // 120 - (min, max)
    //
    // 101 - (mapper)
    // 111 - (max, mapper)
    // 121 - (min, max, mapper)
    static int _randomInt(int mode, int min, int max, Function<Integer, Integer> mapper) {
        if (mode == 100) {
            return NumLib.randomInt();
        } else if (mode == 110) {
            return NumLib.randomInt(max);
        } else if (mode == 120) {
            return NumLib.randomInt(min, max);
        } else if (mode == 101) {
            return NumLib.randomInt(mapper);
        } else if (mode == 111) {
            return NumLib.randomInt(max, mapper);
        } else if (mode == 121) {
            return NumLib.randomInt(min, max, mapper);
        }
        return 0;
    }

    // 100 - ()
    // 110 - (max)
    // 120 - (min, max)
    //
    // 101 - (mapper)
    // 111 - (max, mapper)
    // 121 - (min, max, mapper)
    static float _randomFloat(int mode, float min, float max, Function<Float, Float> mapper) {
        if (mode == 100) {
            return NumLib.randomFloat();
        } else if (mode == 110) {
            return NumLib.randomFloat(max);
        } else if (mode == 120) {
            return NumLib.randomFloat(min, max);
        } else if (mode == 101) {
            return NumLib.randomFloat(mapper);
        } else if (mode == 111) {
            return NumLib.randomFloat(max, mapper);
        } else if (mode == 121) {
            return NumLib.randomFloat(min, max, mapper);
        }
        return 0f;
    }

    // 100 - ()
    // 110 - (max)
    // 120 - (min, max)
    //
    // 101 - (mapper)
    // 111 - (max, mapper)
    // 121 - (min, max, mapper)
    static double _randomDouble(int mode, double min, double max, Function<Double, Double> mapper) {
        if (mode == 100) {
            return NumLib.randomDouble();
        } else if (mode == 110) {
            return NumLib.randomDouble(max);
        } else if (mode == 120) {
            return NumLib.randomDouble(min, max);
        } else if (mode == 101) {
            return NumLib.randomDouble(mapper);
        } else if (mode == 111) {
            return NumLib.randomDouble(max, mapper);
        } else if (mode == 121) {
            return NumLib.randomDouble(min, max, mapper);
        }
        return 0d;
    }

}
