package com.pm490.PM490.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BooleanDto {
    private  boolean bool;
    public static BooleanDto build(boolean bool){
        return new BooleanDto(bool);
    }
}