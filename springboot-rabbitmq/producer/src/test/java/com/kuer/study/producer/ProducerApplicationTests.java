package com.kuer.study.producer;

import com.kuer.study.producer.pojo.Order;
import com.kuer.study.producer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProducerApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        orderService.pushMsg(new Order(){{
            setNum(80);
            setPrice(80.0);
            setOrderName("订单一号");
        }});
    }

    @Test
    void test01() {
        orderService.pushMsgWithDirect(new Order(){{
            setNum(80);
            setPrice(80.0);
            setOrderName("订单一号");
        }});
    }
    @Test
    void test02() {
        orderService.pushMsgWithTopic(new Order(){{
            setNum(80);
            setPrice(80.0);
            setOrderName("订单一号");
        }});
    }
    @Test
    void test03() {
        orderService.pushMsgWithTtl(new Order(){{
            setNum(80);
            setPrice(80.0);
            setOrderName("订单一号");
        }});
    }
    @Test
    void test04() {
        orderService.pushMsgWithMessageTtl(new Order(){{
            setNum(80);
            setPrice(80.0);
            setOrderName("订单一号");
        }});
    }

}
