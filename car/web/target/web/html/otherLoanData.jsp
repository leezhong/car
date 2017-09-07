<%@ page import="com.teamshopping.teamshopping.util.SYSUtil" %>
<%@ page import="com.teamshopping.teamshopping.util.AppContext" %>
<%@ page import="com.feili.friendcredit.manager.TOtherLoanManager" %>
<%@ page import="com.teamshopping.teamshopping.dao.Dao" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.feili.friendcredit.util.DateUtils" %>
<%@ page import="com.feili.friendcredit.entity.TOtherLoan" %>
<%@ page import="com.feili.friendcredit.util.JsonUtil" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.fuiou.utils.MD5Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%!
    //深圳百家借导流数据对接
    public static String key = "dslkfh88D663";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
%>
<%
    String sign = SYSUtil.getParameter(request, "sign");
    int pageSize = SYSUtil.getIntParameter(request, "pageSize", 0);
    if (StringUtils.isBlank(sign)) {
        return;
    }
    String date = SYSUtil.getParameter(request, "date");//yyyy-MM-dd
    int pageNo = SYSUtil.getIntParameter(request, "pageNo", 0);//yyyyMMdd
    String origin = MD5Util.encode(date + "|" + pageNo + "|" + key, "UTF-8");
    if (!sign.equals(origin.toLowerCase())) {
        return;
    }
    int from = (pageNo - 1) * pageSize;
    Dao daoManager = (Dao) AppContext.getInstance().getAppContext().getBean("daoManager");
    String hql = "from TOtherLoan t where t.createTime>='" + date + " 00:00:00' and t.createTime<='" + date + " 23:59:59' ";
    Object byAggregate = daoManager.findByAggregate("select count(*) " + hql, new Object[]{});
    if (byAggregate == null) byAggregate = 0;
    List<TOtherLoan> list = daoManager.find(hql, from, pageSize);
    if (list == null) list = new ArrayList<TOtherLoan>();
    HashMap<String, Object> map = new HashMap<String, Object>();
    ArrayList<Map> dataList = new ArrayList<Map>();
    HashMap<String, String> dataMap = null;
    if (list.size() > 0) {
        for (TOtherLoan tOtherLoan : list) {
            dataMap = new HashMap<String, String>();
            dataMap.put("id", tOtherLoan.getId() + "");
            dataMap.put("name", tOtherLoan.getRealName());
            dataMap.put("age", tOtherLoan.getAge() + "");
            dataMap.put("mobi", tOtherLoan.getMobile());
            dataMap.put("money", tOtherLoan.getMoney() + "");
            dataMap.put("days", (DateUtils.daysBetween(tOtherLoan.getEndTime(), tOtherLoan.getStartTime()) + 1) + "");
            dataMap.put("interest", tOtherLoan.getInterest() + "");
            dataMap.put("addr", tOtherLoan.getAddr());
            dataMap.put("provinceNo", tOtherLoan.getStr1());
            dataMap.put("zmxy", tOtherLoan.getZmxy());
            dataMap.put("qq", tOtherLoan.getQqNo());
            dataMap.put("wechat", tOtherLoan.getWechat());
            dataMap.put("createtime", DateUtils.sdf_all.format(tOtherLoan.getCreateTime()));
            dataMap.put("repaytype", tOtherLoan.getRepayType() == 0 ? "全额" : "分期");
            dataList.add(dataMap);
        }
    }
    map.put("totalSize", byAggregate);
    map.put("size", list.size());
    map.put("data", dataList);
    map.put("pageSize", pageSize);
%>
<%out.print(JsonUtil.getJsonStringFromMap(map));%>
