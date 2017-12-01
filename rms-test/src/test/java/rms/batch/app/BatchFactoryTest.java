package rms.batch.app;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rms.SpringBatchApplication;
import rms.common.abstracts.AbstractBatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBatchApplication.class)
public class BatchFactoryTest {

    @Autowired
    BatchFactory factory;

    @Test
    public void test_create_バッチクラス生成() throws Exception {
        String batchId = "B001";
        AbstractBatch batch = factory.create(batchId);
        assertTrue(batch instanceof B001Batch);
    }
}
