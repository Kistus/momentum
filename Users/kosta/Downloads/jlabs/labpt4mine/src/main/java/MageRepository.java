import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class MageRepository {
    private final EntityManager entityManager;
    public MageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Mage> findAll() {
        return entityManager.createQuery("from Mage").getResultList();
    }

    public Optional<Mage> save(Mage mage) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(mage);
            entityManager.getTransaction().commit();
            return Optional.of(mage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Mage> findByName(String name) {
        Mage mage = entityManager.createQuery("SELECT b FROM Mage b WHERE b.name = :name", Mage.class)
                .setParameter("name", name)
                .getSingleResult();
        return mage != null ? Optional.of(mage) : Optional.empty();
    }


    public void remove(Mage mage){
        mage.getTower().getMages().remove(mage);
        entityManager.getTransaction().begin();
        entityManager.remove(mage);
        entityManager.getTransaction().commit();
    }
}