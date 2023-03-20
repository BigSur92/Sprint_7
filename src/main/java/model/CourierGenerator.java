package model;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class CourierGenerator {

    public static Courier getRandom(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login,password, firstName);
    }
    public static Courier getWithOutPassword(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(0);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login,password, firstName);
    }
    public static Courier getWithOutLogin(){
        String login = RandomStringUtils.randomAlphabetic(0);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        return new Courier(login,password, firstName);
    }
    public static Courier getWithOutFirstName(){
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(0);
        return new Courier(login,password, firstName);
    }
}
