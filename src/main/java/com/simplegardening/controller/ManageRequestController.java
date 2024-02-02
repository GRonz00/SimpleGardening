package com.simplegardening.controller;

import com.simplegardening.bean.in.RequestFormInBean;
import com.simplegardening.bean.in.FindRequestInBean;
import com.simplegardening.bean.in.RequestInBean;
import com.simplegardening.bean.in.SendRequestInBean;
import com.simplegardening.bean.out.RequestOutBean;
import com.simplegardening.dao.PlantDAO;
import com.simplegardening.dao.RequestDAO;
import com.simplegardening.dao.RequestFormDAO;
import com.simplegardening.dao.UserDAO;
import com.simplegardening.exception.ControllerException;
import com.simplegardening.exception.DatabaseException;
import com.simplegardening.exception.SessionException;
import com.simplegardening.model.*;
import com.simplegardening.model.decoration.ExtraHolidaysPrice;
import com.simplegardening.model.decoration.PickupPrice;
import com.simplegardening.utils.RequestState;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageRequestController {
    

    public void addRequestForm(RequestFormInBean beanIn) throws ControllerException {
        try {
            RequestForm requestForm = new RequestForm(beanIn.getStart(), beanIn.getEnd(), beanIn.getBasePrice(), beanIn.isPickupAvailable(), beanIn.getMaxKm(), beanIn.getPickupBasePrice(), beanIn.getKmPrice() );
            requestForm.requestForm2(beanIn.getPlantSize(), beanIn.getPlantType(), beanIn.isNewCustomer(), beanIn.getExtraHoliday(), beanIn.getAmount());
            Session session = SessionManager.getInstance().getSession(beanIn.getIdSession());
            Pro pro = (Pro) session.getUser();
            RequestFormDAO requestFormDAO = new RequestFormDAO();
            requestFormDAO.saveRequestForm(pro, requestForm, session);
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        }
    }
    public RequestOutBean findsPossibleRequest(FindRequestInBean bean) throws ControllerException {

        try {
            Session session = SessionManager.getInstance().getSession(bean.getIdSession());
            Client client = (Client) session.getUser();
            PlantDAO plantDAO = new PlantDAO();
            Plant plant = plantDAO.getPlantFromName(bean.getPlantName(), client, session);
            RequestFormDAO requestFormDAO = new RequestFormDAO();
            List<RequestForm> requestFormList = requestFormDAO.getAllRequestForm(session);
            checkAvailability(requestFormList);
            checkAcceptablePlant(requestFormList, plant);
            checkAcceptablePeriod(requestFormList, bean.getStart(), bean.getEnd());
            if (bean.isPickUp()) checkAcceptableDistance(requestFormList, bean.getMaxKm(), bean.getIdSession());
            List<Request> requests = new ArrayList<>();
            for (RequestForm rf : requestFormList) {
                User pro = new UserDAO().getUserByUsername(rf.getPro());
                float price1 = calculatePrice(rf, bean.isPickUp(), bean.getStart(), bean.getEnd(), pro, client, session);
                Request r = new Request( plant, price1, bean.isPickUp(), (Pro) pro, client, bean.getStart(), bean.getEnd());
                r.setRequestForm(rf);
                r.setState(RequestState.SENT.toString());
                requests.add(r);
            }
            return new RequestOutBean(requests);

        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException(e);
        }


    }
    public RequestOutBean getRequests(int idSession) throws ControllerException {
        RequestDAO requestDAO = new RequestDAO();
        try {
            SessionManager.getInstance().validSession(idSession);
            Session session = SessionManager.getInstance().getSession(idSession);
            List<Request> requests = requestDAO.getRequestFromUser(session.getUser(), session);
            return new RequestOutBean(requests);

        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException(e);
        }
    }
    public void sendRequest(SendRequestInBean bean) throws ControllerException {
        try {
            Session session = SessionManager.getInstance().getSession(bean.getIdSession());
            RequestForm requestForm = new RequestFormDAO().getRequestFormFromId(bean.getIdRequestForm(),session );
            if(requestForm.getAmount()==0)throw new ControllerException("The pro has run out of availability");
            Plant plant = new PlantDAO().getPlantFromName(bean.getPlant(), (Client)session.getUser(),session);
            if((!requestForm.isPickupAvailable()&&bean.isPickup()))throw new ControllerException("Pickup not available");
            User pro = new UserDAO().getUserByUsername(requestForm.getPro());
            if(bean.isPickup()&&calculateDistance(session.getUser().getLatitude(),session.getUser().getLongitude(),pro.getLatitude(), pro.getLongitude())>requestForm.getMaxKm())throw new ControllerException("Pickup not available too distant");
            if(!requestForm.getPlantSize().equals(plant.getSize()) || !requestForm.getPlantType().equals(plant.getType()))throw new ControllerException("Unacceptable plant");
            if((requestForm.getStart()).after(Date.from(bean.getStart().atStartOfDay(ZoneId.systemDefault()).toInstant())) ||
                    requestForm.getEnd().before(Date.from(bean.getEnd().atStartOfDay(ZoneId.systemDefault()).toInstant())))throw new ControllerException("Period not acceptable");
            float price = calculatePrice(requestForm, bean.isPickup(), bean.getStart(),bean.getEnd(),pro, session.getUser(), session);
            Request request = new Request(plant,price, bean.isPickup(),(Pro) pro,(Client)session.getUser(),bean.getStart(),bean.getEnd());
            request.setRequestForm(requestForm);
            new RequestDAO().saveRequest(request, SessionManager.getInstance().getSession(bean.getIdSession()));
            new PlantDAO().changeState(plant, SessionManager.getInstance().getSession(bean.getIdSession()));

        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        } catch (SQLException | DatabaseException e) {
            throw new ControllerException("SQL", e);
        }

    }
    public void acceptRequest(RequestInBean bean) throws ControllerException {

        try {
            if (bean.getStart().isBefore(LocalDate.now())) throw new ControllerException("The request is expired");
            RequestDAO requestDAO = new RequestDAO();
            Request request = createRequest(bean);
            if (!requestDAO.checkRequestDaysPlant(request, SessionManager.getInstance().getSession(bean.getIdSession())))
                throw new ControllerException("The plat is busy");

            Session session = SessionManager.getInstance().getSession(bean.getIdSession());
            new RequestFormDAO().decreaseAviability(request.getRequestForm().getIdRequestForm(), session);
            requestDAO.acceptRequest(request, session);
            requestDAO.updateStateRequestPlantDay(request, session);

        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }

    }
    private void checkAvailability(List<RequestForm> requestFormList) {
        requestFormList.removeIf(requestForm -> requestForm.getAmount() == 0);

    }
    public void refuseRequest(RequestInBean bean) throws ControllerException {

        RequestDAO requestDAO = new RequestDAO();
        Request request = createRequest(bean);
        try {
            requestDAO.refuseRequest(request, SessionManager.getInstance().getSession(bean.getIdSession()));
        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        }

    }

    private Request createRequest(RequestInBean bean) throws ControllerException {
        try {
            UserDAO userDAO = new UserDAO();
            Client client = (Client) userDAO.getUserByUsername(bean.getClient());
            PlantDAO plantDAO = new PlantDAO();
            Session session = SessionManager.getInstance().getSession(bean.getIdSession());
            Plant plant = plantDAO.getPlantFromName(bean.getPlant(), client, session);
            RequestDAO requestDAO = new RequestDAO();
            return requestDAO.getRequestFromPlant( plant, bean.getIdRequestForm(), client, bean.getStart(), bean.getEnd(),session);


        } catch (SessionException e) {
            throw new ControllerException(e.getMessage());
        } catch (SQLException e) {
            throw new ControllerException("SQL", e);
        } catch (DatabaseException e) {
            throw new ControllerException("Database", e);
        }
    }

    private float calculatePrice(RequestForm requestForm, boolean pickup, LocalDate start, LocalDate end, User pro, User client, Session session) throws SQLException {
        Price price;
        BasePrice bp = new BasePrice(start, end, requestForm.getBasePrice());
        price= bp;
        if (!(requestForm.isNewCustomer() && new RequestDAO().newClient(pro, client, session))) {
            ExtraHolidaysPrice ehpBp = new ExtraHolidaysPrice(bp);
            ehpBp.defineThePrize(start, end, requestForm.getBasePrice(), requestForm.getExtraHoliday());
            price = ehpBp;
            if (pickup) {
                PickupPrice ppEhpBp = new PickupPrice(ehpBp);
                double distance = calculateDistance(pro.getLatitude(), pro.getLongitude(), client.getLatitude(), client.getLongitude());
                ppEhpBp.defineThePrize(requestForm.getPickupBasePrice(), requestForm.getKmPrice(), distance);
                price = ppEhpBp;
            }
        } else if (pickup) {
            double distance = calculateDistance(pro.getLatitude(), pro.getLongitude(), client.getLatitude(), client.getLongitude());
            PickupPrice ppBp = new PickupPrice(bp);
            ppBp.defineThePrize(requestForm.getPickupBasePrice(), requestForm.getKmPrice(), distance);
            price = ppBp;
        }

        return price.calculatePrice();
    }

    private void checkAcceptablePlant(List<RequestForm> requestFormList, Plant plant) {
        requestFormList.removeIf(requestForm -> !requestForm.getPlantSize().equals(plant.getSize()) || !requestForm.getPlantType().equals(plant.getType()));

    }

    private void checkAcceptableDistance(List<RequestForm> requestFormList, int maxKm, int idSession) throws ControllerException {
        UserDAO userDAO = new UserDAO();
        List<RequestForm> toRemove = new ArrayList<>();
        try {
            SessionManager.getInstance().validSession(idSession);
            User client = SessionManager.getInstance().getSession(idSession).getUser();
            for (RequestForm requestForm : requestFormList) {
                User pro = userDAO.getUserByUsername(requestForm.getPro());
                double distance = calculateDistance(pro.getLatitude(), pro.getLongitude(), client.getLatitude(), client.getLongitude());
                if (distance > maxKm) toRemove.add(requestForm);
            }
            requestFormList.removeAll(toRemove);
        } catch (DatabaseException | SQLException | SessionException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    private void checkAcceptablePeriod(List<RequestForm> requestFormList, LocalDate startRequest, LocalDate endRequest) {
        requestFormList.removeIf(requestForm -> (requestForm.getStart()).after(Date.from(startRequest.atStartOfDay(ZoneId.systemDefault()).toInstant())) || requestForm.getEnd().before(Date.from(endRequest.atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }

    private double calculateDistance(double lat1, double longit1, double lat2, double longit2) {

        double distance;

        lat1 = lat1 * Math.PI / 180;
        longit1 = longit1 * Math.PI / 180;
        lat2 = lat2 * Math.PI / 180;
        longit2 = longit2 * Math.PI / 180;

        double distLong = longit2 - longit1;

        double pezzo1 = Math.cos(lat2) * Math.sin(distLong);
        double pezzo11 = pezzo1 * pezzo1;

        double pezzo2 = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(distLong);
        double pezzo22 = pezzo2 * pezzo2;

        double pezzo3 = Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(distLong);

        double pezzo4 = Math.atan((Math.sqrt(pezzo11 + pezzo22)) / pezzo3);

        distance = pezzo4 * 6372;
        return distance;
    }


}
