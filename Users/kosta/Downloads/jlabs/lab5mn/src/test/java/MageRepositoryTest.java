import org.junit.Test;
import java.util.Optional;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.junit.Assert.assertEquals;


public class MageRepositoryTest {
    @Test
    public void find1(){
        MageRepository mageRepository = new MageRepository();
        assertEquals(mageRepository.find("Marcus"), Optional.empty());
    }

    @Test
    public void find2(){
        MageRepository mageRepository = new MageRepository();
        Mage mage = new Mage("Marcus", 20);
        mageRepository.save(mage);
        assertEquals(mageRepository.find("Marcus"), Optional.of(mage));
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete() throws IllegalArgumentException{
        MageRepository mageRepository = new MageRepository();
        mageRepository.delete("Marcus");
    }

    @Test(expected = IllegalArgumentException.class)
    public void save() throws IllegalArgumentException{
        MageRepository mageRepository = new MageRepository();
        mageRepository.save(new Mage("John", 12));
        mageRepository.save(new Mage("Marcus", 13));
        mageRepository.save(new Mage("Marcus", 11));
    }
}
