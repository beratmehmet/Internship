package com.obss.onlinemarketplace.File;

import com.obss.onlinemarketplace.model.Seller;
import com.obss.onlinemarketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FileAttachmentRepository extends JpaRepository<FileAttachment,Long> {

    List<FileAttachment> findByDateBeforeAndProductIsNull(Date date);

    List<FileAttachment> findByProductUser(User user);

    List<FileAttachment> findByProductSeller(Seller seller);
}
