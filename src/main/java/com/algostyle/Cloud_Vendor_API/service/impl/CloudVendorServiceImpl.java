package com.algostyle.Cloud_Vendor_API.service.impl;

import com.algostyle.Cloud_Vendor_API.exception.CloudVendorAlreadyExists;
import com.algostyle.Cloud_Vendor_API.exception.CloudVendorNotFoundException;
import com.algostyle.Cloud_Vendor_API.model.CloudVendor;
import com.algostyle.Cloud_Vendor_API.repository.CloudVendorRepository;
import com.algostyle.Cloud_Vendor_API.service.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * - Implémenter le service pour la gestion des fournisseurs de cloud.
 * - Fournir les méthodes nécessaires pour effectuer les opérations CRUD et la gestion des exceptions personnalisées
 */

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }


    /**
     * Crée un "CloudVendor" après avoir vérifié son existence
     *
     * @param cloudVendor Les informations du fournisseur à créer
     * @return Message confirmant la création
     * @throws CloudVendorAlreadyExists si le fournisseur existe déjà
     */
    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        if(!cloudVendorRepository.findById(cloudVendor.getVendorId()).isEmpty())
            throw new CloudVendorAlreadyExists("Cloud Vendor already exists");
        cloudVendorRepository.save(cloudVendor);
        return "Successfully saved";
    }





    /**
     * Met à jour un fournisseur de cloud existant
     *
     * @param cloudVendor Les informations mises à jour du fournisseur
     * @return Message confirmant la mise à jour
     * @throws CloudVendorNotFoundException si le fournisseur n'existe pas
     */
    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        if(cloudVendorRepository.findById(cloudVendor.getVendorId()).isEmpty()){
            throw new CloudVendorNotFoundException("cloud vendor not found");
        }
        cloudVendorRepository.save(cloudVendor);
        return "successfully updated";
    }






    /**
     * Supprime un fournisseur de cloud par son ID
     * @param cloudVendorId L'ID du fournisseur à supprimer
     * @return Message confirmant la suppression
     * @throws CloudVendorNotFoundException si le fournisseur à supprimer n'existe pas
     */
    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty()){
            throw new CloudVendorNotFoundException("cloud vendor not found");
        }
        cloudVendorRepository.deleteById(cloudVendorId);
        return "successfully deleted";
    }






    /**
     * Récupère les détails d'un fournisseur de cloud par son ID
     * @param cloudVendorId L'ID du fournisseur à récupérer
     * @return Les informations du fournisseur
     * @throws CloudVendorNotFoundException si le fournisseur à n'existe pas
     */
    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
           throw new CloudVendorNotFoundException("Requested Cloud Vendor does not exist");
        return cloudVendorRepository.findById(cloudVendorId).get();
    }









    /**
     * Récupère la liste de tous les "Cloud Vendors"
     *
     * @return La Liste des fournisseurs disponibles
     */
    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }






    /**
     * Récupère une liste de fournisseurs de cloud par leur nom
     *
     * @param name Le nom du fournisseur à rechercher
     * @return La liste des fournisseurs correspondants
     */
    @Override
    public List<CloudVendor> getCloudVendorByName(String name) {
       return cloudVendorRepository.findByVendorName(name);
    }
}
