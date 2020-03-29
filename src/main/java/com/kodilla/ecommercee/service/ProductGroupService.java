package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.common.Validator;
import com.kodilla.ecommercee.dto.ProductGroupDto;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.ProductGroup;
import com.kodilla.ecommercee.exception.EntityAlreadyExistsException;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupService {
    private static final String NOT_FOUND = "ERROR: Group not found.";
    private static final String GROUP_EXISTS = "ERROR: Group already exists.";

    @Autowired
    ProductGroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductGroupMapper groupMapper;

    @Autowired
    Validator validator;

    public List<ProductGroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupRepository.findAll());
    }

    public ProductGroupDto getGroup(Long id) {
        ProductGroup group = groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        return groupMapper.mapToGroupDto(group);
    }

    public void create(ProductGroupDto groupDto) {
        validator.validateUser(groupDto.getKey());
        if (groupRepository.existsByName(groupDto.getName())) throw new EntityAlreadyExistsException(GROUP_EXISTS);

        groupRepository.save(groupMapper.mapToGroup(groupDto));
    }

    public ProductGroupDto update(ProductGroupDto groupDto) {
        validator.validateUser(groupDto.getKey());
        if (!groupRepository.existsById(groupDto.getId())) throw new EntityNotFoundException(NOT_FOUND);
        ProductGroup group = groupMapper.mapToGroup(groupDto);

        return groupMapper.mapToGroupDto(groupRepository.save(group));
    }

    public void delete(Long id, String key) {
        validator.validateUser(key);
        ProductGroup group = groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        detachProduct(group);
        groupRepository.deleteById(id);
    }

    private void detachProduct(ProductGroup group) {
        for (Product product : group.getProducts()) {
            product.setProductGroup(null);
            productRepository.save(product);
        }
    }
}
