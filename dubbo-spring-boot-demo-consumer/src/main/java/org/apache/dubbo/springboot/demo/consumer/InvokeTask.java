package org.apache.dubbo.springboot.demo.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.springboot.demo.AsyncService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;


/**
 * Consumer异步
 */
@Component
public class InvokeTask implements CommandLineRunner {


    @DubboReference
    private AsyncService asyncService;

    @Override
    public void run(String... args) throws Exception {
        // 调用异步接口
        CompletableFuture<String> future1 = asyncService.asyncInvoke("async call request");
        future1.whenComplete((v, t) -> {
           if (t != null) {
               t.printStackTrace();
           } else {
               System.out.println("AsyncTask Response-1: " + v);
           }
        });
        // 两次调用并顺序返回
        CompletableFuture<String> future2 = asyncService.asyncInvoke("async call request2");
        future2.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("AsyncTask Response-2: " + v);
            }
        });
        //consumer异步调用
        CompletableFuture<String> future3 =  CompletableFuture.supplyAsync(() -> {
            return asyncService.invoke("invoke call request3");
        });
        future3.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println("AsyncTask Response-3: " + v);
            }
        });

        System.out.println("AsyncTask Executed before response return.");
    }
}
