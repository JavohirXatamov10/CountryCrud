package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.PrimitiveIterator;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Region {
    private Integer id;
    private String name;
    private Integer countryId;
}
