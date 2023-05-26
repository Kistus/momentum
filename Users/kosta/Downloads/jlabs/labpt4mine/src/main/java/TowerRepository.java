import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TowerRepository {
    private final EntityManager entityManager;
    public TowerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Tower> findAll() {
        return entityManager.createQuery("from Tower").getResultList();
    }

    public void update(Tower tower){
        try {
            entityManager.getTransaction().begin();
            entityManager.refresh(tower);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Tower> save(Tower tower) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(tower);
            entityManager.getTransaction().commit();
            return Optional.of(tower);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void remove(Tower tower){
        entityManager.getTransaction().begin();
        for (Mage mage : tower.getMages()){
            entityManager.remove(mage);
        }
        entityManager.remove(tower);
        entityManager.getTransaction().commit();
    }
}
