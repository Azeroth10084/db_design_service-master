package com.example.db_design_service.dao;

import com.example.db_design_service.bean.TrainScheduleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrainScheduleDao {



    @Select("select C.train_no as train_no ,C.train_number as train_number ,\n" +
            " C.station_name as start_station ,D.station_name as end_station ,\n" +
            " C.station_no as start_no , D.station_no as  end_no  ,\n" +
            " C.start_time as start_time , D.arrive_time as arrive_time,\n" +
            " C.running_time as start_running_time ,D.running_time as end_running_time \n" +
            " from train_parking_station as C ,train_parking_station as D \n" +
            " where C.train_no = D.train_no \n" +
            " and C.station_name = #{start_station} and D.station_name = #{end_station}\n" +
            " and C.train_no in (select A.train_no from \n" +
            "train_parking_station as A ,train_parking_station as B \n" +
            "where  A.train_no = B.train_no and \n" +
            "A.station_name = #{start_station} and\n" +
            " B.station_name = #{end_station} \n" +
            " and A.station_no <B.station_no)")
    List<TrainScheduleInfo>  searchTrainSchedule(@Param("start_station") String start_station , @Param("end_station") String end_station);
    @Select("select A.train_no as train_no, A.train_number as train_number ,\n" +
            "A.station_name as start_station ,B.station_name as end_station , \n" +
            " A.station_no as start_no , B.station_no as  end_no  ,\n" +
            "  A.start_time as start_time , B.arrive_time as arrive_time,\n" +
            "   A.running_time as start_running_time ,B.running_time as end_running_time \n" +
            "    from train_parking_station as A ,train_parking_station as B \n" +
            "    where A.station_no between #{start_no} and #{end_no} \n" +
            "    and  B.station_no between #{start_no} and #{end_no} \n" +
            "    and A.train_no = #{train_no} \n" +
            "    and A.train_no = B.train_no \n" +
            "    and B.station_no = A.station_no +1 ")
    List<TrainScheduleInfo>  searchTrainScheduleList(@Param("train_no") String train_no,@Param("start_no") String start_no , @Param("end_no") String end_no);

}
