package fr.unice.polytech.isa.polyevent.entities.interceptors;

import fr.unice.polytech.isa.polyevent.entities.Token;
import fr.unice.polytech.isa.polyevent.entities.exceptions.TokenInvalideException;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.Date;

public class VerifierToken implements Serializable
{
    @AroundInvoke
    public Object intercept(InvocationContext ctx) throws Exception
    {
        for (Object o : ctx.getParameters())
        {
            if (o instanceof Token)
            {
                Token token = (Token) o;
                if (token.getDateDeValidite().before(new Date()))
                {
                    throw new TokenInvalideException();
                }
            }
        }
        return ctx.proceed();
    }
}
