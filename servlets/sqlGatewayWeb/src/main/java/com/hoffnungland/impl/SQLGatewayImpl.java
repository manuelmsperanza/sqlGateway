package com.hoffnungland.impl;

import java.sql.SQLException;
import java.text.ParseException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.hoffnungland.SQLGateway;
import com.hoffnungland.model.SQLGatewayRequest;
import com.hoffnungland.model.SQLGatewayResponse;

@WebService(endpointInterface = "***REMOVED***.SQLGateway")
public class SQLGatewayImpl implements SQLGateway {
	
	private static final Logger logger = LogManager.getLogger(SQLGateway.class);

	public SQLGatewayResponse invokeSql(SQLGatewayRequest request) throws SQLGatewayException {
		logger.traceEntry();
    	SQLGatewayWorker sqlGatewayWorker = new SQLGatewayWorker();
    	SQLGatewayResponse sqlGatewayResponse = null;
		try {
			sqlGatewayResponse = sqlGatewayWorker.executeRequest(request);
		} catch (SQLException | ParseException | NamingException e) {
			logger.catching(e);
			throw new SQLGatewayException("Exception during SQLGatewayRequest ", e);
		}
        return logger.traceExit(sqlGatewayResponse);
	}

}
