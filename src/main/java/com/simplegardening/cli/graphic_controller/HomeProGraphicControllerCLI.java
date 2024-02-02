package com.simplegardening.cli.graphic_controller;

import com.simplegardening.CLIApplication;
import com.simplegardening.bean.in.MessageInBean;
import com.simplegardening.bean.in.RequestFormInBean;
import com.simplegardening.bean.in.RequestInBean;
import com.simplegardening.bean.out.RequestOutBean;
import com.simplegardening.cli.view.HomeProViewCLI;
import com.simplegardening.cli.view.MessageViewCLI;
import com.simplegardening.controller.ManageRequestController;
import com.simplegardening.controller.SendMessageController;
import com.simplegardening.exception.BeanException;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.model.SessionManager;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class HomeProGraphicControllerCLI {
    public void initialize(int idSession) {
        HomeProViewCLI homeProViewCLI = new HomeProViewCLI();
        ManageRequestController manageRequestController = new ManageRequestController();
        try {
            homeProViewCLI.init();
            RequestOutBean requests = manageRequestController.getRequests(idSession);
            for (int i = 0; i < requests.getPro().size(); i++) {
                homeProViewCLI.viewRequest(requests.getClient().get(i),requests.getPlant().get(i).getName(),requests.getPlant().get(i).getSize(),requests.getPlant().get(i).getType(), requests.getPrice().get(i),requests.getStart().get(i).toString(),requests.getEnd().get(i).toString(),requests.getState().get(i),requests.getIdRequestForm().get(i).toString());
            }
            int choice = homeProViewCLI.getAction();
            switch (choice){
                case 1->{
                    List<String> rf = homeProViewCLI.addRequestForm();
                    RequestFormInBean requestFormInBean = new RequestFormInBean(LocalDate.parse(rf.get(0)),LocalDate.parse(rf.get(1)),rf.get(2),rf.get(3).equals("true"),rf.get(4),rf.get(5),rf.get(6));
                    requestFormInBean.requestFormInBean2(rf.get(7).equals("true"),rf.get(8),rf.get(9).toUpperCase(),rf.get(10).toUpperCase(),rf.get(11),idSession);
                    manageRequestController.addRequestForm(requestFormInBean);
                }
                case 2->{
                    List<String> req = homeProViewCLI.choiceRequest();
                    RequestInBean request = new RequestInBean(req.get(2), req.get(1),LocalDate.parse(req.get(3)),LocalDate.parse(req.get(4)),Integer.parseInt(req.get(0)) );
                    manageRequestController.acceptRequest(request);
                }
                case 3->{
                    List<String> req = homeProViewCLI.choiceRequest();
                    RequestInBean request = new RequestInBean(req.get(2), req.get(1),LocalDate.parse(req.get(3)),LocalDate.parse(req.get(4)),Integer.parseInt(req.get(0)) );
                    manageRequestController.refuseRequest(request);
                }
                case 4->{
                    List<String> mes = new MessageViewCLI().sendMessage();
                    MessageInBean message = new MessageInBean(idSession,mes.get(1),mes.get(0));
                    new SendMessageController().sendMessage(message);
                }
                case 5-> {
                    SessionManager.getInstance().closeSession(idSession);
                    CLIApplication.main(new String[1]);
                }
            }
        } catch (ControllerException | IOException | BeanException | SQLException | SessionException e) {
            System.out.println("[ERR] " + e.getMessage());
            System.out.println("Please retry.");
        }finally {
            new HomeProGraphicControllerCLI().initialize(idSession);
        }
    }
}