package me.hsanchez.digital_library.dao.queries;

public class DeliveryTimeQueries {
	public static final String INSERT_DELIVERY_TIME = "INSERT INTO DELIVERY_TIME(TIME, UNIT) VALUES(?,?)";
	public static final String DELETE_DELIVERY_TIME_BY_ID = "DELETE FROM DELIVERY_TIME WHERE ID = ?";
	public static final String UPDATE_DELIVERY_TIME = "UPDATE DELIVERY_TIME SET `TIME` = ?, UNIT = ? WHERE ID = ?";
}
