package com.power.spring.lesson6;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.leader.lesson6.bean.Order;
import com.leader.lesson6.bean.User;
import com.leader.lesson6.config.MySpringConfig;
import com.leader.lesson6.service.OrderService;
import com.leader.lesson6.service.UserService;

/**
 * @author zss
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MySpringConfig.class)
public class lesson6_1 {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Test
    public void testUserService() throws InterruptedException {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                testFindAllAndUpdateUser();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                testFindAllAndUpdateUser();
            }
        };
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private void testFindAllAndUpdateUser(){
        try {
            List<User> all = userService.findAll();
            System.out.println("all = " + all);
            if (all != null && !all.isEmpty()) {
                User user = all.get(0);
                user.setAge(user.getAge() + 1);
                boolean updated = userService.updateUser(user);
                System.out.println("updated = " + updated);
            }
        }catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        }
    }

    @Test
    public void testOrderService() throws FileNotFoundException {
        System.out.println("orderService = " + orderService);
        List<Order> orders = orderService.findAll();
        System.out.println("orders = " + orders);
        if (orders != null && !orders.isEmpty()) {
            Order order = orders.get(0);
            order.setPrice(order.getPrice() + 1);
            boolean updated = orderService.updateOrder(order);
            System.out.println("updated = " + updated);
        }
    }

    @Test
    public void testFindAndUpdate() {
        userService.find2();
    }



}
