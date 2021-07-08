package com.zss.commons.appmodel.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseResult implements Serializable {

	private static final long serialVersionUID = 1L;

	private Map<String, String> errorList = new HashMap<String, String>();

	private String detailStack;

	public boolean isSuccess() {
		return errorList.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public <SubClass extends BaseResult> SubClass withError(String errorCode, String errorMsg) {
		errorList.put(errorCode, errorMsg);
		return (SubClass) this;
	}

	public <SubClass extends BaseResult> SubClass toErrorResult(SubClass result){
		result.setErrorList(errorList);
		return result;
	}


	/**
	 * 先判断isSuccess()才能用，多重错误时可转用getErrorList()
	 * 
	 * @return
	 */
	public String getErrorMsg() {
		if (errorList.isEmpty()) {
			return "";
		}
		return errorList.values().iterator().next();
	}


	public Map<String, String> getErrorList() {
		return errorList;
	}

	public void setErrorList(Map<String, String> errorList) {
		this.errorList = errorList;
	}

	public String getErrorCode() {
		if (errorList.isEmpty()) {
			return "";
		}
		return errorList.keySet().iterator().next();
	}

    public String getDetailStack() {
        return detailStack;
    }

    public void setDetailStack(String detailStack) {
        this.detailStack = detailStack;
    }

}
