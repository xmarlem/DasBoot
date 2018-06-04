package com.boot.dasboot;

import com.boot.dasboot.controller.HomeController;
import com.boot.dasboot.controller.ShipwreckController;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DasBootUnitTests {
	@Test
	public void contextLoads() {
		HomeController hc = new HomeController();
		Assert.assertEquals(hc.home(), "Home page");
	}

}
