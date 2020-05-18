package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.group.ProductGroupDto;
import com.kodilla.ecommercee.dto.group.ProductGroupExtDto;
import com.kodilla.ecommercee.dto.group.ProductGroupLinkDto;
import com.kodilla.ecommercee.entity.ProductGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGroupMapper {

    public ProductGroup mapToGroup(ProductGroupDto groupDto) {
        return new ProductGroup(groupDto.getId(), groupDto.getName());
    }

    public ProductGroupDto mapToGroupDto(ProductGroup group) {
        return new ProductGroupDto(group.getId(), group.getName());
    }

    public ProductGroupExtDto mapToGroupExtDto(ProductGroup group) {
        return new ProductGroupExtDto(group.getId(), group.getName(), new ArrayList<>());
    }

    public List<ProductGroupDto> mapToGroupDtoList(List<ProductGroup> groups) {
        return groups.stream().map(this::mapToGroupDto).collect(Collectors.toList());
    }
}
