# Rapport Projet Team H
## Points positifs et négatifs

-   Notre architecture s’approche de celle d’une architecture micro-services. Cela nous donne une facilité d’extension ainsi qu’une factorisation importante du code. En revanche, si notre projet venait à grandir on se retrouverait avec une architecture “spaghetti”. De plus, la technologie JEE n’est pas adapté pour ce type d’architecture.
    
-   Notre architecture utilise des composants qui sont destinés à communiquer avec la base de donnée, cela nous permet de factoriser le code à un seul endroit mais cela crée des goulots d'étranglement dans notre architecture.
    
-   Nous utilisons des objets spécifique pour communiquer avec le client ce qui nous permet de transmettre seulement les informations importantes et de transmettre des objets non persistant.
    
-   Pour factoriser les vérifications effectués sur les objets de communications, nous utilisons des intercepteurs. Par exemple, le système donne un Token de connexion lorsque l’utilisateur se connecte au système. Ce token permet à l’utilisateur d'accéder à tous les services nécessitant d’être connecté mais il n’est valide que pour un certain temps. Pour factoriser le contrôle de la date de validité du Token, nous utilisons un intercepteur qui se place avant l’appel aux différents services et qui empêche la requête d’être effectué si le Token n’est plus valide.
    
-   Comme nos composants, nos web services sont fins ce qui permet au client d’utiliser uniquement les fonctionnalités qu’il souhaite intégrer. Cependant si un client veut utiliser un grand nombre de services, il devra importer un grand nombre de wsdl différents.
    
-   Notre système se veut robuste quand à la disponibilité ou non des services externe notamment grâce à la mise en place d’un MOM qui va de manière asynchrone tenter à 3 reprise d’atteindre le service externe de la comptabilité avant de signaler l’impossibilité d’effectuer la demande.
