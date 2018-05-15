using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;
using Partner.Data;

namespace Partner.Service {

  [ServiceContract]
  public interface IFactureService
  {
    [OperationContract]
    [WebInvoke( Method = "POST", UriTemplate = "facture",
                RequestFormat = WebMessageFormat.Json,
                ResponseFormat = WebMessageFormat.Json)]
        void ReceiveRequest(DemandeFacturation request);
  }

}
