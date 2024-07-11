package edu.cxy.svspcxy.mapper;

import edu.cxy.svspcxy.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<Report> getAll();

    void commitState(@Param("id") Integer id,@Param("state") String state);
}
