package com.ruiliang.appsrv.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ruiliang.appsrv.pojo.DeviceInfo;

/**
 * @author LinJian.Liu
 *
 */
@Mapper
public interface DeviceInfoDAO {
	
	/**
	 * 添加设备信息
	 * @param di
	 * @return
	 */
	Integer insertDeviceInfo(DeviceInfo di);
}
