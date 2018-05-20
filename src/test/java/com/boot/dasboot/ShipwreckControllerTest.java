package com.boot.dasboot;

import com.boot.dasboot.controller.ShipwreckController;
import com.boot.dasboot.model.Shipwreck;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.hamcrest.MatcherAssert.*;
import org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShipwreckControllerTest {
    @InjectMocks
    private com.boot.dasboot.controller.ShipwreckController sc;

    @Mock
    private com.boot.dasboot.repository.ShipWreckRepository repos;


    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShipwreckGet() throws Exception {
        ///creo il mio oggetto mock...
        Shipwreck ship = new Shipwreck();
        ship.setId(1l);

        //dico che quando viene richiesto quell'oggetto al repository, deve essere restituito esattamente quello

        when(repos.findOne(1l)).thenReturn(ship);

        //dopo aver fatto il mock del repository...
        //...ora testo il controller
        Shipwreck wreck = sc.get(1l);

        verify(repos).findOne(1L);

//        Assert.assertEquals(1L, ship.getId().longValue());
        MatcherAssert.assertThat(wreck.getId().longValue(), Matchers.is(1l));
    }
}
