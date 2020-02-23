package com.parsefile.dao;

import com.parsefile.model.Statistics;

public interface StatisticsDao {
    int addLine(String line);

    void addLineStatistics(Statistics statistics, int lineId);

    void addFileStatistics(Statistics statistics);
}
