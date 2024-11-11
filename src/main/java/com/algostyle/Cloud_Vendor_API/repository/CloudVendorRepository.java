package com.algostyle.Cloud_Vendor_API.repository;

import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor,String> {

}
