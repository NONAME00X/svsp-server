package edu.cxy.svspcxy.service;

import edu.cxy.svspcxy.entity.Report;

import java.util.List;

public interface ReportService {

    List<Report> getAll();

    void commitState(Integer id, String state, Integer vid);
}
