package com.reve.antivirus.constants;

public enum ActionMessages {
	
	USE_SEARCH(ApplicationConstant.WARNING_MESSAGE, "Use A Search Criterion"),
	NO_DATA_FOUND(ApplicationConstant.ERROR_MESSAGE, "No Data Was Found!"),
	INVALID_REQUEST(ApplicationConstant.ERROR_MESSAGE, "Inavlid Request!"),
	INVALID_DATA_FORMAT(ApplicationConstant.ERROR_MESSAGE, "Inavlid Data Format! Please Notify the Web Administrator."),
	RECEIVED_AMOUNT_GREATER_THAN_DUE(ApplicationConstant.ERROR_MESSAGE, "Received Amount Can Not Be Greater Than Due!"),
	RECEIVED_AMOUNT_ZERO(ApplicationConstant.ERROR_MESSAGE, "Received Amount Can Not Be Zero!"),
	TRY_AGAIN(ApplicationConstant.ERROR_MESSAGE, "Please Try Again!"),
	SUCCESSFULLY_UPDATED(ApplicationConstant.SUCCESS_MESSAGE, "Successfully Updated!"),
	INVALID_AMOUNT(ApplicationConstant.ERROR_MESSAGE, "Invalid Amount!"),
	TRANSACTION_ERROR(ApplicationConstant.ERROR_MESSAGE, "Balance must be equal or greater than debited amount!"),
	VALIDATION_ERROR(ApplicationConstant.ERROR_MESSAGE, "Please Check Mandatory Fields!"),
	DUPLICATE_ENTRY(ApplicationConstant.ERROR_MESSAGE, "Duplicate Entry!"),
	MULTIPLE_SUBMIT(ApplicationConstant.ERROR_MESSAGE, "Wait! You have submitted the form multiple times. Give it sometime to process."),
	SYSTEM_ERROR(ApplicationConstant.ERROR_MESSAGE, "Unexpected Error! Please Contact Web Admin."),
	SUCCESSFULLY_ADDED(ApplicationConstant.SUCCESS_MESSAGE, "Successfully Added."),
	UPDATED_WITH_EXCEPTION(ApplicationConstant.WARNING_MESSAGE, "Update, but has exceptions. Please Inform The Web Team. Thank You."),
	SERVER_NOT_AVAILABLE(ApplicationConstant.ERROR_MESSAGE, "UPSS! Server is not available. Please Inform The Web Team. Thank You."),
	MYSQL_EXCEPTION(ApplicationConstant.ERROR_MESSAGE, "OPSS! An Unexpected Condition Was Encountered. Please Inform The Web Team. Thank You."),
	FILE_SIZE_EXCEPTION(ApplicationConstant.ERROR_MESSAGE, "OPSS! Your file size has crossed the max limit."),
	FILE_FORMAT_EXCEPTION(ApplicationConstant.ERROR_MESSAGE, "OPSS! You have selected an unsupported file format."),
	ACCESS_DENIED(ApplicationConstant.WARNING_MESSAGE, "Access Denied"),
	EMAIL_SENT_SUCCESSFUL(ApplicationConstant.SUCCESS_MESSAGE, "Your Email has been successfully"),
	LOGIN_ERROR(ApplicationConstant.ERROR_MESSAGE, "Invalid Email or Password!!!"),
	OTP_ERROR(ApplicationConstant.ERROR_MESSAGE, "Invalid One Time Password!!!"),
	PERMISSION_DENIED(ApplicationConstant.ERROR_MESSAGE, "You Do Not Have Permission To Take This Action"),
	MONEY_RECEIPT_NO(ApplicationConstant.ERROR_MESSAGE, "Enter money receipt no"),
	BANK_ERROR(ApplicationConstant.ERROR_MESSAGE, "Select Bank");
	
	private ActionMessages(String type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	private String type;
	private String msg;
	
	public String getType(){
		return type;
	}
	
	public String getMsg() {
		return msg;
	}

}
