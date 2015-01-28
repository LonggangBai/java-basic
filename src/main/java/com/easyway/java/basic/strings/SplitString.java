package com.easyway.java.basic.strings;

/**
 * String和StringBuffer的区别和不同
 * 
 * <pre>
 * StingBuffer是一个可变的字符串，它可以被更改。同时StringBuffer是Thread   safe的，   你可以放心的使用.
 * 
 * 因为String被设计成一种安全的字符串，   避免了C/C++中的尴尬。因此在内部操作的时候会频繁的进行对象的交换，   因此它的效率不如StringBuffer。   
 * 如果需要频繁的进行字符串的增删操作的话最好用StringBuffer。   比如拼SQL文，   写共函。   另：   编绎器对String的+操作进行了一定的优化。
 * x   =   “a”   +   4   +   “c”
 * 会被编绎成
 * x   =   new   StringBuffer().append(“a”).append(4).append(“c”).toString()
 * 但：
 * x   =   “a”;
 * x   =   x   +   4;
 * x   =   x   +   “c”;
 * 则不会被优化。   可以看出如果在一个表达式里面进行String的多次+操作会被优化，   而多个表达式的+操作不会被优化。
 * 
 * </pre>
 * 
 * @author Administrator
 * 
 */
class SplitString {

	String SplitStr;

	int SplitByte;

	public SplitString(String str, int bytes)

	{

		SplitStr = str;

		SplitByte = bytes;

	}

	public void SplitIt()

	{

		int loopCount;

		loopCount = (SplitStr.length() % SplitByte == 0) ? (SplitStr.length() / SplitByte)
				: (SplitStr.length() / SplitByte + 1);
		for (int i = 1; i <= loopCount; i++)

		{

			if (i == loopCount) {

				System.out.println(SplitStr.substring((i - 1) * SplitByte,
						SplitStr.length()));

			} else {

				System.out.println(SplitStr.substring((i - 1) * SplitByte,
						(i * SplitByte)));

			}

		}

	}

	public static void main(String[] args)

	{

		SplitString ss = new SplitString("我ABC汉DEF", 6);

		ss.SplitIt();

	}

}