package com.algostyle.Cloud_Vendor_API.controller;


import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import com.algostyle.Cloud_Vendor_API.response.ResponseHandler;
import com.algostyle.Cloud_Vendor_API.service.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur API pour gérer les opérations CRUD sur les fournisseurs de cloud (Cloud Vendors)
 * Ce contrôleur expose des points de terminaison pour créer, lire, mettre à jour et supprimer des informations
 * sur les fournisseurs de services cloud dans le système
 */

@RestController
@RequestMapping("/cloudvendor")  // Définir la base de l'URL pour les endpoints de ce contrôleur
public class CloudVendorAPIController {

    // Service de gestion des fournisseurs de cloud injecté par le constructeur
    CloudVendorService cloudVendorService;
    public CloudVendorAPIController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }


    /**
     * Point de terminaison pour récupérer les détails d'un fournisseur de cloud spécifique
     * @param vendorId ID du fournisseur de cloud à récupérer
     * @return Détails du VendorCloud ou une réponse d'erreur si introuvable
     */
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId){
        // On fait appel à notre service qu'on a crée pour obtenir les détails du VendorCloud
        return ResponseHandler.responseBuilder("returning requested cloud vendor",
                HttpStatus.OK,
                cloudVendorService.getCloudVendor(vendorId));
    }


    /**
     * Point de terminaison pour récupérer tous les fournisseurs de cloud
     * @return Liste de tous les fournissurs de cloud qu'on a dans la BD
     */
    @GetMapping()
    public List<CloudVendor> getAllCloudVendorDetails(){
        return cloudVendorService.getAllCloudVendors();
    }


    /**
     * Point de terminaison pour créer un nouveau fournisseur de cloud
     * @param cloudVendor
     * @return Réponse indiquant si la création a réussi
     */
    @PostMapping
    public ResponseEntity<Object> createDetails(@RequestBody CloudVendor cloudVendor){
        return ResponseHandler.responseBuilder("Cloud Vendor created successfully"
                        ,HttpStatus.OK
                        ,cloudVendorService.createCloudVendor(cloudVendor));
    }




    /**
     * Point de terminaison pour mettre à jour les détails d'un fournisseur de cloud
     * @param cloudVendor Objet contenant les informations mises à jour du fournisseur de cloud
     * @return Message de confirmation de mise à jour réussie
     */
    @PutMapping
    public String updateDetails(@RequestBody CloudVendor cloudVendor){
        cloudVendorService.updateCloudVendor(cloudVendor);
        return "cloud vendor updated successfully";
    }


    /**
     * Point de terminaison pour supprimer un fournisseur de cloud spécifique
     * @param vendorId ID du fournisseur de cloud à supprimer
     * @return Message de confirmation de suppression réussie
     */
    @DeleteMapping("{vendorId}")
    public String delete(@PathVariable("vendorId") String vendorId){
        cloudVendorService.deleteCloudVendor(vendorId);
        return "cloud vendor deleted successfully";
    }
}
