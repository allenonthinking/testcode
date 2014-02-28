package com.httpclient.dto;


public class TrainInfoDTO  {

	private String statist_date;// 统计日期,即数据采样截止日期

	private String id_name;
	
	private String id_kind;// 证件类型

	private String id_no;// 证件编号

	private String train_date;// 乘车日期

	private String start_time;// 发时,即上车时间

	private String from_station_name;// 发站

	private String to_station_name;// 到站

	private String board_train_code;// 车次


	private String stutsMessage;
	
	public String getStutsMessage() {
		return stutsMessage;
	}

	public void setStutsMessage(String stutsMessage) {
		this.stutsMessage = stutsMessage;
	}


	public String getTrain_date() {
		return train_date;
	}

	public void setTrain_date(String train_date) {
		this.train_date = train_date;
	}

	public String getId_kind() {
		return id_kind;
	}

	public void setId_kind(String id_kind) {
		this.id_kind = id_kind;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public String getBoard_train_code() {
		return board_train_code;
	}

	public void setBoard_train_code(String board_train_code) {
		this.board_train_code = board_train_code;
	}



	public String getFrom_station_name() {
		return from_station_name;
	}

	public void setFrom_station_name(String from_station_name) {
		this.from_station_name = from_station_name;
	}

	public String getTo_station_name() {
		return to_station_name;
	}

	public void setTo_station_name(String to_station_name) {
		this.to_station_name = to_station_name;
	}


	public String getStatist_date() {
		return statist_date;
	}

	public void setStatist_date(String statist_date) {
		this.statist_date = statist_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}


//	@Override
//	public String toString() {
//		return "SaleRecordDTO [id_kind=" + id_kind + ", id_no=" + id_no
//				+ ", statist_date=" + statist_date + ", train_date="
//				+ train_date + ", board_train_code=" + board_train_code
//				+ ", from_tele_code="  + ", to_tele_code="
//				 + ", from_station_name=" + from_station_name
//				+ ", to_station_name=" + to_station_name + ", start_time="
//				+ start_time + ", coach_no="  + ", seat_no="
//				 + ", seat_type_code=" 
//				+ ", ticket_type="  + ", ticket_price="
//				 + ", inner_code="  + ", sale_time="
//				 + ", statistics_date=" 
//				+ ", office_no="  + ", window_no=" 
//				+ ", ticket_no="  + ", operater_no=" 
//				+ ", sale_mode="  + ", record_type="
//				+ "]";
//	}

	public String getId_name() {
		return id_name;
	}

	public void setId_name(String id_name) {
		this.id_name = id_name;
	}


}