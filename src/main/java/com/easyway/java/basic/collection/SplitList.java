package com.easyway.java.basic.collection;

import java.util.ArrayList;
import java.util.List;

public class SplitList {
	public static void main(String[] args) {
		List<Integer> dataList = new ArrayList<Integer>();
		for (int i = 0; i < 12888; i++)
			dataList.add(i);

		// 分批处理
		if (null != dataList && dataList.size() > 0) {
			int pointsDataLimit = 1000;// 限制条数
			Integer size = dataList.size();
			// 判断是否有必要分批
			if (pointsDataLimit < size) {
				int part = size / pointsDataLimit;// 分批数
				System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
				//
				for (int i = 0; i < part; i++) {
					// 1000条
					List<Integer> listPage = dataList.subList(0, pointsDataLimit);
					System.out.println(listPage);
					// 剔除
					dataList.subList(0, pointsDataLimit).clear();
				}

				if (!dataList.isEmpty()) {
					System.out.println(dataList);// 表示最后剩下的数据
				}
			} else {
				System.out.println(dataList);
			}
		} else {
			System.out.println("没有数据!!!");
		}
	}
	
	/**
     * 分割List List分割,分段函数  
     * @author bianrx
     * @date 2013.12.31
     * @param list 待分割的list
     * @param pageSize 每段list的大小
     * @return List<<List<T>> 
     */
     public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size(); //list的大小
        int page = (listSize + (pageSize-1))/ pageSize;//页数
        List<List<T>> listArray = new ArrayList<List<T>>();//创建list数组 ,用来保存分割后的list
        for(int i=0;i<page;i++) { //按照数组大小遍历
            List<T> subList = new ArrayList<T>(); //数组每一位放入一个分割后的list
            for(int j=0;j<listSize;j++) {//遍历待分割的list
                int pageIndex = ( (j + 1) + (pageSize-1) ) / pageSize;//当前记录的页码(第几页)
                if(pageIndex == (i + 1)) {//当前记录的页码等于要放入的页码时
                    subList.add(list.get(j)); //放入list中的元素到分割后的list(subList)
                }
                if( (j + 1) == ((j + 1) * pageSize) ) {//当放满一页时退出当前循环
                    break;
                }
            }
            listArray.add(subList);//将分割后的list放入对应的数组的位中
        }
        return listArray;
    }
}
