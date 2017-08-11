package com.easyway.java.algorithm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** Optimized implementation of a vector of bits.  This is more-or-less like
  java.util.BitSet, but also includes the following:
  

  
a count() method, which efficiently computes the number of one bits;

  
optimized read from and write to disk;

  
inlinable get() method;

  


  @author Doug Cutting
  @version $Id: BitVector.java,v 1.4 2004/03/29 22:48:05 cutting Exp $
  */
public final class BitVector {

  private byte[] bits;
  private int size;
  private int count = -1;

  /** Constructs a vector capable of holding n bits. */
  public BitVector(int n) {
    size = n;
    bits = new byte[(size >> 3) + 1];
  }

  /** Sets the value of bit to one. */
  public final void set(int bit) {
    bits[bit >> 3] |= 1 << (bit & 7);
    count = -1;
  }

  /** Sets the value of bit to zero. */
  public final void clear(int bit) {
    bits[bit >> 3] &= ~(1 << (bit & 7));
    count = -1;
  }

  /** Returns true if bit is one and
    false if it is zero. */
  public final boolean get(int bit) {
    return (bits[bit >> 3] & (1 << (bit & 7))) != 0;
  }

  /** Returns the number of bits in this vector.  This is also one greater than
    the number of the largest valid bit number. */
  public final int size() {
    return size;
  }

  /** Returns the total number of one bits in this vector.  This is efficiently
    computed and cached, so that, if the vector is not changed, no
    recomputation is done for repeated calls. */
  public final int count() {
    // if the vector has been modified
    if (count == -1) {
      int c = 0;
      int end = bits.length;
      for (int i = 0; i < end; i++)
        c += BYTE_COUNTS[bits[i] & 0xFF];	  // sum bits per byte
      count = c;
    }
    return count;
  }

  private static final byte[] BYTE_COUNTS = {	  // table of bits/byte
    0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4,
    1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
    1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
    2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
    1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
    2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
    2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
    3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
    1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5,
    2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
    2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
    3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
    2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6,
    3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
    3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7,
    4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8
  };


  

}