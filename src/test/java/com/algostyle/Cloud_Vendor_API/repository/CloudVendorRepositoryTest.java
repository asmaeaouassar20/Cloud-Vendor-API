package com.algostyle.Cloud_Vendor_API.repository;

import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest // C'est une annotation de test dans Spring Boot qui configure uniquement les composants JPA por tester les repositories en isolation
public class CloudVendorRepositoryTest {

    // given - when - then
    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp(){
        cloudVendor=new CloudVendor("1","Amazon","USA","xxxx");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown(){
        cloudVendor=null;
        cloudVendorRepository.deleteAll();
    }


    // Test case SUCCESS
    @Test
    void testFindByVendorName_Success(){
         List<CloudVendor> cloudVendorList= cloudVendorRepository.findByVendorName("Amazon");
         assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
         assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }


    // Test case FAILURE
    @Test
    void testFindByVendorName_Failure(){
        List<CloudVendor> cloudVendorList=cloudVendorRepository.findByVendorName("GCP");
        assertThat(cloudVendorList.size()).isEqualTo(0);
        assertThat(cloudVendorList.isEmpty()).isTrue();
    }

}
