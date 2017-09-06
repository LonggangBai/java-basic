package com.easyway.java.rpc.secure;


import java.util.Map;

public class RSADoubleToken {
    public static final String CHAR_ENCODING = "utf-8";
    /**
     *
     /**
     * 权限认证
     * @return 是否校验成功
     * @author HaoXB
     * @throws Throwable
     * @date 2010-09-01
     * 处理过程:
     * 1、建立连接
     * 2、客户端生成RSA公(CPublicKey)/私(CPrivateKey)钥,并将公钥(CPublicKey)传送给服务器端
     * 3、服务器端接收客户端提供的公钥(CPublicKey),并生成新的RSA公(SPublicKey)/私(SPrivateKey)钥,将公钥(SPublicKey)传送给客户端
     * 4、客户端用服务器端提供的公钥(SPublicKey)加密授权文件，并传送给服务器端
     * 5、服务器端通过服务器端私钥(SPrivateKey)解密、并校验授权文件是否正确，如果正确则返回通过客户端公钥(CPublicKey)加密的RSA密钥，否则返回null/false
     * 6、客户端通过客户端私钥(CPrivateKey)解密服务器端返回数据获得RSA密钥
     * 7、客户端、服务器端通过RSA加密数据进行交互
     */
    public static void main(String[] args) throws Exception {
        //建立客户端和服务端连接
        //java公钥加密 私钥解密
        //客户端
        RSACoderHelper clientrsa = RSACoderHelper.getInstance();
        Map clientMap = clientrsa.initKey();
        String cpublickey = clientrsa.getStringPublicKey(clientMap);
        System.out.println("客户端公钥为:"+cpublickey);
        String cprivatekey = clientrsa.getStringPrivateKey(clientMap);
        System.out.println("客户端私钥为:"+cprivatekey);

        //服务端
        RSACoderHelper serverrsa = RSACoderHelper.getInstance();
        Map serverMap = serverrsa.initKey();
        String publickey = serverrsa.getStringPublicKey(serverMap);
        System.out.println("服务端公钥为:"+publickey);
        String privatekey = serverrsa.getStringPrivateKey(serverMap);
        System.out.println("服务端私钥为:"+privatekey);
        String inputstr= StringUtils.getRandomNumAndStr(8);
        byte [] istr = inputstr.getBytes(CHAR_ENCODING);
        System.out.println("服务端原文:"+inputstr);
        //将服务端公钥用客户端公钥加密
        String miwen = serverrsa.encryptByPublicKeyString(inputstr, cpublickey);
        System.out.println("服务端密文为:"+miwen);

        //客户端
        String clientYuanWen=clientrsa.decryptByPrivateKey(miwen,cprivatekey);
        System.out.println("客户端解密为:"+clientYuanWen);

    }



}
