package com.xqs.commoncore.base.request;

/**
 * @Author 迪迦.
 * @Date 2023/5/18 14:12
 */
public class BaseRequestDTO {

    private String objectCode;

    private Long objectId;

    private int pageNum;

    private int pageSize;

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

}