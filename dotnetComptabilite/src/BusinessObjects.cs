using System.Runtime.Serialization;
using System;

namespace Partner.Data
{

    [DataContract(Namespace = "http://partner/external/hyperplanning/data/",
      Name = "DemandeReservationSalle")]
    public class DemandeFacturation
    {
        [DataMember]
        public string facture { get; set; }
       

        override public string ToString()
        {
            return "SalleRequest[" + facture + "]";
        }
    }
}
