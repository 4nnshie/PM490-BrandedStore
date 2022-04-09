// Author Angie
package com.pm490.PM490.dto;

import com.pm490.PM490.model.Category;
import com.pm490.PM490.model.ProductStatus;
import com.pm490.PM490.model.Vendor;
import lombok.Data;
@Data
public class ProductRequest {

    private String name;
    private String color;
    private long idVendor;
    private ProductStatus status;
    private int quantity;
    private long idCategory;
    private double price;

}
