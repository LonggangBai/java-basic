package com.easyway.java.concurrent;

public class RxBusTest {
    public static void main(String[] args) throws InterruptedException {

//        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
//            @Override
//            public EventMsg apply(Object o) throws Exception {
//                return (EventMsg) o;
//            }
//        }).subscribe(new Consumer<EventMsg>() {
//            @Override
//            public void accept(EventMsg eventMsg) throws Exception {
//                if (eventMsg != null) {
//                    mTvContent.setText(eventMsg.getMsg());
//                }
//            }
//        });
//
//
//        EventMsg eventMsg = new EventMsg();
//        eventMsg.setMsg("来自第二个页面发送过来的数据 --- 修改成功");
//        RxBus.getInstance().post(eventMsg);

    }

   static  class EventMsg {
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
