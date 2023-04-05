package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * CustomErrorResponse class that will be used to let Users
 * know of any errors they may have stumbled upon.
 */
public class CustomErrorResponse {
    String errorMsg;
    int status;
    String errorCode;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime timestamp;

    /**
     * Constructor of a CustomErrorResponse Object.
     * @param errorCode errorCode of the CustomErrorResponse
     * @param errorMsg errorMsg of the CustomErrorResponse
     */
    public CustomErrorResponse(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * Getter for the errorMsg of the CustomErrorResponse Object.
     * @return Returns a String, the errorMsg of the
     *         CustomErrorResponse Object.
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter for the errorMsg of the CustomErrorResponse Object.
     * @param errorMsg String taken in to set the errorMsg of
     *                 the CustomErrorResponse Object.
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * Getter for the status of the CustomErrorResponse Object.
     * @return Returns an int, the status of the CustomErrorResponse Object.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Setter for the status of the CustomErrorResponse Object.
     * @param status int taken in to set the status of the
     *               CustomErrorResponse Object.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Getter for the errorCode of the CustomErrorResponse Object.
     * @return Returns a String, the errorCode of the
     *         CustomErrorResponse Object.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Setter for the errorCode of the CustomErrorResponse Object.
     * @param errorCode String taken in to set the errorCode of the
     *                  CustomErrorResponse Object.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter for the timeStamp of the CustomErrorResponse Object.
     * @return Returns a LocalDateTime Object, the timeStamp of
     *         the CustomErrorResponse Object.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Setter for the timeStamp of the CustomErrorResponse Object.
     * @param timestamp LocalDateTime taken in to set the timeStamp
     *                  of the CustomErrorResponse Object.
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

