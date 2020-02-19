package edu.csumb.project1_cst438;

import org.junit.Test;

import edu.csumb.project1_cst438.Model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserUnitTest {
    @Test
    public void create_user() {
        User user = new User();
        assertNotNull(user);
    }
}
