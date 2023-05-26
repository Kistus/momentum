import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MageControllerTest {
    @Test
    public void delete1(){
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        doThrow(IllegalArgumentException.class).when(mageRepository).delete("Marcus");
        assertEquals(mageController.delete("Marcus"), "not found");
    }

    @Test
    public void delete2(){
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        doNothing().when(mageRepository).delete("Marcus");
        assertEquals(mageController.delete("Marcus"), "done");
    }

    @Test
    public void find1(){
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        when(mageRepository.find("Marcus")).thenReturn(Optional.empty());
        assertEquals(mageController.find("Marcus"), "not found");
    }

    @Test
    public void find2(){
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        Mage mage = new Mage("Marcus", 12);
        when(mageRepository.find("Marcus")).thenReturn(Optional.of(mage));
        assertEquals(mageController.find("Marcus"), "Mage{name='Marcus', level=12}");
    }

    @Test
    public void save1(){
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        doThrow(IllegalArgumentException.class).when(mageRepository).save(new Mage("Marcus", 12));
        assertEquals(mageController.save("Marcus", 12), "bad request");
    }

    @Test
    public void save2(){
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        doNothing().when(mageRepository).save(new Mage("Marcus", 12));
        assertEquals(mageController.save("Marcus", 12), "done");
    }
}