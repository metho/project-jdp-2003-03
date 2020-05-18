package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.group.ProductGroupDto;
import com.kodilla.ecommercee.dto.group.ProductGroupExtDto;
import com.kodilla.ecommercee.facade.ProductGroupFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @Autowired
    private ProductGroupFacade facade;

    @GetMapping
    public List<ProductGroupDto> getGroups() {
        return facade.fetchGetGroups();
    }

    @GetMapping("/{id}")
    public ProductGroupExtDto getGroup(@PathVariable Long id) {
        return facade.fetchGetGroup(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        facade.fetchDeleteGroup(id);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDto update(@RequestBody ProductGroupDto groupDto) {
        return facade.fetchUpdateGroup(groupDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ProductGroupDto groupDto) {
        facade.fetchCreateGroup(groupDto);
    }
}
