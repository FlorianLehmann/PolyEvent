using System.Runtime.Serialization;
using System;

namespace Partner.Data {

  [DataContract(Namespace = "http://partner/external/hyperplanning/data/",
    Name = "Salle")]
  public class Salle
  {
    [DataMember]
    public string dateDebut { get; set; }
    [DataMember]
    public string dateFin { get; set; }
    [DataMember]
    public string emplacement { get; set; }
    [DataMember]
    public string nomEvenement  { get; set; }

    override public string ToString()
    {
      return "SalleRequest[" + dateDebut + ", " + dateFin + ", " + emplacement + ", " + nomEvenement + "]";
    }
  }

}
