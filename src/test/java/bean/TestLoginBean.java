package bean;

import com.simplegardening.bean.in.LoginBeanIn;
import com.simplegardening.exception.BeanException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestLoginBean {
    private static final String RIGHT_LENGTH = "right_length";

    @Test
    public void testLoginBean() {
        assertAll("LoginBean",
                () -> assertThrows(BeanException.class, () -> new LoginBeanIn("a", "b","JDBC")),
                () -> assertThrows(BeanException.class, () -> new LoginBeanIn(RIGHT_LENGTH, "b","JDBC")),
                () -> assertThrows(BeanException.class, () -> new LoginBeanIn("a", RIGHT_LENGTH,"JDBC")),
                () -> assertDoesNotThrow(() -> new LoginBeanIn(RIGHT_LENGTH, RIGHT_LENGTH,"JDBC"))
        );
    }
}
