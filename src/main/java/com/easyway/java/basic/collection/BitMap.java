package com.easyway.java.basic.collection;

/**
 * 实现BitMap
 * <pre>
 * 
 * 
 * Bitmap算法，
问题：对40亿个数据进行排序，数据类型为 int，无相同数据。
思考：关于40亿个数据的排序，首先想如何存储呢？一个int 4个字节，也就是160亿个字节，也就是大概有16GB的数据，现在所有的计算机估计
没有这么大的内存吧，所以我们就可以文件归并排序，也可以分段读入数据在进行Qsort，但是都需要不停地读入文件，可以想象不停地读取文件硬件操作会有多么浪费时间。
 
我们这样都是用4个字节来存储了一个数据，在计算机里都是用二进制进行表示，
例如 5 ：0000 0000 0000 0000 0000 0000 0000 0101
现在引入Bitmap，所谓Bitmap就是用一个bit来表示一个数据。平时32位存储一个数据，我们可以换一种想法，用一个字节32位来存储0-31这32个数据，例如我们对2，1，5，12这四个数据进行由小到大的排序，首先把32位初始化为0，我们可以把这4个数据存储为0000 0000 0000 0000 0001 0000 0010 0110
 
我们就把32位中的分别把 2  1  5  12位置为1，然后从第0位开始遍历，看相应位是否为1，为1就进行输出，就完成了数据从小到大的排序。
 
再返回原题应用Bitmap就可以把16GB的存储空间缩小为16GB/32 = 512M,就可以大大减少读取文件的工作。直接读一次文件存入内存，然后遍历输出就完成了排序。
 
优点：既大量节省了空间，又把时间复杂度降低到O(n)。
不足：如果数据过于稀疏就会有大量无用遍历，浪费时间。

 * int中32位只能表示一个数，而用BitMap可以表示32一个数: 
int[] bitMap: 
bitMap[0]:可以表示数字0~31 比如表示0：00000000 00000000 00000000 00000000 
比如表示1 : 00000000 00000000 00000000 00000001 
bitMap[1]:可以表示数字32~63 
bitMap[2]:可以表示数字64~95 
…… 
因此要将一个数插入到bitMap中要进过三个步骤： 
1)找到所在bitMap中的index也就是bitMap数组下标 
比如我们要在第64一个位置上插入一个数据（也就是插入63） 
index = 63 >> 5 = 1，也就是说63应该插入到bitMap[1]中 
2)找到63在bitMap[1]中的偏移位置 
offset = 63 & 31 = 31说明63在bitMap[1]中32位的最高位 
3)将最高位设为1
 * </pre>
 * @author xialonglei
 * @since 2016/5/26
 */
public class BitMap {
    /** 插入数的最大长度，比如100，那么允许插入bitsMap中的最大数为99 */
    private long length;
    private static int[] bitsMap;
    private static final int[] BIT_VALUE = { 0x00000001, 0x00000002, 0x00000004, 0x00000008, 0x00000010, 0x00000020,
            0x00000040, 0x00000080, 0x00000100, 0x00000200, 0x00000400, 0x00000800, 0x00001000, 0x00002000, 0x00004000,
            0x00008000, 0x00010000, 0x00020000, 0x00040000, 0x00080000, 0x00100000, 0x00200000, 0x00400000, 0x00800000,
            0x01000000, 0x02000000, 0x04000000, 0x08000000, 0x10000000, 0x20000000, 0x40000000, 0x80000000 };
    public BitMap(long length) {
        this.length = length;
        // 根据长度算出，所需数组大小
        bitsMap = new int[(int) (length >> 5) + ((length & 31) > 0 ? 1 : 0)];
    }
    /**
     * 根据长度获取数据 比如输入63，那么实际上是确定数62是否在bitsMap中
     * 
     * @return index 数的长度
     * @return 1:代表数在其中 0:代表
     */
    public int getBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        int intData = (int) bitsMap[(int) ((index - 1) >> 5)];
        return ((intData & BIT_VALUE[(int) ((index - 1) & 31)])) >>> ((index - 1) & 31);
    }
    /**
     * @param index
     *            要被设置的值为index - 1
     */
    public void setBit(long index) {
        if (index < 0 || index > length) {
            throw new IllegalArgumentException("length value illegal!");
        }
        // 求出该index - 1所在bitMap的下标
        int belowIndex = (int) ((index - 1) >> 5);
        // 求出该值的偏移量(求余)
        int offset = (int) ((index - 1) & 31);
        int inData = bitsMap[belowIndex];
        bitsMap[belowIndex] = inData | BIT_VALUE[offset];
    }
    public static void main(String[] args) {
        BitMap bitMap = new BitMap(63);
        bitMap.setBit(63);
        System.out.println(bitMap.getBit(63));
        System.out.println(bitMap.getBit(62));
    }
}