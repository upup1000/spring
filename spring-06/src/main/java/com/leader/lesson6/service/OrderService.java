package com.leader.lesson6.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.leader.lesson6.bean.Order;
import com.leader.lesson6.dao.OrderDao;

/**
 * @author zss
 */
@Component
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<Order> findAll(){
        return orderDao.findAll();
    }

    public boolean updateOrder(Order order) throws FileNotFoundException {
        int num = orderDao.updateOrder(order);
//        runtime();
//        noRuntime();
        return num > 0;
    }

    public void runtime(){
        String[] a = new String[]{};
        System.out.println("a[1] = " + a[1]);
    }

    public void noRuntime() throws FileNotFoundException {
        File f = new File("/ABC/");
        new FileInputStream(f);
        System.out.println(f.getTotalSpace());
    }

}
