package com.boot.dasboot.controller;


import com.boot.dasboot.model.Shipwreck;
import com.boot.dasboot.repository.ShipWreckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class ShipwreckController {

    @Autowired
    private ShipWreckRepository shipWreckRepository;

    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
    public List<Shipwreck> list(){
        return shipWreckRepository.findAll();
//        return ShipwreckStub.list();
    }

    @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
    public Shipwreck create(@RequestBody Shipwreck shipwreck){
        return shipWreckRepository.saveAndFlush(shipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
    public Shipwreck get(@PathVariable long id){
        return shipWreckRepository.findOne(id);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
    public Shipwreck update(@PathVariable long id, @RequestBody Shipwreck shipwreck){
        Shipwreck existent = shipWreckRepository.findOne(id);
        BeanUtils.copyProperties(shipwreck,existent);
        return shipWreckRepository.saveAndFlush(existent);
    }


    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
    public Shipwreck delete(@PathVariable long id){
        Shipwreck existent = shipWreckRepository.findOne(id);
        shipWreckRepository.delete(id);
        return existent;
    }



}
