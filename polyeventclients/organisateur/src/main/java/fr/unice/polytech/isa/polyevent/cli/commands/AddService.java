package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.DemandePrestataire;
import fr.unice.polytech.isa.polyevent.stubs.TypeSalle;
import fr.unice.polytech.isa.polyevent.stubs.TypeService;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddService implements Command
{
    private static final Identifier IDENTIFIER = Identifier.ADD_SERVICE;
    private final List<DemandePrestataire> demandePrestataires;
    private TypeService typeService;
    private XMLGregorianCalendar dateDebut;
    private XMLGregorianCalendar dateFin;

    AddService(List<DemandePrestataire> demandePrestataires)
    {
        this.demandePrestataires = demandePrestataires;
    }

    private static String availableRoomType()
    {
        return Arrays.stream(TypeService.values()).map(Enum::name).collect(Collectors.joining(", "));
    }

    @Override
    public void load(List<String> args) throws Exception
    {
        if (args.size() < 3)
        {
            String message = String.format("%s expects 3 arguments: %s TYPE_ROOM START_DATE END_DATE", IDENTIFIER, IDENTIFIER);
            throw new IllegalArgumentException(message);
        }
        String value = args.get(0);
        try
        {
            typeService = TypeService.valueOf(value);
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(String.format("The service type \"%s\" does not exist.%n" +
                            "Choose one type from the following list: %s.%nType help %s for more details.",
                    value, availableRoomType(), IDENTIFIER));
        }
        try
        {
            DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
            dateDebut = datatypeFactory.newXMLGregorianCalendar(args.get(1));
            dateFin = datatypeFactory.newXMLGregorianCalendar(args.get(2));
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException(String.format("Illegal date format: %s", e.getMessage()));
        }
    }

    @Override
    public void execute() throws Exception
    {
        DemandePrestataire demandePrestataire = new DemandePrestataire();
        demandePrestataire.setDateDebut(dateDebut);
        demandePrestataire.setDateFin(dateFin);
        demandePrestataire.setTypeService(typeService);
        demandePrestataires.add(demandePrestataire);
    }

    public static class Builder implements CommandBuilder<AddService>
    {
        private final List<DemandePrestataire> demandePrestataires;

        public Builder(List<DemandePrestataire> demandePrestataires)
        {
            this.demandePrestataires = demandePrestataires;
        }

        @Override
        public String identifier()
        {
            return IDENTIFIER.keyword;
        }

        @Override
        public String describe()
        {
            return IDENTIFIER.description;
        }

        @Override
        public AddService build(Context context)
        {
            return new AddService(demandePrestataires);
        }
    }
}
