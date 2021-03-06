package com.ruiliang.appsrv.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ruiliang.appsrv.pojo.OperLog;

/**
 * @author LinJian.Liu
 *
 */
@Mapper
public interface OperLogDAO {
	
	/**
	 * 保存操作日志
	 * @param ol
	 * @return
	 */
	Integer saveOperLog(OperLog ol);
}
