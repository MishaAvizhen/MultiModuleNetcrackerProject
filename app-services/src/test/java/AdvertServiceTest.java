import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.services.config.ServiceConfig;
import com.netcracker.avizhen.services.service.AdvertService;
import com.netcracker.avizhen.services.service.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
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
    private static Logger LOG = LogManager.getLogger();

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CarService carService;

    @Test
    @Transactional
    public void printAllAdverts() {
        for (Advert advert : advertService.findAllAdverts()) {
            LOG.info(advert);
        }
    }

    @Test
    public void testOneToOneBetweenAdvertAndCar() {
        Assert.assertEquals(carService.getCount(), advertService.getCount());
    }
}
