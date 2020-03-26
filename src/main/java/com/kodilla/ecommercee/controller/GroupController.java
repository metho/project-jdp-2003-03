package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupProductDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @GetMapping
    public List<GroupProductDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public GroupProductDto getGroup(@PathVariable Long id) {
        return new GroupProductDto(1L, "Cameras", "12345");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupProductDto update(@RequestBody GroupProductDto groupProductDto) {
        return new GroupProductDto(2L, "Laptops", "12345");
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody GroupProductDto groupDto) {

    }
}
