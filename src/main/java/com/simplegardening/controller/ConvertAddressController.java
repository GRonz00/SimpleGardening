package com.simplegardening.controller;

import com.simplegardening.bean.in.ConvertAddressInAPIBean;
import com.simplegardening.bean.in.ConvertAddressInBean;
import com.simplegardening.bean.out.ConvertAddressOutAPIBean;
import com.simplegardening.bean.out.ConvertAddressOutBean;
import com.simplegardening.exception.APIException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.graphic_controller.ConvertAddressAPIGraphicController;

public class ConvertAddressController {
    public ConvertAddressOutBean convert(ConvertAddressInBean convertAddressInBean) throws ControllerException {
        ConvertAddressOutAPIBean convertAddressOutAPIBean = new ConvertAddressOutAPIBean(convertAddressInBean.getNation(), convertAddressInBean.getCity(), convertAddressInBean.getStreet(), convertAddressInBean.getpC());
        ConvertAddressAPIGraphicController convertAddressAPIGraphicController = new ConvertAddressAPIGraphicController();
        ConvertAddressInAPIBean convertAddressInAPIBean;
        try {
            convertAddressInAPIBean = convertAddressAPIGraphicController.convertAddress(convertAddressOutAPIBean);
        } catch (APIException e) {
            throw new ControllerException(e.getMessage());
        }
        return new ConvertAddressOutBean(convertAddressInAPIBean.getLongitude(),convertAddressInAPIBean.getLatitude());
    }
}
