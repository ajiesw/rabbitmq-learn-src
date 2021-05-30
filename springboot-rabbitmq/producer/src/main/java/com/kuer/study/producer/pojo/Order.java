package com.kuer.study.producer.pojo;

import lombok.Data;

/**
 * @author kuer
 */
@Data
public class Order {

    private String orderName;

    private Double price;

    private Integer num;
}
