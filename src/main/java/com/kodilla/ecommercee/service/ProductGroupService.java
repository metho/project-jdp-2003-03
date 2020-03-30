package com.kodilla.ecommercee.service;

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
    private static final String TECHNICAL_GROUP = "Unbound";

    @Autowired
    ProductGroupRepository groupRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductGroupMapper groupMapper;

    public List<ProductGroupDto> getGroups() {
        return groupMapper.mapToGroupDtoList(groupRepository.findAll());
    }

    public ProductGroupDto getGroup(Long id) {
        ProductGroup group = getGroupOrException(id);
        return groupMapper.mapToGroupDto(group);
    }

    public void create(ProductGroupDto groupDto) {
        if (groupRepository.existsByName(groupDto.getName())) throw new EntityAlreadyExistsException(GROUP_EXISTS);
        groupRepository.save(groupMapper.mapToGroup(groupDto));
    }

    public ProductGroupDto update(ProductGroupDto groupDto) {
        ProductGroup group = getGroupOrException(groupDto.getId());
        group.setName(groupDto.getName());

        return groupMapper.mapToGroupDto(groupRepository.save(group));
    }

    public void delete(Long id) {
        detachProducts(id);
        groupRepository.deleteById(id);
    }

    private void detachProducts(Long id) {
        ProductGroup group = getGroupOrException(id);
        if (group.getProducts().size() > 0) {
            ProductGroup unbound = getUnboundGroup();
            for (Product product : group.getProducts()) {
                product.setProductGroup(unbound);
                productRepository.save(product);
            }
        }
    }

    private ProductGroup getUnboundGroup() {
        ProductGroup group = groupRepository.findFirstByName(TECHNICAL_GROUP).orElseGet(() -> new ProductGroup(TECHNICAL_GROUP));
        return groupRepository.save(group);
   }

    private ProductGroup getGroupOrException(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
