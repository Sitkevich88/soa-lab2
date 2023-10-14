package ru.itmo.soa.spec;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria<T> {
    private String key;
    private String operation;
    private T value;
}