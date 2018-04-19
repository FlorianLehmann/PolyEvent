package fr.unice.polytech.isa.polyevent.cli.commands;

import fr.unice.polytech.isa.polyevent.cli.framework.Command;
import fr.unice.polytech.isa.polyevent.cli.framework.CommandBuilder;
import fr.unice.polytech.isa.polyevent.cli.framework.Context;
import fr.unice.polytech.isa.polyevent.stubs.DemandePrestataire;

import java.util.List;

public class AddService implements Command
{
    private static final Identifier IDENTIFIER = Identifier.ADD_SERVICE;
    private final List<DemandePrestataire> demandePrestataires;

    public AddService(List<DemandePrestataire> demandePrestataires)
    {
        this.demandePrestataires = demandePrestataires;
    }

    @Override
    public void execute() throws Exception
    {
        demandePrestataires.add(new DemandePrestataire());
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
