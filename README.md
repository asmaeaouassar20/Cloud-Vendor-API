### controller/CloudVendorAPIController
- Cette classe est un contrôleur d'API REST, qui gère les opérations CRUD pour les "Cloud Vendors" en fournissant plusieurs points de terminaison (endpoints) de l'API.
- Structure de l'API
  - GET ```/cloudvendor/{vendorId}``` : Récupère les détail d'un cloudVendor en fonction de son identifiant.
  - GET ```/cloudvendor``` : Récupère la liste de tous les fournisseurs de cloud
  - POST ```cloudvendor``` : Crée un nouveau fournisseur de cloud en envoyant les données via le corps de la requête.
  - PUT ```/cloudvendor``` : Met à jour les informationq d'un fournisseur de cloud existant.
  - DELETE ```/cloudvendor/{vendorId}``` : Supprime un vendorCloud en fonction de son identifiant.
- Réponse unifiée avec ```ResponseHandler``` :
  - On a crée un utilitaire appelé ```ResponseHandler``` pour formater les réponses HTTP.
  - Cela garantit une gestion uniforme des réponses, avec un message personnalisé, un statut HTTP, et les données associées.
- La classe est annotée avec ```@RestController```, ce qui signiife que c'est un contôleur de type REST, qui est une partie essentielle de l'architecture microservices dans Spring Boot.

### exception/CloudVendorAlreadyExists
- Cette classe ```CloudVendorAlreadyExists``` est une exception personnalisée qui est utilisée pour signaler qu'une tentative de création ou d'ajout d'un VendorCloud  échoue parce que ce fournisseur existe déjà dans le système.
- Elle étend ```RuntimeException```.
