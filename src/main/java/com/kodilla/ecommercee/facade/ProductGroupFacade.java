package com.kodilla.ecommercee.facade;

import com.kodilla.ecommercee.dto.group.ProductGroupDto;
import com.kodilla.ecommercee.dto.group.ProductGroupExtDto;
import com.kodilla.ecommercee.exception.EntityAlreadyExistsException;
import com.kodilla.ecommercee.exception.ExceptionType;
import com.kodilla.ecommercee.mapper.ProductGroupMapper;
import com.kodilla.ecommercee.repository.ProductGroupRepository;
import com.kodilla.ecommercee.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupFacade {

    @Autowired
    private ProductGroupMapper groupMapper;

    @Autowired
    private ProductGroupService groupService;

    @Autowired
    private ProductGroupRepository groupRepository;

    public List<ProductGroupDto> fetchGetGroups() {
        return groupMapper.mapToGroupDtoList(groupService.getGroups());
    }

    public ProductGroupExtDto fetchGetGroup(Long id) {
        ProductGroupExtDto productGroupExtDto = groupMapper.mapToGroupExtDto(groupService.getGroup(id));
        productGroupExtDto.setLinks(groupService.getLinks(productGroupExtDto.getName()));
        return productGroupExtDto;
    }

    public void fetchCreateGroup(ProductGroupDto groupDto) {
        if (groupRepository.existsByName(groupDto.getName())) {
            throw new EntityAlreadyExistsException(ExceptionType.GROUP_FOUND, groupDto.getName());
        }
        groupService.create(groupMapper.mapToGroup(groupDto));
    }

    public ProductGroupDto fetchUpdateGroup(ProductGroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupService.update(groupDto));
    }

    public void fetchDeleteGroup(Long id) {
        groupService.delete(id);
    }
}
