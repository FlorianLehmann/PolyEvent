package fr.unice.polytech.isa.polyevent.entities;

public class TypeService {
        private String type;

        public TypeService(){};

        public TypeService(String type){
            this.type = type;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeService that = (TypeService) o;

        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}
