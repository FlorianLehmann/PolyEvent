
# HyperPlanning (.Net)
## Code architecture

The code is kept as simple as possible, and consists in only four files:

  * `BusinessObjects.cs`: The different data structure to be used to support the payment service (_i.e._, `Salle`);
  * `ISalleService.cs`: the interface that models the resources exposed by the service:
    * a list of all `salle` performed on the system;
  * `SalleService.cs`: the concrete class that implement the previously described interface;
  * `Server.cs`: this main class starts an HTTP server and binds the implemented service to it.
    
## Running the client

To compile the client, you need to use a version of Mono that bundles the _Windows Communication Foundations_ (WCF) framework. Recent versions of mono include it natively. To compile all the C# source code with the WCF package available and create a `server.exe` binary, simply run the following command:

    azrael:dotNet mosser$ mcs src/*.cs -pkg:wcf -out:server.exe  
     
Then, one can start the `server.exe` using the mono runtime environment:

    azrael:dotNet mosser$ mono server.exe
    Starting a WCF self-hosted .Net server... done!
    
    Listening to localhost:9090
    
    Hit Return to shutdown the server.  
    
## Route
### Get localhost:9090/salle
List of all reserved room

### Post localhost:9090/salle
Create a new reserved room
Format request
{
  "dateDebut": "",
  "dateFin": "",
  "emplacement": "",
  "nomEvenement": ""
}