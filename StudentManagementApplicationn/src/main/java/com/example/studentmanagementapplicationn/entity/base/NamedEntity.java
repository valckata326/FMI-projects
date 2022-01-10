package com.example.studentmanagementapplicationn.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public abstract class NamedEntity {
    @Column
    private String name;
}
