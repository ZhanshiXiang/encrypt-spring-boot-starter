package com.study.idgenerator.contract;

/**
 * @Author 真香
 * @Date 2021/3/23 15:03
 * @Version 1.0
 */
public interface ISnowWorker {

    long nextId() throws IdGeneratorException;
}
