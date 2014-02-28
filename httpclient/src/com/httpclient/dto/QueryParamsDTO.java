package com.httpclient.dto;

import java.util.List;

public class QueryParamsDTO {
private String startDate;
private String endDate;
private String id_kind ;
private List<String> listId_no;
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getEndDate() {
	return endDate;
}
public void setEndDate(String endDate) {
	this.endDate = endDate;
}
public String getId_kind() {
	return id_kind;
}
public void setId_kind(String id_kind) {
	this.id_kind = id_kind;
}
public List<String> getListId_no() {
	return listId_no;
}
public void setListId_no(List<String> listId_no) {
	this.listId_no = listId_no;
}

}
