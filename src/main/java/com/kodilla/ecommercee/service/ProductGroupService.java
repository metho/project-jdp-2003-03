package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.group.ProductGroupDto;
import com.kodilla.ecommercee.dto.group.ProductGroupLinkDto;
import com.kodilla.ecommercee.entity.ProductGroup;
import com.kodilla.ecommercee.exception.EntityNotFoundException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductGroupService {
    private static final String TECHNICAL_GROUP = "Unbound";
    private static final String BASIC_PATH = "http://localhost:8080/v1/product/bygroup?name=";

    @Autowired
    private ProductGroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<ProductGroup> getGroups() {
        return groupRepository.findAll();
    }

    public ProductGroup getGroup(Long id) {
        return getGroupOrException(id);
    }

    public void create(ProductGroup group) {
        groupRepository.save(group);
    }

    public ProductGroup update(ProductGroupDto groupDto) {
        ProductGroup group = getGroupOrException(groupDto.getId());
        group.setName(groupDto.getName());

        return groupRepository.save(group);
    }

    public void delete(Long id) {
        detachProducts(id);
        groupRepository.deleteById(id);
    }

    public List<ProductGroupLinkDto> getLinks(String name) {
        ProductGroupLinkDto link = new ProductGroupLinkDto("LinkToProductList", BASIC_PATH + name);
        List<ProductGroupLinkDto> links = new ArrayList<>();
        links.add(link);
        return links;
    }

    private void detachProducts(Long id) {
        ProductGroup group = getGroupOrException(id);
        ProductGroup unbound = getUnboundGroup();
        productRepository.updateGroupId(unbound, group);
    }

    private ProductGroup getUnboundGroup() {
        ProductGroup group = groupRepository.findFirstByName(TECHNICAL_GROUP).orElseGet(() -> new ProductGroup(TECHNICAL_GROUP));
        return groupRepository.save(group);
    }

    private ProductGroup getGroupOrException(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionType.GROUP_NOT_FOUND, id.toString()));
    }
}
