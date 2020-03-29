package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductGroupDto;
import com.kodilla.ecommercee.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @Autowired
    ProductGroupService service;

    @GetMapping
    public List<ProductGroupDto> getGroups() {
        return service.getGroups();
    }

    @GetMapping("/{id}")
    public ProductGroupDto getGroup(@PathVariable Long id) {
        return service.getGroup(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id, @RequestParam String key) {
        service.delete(id, key);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDto update(@RequestBody ProductGroupDto groupDto) {
        return service.update(groupDto);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ProductGroupDto groupDto) {
        service.create(groupDto);
    }
}
