package com.dubbo.custom.loadbalancer;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;

import java.net.InetAddress;
import java.util.List;
import java.util.Optional;

/**
 * @author ikun
 * @version v1.0.0
 * @since 2024/3/26 14:59
 **/

/**
 * This class select one provider local first.
 */
public class LocalFirstLoadBalance extends AbstractLoadBalance {

    public static final String NAME = "localfirst";

    @Override
    protected <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            Optional<Invoker<T>> self = invokers.stream().filter(v -> v.getUrl().getHost().equals(addr.getHostAddress())).findFirst();
            if (self.isEmpty()) {
                throw new Exception("no local provider");
            }
            return self.get();
        } catch (Exception e) {

            return invokers.get(0);
        }
    }
}
