package com.parsefile.dao;

import com.parsefile.model.Statistics;

public interface StatisticsDao {
    void addLine(String line);
    void addStatistics(Statistics statistics);
}
