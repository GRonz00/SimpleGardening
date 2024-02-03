package controller;

import com.simplegardening.controller.ManageRequestController;
import com.simplegardening.model.Client;
import com.simplegardening.model.Pro;
import com.simplegardening.model.RequestForm;
import com.simplegardening.model.User;
import com.simplegardening.utils.PlantSize;
import com.simplegardening.utils.PlantType;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestManageRequestController {

    // Ronzello Gianluca

    @Test
    public void testCalculatePrice() {
        ManageRequestController controller = new ManageRequestController();
        RequestForm requestForm = new RequestForm(Date.valueOf("2024-02-01"),Date.valueOf("2024-02-09"),3f,true,30,10,0.5f);
        requestForm.requestForm2(PlantSize.SMALL, PlantType.INDOOR,false,20,6);
        User pro = new Pro("pro","pass",12.6742834,41.8164419,"frascati");
        User client = new Client("client","pass",12.5524073,41.8013875,"grottaferrata");
        float output = controller.calculatePrice(requestForm,true, LocalDate.parse("2024-02-03"),LocalDate.parse("2024-02-06"),pro,client,true);
        assertEquals(28.3f, output,0.1);
    }
    @Test
    public void testCalculateDistance() {
        ManageRequestController controller = new ManageRequestController();
        double output = controller.calculateDistance(41.8164419,12.6742834,41.8013875,12.5524073);
        assertEquals(10.24, output,0.01);
    }
}
