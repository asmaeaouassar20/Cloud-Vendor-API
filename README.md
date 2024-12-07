# Architecture et Implémentation des API REST
## CONTROLLER
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

## EXCEPTION
### exception/CloudVendorAlreadyExists
- Cette classe ```CloudVendorAlreadyExists``` est une exception personnalisée qui est utilisée pour signaler qu'une tentative de création ou d'ajout d'un VendorCloud  échoue parce que ce fournisseur existe déjà dans le système.
- Elle étend ```RuntimeException```.

### exception/CloudVendorException
- La classe ```CloudVendorException``` est une exception personnalisée utilisée pour encapsuler les informations liées aux erreurs survenant dans l'application.
- Elle permet de fournir un message explicatif, une exception sous-jacente (cause) et un code de statut HTTP approprié pour indiquer l'état de l'erreur.

### exception/CloudVendorExceptionHandlerController
- On a crée la classe ```CloudVendorExceptionHandlerController```  comme **gestionnaire global des exceptions dans notre application Spring Boot**
- Elle utilise l'annotation ```@ControllerAdvice``` pour capturer et gérer des exceptions spécifiques, afin de fournir des réponses HTTP appropriées aux erreurs.

### exception/CloudVendorNotFoundException
On a crée cette Exception qui sera levée lorsqu'un fournisseur Clous n'est pas trouvé dans le système

## MODEL
### model/CloudVendor
Pour Représenter notre entité de **fournisseur cloud** dans l'application.

## RESPONSE
### response/ResponseHandler
- La classe ```ResponseHandler``` est utilisée pour centraliser la gestion des réponses HTTP.
- Elle permet de construire des réponses structurées qui incluent un message descriptif, un code de statut HTTP et des données sous forme d'objet.
- Elle est caractérisée par l'utilisation de la méthode statique ```responseBuilder```, qui prend en charge la création d'une réponse sous la forme d'une ```ResponseEntity```.
- Cette classe est essentielle pour standardiser la manière dont les réponses sont renvoyées aux clients, assurant une gestion cohérente des erreurs et des données dans l'API.

<img src="https://github.com/user-attachments/assets/74b58cef-e5c8-4f50-ac74-98545f41eeee"><br>
*En résumé*, elle facilite le contrôle de la réponse HTTP tout en encapsulant les informations pertinentes dans un format structuré et compréhensible.

## SERVICE
### service/impl/CloudVendorServiceImpl
- Cette classe implémente l'interface ```CloudVendorService``` et fournit une logique métier pour gérer les opérations CRUD.
- Elle est caractérisée par l'utilisation de ```CloudVendorRepository``` pour la persistence des données.
- Elle est caractérisée aussi par la gestion des exceptions via ```CloudVendorAlreadyExists``` et ```CloudVendorNotFoundException``` pour gérer les cas d'erreur.

# Testing
- Les testing dans les applications Spring Boot MVC consiste à tester les contrôleurs via ```MockMvc```, qui simule des requêtes HTTP et vérifie les réponses (statut, contenu, modèle, etc).
- On utilise généralement ```WebMvcTest``` pour tester les contrôleurs spécifiquement, et ```MockMvc``` pour effectuer les tests sans démarrer un serveur complet.
- Les assertions se font avec des **matchers** comme ```status()```, ```view()``` et ```model()```.
- **ResultMatcher** est une interface utilisée dans les tests Spring MVC pour valider les résultats des requêtes HTTP simulées avec ```MockMvc```. Elle permet de vérifier des éléments comme le statut HTTP, la vue, le modèle ou le contenu de la réponse.
- **Le cas nominal :** est le scénario où tout se passe bien, comme prévu (C'est le comportement attendu dans des conditions normales)
## controller
### GET
<img src="https://github.com/user-attachments/assets/6d868522-9b55-4e2a-a996-7b5fc3a6b854">

### DELETE
<img src="https://github.com/user-attachments/assets/d193896c-9a0e-4a12-9750-14cb34e980a9">
