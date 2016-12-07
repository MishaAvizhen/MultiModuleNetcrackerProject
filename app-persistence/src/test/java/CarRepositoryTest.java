import com.netcracker.avizhen.persistence.config.PersistenceConfig;
import com.netcracker.avizhen.persistence.entity.Car;
import com.netcracker.avizhen.persistence.repository.CarRepository;
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
 * Created by Александр on 06.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class CarRepositoryTest {
    private static Logger LOG = LogManager.getLogger();

    @Autowired
    private CarRepository carRepository;

    @Test
    @Transactional
    public void printAllCars() {
        for (Car car : carRepository.getAll()) {
            LOG.info(car);
        }
    }

    @Test
    public void findByParams() {
        for (Car car : carRepository.getAll()) {
            Car findCar = carRepository.findByMakeAndPriceBetweenAndYearBetween(car.getMake(), car.getPrice(), car.getPrice(),
                    car.getYear(), car.getYear()).get(0);
            Assert.assertEquals(car.getId(), findCar.getId());
        }
    }


}
