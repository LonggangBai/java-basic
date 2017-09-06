package com.easyway.java.rpc.secure;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class RSADoubleToken {

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
        //第一步建立连接
        Client client=new Client();
        Server server=new Server();
        //客户端
        String cpublickey=client.getCpublicKey();
        //服务端
        String spublickey=server.getSpublicKey();
        String encrySpublicKey=server.encryptByPublicKey(spublickey,cpublickey);
        //客户端
        String cprivatekey=client.getCpriveKey();
        String cspublickey=client.decryptByPrivateKey(encrySpublicKey,cprivatekey);
        String encrptyKey=client.encryptRandom(client.getRandomKey(), cspublickey);
        //服务单
        String decryptValue=server.encryptByPublicKey(encrptyKey,spublickey);

        System.out.print("Server:"+decryptValue);



    }


    static class Client{
        private static  RSACoderHelper  rsaCoderHelper=new RSACoderHelper();
       // private static  DESCoderHelper  descCoderHelper=new DESCoderHelper();
        private String cpublicKey;
        private String cpriveKey;
        private String spublickey;
        private String saltKey="abcdefgh";
        private String randomKey;




        public Client() throws Exception {
            Map map = rsaCoderHelper.initKey();
            cpublicKey = rsaCoderHelper.getStringPublicKey(map);
            cpriveKey = rsaCoderHelper.getStringPrivateKey(map);
    //		System.out.println("公钥为:"+cpublicKey);

        }


        public String getRandomKey() throws Exception {
            return StringUtils.getRandomNumAndStr(8);
        }

        public String encryptRandom(String data,String key) throws Exception {
            return rsaCoderHelper.encryptByPublicKeyString(data,key);
        }

        public String getCpublicKey(){
            return cpublicKey;
        }
        public String getCpriveKey(){
            return cpriveKey;
        }

        /**
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public String decryptByPrivateKey(String data,String key) throws Exception {
           return rsaCoderHelper.decryptByPrivateKey(data,key);
        }




    }
    static class Server{
        private static  RSACoderHelper  rsaCoderHelper=new RSACoderHelper();
        private static  DESCoderHelper  descCoderHelper=new DESCoderHelper();
        private String spublicKey;
        private String spriveKey;
        private String cpublicKey;
        private String saltKey="abcdefgh";
        private String randomKey;
        private String randomValue=null;
        public Server() throws Exception {
            Map map = rsaCoderHelper.initKey();
            spublicKey = rsaCoderHelper.getStringPublicKey(map);
            spriveKey = rsaCoderHelper.getStringPrivateKey(map);
            //		System.out.println("公钥为:"+cpublicKey);
        }
        public String getSpublicKey(){
            return spublicKey;
        }

        /**
         *
         * @param data
         * @param key
         * @return
         * @throws Exception
         */
        public String  encryptByPublicKey(String data,String key) throws Exception {
            cpublicKey= rsaCoderHelper.encryptByPublicKeyString(data,key);
            return cpublicKey;
        }
        public String decryptRandomByPublicKey(String data,String key) throws Exception {
            return rsaCoderHelper.encryptByPublicKeyString(data,key);
        }


    }
}
