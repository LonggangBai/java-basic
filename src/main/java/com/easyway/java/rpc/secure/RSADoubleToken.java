package com.easyway.java.rpc.secure;


import java.util.Map;

public class RSADoubleToken {
    public static final String CHAR_ENCODING = "utf-8";
     /**
      * <pre>
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
      *
      * 特点：每次通信前，进行密钥协商，一次一密

      *密钥协商过程，如下图所述，需要随机生成三次密钥，两次非对称加密密钥（公钥，私钥），一次对称加密密钥，简称安全信道建立的“三次握手”，在客户端发起安全信道建立请求后：

      * 服务端随机生成公私钥对(公钥pk1，私钥pk2)，并将公钥pk1传给客户端
      *  (注意：此时黑客能截获pk1)
      *  客户端随机生成公私钥对(公钥pk11，私钥pk22)，并将公钥pk22，通过pk1加密，传给服务端
      *  (注意：此时黑客能截获密文，也知道是通过pk1加密的，但由于黑客不知道私钥pk2，是无法解密的)
      * 服务端收到密文，用私钥pk2解密，得到pk11
      *  服务端随机生成对称加密密钥key=X，用pk11加密，传给客户端
      *  (注意：同理，黑客由密文无法解密出key)
      * 客户端收到密文，用私钥pk22解密，可到key=X
      *
      * 至此，安全信道建立完毕，后续通讯用key=X加密，以保证信息的安全性
      * </pre>
      */
     public static  void onePersonOneKey()throws Exception{
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
    public static void main(String[] args) throws Exception {
        onePersonOneKey();

    }


    /**
     *  1、客户端和服务端事先约定公私钥对，客户端持有公钥pk2，服务端持有私钥pk1
     *  2、客户端随机生成加密key，使用此加密key采用对称加密算法加密报文M为M-secret，同时将加密key通过pk2采用非对称加密算法加密成key-secret
     * 客户端将M-secret和key-secret传给服务端
     *  3、服务端通过私钥pk1解密key-secret为key，再通过key解密报文M-secret为M
     * 此过程完成，黑客拦截到key-secret是没有意义的，因为没有私钥pk1是无法解密的
     *
     */
    public static void dd()throws Exception{
        //客户端/服务端约定
        RSACoderHelper serverrsa = RSACoderHelper.getInstance();
        DESCoderHelper desCoderHelper=new DESCoderHelper();
        Map serverMap = serverrsa.initKey();
        String cpublickey = serverrsa.getStringPublicKey(serverMap);
        System.out.println("服务端公钥为:"+cpublickey);
        String cprivatekey = serverrsa.getStringPrivateKey(serverMap);
        System.out.println("服务端私钥为:"+cprivatekey);


        //客户端
        String clientKey= StringUtils.getRandomNumAndStr(8);
        System.out.println("客户端KEY为:"+clientKey);

        String request="请求信息";
        byte [] requestStr = request.getBytes(CHAR_ENCODING);

        String msecret=new String(desCoderHelper.decrypt(requestStr, clientKey.getBytes()));
                //Base64.decodeBase64(istr),CHAR_ENCODING);

        String keysecret = serverrsa.encryptByPublicKeyString(clientKey, cpublickey);
        System.out.println("客户端端msecret密文为:"+msecret);
        System.out.println("客户端端keysecret密文为:"+keysecret);

        //服务端
        String serverkey=serverrsa.decryptByPrivateKey(keysecret,cprivatekey);
        System.out.println("客户端请求KEY为:"+serverkey);
        String srequest=desCoderHelper.decryptNetString(msecret,serverkey);
        System.out.println("客户端请求信息为:"+srequest);



    }



}
