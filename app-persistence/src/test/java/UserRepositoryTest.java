import com.netcracker.avizhen.persistence.config.PersistenceConfig;
import com.netcracker.avizhen.persistence.entity.User;
import com.netcracker.avizhen.persistence.repository.UserRepository;
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
 * Created by Александр on 29.11.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
public class UserRepositoryTest {
    private static Logger LOG = LogManager.getLogger();

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void printAllUser() {
        for (User user : userRepository.getAll()) {
            LOG.info(user);
        }
    }

    @Test
    public void testFindByUsername() {
        for (User user : userRepository.getAll()) {
            Assert.assertNotNull(userRepository.findByUserName(user.getUserName()));
        }
    }

}
