package com.easyway.java.algorithm;
/**
 * 
 * @author longgangbai
 *
 */
public  class BitArray {
    private int[] bits = null;
    private long length;
    private final  int[] bitValue = {
            0x80000000,
            0x40000000,
            0x20000000,
            0x10000000,
            0x08000000,
            0x04000000,
            0x02000000,
            0x01000000,
            0x00800000,
            0x00400000,
            0x00200000,
            0x00100000,
            0x00080000,
            0x00040000,
            0x00020000,
            0x00010000,
            0x00008000,
            0x00004000,
            0x00002000,
            0x00001000,
            0x00000800,
            0x00000400,
            0x00000200,
            0x00000100,
            0x00000080,
            0x00000040,
            0x00000020,
            0x00000010,
            0x00000008,
            0x00000004,
            0x00000002,
            0x00000001
    };

    public BitArray(long length){
            if(length<0){
                    throw new IllegalArgumentException("length must be above zero!");
            }
            bits = new int[(int) (length/32+((length%32)>0?1:0))];
            this.length = length;
    }

    public int getBit(long index){
            if(index<0 || index>length) {
                    throw new IllegalArgumentException("length value illegal!");
            }

            int intData=(int) bits[(int) (index/32)];
            return ((intData & bitValue[(int) (index%32)])>>>(32-index%32-1));
    }

    public void setBit(long index,int value) {
            if(index<0||index>length) {
                    throw new IllegalArgumentException("length value illegal!");
            }

            if(value!=1&&value!=0){
                    throw new IllegalArgumentException("value must be 1 or 0!");
            }

            int intData = (int) bits[(int) (index/32)];

            if(value==1) {
                    bits[(int) (index/32)] = intData | bitValue[(int) (index%32)];
            }else{
                    bits[(int) (index/32)] = intData & ~bitValue[(int) (index%32)];
            }
    }

    public long getLength() {
            return length;
    }

    public static void main(String[] args){
            BitArray bitArray = new BitArray(1100000000L);
            bitArray.setBit(1100000000L-1,1);
            System.out.println(bitArray.getBit(1100000000L-1));
    }
}