package com.foodie.eatzy.service;

import java.util.List;

import com.foodie.eatzy.dto.DeliveryEarningDto;

public interface DeliveryEarning {

    List<DeliveryEarningDto> getDeliveryEarningByDeliveryBoy(String deliveryBoyId);

}
