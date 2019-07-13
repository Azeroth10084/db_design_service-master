package com.example.db_design_service.controller;


import com.example.db_design_service.bean.*;
import com.example.db_design_service.dao.TrainParkingStationDao;
import com.example.db_design_service.service.TrainInfoService;
import com.example.db_design_service.service.TrainParkingStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainInfoSelectController {
    private static final Logger logger = LoggerFactory.getLogger(TrainInfoSelectController.class);
    @Resource
    private TrainInfoService  trainInfoService;

    @Resource
    private TrainParkingStationService trainParkingStationService;

    @RequestMapping(value ="/traininfo",method = RequestMethod.GET)
    public TrainInfoReturnData TrainInfo(Integer offset,Integer limit)
    {

        List<TrainInfo>  trainInfos = trainInfoService.selectAllTrainInfo(offset,limit);


        if(!trainInfos.isEmpty())
        {
            return new TrainInfoReturnData(1,trainInfos);
        }

        return new TrainInfoReturnData(404,trainInfos);
    }

    @RequestMapping(value ="/searchtraininfo",method = RequestMethod.GET)
    public SearchTrainInfoReturnData SearchTrainInfo(String train_number)
    {

        TrainInfo trainInfo = trainInfoService.selectTrainInfo(train_number);
        if(!trainInfo.toString().equals(""))
        {
            return new SearchTrainInfoReturnData(1, trainInfo);
        }

            return new SearchTrainInfoReturnData(404,trainInfo);
        }

    @RequestMapping(value ="/searchtrainparkingInfo",method = RequestMethod.GET)
    public TrainParkingInfoReturnData SearchTrainInfoList(String train_number)
    {
        logger.info(train_number);
        List<TrainParkingInfo> trainParkingInfos = trainParkingStationService.selectTrainParkingInfo(train_number);
        logger.info(String.valueOf(trainParkingInfos.size()));
        for(TrainParkingInfo trainParkingInfo :trainParkingInfos)
        {
            logger.info(trainParkingInfo.getStation_no());
        }
        return new TrainParkingInfoReturnData(1,trainParkingInfos);


    }

}