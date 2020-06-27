package com.feng.dubbo.paimary;

import com.feng.dubbo.primary.api.PayService;

public class PayServiceImpl implements PayService {

    public String pay(String info) {
        return "Dubbo Service:"+info;
    }
}
