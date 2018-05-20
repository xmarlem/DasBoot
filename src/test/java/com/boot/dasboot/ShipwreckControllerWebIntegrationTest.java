package com.boot.dasboot;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShipwreckControllerWebIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void testListAll() throws Exception {

        ResponseEntity<String> response = this.testRestTemplate.getForEntity("/api/v1/shipwrecks", String.class);
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        Assert.assertThat(jsonNode.isMissingNode(), is(false));
        Assert.assertThat(jsonNode.toString(), isA(String.class));

        Assert.assertThat(jsonNode.size(), greaterThanOrEqualTo(1));
    }
}
