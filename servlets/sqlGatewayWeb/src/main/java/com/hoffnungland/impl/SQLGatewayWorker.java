package com.hoffnungland.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoffnungland.model.SQLGatewayParam;
import com.hoffnungland.model.SQLGatewayRequest;
import com.hoffnungland.model.SQLGatewayResponse;
import com.hoffnungland.model.ValueTypeEnum;

public class SQLGatewayWorker {
	
	private static final Logger logger = LogManager.getLogger(SQLGatewayWorker.class);
	
	public SQLGatewayResponse executeRequest(SQLGatewayRequest request) throws SQLException, ParseException, NamingException {
		logger.traceEntry();
		SQLGatewayResponse response = new SQLGatewayResponse();

		Context initContext = new InitialContext();
		//Context envContext  = (Context) initContext.lookup("java:/comp/env");
		javax.sql.DataSource ds = (javax.sql.DataSource) initContext.lookup("jdbc/__defaultDataSource");
		
		Connection dsConnection = ds.getConnection();
		
		StringBuilder invokeStm = new StringBuilder("{");

		if(request.getReturnValueType() != null) {
			invokeStm.append("? = ");
		}
		
		invokeStm.append("call " + request.getInvokeName());
		
		if(request.getParams() != null && request.getParams().length > 0) {
			invokeStm.append("(");
			boolean isFirst = true;
			for(int i = 0; i < request.getParams().length; i++) {
				if(isFirst) {
					invokeStm.append("?");
					isFirst = false;
				} else {
					invokeStm.append(", ?");
				}
			}
			invokeStm.append(")");
		}
		
		invokeStm.append("}");
		
		logger.debug(invokeStm.toString());
		CallableStatement invokeCallableStm = dsConnection.prepareCall(invokeStm.toString());
		int paramIdx = 0;
		if(request.getReturnValueType() != null) {
			int sqlType = -1;
			switch(request.getReturnValueType()) {
			case VARCHAR2:
				sqlType = java.sql.Types.VARCHAR;
				break;
			case NUMBER:
				sqlType = java.sql.Types.NUMERIC;
				break;
			case DATE:
				sqlType = java.sql.Types.DATE;
			case BOOLEAN:
				sqlType = java.sql.Types.BOOLEAN;
				break;
			}
			invokeCallableStm.registerOutParameter(++paramIdx, sqlType);
		}
		
		for(SQLGatewayParam curParam : request.getParams()) {
			paramIdx++;
			
			switch(curParam.getDataDirection()) {
			case IN:
			case INOUT:
				
				switch(curParam.getDataType()) {
				case VARCHAR2:
					invokeCallableStm.setString(paramIdx, curParam.getValue());
					break;
				case NUMBER:
					invokeCallableStm.setLong(paramIdx, Long.parseLong(curParam.getValue()));
					break;
					
				case DATE:
					java.util.Date dateValue =  new SimpleDateFormat(curParam.getFormat()).parse(curParam.getValue());
					java.sql.Date sqlDateValue = new java.sql.Date(dateValue.getTime());
					invokeCallableStm.setDate(paramIdx, sqlDateValue);
					break;
				case BOOLEAN:
					invokeCallableStm.setBoolean(paramIdx, Boolean.parseBoolean(curParam.getValue()));
					break;
				}
				
				break;
			case OUT:
				break;
			}
			
			switch(curParam.getDataDirection()) {
			case IN:
				break;
			case INOUT:
			case OUT:
				int sqlType = -1;
				switch(curParam.getDataType()) {
				case VARCHAR2:
					sqlType = java.sql.Types.VARCHAR;
					break;
				case NUMBER:
					sqlType = java.sql.Types.NUMERIC;
					break;
				case DATE:
					sqlType = java.sql.Types.DATE;
					break;
				case BOOLEAN:
					sqlType = java.sql.Types.BOOLEAN;
					break;
				}
				
				invokeCallableStm.registerOutParameter(paramIdx, sqlType);
				break;
			}
			
		}
		
		logger.info("execute " + request.getInvokeName());
		invokeCallableStm.execute();
		paramIdx = 0;
		if(request.getReturnValueType() != null) {
			String returnValue = null;			
			switch(request.getReturnValueType()) {
			case VARCHAR2:
				returnValue = invokeCallableStm.getString(paramIdx);
				break;
			case NUMBER:
				returnValue = Long.toString(invokeCallableStm.getLong(paramIdx));
				break;
			case DATE:
				java.sql.Date sqlDateValue = invokeCallableStm.getDate(paramIdx);
				java.util.Date dateValue = new java.util.Date(sqlDateValue.getTime());
				returnValue = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(dateValue);
				break;
			case BOOLEAN:
				returnValue = Boolean.toString(invokeCallableStm.getBoolean(paramIdx));
				break;
			}
			
			response.setReturnValue(returnValue);
			
		}
		
		for(SQLGatewayParam curParam : request.getParams()) {
			paramIdx++;
			
			switch(curParam.getDataDirection()) {
			case IN:
				break;
			case INOUT:
			case OUT:
				String paramValue = null;
				switch(curParam.getDataType()) {
				case VARCHAR2:
					paramValue = invokeCallableStm.getString(paramIdx);
					break;
				case NUMBER:
					paramValue = Long.toString(invokeCallableStm.getLong(paramIdx));
					break;
				case DATE:
					java.sql.Date sqlDateValue = invokeCallableStm.getDate(paramIdx);
					java.util.Date dateValue = new java.util.Date(sqlDateValue.getTime());
					paramValue = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(dateValue);
					break;
				case BOOLEAN:
					paramValue = Boolean.toString(invokeCallableStm.getBoolean(paramIdx));
					break;
				}
				curParam.setValue(paramValue);
				break;
			}
			
		}
		
		response.setParams(request.getParams());
		
		return logger.traceExit(response);
	}

}
