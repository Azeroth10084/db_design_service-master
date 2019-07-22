package com.example.db_design_service.dao;

import com.example.db_design_service.bean.GetOrderList;
import com.example.db_design_service.bean.OrderList;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 *
 * 有关数据库order_list的操作
 */
@Mapper
public interface TrainTicketOrderDao {



    @Insert("insert into order_list (user_phone_number, passenger_phone_number, passenger_id_number, train_no, " +
            "start_station_no,start_station_name, end_station_no, end_station_name, carriage_no, seat_no," +
            " order_money, order_create_time, order_status, train_start_date) values (#{ orderList.user_phone_number}," +
            "#{ orderList.passenger_phone_number},#{ orderList.passenger_id_number},#{ orderList.train_no},#{ orderList.start_station_no}," +
            "#{ orderList.start_station_name}" +
            ",#{ orderList.end_station_no},#{ orderList.end_station_name},#{ orderList.carriage_no},#{ orderList.seat_no}," +
            "#{ orderList.order_money},#{ orderList.order_create_time}" +
            ",#{ orderList.order_status},#{ orderList.train_start_date})")
    void AddOrder(@Param("orderList") OrderList orderList);

    @Select("select A.order_id as order_id,B.passenger_real_name as passenger_real_name ," +
            "A.passenger_phone_number as passenger_phone_number,A.passenger_id_number as passenger_id_number," +
            "A.carriage_no  as carriage_no, C.seat_type as seat_type,A.seat_no as seat_no" +
            " from order_list as A , passenger as B ,seat as C " +
            " where A.user_phone_number = #{user_phone_number} and A.train_start_date = #{datetime} and A.train_no = #{train_no} and A.start_station_no = #{start_no} " +
            " and A.end_station_no = #{end_no} and A.train_no = C.train_no and A.carriage_no = C.carriage_no and A.passenger_phone_number = B.passenger_phone_number and A.order_status = '未支付'")
    List<GetOrderList> GetOrderList(@Param("user_phone_number") String user_phone_number, @Param("datetime") String datetime,
                                    @Param("train_no") String train_no, @Param("start_no") String start_no, @Param("end_no") String end_no);


    @Update("update order_list set order_status  = '未出行' where order_id = #{order_id}")
    void UpdateOrderPaySuccess(@Param("order_id") String order_id );





}
