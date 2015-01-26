package com.easyway.java.basic.strings;

class SplitString
{

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