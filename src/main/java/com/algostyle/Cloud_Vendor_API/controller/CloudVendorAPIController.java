package com.algostyle.Cloud_Vendor_API.controller;


import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import com.algostyle.Cloud_Vendor_API.response.ResponseHandler;
import com.algostyle.Cloud_Vendor_API.service.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorAPIController {

    public CloudVendorAPIController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    CloudVendorService cloudVendorService;



    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){

        return ResponseHandler.responseBuilder("returning requested cloud vendor",
                HttpStatus.OK,
                cloudVendorService.getCloudVendor(vendorId));
    }
    @GetMapping()
    public List<CloudVendor> getAllCloudVendorDetails(){
        return cloudVendorService.getAllCloudVendors();
    }


    @PostMapping
    public String createDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.createCloudVendor(cloudVendor);
        return "Cloud Vendor created successfully";
    }



    @PutMapping
    public String updateDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "cloud vendor updated successfully";
    }



    @DeleteMapping("{vendorId}")
    public String delete(@PathVariable("vendorId") String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "cloud vendor deleted successfully";
    }
}
