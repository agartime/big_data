package org.agartime.utad.storm.algorithm;

import java.nio.ByteBuffer;

/**
 * Created by agartime on 26/04/14.
 *
 * The Flajolet-Martin algorithm
 * Source: http://ravi-bhide.blogspot.com.es/2011/04/flajolet-martin-algorithm.html
 * This is an informal description. Formal treatment can be found in the original paper listed in the reference section.
 * 1. Create a bit vector (bit array) of sufficient length $L$, such that $2^L>n$, the number of elements in the stream.
 *    Usually a 64-bit vector is sufficient since $2^{64}$ is quite large for most purposes.
 * 2. The i-th bit in this vector/array represents whether we have seen a hash function value whose binary
 *     representation ends in $0^i$. So initialize each bit to 0.
 * 3. Generate a good, random hash function that maps input (usually strings) to natural numbers.
 * 4. Read input. For each word, hash it and determine the number of trailing zeros. If the number of trailing zeros is
 *    k, set the k-th bit in the bit vector to 1.
 * 5. Once input is exhausted, get the index of the first 0 in the bit array (call this R). By the way, this is just the
 *    number of consecutive 1s (i.e. we have seen 0, 00, ...,  as the output of the hash function) plus one.
 * 6. Calculate the number of unique words as $2^R/\phi$, where $\phi$ is 0.77351. A proof for this can be found in the
 *    original paper listed in the reference section.
 * 7. The standard deviation of R is a constant: $\sigma(R)=1.12$. In other words, R can be off by about 1 (for 68% of
 *    the observations, off  by 2 for about 95% of the observations, off by 3 for 99.7% of the observations using the
 *    Empirical rule of statistics). This implies that our count can be off by a factor of 2 (for 68% of the
 *    observations, off by 4 for 95% of the observations and so on).
 */

public class FlajoletMartin {
    public final static int N = 64;
    final static float SIGMA = 0.77531f;
    final static long buckets = Math.round(Math.pow(2,N));

    public static long getNumberOfUniqueElementsFromArray(boolean [] array) {
        int firstZeroIndex = getFirstZeroIndex(array);
        return Math.round(Math.pow(2, firstZeroIndex) / SIGMA);
    }

    private static long hash(String element) {
        return element.hashCode() % buckets;
    }

    public static int getTrailingZeroes(String element) {
        return getTrailingZeroes(getBitRepresentation(hash(element)));
    }

    public static int getTrailingZeroes(boolean[] bitRepresentation) {
        for (int i=bitRepresentation.length-1; i>=0; i--) {
            if (bitRepresentation[i]) {
                return bitRepresentation.length-i-1;
            }
        }
        return bitRepresentation.length;
    }

    private static int getFirstZeroIndex(boolean[] bitRepresentation) {
        for (int i=0;i<bitRepresentation.length;i++) {
            if (bitRepresentation[i]) {
                return bitRepresentation.length-1-i-1;
            }
        }
        return bitRepresentation.length-1;
    }

    private static boolean[] getBitRepresentation(long value) {
        String bitRepresentation = Long.toBinaryString(value);
        boolean[] repr=new boolean[N];

        for (int i = bitRepresentation.length()-1;i>=0; i--) {
            char c = bitRepresentation.charAt(i);
            if (c == '1') {
                repr[repr.length-bitRepresentation.length()+i]=true;
            }
        }

        return repr;
    }

    private static void print(boolean[] arr) {
        for (boolean b : arr) {
            System.out.print(b?"1":"0");
        }
        System.out.println();
    }

    public static void main(String []args) {
        long d = 2273123612123251551L;
        boolean [] b = FlajoletMartin.getBitRepresentation(d);
        FlajoletMartin.print(b);
        System.out.println(FlajoletMartin.getTrailingZeroes(b));
        System.out.println(FlajoletMartin.getFirstZeroIndex(b));
        System.out.println(FlajoletMartin.getNumberOfUniqueElementsFromArray(b));
    }
}
