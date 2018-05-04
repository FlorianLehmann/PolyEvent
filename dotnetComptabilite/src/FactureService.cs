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
	public class FactureService : IFactureService
	{

		public void ReceiveRequest(DemandeFacturation request)
		{
			Console.WriteLine("ReceiveRequest");
		}
	}
}
