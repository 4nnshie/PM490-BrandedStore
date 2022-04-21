package com.pm490.PM490.dto;

import com.pm490.PM490.model.PurchaseStatus;
import lombok.Data;

@Data
public class ItemListRequest {
    private long idProduct;
    private int quantity;
}
