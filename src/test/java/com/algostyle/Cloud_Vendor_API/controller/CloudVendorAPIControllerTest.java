package com.algostyle.Cloud_Vendor_API.controller;

import com.algostyle.Cloud_Vendor_API.exception.CloudVendorAlreadyExists;
import com.algostyle.Cloud_Vendor_API.exception.CloudVendorNotFoundException;
import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import com.algostyle.Cloud_Vendor_API.repository.CloudVendorRepository;
import com.algostyle.Cloud_Vendor_API.service.CloudVendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @MockBean
    private CloudVendorRepository cloudVendorRepository;

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
        this.mockMvc.perform(get("/cloudvendor/1"))
                .andDo(print()) // Affiche la réponse JSON dans la console
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.vendorId").value("1"))  // Accède à l'intérieur du champ "data"
                .andExpect(jsonPath("$.data.vendorName").value("Amazon"))
                .andExpect(jsonPath("$.message").value("returning requested cloud vendor")); // Vérifier le message
    }


    @Test
    void testCreateDetails_SUCCESS() throws Exception{
        // Simuler la réponse du service
        when(cloudVendorService.createCloudVendor(Mockito.any())).thenReturn("Successfully saved");

        // Créons l'objet à envoyer dans la requête
        // Utilisez un ObjectMapper pour sérialiser l'objet en JSON
        ObjectMapper objectMapper=new ObjectMapper();
        String cloudVendorJson= objectMapper.writeValueAsString(cloudVendor1);

        // Simuler la requête POST avec le JSON sérialisé
        mockMvc.perform(post("/cloudvendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cloudVendorJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cloud Vendor created successfully"));
    }


    /**
     *
     * Simuler le comportement du service
     * Vérifier que la méthode createDetails réagit correctement lorsque l'exception {@link CloudVendorAlreadyExists est levée}
     */
    @Test
    @DisplayName("raise exception when create details of cloud vendor already exists")
    void testCreate_FAILED() throws Exception {

        // le cloud vendor qui existe déjà
        CloudVendor cloudVendor=new CloudVendor("1","Amazon","USA","xxxx");

        // Simuler l'exception lorsque la méthode createCloudVendor est appelée
        when(cloudVendorService.createCloudVendor(any(CloudVendor.class)))
                .thenThrow(new CloudVendorAlreadyExists("Cloud Vendor already exists"));

        // Tester la méthode createDetails en simulant une requête POST
        this.mockMvc.perform(post("/cloudvendor") // l'URL de la méthode createDetails
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cloudVendor))) // corps de la requête
                .andExpect(status().isConflict()); //409
    }

    @Test
    void testUpdateDetails_SUCCESS() throws Exception{
        // Créer un objet CloudVendor
        CloudVendor cloudVendor=new CloudVendor("1","newCloudVendor","newAddress","123456789");

        // Simuler le comportement de la méthode updateCloudVendor
        Mockito.when(cloudVendorService.updateCloudVendor(Mockito.any(CloudVendor.class)))
                .thenReturn("successfully updated");

        // Convertir l'objet CloudVendor en JSON
        ObjectMapper objectMapper=new ObjectMapper();
        String cloudVendorJson=objectMapper.writeValueAsString(cloudVendor);

        // Effectuez une requête PUT et vérifiez la réponse
        this.mockMvc.perform(put("/cloudvendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cloudVendorJson))
                .andExpect(status().isOk()) // Vérifier que le statut HTTP est 200 OK
                .andExpect(content().string("cloud vendor updated successfully"));
    }

    @Test
    @DisplayName("should raise exception because cloud vendor does not exist")
    void testUpdateDetails_FAILED() throws Exception{
        // Créer un objet CloudVendor
        CloudVendor cloudVendor=new CloudVendor();

        // Simuler l'exception levée par le service
        Mockito.doThrow(new CloudVendorNotFoundException("cloud vendor not found"))
                .when(cloudVendorService).updateCloudVendor(Mockito.any(CloudVendor.class));

        // Convertissez l'objet CloudVendor en JSON
        ObjectMapper objectMapper=new ObjectMapper();
        String cloudVendorJson=objectMapper.writeValueAsString(cloudVendor);

        // Effectuez une requête PUT et vérifiez la réponse
        this.mockMvc.perform(put("/cloudvendor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cloudVendorJson))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("cloud vendor not found"));
    }

    @Test
    void testDeleteCloudVendorDetails() throws Exception{
        when(cloudVendorService.deleteCloudVendor("1")).thenReturn("cloud vendor deleted successfully");

        this.mockMvc.perform(delete("/cloudvendor/1")).andDo(print()).andExpect(status().isOk());


        // 1. Vérifier que l'ID passé dans l'URL est bien transmis au service
        verify(cloudVendorService).deleteCloudVendor("1");


        //this.mockMvc.perform(delete("/cloudvendor/8")).andDo(print()).andExpect(status().isOk()); // Attention si vous utilisez cette assertion toute seule, ça sera toujours vrai, même si id=8 n'existe pas, car on ne valide que le statut HTTP de la réponse et non la logique métier
    }



    // Cas nominal : Suppression réussie
    @Test
    void testDeleteCloudVendor_Success() throws Exception{

        // ARRANGE : Configurer le comportement
        String vendorId="1";
        when(cloudVendorRepository.findById(vendorId)).thenReturn(Optional.of(cloudVendor1));
        doNothing().when(cloudVendorRepository).deleteById(vendorId);

        // ACT : Simuler une requête DELETE
        this.mockMvc.perform(delete("/cloudvendor/1"))
                .andExpect(status().isOk()) // Statut 200 attendu
                .andExpect(content().string("cloud vendor deleted successfully")); // Vérifie le message de réponse

        // ASSERT : Vérifier les appels au dépôt
        verify(cloudVendorService,times(1)).deleteCloudVendor(vendorId);
    }




    /**
     * --------   Cas où le fournisseur est introuvable  ----------------
     * - On écrit un test unitaire dans lequel un identifiant inexistant est fourni à la méthode
     * - L'objectif est de vérifier que l'exception {@link com.algostyle.Cloud_Vendor_API.exception.CloudVendorNotFoundException} est bien levée
     */
    @Test
    @DisplayName("delete cloud vendor when id not found should throw exceptin")
    void testDeleteCloudVendor_NotFound() throws Exception{
        //ARRANGE
        // ID d'un fournisseur inexistant
        String nonExistantVendorId="999";

        // Simuler le comportement du repository pour retourner une valeur vide
       // when(cloudVendorRepository.findById(nonExistantVendorId)).thenReturn(Optional.empty());

        // Configurer le service pour lancer l'exception
        doThrow(new CloudVendorNotFoundException("cloud vendor not found"))
                .when(cloudVendorService).deleteCloudVendor(nonExistantVendorId);


        // ACT & ASSERT
        // Envoyer une requête DELETE et vérifier le résultat
        mockMvc.perform(delete("/cloudvendor/999"))
                .andExpect(status().isNotFound())  // Vérifier que le statut est 404
                .andExpect(jsonPath("$.message").value("cloud vendor not found"));  // Vérifier le message d'erreur

        // Vérifier que la méthode a été appelée correctement
        verify(cloudVendorService,times(1)).deleteCloudVendor(nonExistantVendorId);
    }



    @Test
    void testGetAllCloudVendorDetails() throws Exception{
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/cloudvendor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].vendorId").value("1"))
                .andExpect(jsonPath("$[1].vendorName").value("GCP"));
    }
}