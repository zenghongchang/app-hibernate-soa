package edu.hnust.application.orm;

import java.io.Serializable;

/*
 * 文 件 名:  Customer.java
 * 描    述:  <描述>
 * 创  建  人:  Henry(fba02)
 * 创建时间:  2017年9月21日
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;// 客户ID    
    private String customerLoginName;// 登录名    
    private String customerPassWord;// 密码    
    private String passwd;// 密码明文 界面输入和显示的是明文，不允许键入中文    
    private String registeredMobilePhoneNumber;// 注册手机    
    private String registeredAttn;// 注册联系人    
    private Integer accountPeriod;// 账期(枚举：0 票结 10 周结【星期一到星期日】 20双周结【1号至15号、16号至月底】30月结【1号至月底】 40季结【按照账期日类推1号到3个月的最后一天】)    
    private String accountPeriodDate;// 账期日(time 精确到天) 选择账期日只能按照账期限制选择。例如票结不允许录入此项 周结：只允许选择为周一的实际， 双周结只允许选择1号 16号， 月结只允许选择1号， 季结只允许选择1号
    private Integer lastPaymentDate;// 最后还款日 int类型 不允许录入超过账期，票结不允许录入此项 ，如果录入的时间大于账期，则默认为账期的前一天。不允许键入负数    
    private Double extendCredit;// 欠款额度 double类型 不允许键入负数    
    private Double accountBalance;// 账户余额 double类型   
    private Double currentDebt;// 当前欠款
    private Integer showAccountBalance;// 客户端是否显示余额 枚举：0 否 1 是    
    private Integer salesman;// 销售 userid    
    private Integer customerService;// 客服 userid    
    private Double chargedWeightErrorRange;// 计费重误差范围 double不允许键入负数    
    private Integer customerLevel;// 客户等级 int 存id    
    private Integer customerType;// 类型 枚举 (0 潜在客户 10 成交客户 -1 黑名单)    
    private String customerNameShort;// 客户简称    
    private String customerFullName;// 客户全称    
    private String registeredAddress;// 注册地址    
    private String registeredAddressState;// 注册地址省    
    private String registeredAddressCity;// 注册地址城市    
    private String registeredAddressArea;// 注册地址区域 
    private String businessAddress;// 办公地址    
    private String businessAddressState;// // 办公地址省    
    private String businessAddressCity;// 包公地址城市    
    private String businessAddressArea;// 办公地址区域     
    private String financeAttnName;// 财务联系人    
    private String financeLandline;// 财务联系人座机    
    private String financeTelephone;// 财务联系人电话    
    private String financeEmail;// 财务联系人邮箱    
    private String financeQQ;// 财务联系人QQ    
    private String logisticsAttnName;// 物流联系人    
    private String logisticsLandline;// 物流联系人座机    
    private String logisticsTelephone;// 物流联系人手机    
    private String logisticsEmail;// 物流联系人邮箱    
    private String logisticsQQ;// 物流联系人QQ    
    private String legalPerson;// 法人
    private Integer saleScale;// 销售规模 枚举 10 小卖家 20 中卖家 30大卖家 40超大卖家 50同行 60直客    
    private String remark;// 备注
    private String customerToken;// 用户鉴权
    private Integer creator;// 创建人    
    private String createDate;// 创建时间    
    private Integer softDeleteFlag = 0;// 软删除标志位,删除1.默认0;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCustomerLoginName() {
        return customerLoginName;
    }

    public void setCustomerLoginName(String customerLoginName) {
        this.customerLoginName = customerLoginName;
    }

    public String getCustomerPassWord() {
        return customerPassWord;
    }

    public void setCustomerPassWord(String customerPassWord) {
        this.customerPassWord = customerPassWord;
    }

    public String getPasswd() {
        return passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public String getRegisteredMobilePhoneNumber() {
        return registeredMobilePhoneNumber;
    }
    
    public void setRegisteredMobilePhoneNumber(String registeredMobilePhoneNumber) {
        this.registeredMobilePhoneNumber = registeredMobilePhoneNumber;
    }
    
    public String getRegisteredAttn() {
        return registeredAttn;
    }
    
    public void setRegisteredAttn(String registeredAttn) {
        this.registeredAttn = registeredAttn;
    }
    
    public Integer getAccountPeriod() {
        return accountPeriod;
    }
    
    public void setAccountPeriod(Integer accountPeriod) {
        this.accountPeriod = accountPeriod;
    }
    
    public String getAccountPeriodDate() {
        return accountPeriodDate;
    }
    
    public void setAccountPeriodDate(String accountPeriodDate) {
        this.accountPeriodDate = accountPeriodDate;
    }
    
    public Integer getLastPaymentDate() {
        return lastPaymentDate;
    }
    
    public void setLastPaymentDate(Integer lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }
    
    public Double getExtendCredit() {
        return extendCredit;
    }
    
    public void setExtendCredit(Double extendCredit) {
        this.extendCredit = extendCredit;
    }
    
    public Double getAccountBalance() {
        return accountBalance;
    }
    
    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
    
    public Double getCurrentDebt() {
        return currentDebt;
    }

    public void setCurrentDebt(Double currentDebt) {
        this.currentDebt = currentDebt;
    }

    public Integer getShowAccountBalance() {
        return showAccountBalance;
    }
    
    public void setShowAccountBalance(Integer showAccountBalance) {
        this.showAccountBalance = showAccountBalance;
    }
    
    public Integer getSalesman() {
        return salesman;
    }
    
    public void setSalesman(Integer salesman) {
        this.salesman = salesman;
    }
    
    public Integer getCustomerService() {
        return customerService;
    }
    
    public void setCustomerService(Integer customerService) {
        this.customerService = customerService;
    }
    
    public Double getChargedWeightErrorRange() {
        return chargedWeightErrorRange;
    }
    
    public void setChargedWeightErrorRange(Double chargedWeightErrorRange) {
        this.chargedWeightErrorRange = chargedWeightErrorRange;
    }
    
    public Integer getCustomerLevel() {
        return customerLevel;
    }
    
    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }
    
    public Integer getCustomerType() {
        return customerType;
    }
    
    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }
    
    public String getCustomerNameShort() {
        return customerNameShort;
    }
    
    public void setCustomerNameShort(String customerNameShort) {
        this.customerNameShort = customerNameShort;
    }
    
    public String getCustomerFullName() {
        return customerFullName;
    }
    
    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }
    
    public String getRegisteredAddress() {
        return registeredAddress;
    }
    
    public void setRegisteredAddress(String registeredAddress) {
        this.registeredAddress = registeredAddress;
    }
    
    public String getRegisteredAddressState() {
        return registeredAddressState;
    }
    
    public void setRegisteredAddressState(String registeredAddressState) {
        this.registeredAddressState = registeredAddressState;
    }
    
    public String getRegisteredAddressCity() {
        return registeredAddressCity;
    }
    
    public void setRegisteredAddressCity(String registeredAddressCity) {
        this.registeredAddressCity = registeredAddressCity;
    }
    
    public String getRegisteredAddressArea() {
        return registeredAddressArea;
    }
    
    public void setRegisteredAddressArea(String registeredAddressArea) {
        this.registeredAddressArea = registeredAddressArea;
    }
    
    public String getBusinessAddress() {
        return businessAddress;
    }
    
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }
    
    public String getBusinessAddressState() {
        return businessAddressState;
    }
    
    public void setBusinessAddressState(String businessAddressState) {
        this.businessAddressState = businessAddressState;
    }
    
    public String getBusinessAddressCity() {
        return businessAddressCity;
    }
    
    public void setBusinessAddressCity(String businessAddressCity) {
        this.businessAddressCity = businessAddressCity;
    }
    
    public String getBusinessAddressArea() {
        return businessAddressArea;
    }
    
    public void setBusinessAddressArea(String businessAddressArea) {
        this.businessAddressArea = businessAddressArea;
    }
    
    public String getFinanceAttnName() {
        return financeAttnName;
    }
    
    public void setFinanceAttnName(String financeAttnName) {
        this.financeAttnName = financeAttnName;
    }
    
    public String getFinanceLandline() {
        return financeLandline;
    }
    
    public void setFinanceLandline(String financeLandline) {
        this.financeLandline = financeLandline;
    }
    
    public String getFinanceTelephone() {
        return financeTelephone;
    }
    
    public void setFinanceTelephone(String financeTelephone) {
        this.financeTelephone = financeTelephone;
    }
    
    public String getFinanceEmail() {
        return financeEmail;
    }
    
    public void setFinanceEmail(String financeEmail) {
        this.financeEmail = financeEmail;
    }
    
    public String getFinanceQQ() {
        return financeQQ;
    }
    
    public void setFinanceQQ(String financeQQ) {
        this.financeQQ = financeQQ;
    }
    
    public String getLogisticsAttnName() {
        return logisticsAttnName;
    }
    
    public void setLogisticsAttnName(String logisticsAttnName) {
        this.logisticsAttnName = logisticsAttnName;
    }
    
    public String getLogisticsLandline() {
        return logisticsLandline;
    }
    
    public void setLogisticsLandline(String logisticsLandline) {
        this.logisticsLandline = logisticsLandline;
    }
    
    public String getLogisticsTelephone() {
        return logisticsTelephone;
    }
    
    public void setLogisticsTelephone(String logisticsTelephone) {
        this.logisticsTelephone = logisticsTelephone;
    }
    
    public String getLogisticsEmail() {
        return logisticsEmail;
    }
    
    public void setLogisticsEmail(String logisticsEmail) {
        this.logisticsEmail = logisticsEmail;
    }
    
    public String getLogisticsQQ() {
        return logisticsQQ;
    }
    
    public void setLogisticsQQ(String logisticsQQ) {
        this.logisticsQQ = logisticsQQ;
    }
    
    public String getLegalPerson() {
        return legalPerson;
    }
    
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
    
    public Integer getSaleScale() {
        return saleScale;
    }
    
    public void setSaleScale(Integer saleScale) {
        this.saleScale = saleScale;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getCustomerToken() {
        return customerToken;
    }

    public void setCustomerToken(String customerToken) {
        this.customerToken = customerToken;
    }

    public Integer getCreator() {
        return creator;
    }
    
    public void setCreator(Integer creator) {
        this.creator = creator;
    }
    
    public String getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public Integer getSoftDeleteFlag() {
        return softDeleteFlag;
    }

    public void setSoftDeleteFlag(Integer softDeleteFlag) {
        this.softDeleteFlag = softDeleteFlag;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", customerLoginName=" + customerLoginName + ", customerPassWord=" + customerPassWord + ", passwd=" + passwd + ", registeredMobilePhoneNumber=" + registeredMobilePhoneNumber + ", registeredAttn="
            + registeredAttn + ", accountPeriod=" + accountPeriod + ", accountPeriodDate=" + accountPeriodDate + ", lastPaymentDate=" + lastPaymentDate + ", extendCredit=" + extendCredit + ", accountBalance=" + accountBalance
            + ", currentDebt=" + currentDebt + ", showAccountBalance=" + showAccountBalance + ", salesman=" + salesman + ", customerService=" + customerService + ", chargedWeightErrorRange=" + chargedWeightErrorRange + ", customerLevel="
            + customerLevel + ", customerType=" + customerType + ", customerNameShort=" + customerNameShort + ", customerFullName=" + customerFullName + ", registeredAddress=" + registeredAddress + ", registeredAddressState="
            + registeredAddressState + ", registeredAddressCity=" + registeredAddressCity + ", registeredAddressArea=" + registeredAddressArea + ", businessAddress=" + businessAddress + ", businessAddressState=" + businessAddressState
            + ", businessAddressCity=" + businessAddressCity + ", businessAddressArea=" + businessAddressArea + ", financeAttnName=" + financeAttnName + ", financeLandline=" + financeLandline + ", financeTelephone=" + financeTelephone
            + ", financeEmail=" + financeEmail + ", financeQQ=" + financeQQ + ", logisticsAttnName=" + logisticsAttnName + ", logisticsLandline=" + logisticsLandline + ", logisticsTelephone=" + logisticsTelephone + ", logisticsEmail="
            + logisticsEmail + ", logisticsQQ=" + logisticsQQ + ", legalPerson=" + legalPerson + ", saleScale=" + saleScale + ", remark=" + remark + ", customerToken=" + customerToken + ", creator=" + creator + ", createDate=" + createDate
            + ", softDeleteFlag=" + softDeleteFlag + "]";
    }
}