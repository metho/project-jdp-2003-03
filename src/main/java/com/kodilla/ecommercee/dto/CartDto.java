package com.kodilla.ecommercee.dto;
import com.kodilla.ecommercee.GenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;

    private List<GenericEntity> products;
}