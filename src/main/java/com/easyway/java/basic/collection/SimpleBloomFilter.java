package com.easyway.java.basic.collection;

import java.io.UnsupportedEncodingException;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
import java.util.BitSet;  
/**
 * 再流计算中计算UV是个相当麻烦的事情，特别数据量很大的时候，中间存储就大的吓人。最近项目中遇到分类目计算UV，UV量大概在7000W，有20w多个类目。如果使用简单的存储中间结果再去重，如果使用内存内存打不下，使用Hbase的话hbase的吞吐又不够。于是准备使用bloom近似计算UV。


写了个bloom filter的demo程序，由于uid都为数字在计算hash值时碰撞率比较搞，于是没有直接对uid使用bloom filter而是对uid 的md5值使用bloom filter：
 * @author longgangbai
 *
 */
public class  SimpleBloomFilter {  
     private static final  int  DEFAULT_SIZE  = 8 << 50000; //2<<60000 ;  
//     private static final  int [] seeds =new  int []{5,7, 11 , 13 , 31 , 37 , 61};  
     private static final int[] seeds = new int []{13,31, 131, 1313, 13131, 131313};  
     private  BitSet bits= new  BitSet(DEFAULT_SIZE);  
     private  SimpleHash[]  func=new  SimpleHash[seeds.length];  
//     private SimpleHash[]  func = new SimpleHash();  
     public static void  main(String[] args) {  
//        String value  = "stone2083@yahoo.cn" ;  
         System.out.println(DEFAULT_SIZE);  
        int count=0;  
        SimpleBloomFilter filter=new  SimpleBloomFilter();  
        for(int i = 10000;i<60000;i++){  
            String a = String.valueOf(i);  
            String value = getMD5Str(a);  
            if(!(filter.contains(value))){  
                count++;  
                filter.add(value);  
            }  
              
        }  
          
        System.out.println("result is :"+count);  
          
    }  
     public  SimpleBloomFilter() {  
         for( int  i= 0 ; i< seeds.length; i ++ ) {  
            func[i]=new  SimpleHash(DEFAULT_SIZE, seeds[i]);  
        }  
    }  
     public void  add(String value) {  
         for(SimpleHash f : func) {  
             System.out.println(f.hash(value));  
            bits.set(f.hash(value),  true );  
        }  
    }  
     public boolean  contains(String value) {  
         if(value ==null ) {  
             return false ;  
        }  
         boolean  ret  = true ;  
         for(SimpleHash f : func) {  
            ret=ret&& bits.get(f.hash(value));  
        }  
         return  ret;  
    }  
       
     private static String getMD5Str(String str) {    
         MessageDigest messageDigest = null;    
     
         try {    
             messageDigest = MessageDigest.getInstance("MD5");    
     
             messageDigest.reset();    
     
             messageDigest.update(str.getBytes("UTF-8"));    
         } catch (NoSuchAlgorithmException e) {    
             System.out.println("NoSuchAlgorithmException caught!");    
             System.exit(-1);    
         } catch (UnsupportedEncodingException e) {    
             e.printStackTrace();    
         }    
     
         byte[] byteArray = messageDigest.digest();    
     
         StringBuffer md5StrBuff = new StringBuffer();    
     
         for (int i = 0; i < byteArray.length; i++) {                
             if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)    
                 md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));    
             else    
                 md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));    
         }    
     
         return md5StrBuff.toString();    
     }    
       
     public static class SimpleHash {  
         private int  cap;  
         private int  seed;  
         public  SimpleHash( int cap, int seed) {  
             this.cap= cap;  
             this.seed =seed;  
        }  
         public int hash(String value) {  
             int  result=0 ;  
             int  len= value.length();  
             for  (int i= 0 ; i< len; i ++ ) {  
//                result =seed* result + value.charAt(i);  
                 result =seed* result + value.charAt(i);  
            }  
//             return (cap - 1 ) & (result/20);  
             return (cap - 1 ) & result;  
//             return result;  
        }  
    }  
}   