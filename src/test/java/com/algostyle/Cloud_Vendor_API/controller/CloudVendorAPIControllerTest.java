package com.algostyle.Cloud_Vendor_API.controller;

import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import com.algostyle.Cloud_Vendor_API.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.List;

/**
 * L'annotation @WebMvcTest" est utilisée pour
 * teste uniquement les contrôleurs Spring MVC
 * dans un environnement de test léger, sans charger tout le contexte de l'application
 */
@WebMvcTest(CloudVendorAPIController.class)
class CloudVendorAPIControllerTest {

    // injecter un objet de type "MockMvc" , utilisé pour simuler les requêtes HTTP, et tester les contrôleurs sans démarrer un serveur réel
    @Autowired
    private MockMvc mockMvc;


    // Créer un Mock de la classe "CloudVendorService" permettant de simuler son comportement dans les tests, sans exécuter la logique réelle
    @MockBean
    private CloudVendorService cloudVendorService;

    CloudVendor cloudVendor1;
    CloudVendor cloudVendor2;
    List<CloudVendor> cloudVendorList=new ArrayList<>();


    @BeforeEach
    void setUp() {
        cloudVendor1=new CloudVendor("1","Amazon","USA","xxxx");
        cloudVendor2=new CloudVendor("2","GCP","UK","yyyyy");
        cloudVendorList.add(cloudVendor1);
        cloudVendorList.add(cloudVendor2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1")).thenReturn(cloudVendor1);
        this.mockMvc.perform(get("/cloudvendor/1 ")).andDo(print()).andExpect(status().isOk());
    }


    @Test
    void testCreateDetails() throws Exception{


    }


    @Test
    void updateDetails() {
    }

    @Test
    void testDeleteCloudVendorDetails() throws Exception{
        when(cloudVendorService.deleteCloudVendor("1")).thenReturn("cloud vendor deleted successfully");
        this.mockMvc.perform(delete("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception{
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/cloudvendor")).andDo(print()).andExpect(status().isOk());
    }
}