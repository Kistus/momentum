import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection = new HashSet<Mage>();

    public MageRepository(){}

    public Optional<Mage> find(String name) {
        for(Mage mage : this.collection){
            if(mage.getName().equals(name)){
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }
    public void delete(String name) throws IllegalArgumentException{
        if(this.collection.isEmpty() || !this.collection.remove(this.find(name).get())){
            throw new IllegalArgumentException();
        }
    }
    public void save(Mage mage) throws IllegalArgumentException{
        if(!this.collection.add(mage)){
            throw new IllegalArgumentException();
        }
    }

    public Collection<Mage> getCollection() {
        return collection;
    }
}
