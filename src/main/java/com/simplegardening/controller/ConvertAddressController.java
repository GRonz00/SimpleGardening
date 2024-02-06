package com.simplegardening.controller;

import com.simplegardening.bean.in.CoordinatesInAPIBean;
import com.simplegardening.bean.in.AddressInBean;
import com.simplegardening.bean.out.AddressOutAPIBean;
import com.simplegardening.bean.out.CoordinatesOutBean;
import com.simplegardening.exception.APIException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.graphic_controller.ConvertAddressAPIGraphicController;

public class ConvertAddressController {
    public CoordinatesOutBean convert(AddressInBean addressInBean) throws ControllerException {
        AddressOutAPIBean addressOutAPIBean = new AddressOutAPIBean(addressInBean.getNation(), addressInBean.getCity(), addressInBean.getStreet(), addressInBean.getpC());
        ConvertAddressAPIGraphicController convertAddressAPIGraphicController = new ConvertAddressAPIGraphicController();
        CoordinatesInAPIBean coordinatesInAPIBean;
        try {
            coordinatesInAPIBean = convertAddressAPIGraphicController.convertAddress(addressOutAPIBean);
        } catch (APIException e) {
            throw new ControllerException("Address cannot be converted");
        }
        return new CoordinatesOutBean(coordinatesInAPIBean.getLongitude(), coordinatesInAPIBean.getLatitude());
    }
}
