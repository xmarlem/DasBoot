package com.boot.dasboot;


import com.boot.dasboot.model.Shipwreck;
import com.boot.dasboot.repository.ShipWreckRepository;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShipwreckRepositoryIntegrationTest {
    @Autowired
    private ShipWreckRepository shipWreckRepository;

    @Test
    public void testFindAll() throws Exception {
        List<Shipwreck> wrecks = shipWreckRepository.findAll();
        Assert.assertThat(wrecks.size(), Matchers.is(Matchers.greaterThanOrEqualTo(0)));
    }
}
