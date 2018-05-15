3 scenarios sont présent dans polyeventclients/organisateur/demo

Pour lancer un des scenario présent ci-dessous utiliser la commande play demo/demoX.pe 
ou X est le numéro du scénario.

demo1:

register marcel@mail.com
login marcel@mail.com
event "La nuit de l'info" 2018-12-03T16:00:00 2018-12-04T08:00:00
reservation AMPHI 2018-12-03T16:00:00 2018-12-03T18:00:00
reservation SALLE 2018-12-03T18:00:00 2018-12-04T08:00:00
reservation SALLE 2018-12-03T18:00:00 2018-12-04T08:00:00
reservation SALLE 2018-12-03T18:00:00 2018-12-04T08:00:00
service CAFE 2018-12-03T18:00:00 2018-12-04T08:00:00
validate
list
logout

Ce scénario réalise les actions suivantes:
- Enregistrement d'un utilisateur
- Connexion d'un utilisateur
- Demande la réservation de 4 salles pour un événement
- Ajout d'un service à l'événement
- liste les événements
- deconnexion de l'utilisateur
 

demo2:
register bob@mail.com
login bob@mail.com
event "Week End Startup" 2018-06-23T16:00:00 2018-06-25T08:00:00
reservation AMPHI 2018-06-23T16:00:00 2018-06-23T18:00:00
reservation SALLE 2018-06-23T18:00:00 2018-06-25T08:00:00
reservation SALLE 2018-06-23T18:00:00 2018-06-25T08:00:00
reservation AMPHI 2018-06-25T08:00:00 2018-06-25T10:00:00
validate
pay 123456789 "Week End Startup" 2018-06-23T16:00:00 2018-06-25T08:00:00
logout

Ce scénario réalise les actions suivantes:
- Enregistrement d'un utilisateur
- Connexion d'un utilisateur
- Demande la réservation de 4 salles pour un événement
- Paiement
- deconnexion de l'utilisateur

demo3:
register alice@etu.unice.com
login alice@etu.unice.com
event "Code Retreat" 2018-12-03T16:00:00 2018-12-04T08:00:00
reservation AMPHI 2018-12-03T16:00:00 2018-12-03T18:00:00
reservation SALLE 2018-12-03T18:00:00 2018-12-04T08:00:00
reservation SALLE 2018-12-03T18:00:00 2018-12-04T08:00:00
reservation SALLE 2018-12-03T18:00:00 2018-12-04T08:00:00
validate
invoice "Code Retreat" 2018-12-03T16:00:00 2018-12-04T08:00:00
logout

Ce scénario réalise les actions suivantes:
- Enregistrement d'un utilisateur
- Connexion d'un utilisateur
- Demande la réservation de 4 salles pour un événement
- Demande d'une facture
- deconnexion de l'utilisateur
