package com.example.db_design_service.service;

import com.example.db_design_service.bean.TrainScheduleInfo;
import com.example.db_design_service.dao.TrainScheduleDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TrainScheduleService {

    @Resource
    private TrainScheduleDao trainScheduleDao;
    public List<TrainScheduleInfo> searchTrainScheduleInfo(String start_station,String end_station)
    {
            return trainScheduleDao.searchTrainSchedule(start_station,end_station);
    }
}
