import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class AdiminTest {
    @Autowired
    private AdminDao adminDao;

    @Test
    public void test1(){
        Admin admin = new Admin();
        admin.setUsername("aaa");
        admin.setPassword("aaa");
        Admin admin1 = adminDao.queryOne(admin);
        System.out.println(admin1);
    }
}
