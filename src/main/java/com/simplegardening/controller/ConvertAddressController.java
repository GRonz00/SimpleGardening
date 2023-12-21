package com.simplegardening.controller;

import com.simplegardening.bean.in.ConvertAddressInAPIBean;
import com.simplegardening.bean.in.ConvertAddressInBean;
import com.simplegardening.bean.out.ConvertAddressOutAPIBean;
import com.simplegardening.bean.out.ConvertAddressOutBean;
import com.simplegardening.graphic_controller.ConvertAddressAPIGraphicController;

public class ConvertAddressController {
    public ConvertAddressOutBean convert(ConvertAddressInBean convertAddressInBean){
        ConvertAddressOutAPIBean convertAddressOutAPIBean = new ConvertAddressOutAPIBean(convertAddressInBean.getNation(), convertAddressInBean.getCity(), convertAddressInBean.getCity(), convertAddressInBean.getpC());
        ConvertAddressAPIGraphicController convertAddressAPIGraphicController = new ConvertAddressAPIGraphicController();
        ConvertAddressInAPIBean convertAddressInAPIBean = convertAddressAPIGraphicController.convertAddress(convertAddressOutAPIBean);
        return new ConvertAddressOutBean(convertAddressInAPIBean.getLongitude(),convertAddressInAPIBean.getLatitude());
    }
}
