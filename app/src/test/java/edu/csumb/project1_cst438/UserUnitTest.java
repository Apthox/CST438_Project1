package edu.csumb.project1_cst438;

import org.junit.Test;

import edu.csumb.project1_cst438.Model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserUnitTest {
    @Test
    public void create_user() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void set_name() {
        User user = new User();
        user.setFirstName("Kevin");
        user.setLastName("Guzman");
        assertTrue(user.getFirstName() == "Kevin" && user.getLastName() == "Guzman");
    }
}
