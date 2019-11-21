package com.easyway.rxjava;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import javafx.util.Callback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import java.util.concurrent.Callable;

public class CallBackTest {

    public static  String getUser( String userId, Callable<String> callback) throws Exception
    {
        System.out.println("userId="+userId);
       String result = callback.call();
      return result;
    }

    public static void main(String args[])throws Exception {
        final String userId="abc";
        String result = getUser(userId, new Callable<String>() {
           public  String  call() throws Exception
            {
                String tmp= userId+ "goto next";
                System.out.println("tmp="+tmp);
                return tmp;
            }

        });
    }
}
