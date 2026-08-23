package com.springboot.journalApp.service;

import com.springboot.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests
{
    @Autowired
    private UserRepo userRepo;

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "Ram",
            "Shyam",
            "Vipul",
            "Admin"
    })
    public void testFindByUserName(String name)
    {
        assertNotNull(userRepo.findByUserName(name), "Failed to find user with name " + name);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected)
    {
        assertEquals(expected, a + b);
    }
}