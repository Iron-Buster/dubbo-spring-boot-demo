package org.apache.dubbo.springboot.demo;


import java.util.concurrent.CompletableFuture;

public interface AsyncService {

    // consumer 同步|异步
    // provider 同步|异步
    // 共有4种组合方式


    /**
     * 同步调用方法
     * @param param
     * @return
     */
    String invoke(String param);


    /**
     * 异步调用方法
     * @param param
     * @return
     */
    CompletableFuture<String> asyncInvoke(String param);



    String sayHello(String name);
}
