package com.sam.lab4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Mail {

    private String from;
    private String to;
    private String content;
    private String subject;
}
