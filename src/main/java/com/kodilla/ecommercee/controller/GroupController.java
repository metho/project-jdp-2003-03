package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductGroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @GetMapping
    public List<ProductGroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public ProductGroupDto getGroup(@PathVariable Long id) {
        return new ProductGroupDto(1L, "Cameras", "12345");
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductGroupDto update(@RequestBody ProductGroupDto productGroupDto) {
        return new ProductGroupDto(2L, "Laptops", "12345");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody ProductGroupDto productGroupDto) {

    }
}
