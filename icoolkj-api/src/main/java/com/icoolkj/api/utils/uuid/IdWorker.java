package com.icoolkj.api.utils.uuid;

/**
 * @ProjectName: IcoolkjOracle
 * @ClassName: IdWorker
 * @Author: icoolkj
 * @Date: 2020/1/2 10:01
 */
public class IdWorker {
    private static Sequence sequence = new Sequence();

    public IdWorker() {
    }

    public static Long nextId() {
        return sequence.nextId();
    }

}
