/*
 * 版权属于：yitter(yitter@126.com)
 * 开源地址：https://gitee.com/yitter/idgenerator
 */
package com.study.idgenerator.idgen;

import com.study.idgenerator.contract.IIdGenerator;
import com.study.idgenerator.contract.ISnowWorker;
import com.study.idgenerator.contract.IdGeneratorException;
import com.study.idgenerator.contract.IdGeneratorOptions;
import com.study.idgenerator.core.SnowWorkerM1;
import com.study.idgenerator.core.SnowWorkerM2;

public class DefaultIdGenerator implements IIdGenerator {

    private final ISnowWorker _SnowWorker;

    public DefaultIdGenerator(IdGeneratorOptions options) throws IdGeneratorException {
        if (options == null) {
            throw new IdGeneratorException("options error.");
        }

        if (options.BaseTime < 315504000000L || options.BaseTime > System.currentTimeMillis()) {
            throw new IdGeneratorException("BaseTime error.");
        }

        if (options.SeqBitLength + options.WorkerIdBitLength > 22) {
            throw new IdGeneratorException("error：WorkerIdBitLength + SeqBitLength <= 22");
        }

        double maxWorkerIdNumber = Math.pow(2, options.WorkerIdBitLength) - 1;
        if (options.WorkerId < 0 || options.WorkerId > maxWorkerIdNumber) {
            throw new IdGeneratorException("WorkerId error. (range:[1, " + maxWorkerIdNumber + "]");
        }

        if (options.SeqBitLength < 2 || options.SeqBitLength > 21) {
            throw new IdGeneratorException("SeqBitLength error. (range:[2, 21])");
        }

        double maxSeqNumber = Math.pow(2, options.SeqBitLength) - 1;
        if (options.MaxSeqNumber < 0 || options.MaxSeqNumber > maxSeqNumber) {
            throw new IdGeneratorException("MaxSeqNumber error. (range:[1, " + maxSeqNumber + "]");
        }

        double maxValue = maxSeqNumber;
        if (options.MinSeqNumber < 1 || options.MinSeqNumber > maxValue) {
            throw new IdGeneratorException("MinSeqNumber error. (range:[1, " + maxValue + "]");
        }

        switch (options.Method) {
            case 1:
                _SnowWorker = new SnowWorkerM1(options);
                break;
            case 2:
                _SnowWorker = new SnowWorkerM2(options);
                break;
            default:
                _SnowWorker = new SnowWorkerM1(options);
                break;
        }

        if (options.Method == 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long newLong() {
        return _SnowWorker.nextId();
    }
}
