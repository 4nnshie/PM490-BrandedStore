package com.pm490.PM490.dto;

import com.pm490.PM490.model.PurchaseStatus;
import com.pm490.PM490.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderRequest {

    private LocalDate dateOrdered;
    private LocalDate dateShipped;

}
