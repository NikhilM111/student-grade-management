package com.nikhil.studentgrade.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {

    private Long StudentId;
    private String name;
    private String email;
    private String phone;
    private Long createdBy;
    private Date createdOn;

}
