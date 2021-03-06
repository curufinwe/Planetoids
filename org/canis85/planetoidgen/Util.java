/*
 * Copyright (c) 2012 Eugen "Niphred" Beck
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.canis85.planetoidgen;

import java.util.Map;
import java.util.Random;

/**
 *
 * @author Niphred < niphred@curufinwe.org >
 */
public class Util {
  public static <T> T sample(Random rnd, Map<T, Double> m) {
    return sample(rnd, m, true);
  }

  public static <T> T sample(Random rnd, Map<T, Double> m, boolean normalize) {
    double sum = 0.0;

    if (normalize) {
      for (T key: m.keySet()) {
        sum += Math.abs(m.get(key));
      }
    }

    if (sum == 0.0 || !normalize) {
      sum = 1.0;
    }

    double goal = rnd.nextDouble();

    for (T key: m.keySet()) {
      double prob = Math.abs(m.get(key)) / sum;
      goal -= prob;
      if (goal <= 0.0) {
        return key;
      }
    }

    return null;
  }

    public static <K,V> String mapToString(Map<K, V> m) {
      StringBuilder sb = new StringBuilder();

      for (K key: m.keySet()) {
        sb.append(key.toString());
        sb.append(" => ");
        sb.append(m.get(key).toString());
        sb.append("\n");
      }

      return sb.toString();
    }

}
