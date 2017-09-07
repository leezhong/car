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
   
%>
<%out.print(JsonUtil.getJsonStringFromMap(map));%>
