import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class BannerTest {
    @Autowired
    private BannerDao bannerDao;

    @Test
    public void test1(){
        List<Banner> banners = bannerDao.queryByPage(1, 1);
        System.out.println(banners);
    }

    @Test
    public void test2(){

    }
}
