package fr.unice.polytech.isa.polyevent.entities.interceptors;

import fr.unice.polytech.isa.polyevent.entities.PlageHorraire;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.List;

public class VerifierPlageHorraire
{
    private boolean verifierParametre(Object[] parameters)
    {
        for (Object parameter : parameters)
        {
            if (parameter instanceof List && !verifierPlagesHorraires((List) parameter))
            {
                return false;
            }
        }
        return true;
    }

    private boolean verifierPlagesHorraires(List list)
    {
        for (Object o : list)
        {
            if (o instanceof PlageHorraire && !verifierPlageHorraire((PlageHorraire) o))
            {
                return false;
            }
        }
        return true;
    }

    private boolean verifierPlageHorraire(PlageHorraire plageHorraire)
    {
        if (plageHorraire.getDateDebut() == null || plageHorraire.getDateFin() == null)
        {
            return false;
        }
        return plageHorraire.getDateFin().after(plageHorraire.getDateDebut());
    }

    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception
    {
        if (!verifierParametre(ctx.getParameters()))
            throw new IllegalArgumentException("Date de fin precede date de debut");
        return ctx.proceed();
    }
}
