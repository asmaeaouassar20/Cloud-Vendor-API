package com.algostyle.Cloud_Vendor_API.service.impl;

import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import com.algostyle.Cloud_Vendor_API.repository.CloudVendorRepository;
import com.algostyle.Cloud_Vendor_API.service.CloudVendorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CloudVendorServiceImplTest {

    @Mock // lorsqu'on utilise cette annotation dans les tests unitaires crée une version simulée d'une classe ou d'une interface, permettant de tester le code indépendamment de ses dépendances réelles
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    /**
     * La classe "AutoCloseable" en Java permet aux objets de libérer automatiquement
     * les ressources (comme les flux ou les connexions) lorsqu'ils sont utilisés dans un bloc
     * 'try-with-resources'
     *
     * Dans un test, "AutoCloseable" est utile pour s'assurer que les ressources sont
     * automatiquement fermées après le test, évitant ainsi les fuites de ressources
     * et garantissant que chaque test démarre avec un état propre.
     */



    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this); // initialiser les objets annotés avec "@Mock" dans la classe de test, et assigne à 'autoCloseable' la ressource à fermer ensuite pour libérer ces mocks après le test.
        cloudVendorService=new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor=new CloudVendor("1","Amazon","USA","xxxx");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);          // Créer des instances simulées (mocks) des classes "CloudVendor" et "CloudVendorRepository"
        mock(CloudVendorRepository.class);// permettant de tester le code dépendant de ces classes sans exécuter leur logique  réelle
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        Assertions.assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo("Successfully saved");
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        Assertions.assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("successfully updated");
    }


    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        Assertions.assertThat(cloudVendorService.getCloudVendor("1").getVendorName()).isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void testGetByVendorName(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findByVendorName("Amazon")).thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        Assertions.assertThat(cloudVendorService.getCloudVendorByName("Amazon").get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
    }

    @Test
    void getAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        when(cloudVendorRepository.findAll()).thenReturn(new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)));
        Assertions.assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
    }


    @Test
    void testDeleteCloudVendor() {
    }

}