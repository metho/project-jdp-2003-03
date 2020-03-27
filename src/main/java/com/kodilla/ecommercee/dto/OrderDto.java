package com.kodilla.ecommercee.dto;
import com.kodilla.ecommercee.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long Id;

    private Date orderMade;

    private Boolean resolved;

    private GenericEntity user;

    private List<GenericEntity> products;
}
