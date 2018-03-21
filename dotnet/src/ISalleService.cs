using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;

using Partner.Data;

namespace Partner.Service {

  [ServiceContract]
  public interface ISalleService
  {
    [OperationContract]
    [WebInvoke( Method = "POST", UriTemplate = "salle",
                RequestFormat = WebMessageFormat.Json,
                ResponseFormat = WebMessageFormat.Json)]
    void ReceiveRequest(Salle request);


    [OperationContract]
    [WebInvoke( Method = "GET", UriTemplate = "salle",
                ResponseFormat = WebMessageFormat.Json)]
    List<Salle> GetSalleReserve();

}

}
