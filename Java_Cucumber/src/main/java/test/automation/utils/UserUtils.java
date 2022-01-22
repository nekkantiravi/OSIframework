package test.automation.utils;

import test.automation.framework.Config;
import test.automation.framework.DB;
import test.automation.framework.Data;
import test.automation.framework.Util;
import test.automation.models.Customers;
import test.automation.models.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static test.automation.framework.Data.getAsMap;
import static test.automation.framework.Data.getAsObject;
import static test.automation.framework.Random.*;

public class UserUtils {
    static User user;

    private static User ocUser;
    private static Customers customers;

    public static User getUser() {
        return (User) getAsObject("User");
    }

    public static Map<String, Object> getUserMap() {
        return getAsMap("user_v1");
    }

    public static User getOCUser() {
        if (ocUser == null) {
            getNewOCUser();
        }
        return ocUser;
    }

    public static User getNewOCUser() {
        ocUser = new User();
        ocUser.setFirstName(getRandomString(getRandomIntBetween(5, 10)));
        ocUser.setLastName(getRandomString(getRandomIntBetween(5, 10)));
        ocUser.setEmail(getRandomString(getRandomIntBetween(5, 10)) + System.currentTimeMillis() + "@oc.void.com");
        ocUser.setPhone("9" + getRandomNumber(9));
        ocUser.setPassword("opencarttest");
        return ocUser;
    }

    public static User getOCUserFromDB(String email) {
        User user = new User();
        List<Map<String, Object>> users = DB.executeQuery("select * from oc_customer where email='" + email + "'");
        if (users.size() > 0) {
            user.setFirstName((String) users.get(0).get("firstname"));
            user.setLastName((String) users.get(0).get("lastname"));
            user.setEmail((String) users.get(0).get("email"));
            user.setPhone((String) users.get(0).get("telephone"));
        }
        return user;
    }

    public static User createNewOCUserAccount() {
        User user = getNewOCUser();
        given().contentType("application/x-www-form-urlencoded;charset=UTF-8")
                .formParam("firstname", user.getFirstName())
                .formParam("lastname", user.getLastName())
                .formParam("email", user.getEmail())
                .formParam("telephone", user.getPhone())
                .formParam("password", user.getPassword())
                .formParam("confirm", user.getPassword())
                .formParam("newsletter", 0)
                .formParam("agree", 1)
                .when().post(Config.getUrl() + "/index.php?route=account/register")
                .then().statusCode(302);
        return user;
    }

    public static User getValidUser(String type) {
        List<User> users = ((List<User>) ((Object) Data.getAsObjects("login_credentials", User.class))).stream().filter(u -> u.getType().equals(type)).collect(Collectors.toList());
        user = users.get(Util.getRandomIndex(users.size()));
        return user;
    }

    public static Customers getCustomerDataInfo(String type) {
        List<Customers> cust = ((List<Customers>) ((Object) Data.getAsObjects("customer", Customers.class))).stream()
                .filter(u -> u.getType().equals(type)).collect(Collectors.toList());
        customers = cust.get(Util.getRandomIndex(cust.size()));
        return customers;

    }

}
