import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public class test {

    public static void main(String[] args) {
        ByteSource byteSource = ByteSource.Util.bytes("f58711612143a7ada2676e515f3dedd0");
        System.out.println(byteSource);
    }

}
