package com.zss.commons.support.utils;

import java.text.DecimalFormat;

public class StatsUtil {

    /**
     * 统计当前应用的内存使用情况
     * @return
     */
    public static String memoryStatistic() {
        Runtime runtime = Runtime.getRuntime();

        double freeMemory = (double) runtime.freeMemory() / (1024 * 1024);
        double maxMemory = (double) runtime.maxMemory() / (1024 * 1024);
        double totalMemory = (double) runtime.totalMemory() / (1024 * 1024);
        double usedMemory = totalMemory - freeMemory;
        double percentFree = ((maxMemory - usedMemory) / maxMemory) * 100.0;

        double percentUsed = 100 - percentFree;

        DecimalFormat mbFormat = new DecimalFormat("#0.00");
        DecimalFormat percentFormat = new DecimalFormat("#0.0");

        StringBuilder sb = new StringBuilder();
        sb.append(mbFormat.format(usedMemory)).append("MB of ").append(mbFormat.format(maxMemory)).append(" MB (")
                .append(percentFormat.format(percentUsed)).append("%) used");
        return sb.toString();
    }
}
