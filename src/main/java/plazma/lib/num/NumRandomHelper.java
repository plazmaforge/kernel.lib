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

package plazma.lib.num;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class NumRandomHelper {

    // https://stackoverflow.com/questions/40431966/what-is-the-best-way-to-generate-a-random-float-value-included-into-a-specified
    // rand.nextFloat() * (max - min) + min;

    // java.util.concurrent.ThreadLocalRandom
    // ThreadLocalRandom.current().nextDouble(min, max);

    // https://dzone.com/articles/random-number-generation-in-java

    // private static Random random = new Random();

    private NumRandomHelper() {
    }

    private static ThreadLocalRandom _random() {
        return ThreadLocalRandom.current();
    }

    ////

    static byte _randomByte() {
        return (byte) _randomInt();
    }

    static char _randomChar() {
        return (char) _randomInt();
    }

    static short _randomShort() {
        return (short) _randomInt();
    }

    // RANDOM

    // int

    static int _randomInt() {
        return _nextInt();
    }

    static int _randomInt(int max) {
        return _nextInt(max);
    }

    static int _randomInt(int min, int max) {
        return _nextInt(min, max);
    }

    static int _randomInt(Function<Integer, Integer> mapper) {
        if (mapper == null) {
            return _nextInt();
        }
        int value = _nextInt();
        Integer result = mapper.apply(value);
        if (result == null) {
            return 0;
        }
        return result.intValue();
    }

    static int _randomInt(int max, Function<Integer, Integer> mapper) {
        if (mapper == null) {
            return _nextInt(max);
        }
        int value = _nextInt(max);
        Integer result = mapper.apply(value);
        if (result == null) {
            return 0;
        }
        return result.intValue();
    }

    static int _randomInt(int min, int max, Function<Integer, Integer> mapper) {
        if (mapper == null) {
            return _nextInt(min, max);
        }
        int value = _nextInt(min, max);
        Integer result = mapper.apply(value);
        if (result == null) {
            return 0;
        }
        return result.intValue();
    }

    // long

    static long _randomLong() {
        return _nextLong();
    }

    static long _randomLong(long max) {
        return _nextLong(max);
    }

    static long _randomLong(long min, long max) {
        return _nextLong(min, max);
    }

    static long _randomLong(Function<Long, Long> mapper) {
        if (mapper == null) {
            return _nextLong();
        }
        long value = _nextLong();
        Long result = mapper.apply(value);
        if (result == null) {
            return 0;
        }
        return result.longValue();
    }

    static long _randomLong(long max, Function<Long, Long> mapper) {
        if (mapper == null) {
            return _nextLong(max);
        }
        long value = _nextLong(max);
        Long result = mapper.apply(value);
        if (result == null) {
            return 0;
        }
        return result.longValue();
    }

    static long _randomLong(long min, long max, Function<Long, Long> mapper) {
        if (mapper == null) {
            return _nextLong(min, max);
        }
        long value = _nextLong(min, max);
        Long result = mapper.apply(value);
        if (result == null) {
            return 0;
        }
        return result.longValue();
    }

    // float

    static float _randomFloat() {
        return _nextFloat();
    }

    static float _randomFloat(float max) {
        return _nextFloat(max);
    }

    static float _randomFloat(float min, float max) {
        return _nextFloat(min, max);
    }

    static float _randomFloat(Function<Float, Float> mapper) {
        if (mapper == null) {
            return _nextFloat();
        }
        float value = _nextFloat();
        Float result = mapper.apply(value);
        if (result == null) {
            return 0f;
        }
        return result.floatValue();
    }

    static float _randomFloat(float max, Function<Float, Float> mapper) {
        if (mapper == null) {
            return _nextFloat(max);
        }
        float value = _nextFloat(max);
        Float result = mapper.apply(value);
        if (result == null) {
            return 0f;
        }
        return result.floatValue();
    }

    static float _randomFloat(float min, float max, Function<Float, Float> mapper) {
        if (mapper == null) {
            return _nextFloat(min, max);
        }
        float value = _nextFloat(min, max);
        Float result = mapper.apply(value);
        if (result == null) {
            return 0f;
        }
        return result.floatValue();
    }

    // double

    static double _randomDouble() {
        return _nextDouble();
    }

    static double _randomDouble(double max) {
        return _nextDouble(max);
    }

    static double _randomDouble(double min, double max) {
        return _nextDouble(min, max);
    }

    static double _randomDouble(Function<Double, Double> mapper) {
        if (mapper == null) {
            return _nextDouble();
        }
        double value = _nextDouble();
        Double result = mapper.apply(value);
        if (result == null) {
            return 0d;
        }
        return result.doubleValue();
    }

    static double _randomDouble(double max, Function<Double, Double> mapper) {
        if (mapper == null) {
            return _nextDouble(max);
        }
        double value = _nextDouble(max);
        Double result = mapper.apply(value);
        if (result == null) {
            return 0d;
        }
        return result.doubleValue();
    }

    static double _randomDouble(double min, double max, Function<Double, Double> mapper) {
        if (mapper == null) {
            return _nextDouble(min, max);
        }
        double value = _nextDouble(min, max);
        Double result = mapper.apply(value);
        if (result == null) {
            return 0d;
        }
        return result.doubleValue();
    }

    //// NEXT: next<Primitive>

    // int

    private static int _nextInt() {
        return _random().nextInt();
    }

    private static int _nextInt(int max) {
        return _random().nextInt(max);
    }

    private static int _nextInt(int min, int max) {
        return _random().nextInt(min, max);
    }

    // long

    private static long _nextLong() {
        return _random().nextLong();
    }

    private static long _nextLong(long max) {
        return _random().nextLong(max);
    }

    private static long _nextLong(long min, long max) {
        return _random().nextLong(min, max);
    }

    // float

    private static float _nextFloat() {
        return _random().nextFloat();
    }

    private static float _nextFloat(float max) {
        return (float) _random().nextDouble(max);
    }

    private static float _nextFloat(float min, float max) {
        return (float) _random().nextDouble(min, max);
    }

    // double

    private static double _nextDouble() {
        return _random().nextDouble();
    }

    private static double _nextDouble(double max) {
        return _random().nextDouble(max);
    }

    private static double _nextDouble(double min, double max) {
        return _random().nextDouble(min, max);
    }

    ////

    private static float _float(float value, float min, float max) {
        return (value * ((max - min) /* + 1 */)) + min;
    }

}
