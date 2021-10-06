package com.obss.onlinemarketplace.dto;

import com.obss.onlinemarketplace.File.FileAttachmentVM;
import com.obss.onlinemarketplace.model.Product;
import com.obss.onlinemarketplace.model.Seller;
import lombok.Data;

@Data
public class ProductVM {

    private long id;

    private String name;

    private String content;

    private long price;

    private FileAttachmentVM fileAttachmentVM;

    private SellerVM seller;

    public ProductVM(Product product) {
        this.setId(product.getId());
        this.setContent(product.getContent());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        if (product.getSeller()!=null){
            this.seller=new SellerVM(product.getSeller());
        }
        if (product.getFileAttachment() != null) {
            this.fileAttachmentVM = new FileAttachmentVM(product.getFileAttachment());
        }
    }
}
