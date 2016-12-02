import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.services.config.ServiceConfig;
import com.netcracker.avizhen.services.service.AdvertService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Александр on 30.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class AdvertServiceTest {

    @Autowired
    private AdvertService advertService;

    @Test
    @Transactional
    public void printAllAdverts() {
        for (Advert advert : advertService.findAllAdverts()) {
            System.out.println(advert);
        }
    }
}
