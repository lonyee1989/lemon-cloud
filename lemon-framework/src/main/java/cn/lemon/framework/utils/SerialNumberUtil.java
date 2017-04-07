package cn.lemon.framework.utils;


/**
 * 全局唯一ID生成器
 * 
 *  拼接规则
 *  000000000000000000000000000000000000000000  00000            00000       000000000000
 *  time                                        datacenterId   workerId      sequence
 */
public class SerialNumberUtil {
	private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long twepoch = 149069368700L; //2017/3/28 17:34:47
    private long workerIdBits = 5L; //节点ID长度
    private long datacenterIdBits = 5L; //数据中心ID长度
    private long maxWorkerId = -1L ^ (-1L << workerIdBits); //最大支持机器节点数0~31，一共32个
    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits); //最大支持数据中心节点数0~31，一共32个
    private long sequenceBits = 12L; //序列号12位
    private long workerIdShift = sequenceBits; //机器节点左移12位
    private long datacenterIdShift = sequenceBits + workerIdBits; //数据中心节点左移17位
    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits; //时间毫秒数左移22位
    private long sequenceMask = -1L ^ (-1L << sequenceBits); //4095
    private long lastTimestamp = -1L;
    
    private static class IdGenHolder {
        private static final SerialNumberUtil instance = new SerialNumberUtil();
    }
    
    public static SerialNumberUtil instance(){
        return IdGenHolder.instance;
    }

    public SerialNumberUtil() {
        this(0L, 0L);
    }

    public SerialNumberUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
    
    public synchronized long nextId() {
        long timestamp = timeGen(); //获取当前毫秒数
        //如果服务器时间有问题(时钟后退) 报错。
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果上次生成时间和当前时间相同,在同一毫秒内
        if (lastTimestamp == timestamp) {
            //sequence自增，因为sequence只有12bit，所以和sequenceMask相与一下，去掉高位
            sequence = (sequence + 1) & sequenceMask;
            //判断是否溢出,也就是每毫秒内超过4095，当为4096时，与sequenceMask相与，sequence就等于0
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp); //自旋等待到下一毫秒
            }
        } else {
            sequence = 0L; //如果和上次生成时间不同,重置sequence，就是下一毫秒开始，sequence计数重新从0开始累加
        }
        lastTimestamp = timestamp;
        //按照规则拼出ID
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }
    
}

/* test
public static void main(String[] arg) {
	long avg = 0;
    for (int k = 0; k < 10; k++) {
        List<Callable<Long>> partitions = new ArrayList<Callable<Long>>();
        final SerialNumberUtil idGen = SerialNumberUtil.instance();
        for (int i = 0; i < 1400000; i++) {
            partitions.add(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    return idGen.nextId();
                }
            });
        }
        ExecutorService executorPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            long s = System.currentTimeMillis();
            executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
            long s_avg = System.currentTimeMillis() - s;
            avg += s_avg;
            System.out.println("完成时间需要: " + s_avg / 1.0e3 + "秒");
            executorPool.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    System.out.println("平均完成时间需要: " + avg / 10 / 1.0e3 + "秒");
}*/