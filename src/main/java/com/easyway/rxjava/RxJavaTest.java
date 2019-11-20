package com.easyway.rxjava;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;

public class RxJavaTest {
    private final static Logger Log = LoggerFactory.getLogger(RxJavaTest.class);

    public static void main(String args[]) {
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /**
         * 1) 创建 Observer
         * Observer 即观察者，它决定事件触发的时候将有怎样的行为。 RxJava 中的 Observer 接口的实现方式：
         */
        Observer<String> observer = new Observer<String>() {
            @Override
            public  void onSubscribe(Disposable d){
                 d.dispose();
            }
            @Override
            public void onNext(String s) {
                Log.debug("Item: " + s);
            }

            public void onComplete() {
                Log.debug("Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.debug("Error!");
            }


        };

        /**
         * 除了 Observer 接口之外，RxJava 还内置了一个实现了 Observer 的抽象类：Subscriber。 Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的：
         **/
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.debug("Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.debug("Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.debug("Error!");
            }
        };


        /**
         * 创建 Observable
         * Observable 即被观察者，它决定什么时候触发事件以及触发怎样的事件。 RxJava 使用 create() 方法来创建一个 Observable ，并为它定义事件触发规则：
         */

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });


        //Subscribe (订阅)
        //创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了。代码形式很简单：

//        observable.subscribe(observer);
       // 或者：
        observable.subscribe(subscriber);



        //create() 方法是 RxJava 最基本的创造事件序列的方法。基于这个方法， RxJava 还提供了一些方法用来快捷创建事件队列，例如：
        //
        //just(T...): 将传入的参数依次发送出来。
        Observable observable1 = Observable.just("Hello", "Hi", "Aloha");
// 将会依次调用：
// onNext("Hello");
// onNext("Hi");
// onNext("Aloha");
// onCompleted();


//from(T[]) / from(Iterable<? extends T>) : 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来。
        String[] words = {"Hello", "Hi", "Aloha"};
        Observable observable2 = Observable.from(words);
// 将会依次调用：
// onNext("Hello");
// onNext("Hi");
// onNext("Aloha");
// onCompleted();



//
//        Action1<String> onNextAction = new Action1<String>() {
//            // onNext()
//            @Override
//            public void call(String s) {
//                Log.d(tag, s);
//            }
//        };
//        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
//            // onError()
//            @Override
//            public void call(Throwable throwable) {
//                // Error handling
//            }
//        };
//        Action0 onCompletedAction = new Action0() {
//            // onCompleted()
//            @Override
//            public void call() {
//                Log.d(tag, "completed");
//            }
//        };
//
//// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
//        observable.subscribe(onNextAction);
//// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
//        observable.subscribe(onNextAction, onErrorAction);
//// 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
//        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);



    }
}
