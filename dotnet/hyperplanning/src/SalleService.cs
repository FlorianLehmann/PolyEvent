using System;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;
using System.Linq;
using Partner.Data;

namespace Partner.Service {

  // The service is stateful, as it is only a Proof of Concept.
  // Services should be stateless, this is for demonstration purpose only.
  [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
  public class SalleService : ISalleService
  {

    private List<Salle> salles = new List<Salle>();

    public void ReceiveRequest(Salle request)
    {
      Console.WriteLine("ReceiveRequest: " + request);
      salles.Add(request);
    }


    public List<Salle> GetSalleReserve()
    {
      return salles;
    }

  }
}
