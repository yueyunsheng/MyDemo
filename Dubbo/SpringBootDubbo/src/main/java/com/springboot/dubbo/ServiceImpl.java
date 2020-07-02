package com.springboot.dubbo;

import com.feng.springboot.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class ServiceImpl implements ISayHelloService {


    @Override
    public String sayHello() {
        return null;
    }

}
