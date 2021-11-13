package com.example.demo.designModel.serverResbility;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
	public static void main(String[] args){
		Msg msg = new Msg();
		msg.setMsg("<baidu.com>,false,looser");
		FilterMachine filterMachine = new FilterMachine();
		FilterMachine add = filterMachine.add(new StrFilter());
		FilterMachine filterMachine1 = new FilterMachine();
		FilterMachine add1 = filterMachine1.add(new UrlFilter());
		add.add(add1);
		add.doFilter(msg);
		System.out.println(msg);
	}

}

interface Filter{
	Boolean doFilter(Msg msg);
}
class Msg{
	String name;
	String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Msg{" +
			"msg='" + msg + '\'' +
			'}';
	}
}

class StrFilter implements Filter{

	@Override
	public Boolean doFilter(Msg msg) {
		String msgs = msg.getMsg().replace("<", "[").replace(">", "]");
		msg.setMsg(msgs);
		return true;
	}
}

class UrlFilter implements Filter{

	@Override
	public Boolean doFilter(Msg msg) {
		String msgs = msg.getMsg().replace("baidu.com", "https://baidu.com");
		msg.setMsg(msgs);
		return true;
	}
}

class FilterMachine implements Filter {
	List<Filter> filters = new ArrayList();

	public FilterMachine add(Filter filter){
		filters.add(filter);
		return this;
	}

	@Override
	public Boolean doFilter(Msg msg) {
		for (Filter filter : filters) {
			if (!(filter.doFilter(msg))){
				return false;
			}
		}
		return true;
	}
}