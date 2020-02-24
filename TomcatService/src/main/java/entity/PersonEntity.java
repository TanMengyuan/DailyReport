package entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * @author mengyuantan
 */
@ExcelTarget("PersonEntity")
public class PersonEntity implements java.io.Serializable {

    private int id;

    @Excel(name = "姓名", height = 15, width = 10, isImportField = "true_st")
    private String name;

    @Excel(name = "是否有发热症状", height = 15, width = 25, isImportField = "true_st")
    private String isFever;

    @Excel(name = "是否与疫区人员接触", height = 15, width = 25, isImportField = "true_st")
    private String isContact;

    @Excel(name = "工作简报", height = 15, width = 35, isImportField = "true_st")
    private String report;

    @Excel(name = "其他", height = 15, width = 35, isImportField = "true_st")
    private String others;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsFever() {
        return isFever;
    }

    public void setIsFever(String isFever) {
        this.isFever = isFever;
    }

    public String getIsContact() {
        return isContact;
    }

    public void setIsContact(String isContact) {
        this.isContact = isContact;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
