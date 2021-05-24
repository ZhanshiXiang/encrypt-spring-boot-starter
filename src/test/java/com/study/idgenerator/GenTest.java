package com.study.idgenerator;

import com.study.idgenerator.contract.IIdGenerator;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashSet;
import java.util.Set;
@SpringBootTest
public class GenTest {

    private IIdGenerator IdGen;
    private int GenIdCount;
    private int WorkerId;
    private Set IdSet = new HashSet();

    public GenTest(IIdGenerator idGen, int genIdCount, int workerId) {
        GenIdCount = genIdCount;
        IdGen = idGen;
        WorkerId = workerId;
    }

    public void GenStart() {
        long start = System.currentTimeMillis();

        for (int i = 0; i < GenIdCount; i++) {
            long id = IdGen.newLong();
            // IdSet.add(id);
        }

        long end = System.currentTimeMillis();
        long time = end - start;

        System.out.println("++++++++++++++++++++++++++++++++++++++++WorkerId: "
                + WorkerId + ", total: " + time + " ms");

    }
}
