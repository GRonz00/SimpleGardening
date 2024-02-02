package com.simplegardening.cli.graphic_controller;

import com.simplegardening.bean.in.FindRequestInBean;
import com.simplegardening.bean.in.SendRequestInBean;
import com.simplegardening.bean.out.RequestOutBean;
import com.simplegardening.cli.view.RequestClientViewCLI;
import com.simplegardening.controller.ManageRequestController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RequestClientGraphicControllerCLI {
    private int idSession;
    private RequestClientViewCLI requestClientViewCLI;
    private void findRequest() throws IOException, BeanException, ControllerException {
        try {
            List<String> fr = requestClientViewCLI.request();
        FindRequestInBean findRequestInBean = new FindRequestInBean(LocalDate.parse(fr.get(1)),LocalDate.parse(fr.get(2)),fr.get(0),idSession,fr.get(3).equals("true"),fr.get(4));
        ManageRequestController manageRequestController = new ManageRequestController();
        RequestOutBean outBean = manageRequestController.findsPossibleRequest(findRequestInBean);
        for(int i=0;i<outBean.getStart().size();i++){
            requestClientViewCLI.printPossibleRequest(outBean.getPro().get(i),outBean.getAddressPro().get(i),outBean.getPrice().get(i),outBean.getIdRequestForm().get(i));
        }}catch (DateTimeParseException e)
        {
            throw new BeanException("Date","Not correct format");
        }


    }
    private void sendRequest() throws IOException, BeanException, ControllerException {
        List<String> r = requestClientViewCLI.request();
        try {
            int idRequestForm = Integer.parseInt(requestClientViewCLI.getIdRequestForm());
            SendRequestInBean sendRequestInBean = new SendRequestInBean(r.get(0),idSession,idRequestForm,r.get(3).equals("true"),LocalDate.parse(r.get(1)),LocalDate.parse(r.get(2)));
            ManageRequestController manageRequestController = new ManageRequestController();
            manageRequestController.sendRequest(sendRequestInBean);
        }catch (NumberFormatException e){
            throw new BeanException("Id request form",BeanException.ONLY_NUMBER_REASON);
        }


    }

    public void initialize(int idSession) {

        this.idSession=idSession;
        this.requestClientViewCLI = new RequestClientViewCLI();
        try {
            int choice = requestClientViewCLI.getChoice();
            if(choice==1){
                findRequest();
            }
            if(choice==2){sendRequest();}
        } catch (IOException | BeanException | ControllerException e) {
            System.out.println("[ERR] " + e.getMessage());
            System.out.println("Please retry.");
        }finally {
            HomeGraphicControllerCLI homeGraphicControllerCLI = new HomeGraphicControllerCLI();
            homeGraphicControllerCLI.initialize(idSession);
        }

    }
}
