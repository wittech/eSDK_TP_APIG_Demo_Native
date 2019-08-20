package com.huawei.esdk.bsdemo.codesample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.Duration;

import com.huawei.esdk.bsdemo.util.PropertiesUtils;
import com.huawei.esdk.tp.professional.local.ServiceFactoryEx;
import com.huawei.esdk.tp.professional.local.authentication.AuthorizeServiceEx;
import com.huawei.esdk.tp.professional.local.bean.AdhocConfFreeBusyStateEx;
import com.huawei.esdk.tp.professional.local.bean.AdhocConfTemplateParamEx;
import com.huawei.esdk.tp.professional.local.bean.AdhocConferenceEx;
import com.huawei.esdk.tp.professional.local.bean.CallEndPointEx;
import com.huawei.esdk.tp.professional.local.bean.ConfRecordAddrEx;
import com.huawei.esdk.tp.professional.local.bean.ConferenceInfoEx;
import com.huawei.esdk.tp.professional.local.bean.ConferenceNoticeEx;
import com.huawei.esdk.tp.professional.local.bean.ConferenceStatusEx;
import com.huawei.esdk.tp.professional.local.bean.DateTimeFilterEx;
import com.huawei.esdk.tp.professional.local.bean.FilterBaseEx;
import com.huawei.esdk.tp.professional.local.bean.FreeBusyStateEx;
import com.huawei.esdk.tp.professional.local.bean.GetContinuousPresenceInfoExResult;
import com.huawei.esdk.tp.professional.local.bean.GetContinuousPresenceParamExResult;
import com.huawei.esdk.tp.professional.local.bean.IntFilterEx;
import com.huawei.esdk.tp.professional.local.bean.MCUInfoEx;
import com.huawei.esdk.tp.professional.local.bean.MCUResourceEx;
import com.huawei.esdk.tp.professional.local.bean.MultiPointCDREx;
import com.huawei.esdk.tp.professional.local.bean.MultiPointCDRQueryConfigEx;
import com.huawei.esdk.tp.professional.local.bean.NotificationEx;
import com.huawei.esdk.tp.professional.local.bean.OngoingConfSubscribeEx;
import com.huawei.esdk.tp.professional.local.bean.OrganizationItemEx;
import com.huawei.esdk.tp.professional.local.bean.PageParamEx;
import com.huawei.esdk.tp.professional.local.bean.PointToPointCDREx;
import com.huawei.esdk.tp.professional.local.bean.PointToPointCDRQueryConfigEx;
import com.huawei.esdk.tp.professional.local.bean.QueryConfigEx;
import com.huawei.esdk.tp.professional.local.bean.RecordParamEx;
import com.huawei.esdk.tp.professional.local.bean.RecordVideoSourceType;
import com.huawei.esdk.tp.professional.local.bean.RecurrenceConfInfoEx;
import com.huawei.esdk.tp.professional.local.bean.RseInfoEx;
import com.huawei.esdk.tp.professional.local.bean.SiteAccessInfoEx;
import com.huawei.esdk.tp.professional.local.bean.SiteInfoEx;
import com.huawei.esdk.tp.professional.local.bean.SiteMCUEx;
import com.huawei.esdk.tp.professional.local.bean.SiteStatusEx;
import com.huawei.esdk.tp.professional.local.bean.SiteVolumeEx;
import com.huawei.esdk.tp.professional.local.bean.SortItemEx;
import com.huawei.esdk.tp.professional.local.bean.StringFilterEx;
import com.huawei.esdk.tp.professional.local.bean.SubscribeInfoEx;
import com.huawei.esdk.tp.professional.local.bean.TPSDKResponseEx;
import com.huawei.esdk.tp.professional.local.bean.TPSDKResponseWithPageEx;
import com.huawei.esdk.tp.professional.local.bean.TerminalInfoEx;
import com.huawei.esdk.tp.professional.local.bean.VideoCapbilityItemEx;
import com.huawei.esdk.tp.professional.local.bean.VideoParamEx;
import com.huawei.esdk.tp.professional.local.bean.WSConfTextParamEx;
import com.huawei.esdk.tp.professional.local.bean.WSCtrlSiteCommParamEx;
import com.huawei.esdk.tp.professional.local.conference.ConferenceServiceEx;
import com.huawei.esdk.tp.professional.local.mcu.MCUServiceEx;
import com.huawei.esdk.tp.professional.local.organization.OrganizationServiceEx;
import com.huawei.esdk.tp.professional.local.rse.RSEServiceEx;
import com.huawei.esdk.tp.professional.local.site.SiteServiceEx;
import com.huawei.esdk.tp.professional.local.subscribe.SubscribeServiceEx;

public class CodeSample
{  
    public static void main(String[] args)
    {
        CodeSample codeSample = new CodeSample();
        //从配置文件中读取登录eSDK所需要的用户名
        String userName = PropertiesUtils.getValue("userName");
        //从配置文件中读取登录eSDK所需要的密码
        String password = PropertiesUtils.getValue("password");
        //登录
        codeSample.login(userName, password);
        
        codeSample.keepAlive();
        
         //codeSample.addSiteInfoEx();
          //codeSample.setSitesQuietEx();
//        codeSample.queryScheduleConferencesEx();
        //codeSample.editSiteInfoEx();
        
        /* // 调用保活线程代码     
         * // 判断定时任务是否关闭 
        if (ConnectTimer.getTimer().isShutdown()) { 
            ConnectTimer.setTimer(Executors.newSingleThreadScheduledExecutor()); 
        } 
        // 执行定时任务 
        ConnectTimer.getTimer().scheduleAtFixedRate(new ConnectTimer(), 0L, 30L, TimeUnit.SECONDS);
        */
        //codeSample.connectSitesEx();96905   96906
        //codeSample.scheduleConfEx();
        //codeSample.querySitesInfoEx();
        //调用业务接口
        //codeSample.queryHistoryConfRecordAddrEx();
        
        
        //登出
        codeSample.logout();
    }
    
    public void queryAdhocConferencesEx()
    {
    	//设置查询条件
    	QueryConfigEx queryConfigEx = new QueryConfigEx(); 
    	//对查询结果按照会议模板名称升序方式进行排序 
    	List<SortItemEx> sortItemExs = new ArrayList<SortItemEx>(); 
    	SortItemEx sortItemEx = new SortItemEx(); 
    	sortItemEx.setSort(0); 
    	sortItemEx.setItemIndex(4);
    	sortItemExs.add(sortItemEx); 
    	//获取满足会议模板名称包含vct2条件的会议模板 
    	List<FilterBaseEx> filterBaseExs = new ArrayList<FilterBaseEx>(); 
    	StringFilterEx filterBaseEx = new StringFilterEx(); 
    	filterBaseEx.setColumnIndex(1); 
    	filterBaseEx.setValue("AdConf"); 
    	filterBaseExs.add(filterBaseEx); 
    	//每页5个，获取第一页 
    	PageParamEx pageParamEx = new PageParamEx(); 
    	pageParamEx.setNumberPerPage(20); 
    	pageParamEx.setCurrentPage(1); 
    	queryConfigEx.setSortItems(sortItemExs); 
    	queryConfigEx.setFilters(filterBaseExs); 
    	queryConfigEx.setFocusItem(0); 
    	queryConfigEx.setPageParam(pageParamEx); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class);
    	//调用会议服务的getContinuousPresenceInfoEx方法查询多画面模式资源数，返回TPSDKResponseEx<GetContinuousPresenceInfoExResult>对象 
    	TPSDKResponseEx<List<AdhocConferenceEx>> result = conferenceServiceEx.queryAdhocConferencesEx(queryConfigEx);
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后的多画面模式资源数信息 
    		List<AdhocConferenceEx> result2 = result.getResult();
    	}
    }
    
    public void synchAdhocConfFreeBusyEx()
    {
    	//新建一个List对象，用于存放Ad hoc会议接入号 
    	List<String> confAccessCodes = new ArrayList<String>(); 
    	//添加Ad hoc会议接入号 0755666 
    	confAccessCodes.add("523113"); 
    	//查询开始时间为30分钟之后 
    	Date beginTime = new Date(new Date().getTime() - 1000 * 60 * 10); 
    	Duration duration = null; 
    	try 
    	{ 
    	    //会议时长为60分钟 
    	    duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class);
    	//调用会议服务的synchAdhocConfFreeBusyEx方法查询Adhoc会议忙闲状态变化的数据，返回TPSDKResponseEx<List<AdhocConfFreeBusyStateEx>>对象 
		TPSDKResponseEx<List<AdhocConfFreeBusyStateEx>> result = conferenceServiceEx.synchAdhocConfFreeBusyEx(beginTime,
				duration, confAccessCodes);
		//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后的Ad hoc会议忙闲状态变化的数据信息 
    	    List<AdhocConfFreeBusyStateEx> list = result.getResult(); 
    	}
    }
    
    public void delAdhocConfTemplateEx()
    {
    	//会议模板ID 
    	String adhocConfTemplateId = "21";
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的delAdhocConfTemplateEx方法删除Ad hoc会议模板，返回Integer对象 
    	Integer result = conferenceServiceEx.delAdhocConfTemplateEx(adhocConfTemplateId);
    }
    
    public void editAdhocConfTemplateEx()
    {
    	// Adhoc会议模板参数 
    	AdhocConfTemplateParamEx adhocConfTemplate = new AdhocConfTemplateParamEx(); 
    	// Adhoc模板ID, 新增时为0 
    	adhocConfTemplate.setAdhocConfTemplateId("21"); 
    	// 会议模板名称 
    	adhocConfTemplate.setName("AdConf12"); 
    	// 会议激活号码 
    	adhocConfTemplate.setAccessCode("1"); 
    	try 
    	{ 
    	    //会议时长为60分钟 
    	    Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	    adhocConfTemplate.setDuration(duration); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	// 计费码 
    	adhocConfTemplate.setBillCode("0"); 
    	// 多画面资源数 
    	adhocConfTemplate.setCpResource(0); 
    	// 速率 
    	adhocConfTemplate.setRate("1920K"); 
    	// 媒体流加密方式，0：自动协商是否使用加密 
    	adhocConfTemplate.setMediaEncryptType(0); 
    	// 是否支持直播功能, 0：不支持 1：支持 
    	adhocConfTemplate.setIsLiveBroadcast(0); 
    	// 是否支持录播功能, 0：不支持 1：支持 
    	adhocConfTemplate.setIsRecording(0); 
    	// 胶片演示方式 
    	adhocConfTemplate.setPresentation(0); 
    	// 辅流视频参数 
    	VideoParamEx videoParam = new VideoParamEx();

    	// 视频协议为H.261协议 
    	videoParam.setProtocol(1); 
    	// 视频格式为Auto 
    	videoParam.setFormat(0); 
    	adhocConfTemplate.setPresentationVideo(videoParam); 
    	// 会议主会场 
    	adhocConfTemplate.setMainSiteUri("01010086"); 
    	// 会议通知信息 
    	ConferenceNoticeEx notice = new ConferenceNoticeEx(); 
    	// 邮箱地址 
    	notice.setEmail("abc@huawei.com"); 
    	// 通知信息内容 
    	notice.setContent("0"); 
    	// 电话号码 
    	notice.setTelephone("051269993940"); 
    	adhocConfTemplate.setNotice(notice); 
    	List<SiteInfoEx> sites = new ArrayList<SiteInfoEx>(); 
    	//新建一个SiteInfoEx对象
    	SiteInfoEx siteInfo1 = new SiteInfoEx(); 
    	//会场URI为01010086 
    	siteInfo1.setUri("01010086"); 
    	//会场速率为1920k 
    	siteInfo1.setRate("1920K"); 
    	//会场名称为site1 
    	siteInfo1.setName("site1"); 
    	//呼叫方式为MCU主动呼叫会场 
    	siteInfo1.setDialingMode(0); 
    	//会场来源为内部会场 
    	siteInfo1.setFrom(0); 
    	//会场类型为H.323会场类型 
    	siteInfo1.setType(4); 
    	//会场视频格式为Auto 
    	siteInfo1.setVideoFormat(0); 
    	//会场视频协议为H.261 
    	siteInfo1.setVideoProtocol(1); 
    	//预约会议需要两个以上会场，所以再新建一个会场 
    	SiteInfoEx siteInfo2 = new SiteInfoEx(); 
    	siteInfo2.setUri("01010010"); 
    	siteInfo2.setRate("1920K"); 
    	siteInfo2.setName("site2"); 
    	siteInfo2.setDialingMode(0); 
    	siteInfo2.setFrom(0); 
    	siteInfo2.setType(4); 
    	siteInfo2.setVideoFormat(0); 
    	siteInfo2.setVideoProtocol(1); 
    	//向会议中添加会场 
    	List<SiteInfoEx> sites1 = new ArrayList<SiteInfoEx>(); 
    	sites1.add(siteInfo1); 
    	sites1.add(siteInfo2); 
    	adhocConfTemplate.setSites(sites1); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class);
    	//调用会议服务的editAdhocConfTemplateEx方法修改会议模板，返回Integer对象 
    	Integer result = conferenceServiceEx.editAdhocConfTemplateEx(adhocConfTemplate);
    }
    
    public void queryAdhocConfTemplateListEx()
    {
    	//设置查询条件
    	QueryConfigEx queryConfigEx = new QueryConfigEx(); 
    	//对查询结果按照会议模板名称升序方式进行排序 
    	List<SortItemEx> sortItemExs = new ArrayList<SortItemEx>(); 
    	SortItemEx sortItemEx = new SortItemEx(); 
    	sortItemEx.setSort(0); 
    	sortItemEx.setItemIndex(4);
    	sortItemExs.add(sortItemEx); 
    	//获取满足会议模板名称包含testAdhocConf条件的会议模板 
    	List<FilterBaseEx> filterBaseExs = new ArrayList<FilterBaseEx>(); 
    	StringFilterEx filterBaseEx = new StringFilterEx(); 
    	filterBaseEx.setColumnIndex(1); 
    	filterBaseEx.setValue("testAdhocConf"); 
    	filterBaseExs.add(filterBaseEx); 
    	//每页5个，获取第一页 
    	PageParamEx pageParamEx = new PageParamEx(); 
    	pageParamEx.setNumberPerPage(5); 
    	pageParamEx.setCurrentPage(1); 
    	queryConfigEx.setSortItems(sortItemExs); 
    	queryConfigEx.setFilters(filterBaseExs); 
    	queryConfigEx.setFocusItem(0); 
    	queryConfigEx.setPageParam(pageParamEx); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的queryAdhocConfTemplateListEx方法查询所有的会议模板，返回TPSDKResponseWithPageEx<List<AdhocConfTemplateParamEx>>对象 
    	TPSDKResponseWithPageEx<List<AdhocConfTemplateParamEx>> result = conferenceServiceEx.queryAdhocConfTemplateListEx(queryConfigEx); 
    	if (0 == result.getResultCode()) 
    	{ 
    	    //查询成功，返回会议模板列表信息 
    	    List<AdhocConfTemplateParamEx> conferenceStatusExs = result.getResult(); 
    	}
    }
    
    public void queryAdhocConfFreeBusyEx()
    {
    	//新建一个List对象，用于存放Ad hoc会议接入号 
    	List<String> confAccessCodes = new ArrayList<String>(); 
    	//添加Ad hoc会议接入号
    	confAccessCodes.add("523113"); 
    	//查询开始时间为30分钟之后 
    	Date beginTime = new Date(new Date().getTime() + 1000 * 60 * 30); 
    	Duration duration = null; 
    	try 
    	{ 
    	    //会议时长为60分钟 
    	    duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的queryAdhocConfFreeBusyEx方法查询Ad hoc会议忙闲状态，返回TPSDKResponseEx<List<AdhocConfFreeBusyStateEx>>对象 
    	TPSDKResponseEx<List<AdhocConfFreeBusyStateEx>> result = conferenceServiceEx.queryAdhocConfFreeBusyEx(beginTime, duration, confAccessCodes); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后的Ad hoc会议忙闲状态信息 
    	    List<AdhocConfFreeBusyStateEx> list = result.getResult(); 
    	}
    }
    
    public void queryMCUFutureResourceEx()
    {
    	//java code 
    	//新建一个List对象，用于存放待查询资源的MCU ID列表 
    	List<Integer> mcus = new ArrayList<Integer>(); 
    	mcus.add(22); 
    	//查询开始时间为30分钟之后 
    	Date beginTime = new Date(new Date().getTime() + 1000 * 60 * 60 * 100); 
    	Duration duration = null; 
    	try 
    	{ 
    	    //会议时长为60分钟 
    	    duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 6); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//获取MCU管理服务实例 
    	MCUServiceEx mcuServiceEx = ServiceFactoryEx.getService(MCUServiceEx.class); 
    	//调用MCU服务的queryMCUFutureResourceEx方法查询某时间段MCU的资源占用情况，返回TPSDKResponseEx<List<MCUResourceEx>>对象 
    	TPSDKResponseEx<List<MCUResourceEx>> result = mcuServiceEx.queryMCUFutureResourceEx(mcus, beginTime, duration); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后某时间段MCU的资源占用情况 
    	    List<MCUResourceEx> list = result.getResult(); 
    	}
    }
    
    public void queryMultiPointCDREx()
    {
    	//java code 
    	//新建一个MultiPointCDRQueryConfigEx对象 
    	MultiPointCDRQueryConfigEx queryConfig = new MultiPointCDRQueryConfigEx(); 
    	//查询开始时间为10分钟之前 
    	Date beginTime = new Date(new Date().getTime() - 1000 * 60 * 10); 
    	//设置查询开始时间 
    	queryConfig.setBeginTime(beginTime); 
    	try 
    	{ 
    	    //查询范围时长为60分钟 
    	    Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	    queryConfig.setDuration(duration); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//会场URI为524 
    	queryConfig.setSiteUri("524"); 
    	//新建一个PageParamEx对象 
    	PageParamEx pageParam = new PageParamEx(); 
    	//设置当前页为1
    	pageParam.setCurrentPage(1); 
    	//设置每页数为2 
    	pageParam.setNumberPerPage(2); 
    	//设置页码定位参数 
    	queryConfig.setPageParam(pageParam); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的queryMultiPointCDREx方法查询多点CDR话单，返回TPSDKResponseWithPageEx<List<MultiPointCDREx>>对象 
    	TPSDKResponseWithPageEx<List<MultiPointCDREx>> result = conferenceServiceEx.queryMultiPointCDREx(queryConfig); 
    	//调用TPSDKResponseWithPageEx<T>中的getResultCode()方法获取返回码，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{
    	    //查询成功，则返回查询后的多点CDR话单信息 
    	    List<MultiPointCDREx> list = result.getResult();
    	    System.out.println("");
    	}
    }
    
    public void queryPointToPointCDREx()
    {
    	//java code 
    	//新建一个PointToPointCDRQueryConfigEx对象 
    	PointToPointCDRQueryConfigEx queryConfig = new PointToPointCDRQueryConfigEx(); 
    	//查询开始时间为10分钟之前
    	Date beginTime = new Date(new Date().getTime() - 1000 * 60 * 10); 
    	//设置查询开始时间 
    	queryConfig.setBeginTime(beginTime); 
    	try 
    	{ 
    	    //会议时长为60分钟 
    	    Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	    queryConfig.setDuration(duration); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//新建一个CallEndPointEx对象 
    	CallEndPointEx endPoint = new CallEndPointEx(); 
    	//会场URI为01010086 
    	endPoint.setSiteUri("523"); 
    	//设置呼叫类型为主叫方 
    	endPoint.setType(0); 
    	//设置查询指定会场 
    	queryConfig.setEndPoint(endPoint); 
    	//新建一个PageParamEx对象 
    	PageParamEx pageParam = new PageParamEx(); 
    	//设置当前页为1 
    	pageParam.setCurrentPage(1); 
    	//设置每页数为2 
    	pageParam.setNumberPerPage(2); 
    	//设置页码定位参数 
    	queryConfig.setPageParam(pageParam); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的queryPointToPointCDREx方法查询单点CDR话单条件，返回TPSDKResponseWithPageEx<List<PointToPointCDREx>>对象 
    	TPSDKResponseWithPageEx<List<PointToPointCDREx>> result =
    	conferenceServiceEx.queryPointToPointCDREx(queryConfig); 
    	//调用TPSDKResponseWithPageEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后的单点CDR话单条件信息 
    	    List<PointToPointCDREx> list = result.getResult(); 
    	}
    }
    
    public void queryNotificationEx()
    {
    	//java code 
    	//获取订阅推送服务实例 
    	SubscribeServiceEx subscribeServiceEx = ServiceFactoryEx.getService(SubscribeServiceEx.class); 
    	//调用订阅服务的queryNotificationEx方法读取的通知消息，返回TPSDKResponseEx<List<NotificationEx>>对象 
    	TPSDKResponseEx<List<NotificationEx>> result = subscribeServiceEx.queryNotificationEx(); 
    	if (0 == result.getResultCode()) 
    	{ 
    	    //查询成功，返回通知消息 
    	    List<NotificationEx> notificationExs = result.getResult(); 
    	}
    }
    
    public void subscribeEx()
    {
    	//java code 
    	//订阅或取消订阅信息的列表 
    	List<SubscribeInfoEx> subscribeInfoExs = new ArrayList<SubscribeInfoEx>(); 
    	OngoingConfSubscribeEx subscribeInfoEx = new OngoingConfSubscribeEx(); 
    	//订阅 
    	subscribeInfoEx.setIsSubscribe(1); 
    	//需要订阅或取消订阅的会议ID 
    	subscribeInfoEx.getConfIds().add("123456"); 
    	subscribeInfoExs.add(subscribeInfoEx); 
    	//获取订阅推送服务实例 
    	SubscribeServiceEx subscribeServiceEx = ServiceFactoryEx.getService(SubscribeServiceEx.class); 
    	//调用订阅服务的subscribeEx方法修改推送消息的过滤条件，返回Integer对象 
    	Integer resultCode = subscribeServiceEx.subscribeEx(subscribeInfoExs);
    }
    
    public void enablePushEx()
    {
    	//java code 
    	//推送通知机制设为Pull 
    	Integer pushMode = 1; 
    	//获取订阅推送服务实例 
    	SubscribeServiceEx subscribeServiceEx = ServiceFactoryEx.getService(SubscribeServiceEx.class); 
    	//调用订阅服务的enablePushEx方法开启推送功能，返回Integer对象 
    	Integer resultCode = subscribeServiceEx.enablePushEx(pushMode,null);
    }
    
    public void queryMCUInfoEx()
    {
    	//java code 
    	//设置查询条件 
    	QueryConfigEx queryConfig = new QueryConfigEx(); 
    	//对查询结果按照MCU名称升序方式进行排序 
    	List<SortItemEx> sortItems = new ArrayList<SortItemEx>(); 
    	SortItemEx sortItem = new SortItemEx(); 
    	sortItem.setItemIndex(6); 
    	sortItem.setSort(0);
    	sortItems.add(sortItem); 
    	queryConfig.setSortItems(sortItems); 
    	//获取满足名称包含xwytest条件的MCU 
    	List<FilterBaseEx> filters = new ArrayList<FilterBaseEx>(); 
    	StringFilterEx stringFilter = new StringFilterEx(); 
    	stringFilter.setColumnIndex(6); 
    	stringFilter.setValue("MCU208"); 
    	filters.add(stringFilter); 
    	queryConfig.setFilters(filters); 
    	queryConfig.setFocusItem(0); 
    	//每页2个MCU，获取第一页 
    	PageParamEx pageParam = new PageParamEx(); 
    	pageParam.setCurrentPage(1); 
    	pageParam.setNumberPerPage(2); 
    	queryConfig.setPageParam(pageParam); 
    	//获取MCU管理相关服务实例 
    	MCUServiceEx mcuServiceEx = ServiceFactoryEx.getService(MCUServiceEx.class); 
    	//调用MCU服务的queryMCUInfoEx方法查询MCU信息列表，返回TPSDKResponseWithPageEx<List<MCUInfoEx>>对象 
    	TPSDKResponseWithPageEx<List<MCUInfoEx>> result = mcuServiceEx.queryMCUInfoEx(queryConfig); 
    	//调用TPSDKResponseWithPageEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后的MCU信息列表 
    	    List<MCUInfoEx> list = result.getResult(); 
    	}
    }
    
    public void queryRseInfoEx()
    {
    	//java code 
    	//设置查询条件 
    	QueryConfigEx queryConfig = new QueryConfigEx(); 
    	//获取名称包含R的RSE信息 
    	List<FilterBaseEx> filters = new ArrayList<FilterBaseEx>(); 
    	StringFilterEx filter1 = new StringFilterEx(); 
    	filter1.setColumnIndex(7); 
    	filter1.setValue("eSDK_RSE_test"); 
    	filters.add(filter1); 
    	queryConfig.setFilters(filters); 
    	//对查询结果按照RSE名称升序方式进行排序   218
    	List<SortItemEx> sortItems = new ArrayList<SortItemEx>(); 
    	SortItemEx sortItem1 = new SortItemEx(); 
    	sortItem1.setItemIndex(7); 
    	sortItem1.setSort(0); 
    	sortItems.add(sortItem1); 
    	queryConfig.setSortItems(sortItems); 
    	//每页10条记录，获取第一页 
    	PageParamEx pageParam = new PageParamEx(); 
    	pageParam.setCurrentPage(1); 
    	pageParam.setNumberPerPage(10); 
    	queryConfig.setPageParam(pageParam); 
    	//获取RSE相关服务实例 
    	RSEServiceEx rseServiceEx = ServiceFactoryEx.getService(RSEServiceEx.class); 
    	//调用queryRseInfoEx方法，返回TPSDKResponseWithPageEx<List<RseInfoEx>>对象。 
    	TPSDKResponseWithPageEx<List<RseInfoEx>> result = rseServiceEx.queryRseInfoEx(queryConfig); 
    	//调用TPSDKResponseWithPageEx<T>中的getResultCode方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	System.out.println("queryRseInfoEx resultCode:" + resultCode);
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后的RSE信息
    	    List<RseInfoEx> list = result.getResult(); 
    	}
    }
    
    public void startRecordEx()
    {
    	//java code 
    	//设置要录制会议的id 
    	String confId = "101583"; 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//如果返回值为0，则表示开始或停止录制成功，否则表示失败，具体失败原因请参考错误码列表。 
    	int result = conferenceServiceEx.startRecordEx(confId, true);
    }
    
    public void queryHistoryConfRecordAddrEx()
    {
    	//java code 
    	//要查询的会议Id列表 
    	List<String> confIds = new ArrayList<String>(); 
    	confIds.add("101583"); 
    	//获取RSE相关服务实例 
    	RSEServiceEx rseServiceEx = ServiceFactoryEx.getService(RSEServiceEx.class); 
    	//如果返回值为0，则表示查询成功，否则表示失败，具体失败原因请参考错误码列表。 
    	TPSDKResponseEx<List<ConfRecordAddrEx>> result = rseServiceEx.queryHistoryConfRecordAddrEx(confIds);
    	Integer resultCode = result.getResultCode(); 
    }
    
    public void setRecordVideoSourceEx()
    {
    	//java code 
    	//设置要录制会议的id 
    	String confId = "99700"; 
    	//设置录制视频源类型 
    	RecordVideoSourceType arg1 = RecordVideoSourceType.CONF_CONTINUOUS_PRESENCE; 
    	// 多画面类型为3画面模式1 
    	int presenceMode = 2; 
    	//子画面会场标识顺序列表 
    	List<String> subPics = new ArrayList<String>(); 
    	subPics.add("523"); 
    	subPics.add("524"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//如果返回值为0，则表示设置成功，否则表示失败，具体失败原因请参考错误码列表。 
    	int result = conferenceServiceEx.setRecordVideoSourceEx(confId, arg1, presenceMode, subPics);
    }
    
    public void setVSAttrCtrlEx()
    {
    	//java code 
    	//设置会议Id为99624 
    	String confId = "99624"; 
    	//新建一个WSCtrlSiteCommParamEx数组对象 
    	List<WSCtrlSiteCommParamEx> wsCtrlSiteCommParams = new ArrayList<WSCtrlSiteCommParamEx>(); 
    	WSCtrlSiteCommParamEx item1 = new WSCtrlSiteCommParamEx(); 
    	//锁定 
    	item1.setOperaTypeParam(0); 
    	//会场标识为523 
    	item1.setSiteUri("523"); 
    	wsCtrlSiteCommParams.add(item1); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	int resultCode = conferenceServiceEx.setVSAttrCtrlEx(confId,wsCtrlSiteCommParams);
    }
    
    public void setConfTextCtrlEx()
    {
    	//java code 
    	//设置会议Id为99620 
    	String confId = "103106"; 
    	//新建一个WSConfTextParamEx对象 
    	WSConfTextParamEx value = new WSConfTextParamEx(); 
//    	List<String> siteUriList = new ArrayList<>();
//    	siteUriList.add("865717700");
//    	value.setSiteUriList(siteUriList);
    	//设置会议文本内容内容 
    	value.setContent("eSDK 支撑会议aaa"); 
    	//设置生效 
    	value.setOpType(0); 
    	//设置文本类型为字幕 
    	value.setTextType(0); 
    	//设置字幕显示在中部 
    	value.setDisPos(0); 
    	//设置字幕显示位置靠右 
    	value.setDisType(1); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	int resultCode = conferenceServiceEx.setConfTextCtrlEx(confId,value);
    }
    
    public void unlockPresentationEx()
    {
    	//java code 
    	//设置会议Id为99785 
    	String confId = "99785"; 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的unlockPresentationEx方法，返回Integer对象。 
    	//如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.unlockPresentationEx(confId);
    }
    
    public void lockPresentationEx()
    {
    	//java code 
    	//设置会议Id为99785 
    	String confId = "99785"; 
    	//设置会场uri为523 
    	String siteUri = "523"; 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的lockPresentationEx方法，返回Integer对象。 
    	//如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.lockPresentationEx(confId, siteUri);
    }
    
    public void hideConfSiteLocalVideoEx()
    {
    	//java code 
    	//新建一个List对象，用于存放需要操作的会场URI 
    	List<String> siteUris = new ArrayList<String>(); 
    	siteUris.add("523"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的hideConfSiteLocalVideoEx方法，如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.hideConfSiteLocalVideoEx("99785",siteUris);
    }
    
    public void displayConfSiteLocalVideoEx()
    {
    	//java code 
    	//新建一个List对象，用于存放需要操作的会场URI 
    	List<String> siteUris = new ArrayList<String>(); 
    	siteUris.add("523"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的displayConfSiteLocalVideoEx方法，如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.displayConfSiteLocalVideoEx("99700",siteUris);
    }
    
    public void setConfSiteVolumeEx()
    {
    	//java code 
    	//新建一个List对象，用于存放需要操作的会场音量信息 
    	List<SiteVolumeEx> siteVolumes = new ArrayList<SiteVolumeEx>(); 
    	SiteVolumeEx siteVolumeEx1 = new SiteVolumeEx(); 
    	siteVolumeEx1.setSiteUri("524"); 
    	siteVolumeEx1.setVolume(80); 
    	siteVolumes.add(siteVolumeEx1); 
    	SiteVolumeEx siteVolumeEx2 = new SiteVolumeEx(); 
    	siteVolumeEx2.setSiteUri("525"); 
    	siteVolumeEx2.setVolume(80); 
    	siteVolumes.add(siteVolumeEx2); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setConfSiteVolumeEx方法，如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.setConfSiteVolumeEx("99379",siteVolumes);
    }
    
    public void releaseConfChairEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的releaseChairEx方法，让会场URI为524的会场释放主席，返回Integer对象。 
    	//如果返回值为0，则表示释放主席成功，否则表示释放主席失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.releaseConfChairEx("99700");
    }
    
    public void requestConfChairEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的requestChairEx方法，让会场URI为01010086的会场申请主席，返回Integer对象。 
    	//如果返回值为0，则表示申请主席成功，否则表示申请主席失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.requestConfChairEx("99700","524");
    }
    
    public void setFloorEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setFloorEx方法，如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.setFloorEx("99379","525");
    }
    
    public void setSitesQuietEx()
    {
    	//java code 
    	//新建List对象，存放需要静音的会场的URI 
    	List<String> list = new ArrayList<String>(); 
    	list.add("525"); 
    	list.add("123789"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setSitesQuietEx方法，让会议ID为99379的会议中会场URI为523和524的会场静音，返回Integer对象。 
    	//如果返回值为0，则表示静音成功，否则表示静音失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.setSitesQuietEx("102078",list,1);
    }
    
    public void setContinuousPresenceEx()
    {
    	//java code 
    	// 要操作会议的ID 
    	String confId = "101875"; 
    	//多画面标识
//    	String target = "(523)";
    	// 多画面模式，三画面模式1 
    	int presenceMode = 2; 
    	// 子画面会场标识顺序列表 
    	List<String> subPics = new ArrayList<String>(); 
    	subPics.add("524"); 
//    	subPics.add("525"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setContinuousPresenceEx方法，设置会议ID为99379的多画面参数。 
    	Integer resultCode = conferenceServiceEx.setContinuousPresenceEx(confId, null, presenceMode, subPics); 
    	//如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	if (0 == resultCode) 
    	{ 
    	    // 设置多画面成功 
    	}
    }
    
    //广播会议多画面
    public void setBroadcastContinuousPresenceEx()
    {
        //要广播会议的id为10
        String confId = "100390";
        //0:开始广播，1：停止广播
        int isBroadcast = 0;
        //获取会议控制服务类实例
        ConferenceServiceEx tpConfMgrService = ServiceFactoryEx.getService(ConferenceServiceEx.class);
        // 调用会议控制服务的setBroadcastContinuousPresenceEx方法，返回SetBroadcastContinuousPresenceExResponse对象
        Integer result = tpConfMgrService.setBroadcastContinuousPresenceEx(confId, isBroadcast);
        //result是返回码，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。
        if (0 == result)
        {
            //设置广播多画面成功
            System.out.println("setBroadcastContinuousPresence success");
        }
    }
    
    public void setBroadcastSiteEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setBroadcastSiteEx方法，让会议ID为2571的会议中会场URI为01010086的会场开始广播会场，返回Integer对象。 
    	//如果返回值为0，则表示广播会场成功，否则表示广播会场失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.setBroadcastSiteEx("99353","525",0);
    }
    
    public void setAudioSwitchEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setAudioSwitchEx方法，让会议ID为99328的会议启用声控切换，门限值为50，返回Integer对象。 
    	//如果返回值为0，则表示启用声控切换成功，否则表示启用声控切换失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.setAudioSwitchEx("101266",50,0);
    }
    
    public void setVideoSourceEx()
    {
    	//java code 
    	//要操作的会议ID 
    	String congId = "101120"; 
    	//要操作的会场uri 
    	String siteUri = "524"; 
    	//要观看的会场uri 
    	String videoSourceUri = "525"; 
    	//是否锁定视频源 
    	int isLock = 0; 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setVideoSourceEx方法，返回Integer对象。 //如果返回值为0，则表示设置指定会场的视频源成功，否则表示设置指定会场的视频源失败，具体失败原因请参考错误码列表。 
    	Integer errorCode = conferenceServiceEx.setVideoSourceEx(congId, siteUri, videoSourceUri, isLock);
    	System.out.println(errorCode);
    }
    
    public void queryScheduleConferencesEx()
    {
    	//java code 
    	//设置查询条件 
    	QueryConfigEx queryConfigEx = new QueryConfigEx(); 
    	//对查询结果按照会场名升序方式进行排序 
    	List<SortItemEx> sortItemExs = new ArrayList<SortItemEx>(); 
    	SortItemEx sortItemEx = new SortItemEx(); 
    	sortItemEx.setSort(0); 
    	sortItemEx.setItemIndex(0); 
    	sortItemExs.add(sortItemEx); 
    	//获取满足条件的会议列表
    	List<FilterBaseEx> filterBaseExs = new ArrayList<FilterBaseEx>(); 
    	
    	//以会议名称为过滤条件
    	StringFilterEx filterBaseEx = new StringFilterEx(); 
    	filterBaseEx.setColumnIndex(9); 
    	filterBaseEx.setValue("zdgn"); 
    	
    	//以会议ID为过滤条件
    	IntFilterEx filterBaseEx2 = new IntFilterEx();
    	filterBaseEx2.setColumnIndex(8);
    	filterBaseEx2.setEqualTo(1021);
    	
    	//以会议开始或者结束时间为过滤条件
    	DateTimeFilterEx filterBaseEx3 = new DateTimeFilterEx();
    	filterBaseEx3.setColumnIndex(10);
//    	filterBaseEx3.setGreaterThan(new Date(new Date().getTime() - 1000 * 60 * 60 * 8 - 1000 * 60 * 5));
    	filterBaseEx3.setLessThan(new Date(new Date().getTime() - 1000 * 60 * 60 * 8));
    	
    	filterBaseExs.add(filterBaseEx);
//    	filterBaseExs.add(filterBaseEx2);
//    	filterBaseExs.add(filterBaseEx3);
    	//每页5个，获取第一页 
    	PageParamEx pageParamEx = new PageParamEx(); 
    	pageParamEx.setNumberPerPage(5); 
    	pageParamEx.setCurrentPage(1); 
    	queryConfigEx.setSortItems(sortItemExs); 
    	queryConfigEx.setFilters(filterBaseExs); 
    	queryConfigEx.setFocusItem(0); 
    	queryConfigEx.setPageParam(pageParamEx); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的queryScheduleConferencesEx方法查询所有的调度会议状态，返回TPSDKResponseEx<List<ConferenceStatusEx>>对象 
    	TPSDKResponseWithPageEx<List<ConferenceStatusEx>> result = conferenceServiceEx.queryScheduleConferencesEx(queryConfigEx); 
    	if(0 == result.getResultCode()) 
    	{ 
    	    //查询成功，返回会议状态信息 
    	    List<ConferenceStatusEx> conferenceStatusExs = result.getResult(); 
    	}
    }
    
    public void getContinuousPresenceParamEx()
    {
    	//java code 
    	//设置会议Id为99223 
    	String confId = "101739"; 
    	//设置读取的多画面类型 
    	String target = "(524)"; 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的getContinuousPresenceParamEx方法读取指定会议的多画面参数，返回TPSDKResponseEx<GetContinuousPresenceParamExResult>对象 
    	TPSDKResponseEx<GetContinuousPresenceParamExResult> result = conferenceServiceEx.getContinuousPresenceParamEx(confId, target); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //读取成功，则返回读取后的指定会议的多画面参数 
    	    GetContinuousPresenceParamExResult getContinuousPresenceParamExResult = result.getResult();
    	}
    }
    
    public void getContinuousPresenceInfoEx()
    {
    	//设置会议Id为99223 
    	String confId = "101739"; 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class);
    	//调用会议服务的getContinuousPresenceInfoEx方法查询多画面模式资源数，返回TPSDKResponseEx<GetContinuousPresenceInfoExResult>对象 
    	TPSDKResponseEx<GetContinuousPresenceInfoExResult> result = conferenceServiceEx.getContinuousPresenceInfoEx(confId); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode) 
    	{ 
    	    //查询成功，则返回查询后的多画面模式资源数信息 
    	    GetContinuousPresenceInfoExResult getContinuousPresenceInforesult = result.getResult(); 
    	}
    }
    
    public void queryConfSiteMCUEx()
    {
    	//java code 
    	//设置会议Id为100907 
    	String confId = "101577"; 
    	//新建一个List对象，用于存放准备查询的会场列表 
    	List<String> siteUris = new ArrayList<String>(); 
    	//添加524会场 
    	siteUris.add("524"); 
    	//查询开始时间为30分钟之后 
    	//会议开始时间。用于对周期性会议中的单个会议进行查询。若周期性会议不输入时间，则返回周期性会议中第一个会议的会场所属MCU信息。如果是普通会议，则可以不用输入。 
    	Date beginTime = new Date(new Date().getTime() + 1000 * 30 * 60); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的queryConfSiteMCUEx方法查询查询指定会议中的会场所属MCU，返回TPSDKResponseEx<List<SiteMCUEx>>对象 
    	TPSDKResponseEx<List<SiteMCUEx>> result = conferenceServiceEx.queryConfSiteMCUEx(confId, siteUris, beginTime); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if (0 == resultCode)
    	{ 
    	    //查询成功，则返回查询后的指定会议中的会场所属MCU信息 
    	    List<SiteMCUEx> list = result.getResult(); 
    	}
    }
    
    public void delScheduledConfEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的delScheduledConfEx方法，取消或删除会议ID为99174的会议，返回Integer对象。 
    	//如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.delScheduledConfEx ("99174", null);
    }
    
    public void disconnectSitesEx()
    {
    	//java code 
    	//新建一个List对象，用于存放需要挂断的会场URI 
    	List<String> list = new ArrayList<String>(); 
    	list.add("525"); 
    	//list.add("01010010"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的disconnectSitesEx方法，挂断会议ID为99198的会议中会场URI为525的会场，返回Integer对象。 
    	//如果返回值为0，则表示挂断成功，否则表示挂断失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.disconnectSitesEx("99198",list);
    }
    
    public void connectSitesEx()
    {
    	//java code 
    	//新建一个List对象，用于存放需要呼叫的会场URI 
    	List<String> list = new ArrayList<String>(); 
    	//list.add("123456"); 
    	list.add("525"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class);
    	//调用会议服务中的connectSitesEx方法，呼叫会议ID为99198的会议中会场URI为525的会场，返回Integer对象。 
    	//如果返回值为0，则表示呼叫成功，否则表示呼叫失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.connectSitesEx ("99198",list);
    }
    
    public void addSiteToConfEx()
    {
    	//java code 
    	//如果向周期性会议中的单个会议添加会场，才需要该参数（该参数为要操作的会议的召开时间），其他情况beginTime = null就行。 
    	//Date beginTime = new Date(new Date().getTime() - 1000 * 60 * 60 * 8); 
    	//新建一个SiteInfoEX对象 
    	SiteInfoEx siteInfo = new SiteInfoEx(); 
    	//会场URI为123456 
    	siteInfo.setUri("123456"); 
    	siteInfo.setName("msj1");
    	//设置会场类型
    	siteInfo.setType(7);
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的addSiteToConfEx方法，把会场URI为123456的会场添加到开始时间为8小时后的会议ID为99174的会议中，返回TPSDKResponseEx<List<SiteAccessInfoEx>>>对象。 
    	TPSDKResponseEx<List<SiteAccessInfoEx>> result = conferenceServiceEx.addSiteToConfEx("99174", siteInfo ,null); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if(0 == resultCode) 
    	{ 
    	    //添加成功后，返回新会场接入到会议中的接入号信息 
    	    List<SiteAccessInfoEx> list = result.getResult(); 
    	}
    }
    
    public void delSiteFromConfEx()
    {
    	//java code 
    	//Date beginTime = new Date(new Date().getTime() + 1000 * 60 * 60*8); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的delSiteFromConfEx方法，把会场URI为123456的会场从开始时间为8小时后的会议ID为99174的会议中删除，返回Integer对象。 
    	//如果返回值为0，则表示删除成功，否则表示删除失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.delSiteFromConfEx("99174","123456", null);
    }
    
    public void queryConfSitesStatusEx()
    {
    	//java code 
    	//新建一个List对象，用于存放需要查询会场状态的会场URI 
    	List<String> list = new ArrayList<String>(); 
    	list.add("524");
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx =
    	ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的queryConfSitesStatusEx方法，查询会议ID为101290的会议中所有会场的忙闲状态，返回TPSDKResponseEx<List<SiteStatusEx>>对象。 
    	TPSDKResponseEx<List<SiteStatusEx>> result = conferenceServiceEx.queryConfSitesStatusEx("102053", list); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if(0 == resultCode) 
    	{ 
    	    //如果操作成功，则返回List<SiteStatusEx>对象 
    	    List<SiteStatusEx> status = result.getResult(); 
    	}
    }
    
    public void queryConferencesStatusEx()
    {
    	//java code 
    	//新建一个List对象，用于存放需要查询会议状态的会议ID 
    	List<String> list = new ArrayList<String>(); 
    	list.add("991740"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class);
    	//调用会议服务中的queryConfSitesStatusEx方法，查询已预约会议ID为99174的会议状态，返回TPSDKResponseEx<List<ConferenceStatusEx>>对象。 
    	TPSDKResponseEx<List<ConferenceStatusEx>> result = conferenceServiceEx.queryConferencesStatusEx(list); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if(0 == resultCode) 
    	{ 
    	    //查询成功，则返回一个List<ConferenceStatusEx>对象 
    	    List<ConferenceStatusEx> status = result.getResult(); 
    	}
    }
    
    public void prolongScheduledConfEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//设置会议Id
    	String confId = "101519";
    	Date date = new Date(new Date().getTime());
    	System.out.println(date.toString());
    	try 
    	{ 
    	    //延长时间为60分钟 
    	    Duration prolongTime = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	    //调用会议服务中的proLongScheduledConfEx方法，把会议ID为confId的会议延长60分钟，返回Integer对象。 
    	    //如果返回值为0，则表示延长成功，否则表示延长失败，具体失败原因请参考错误码列表。 
    	    Integer resultCode = conferenceServiceEx.prolongScheduledConfEx(confId, null, prolongTime); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	}
    }
    
    public void editScheduledConfEx()
    {
    	//java code 
    	//新建一个ConferenceInfoEx对象 
    	ConferenceInfoEx editConf = new ConferenceInfoEx(); 
    	Date beginTime = new Date(new Date().getTime() - 1000*60*60*8); 
    	//新建一个SiteInfoEx对象
    	//会议至少需要两个会场，所以再建一个SiteInfoEx对象 
    	SiteInfoEx siteInfo2 = new SiteInfoEx(); 
    	siteInfo2.setUri("123789"); 
    	siteInfo2.setRate("1920K"); 
    	siteInfo2.setName("msj2"); 
    	siteInfo2.setDialingMode(0); 
    	siteInfo2.setFrom(0); 
    	siteInfo2.setType(7); 
    	siteInfo2.setVideoFormat(0); 
    	siteInfo2.setVideoProtocol(0); 
    	editConf.setBeginTime(null); 
    	//会议名称修改为“会议002” 
    	editConf.setName("msj_change "); 
    	//会议速率为1920k 
    	editConf.setRate("1920k"); 
    	editConf.setCpResouce(4); 
    	try 
    	{ 
    	    //会议时长为100分钟 
    	    Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 100); 
    	    editConf.setDuration(duration); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//向会议中添加会场 
    	List<SiteInfoEx> sites = new ArrayList<SiteInfoEx>(); 
    	//sites.add(siteInfo1); 
    	sites.add(siteInfo2); 
    	editConf.setSites(sites); 
    	editConf.setConfId("101808");
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//获取会场相关服务实例 
    	SiteServiceEx siteServiceEx = ServiceFactoryEx.getService(SiteServiceEx.class); 
    	//调用会议服务的editScheduledConf方法编辑已预约会议，返回TPSDKResponseEx<ConferenceInfoEx>对象 
    	TPSDKResponseEx<ConferenceInfoEx> result = conferenceServiceEx.editScheduledConfEx(editConf); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if(0 == resultCode) 
    	{ 
    	    //编辑成功，则返回编辑后的会议信息
    	    ConferenceInfoEx conferenceInfo = result.getResult(); 
    	}
    }
    
    public void editRecurrenceConferenceEx()
    {
    	//java code 
    	//新建一个ConferenceInfoEx对象 
    	RecurrenceConfInfoEx editConf = new RecurrenceConfInfoEx(); 
    	//会议开始时间为30分钟之后 
    	Date beginTime = new Date(new Date().getTime() + 1000 * 60 * 60); 
    	//新建一个SiteInfoEx对象 
    	SiteInfoEx siteInfo1 = new SiteInfoEx(); 
    	//会场URI为01010086 
    	siteInfo1.setUri("865717700"); 
    	//会场速率为1920k 
    	siteInfo1.setRate("1920K"); 
    	//会场名称为site1 
    	siteInfo1.setName("lz3"); 
    	//呼叫方式为MCU主动呼叫会场 
    	siteInfo1.setDialingMode(0); 
    	//会场来源为内部会场 
    	siteInfo1.setFrom(0); 
    	//会场类型为H.323会场类型
    	siteInfo1.setType(7); 
    	//会场视频格式为4CIF 
    	siteInfo1.setVideoFormat(0); 
    	//会场视频协议为H.261 
    	siteInfo1.setVideoProtocol(0); 
    	//会议至少需要两个会场，所以再建一个SiteInfoEx对象 
    	SiteInfoEx siteInfo2 = new SiteInfoEx(); 
    	siteInfo2.setUri("865718800"); 
    	siteInfo2.setRate("1920K"); 
    	siteInfo2.setName("lz2");
    	siteInfo2.setDialingMode(0); 
    	siteInfo2.setFrom(0); 
    	siteInfo2.setType(7); 
    	siteInfo2.setVideoFormat(0); 
    	siteInfo2.setVideoProtocol(0); 
    	//设置会议开始时间 
    	editConf.setBeginTime(beginTime); 
    	//会议名称为“editConf” 
    	editConf.setName("lz_Change周期111"); 
    	//会议速率为1920k 
    	editConf.setRate("1920k"); 
    	try 
    	{ 
    	    //会议时长为100分钟 
    	    Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 100); 
    	    editConf.setDuration(duration); 
    	} 
    	catch(DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//向会议中添加会场 
    	List<SiteInfoEx> sites = new ArrayList<SiteInfoEx>(); 
    	sites.add(siteInfo1); 
    	sites.add(siteInfo2); 
    	editConf.setSites(sites); 
    	//周期性会议重复次数为5次 
    	editConf.setCount(5); 
    	//周期类型为每天 
    	editConf.setFrequency(0); 
    	//周期间隔为每两天一次 
    	editConf.setInterval(2); 
    	//会议ID为2444 
    	editConf.setConfId ("102557"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的editRecurrenceConferenceEx方法编辑已预约的周期性会议，返回TPSDKResponseEx<RecurrenceConfInfoEx>对象 
    	TPSDKResponseEx<RecurrenceConfInfoEx> result = conferenceServiceEx.editRecurrenceConferenceEx(editConf,null); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode();
    	if(0 == resultCode) 
    	{ 
    	    //编辑成功，则返回编辑后的周期性会议信息 
    	    RecurrenceConfInfoEx conferenceInfo = result.getResult(); 
    	}
    }
    
    public void scheduleRecurrenceConferenceEx()
    {
    	//java code 
    	//新建一个RecurrenceConfInfoEx对象 
    	RecurrenceConfInfoEx scheduleConf = new RecurrenceConfInfoEx(); 
    	//会议开始时间为30分钟之后 
    	Date beginTime = new Date(new Date().getTime() + 60 * 60 * 1000); 
    	//新建一个SiteInfoEx对象 
    	SiteInfoEx siteInfo1 = new SiteInfoEx(); 
    	//会场URI为123456 
    	siteInfo1.setUri("123456"); 
    	//会场速率为1920k 
    	siteInfo1.setRate("1920K"); 
    	//会场名称为msj1 
    	siteInfo1.setName("msj1"); 
    	//呼叫方式为MCU主动呼叫会场 
    	siteInfo1.setDialingMode(0); 
    	//会场来源为内部会场 
    	siteInfo1.setFrom(0); 
    	//会场类型为H.323会场类型 
    	siteInfo1.setType(7); 
    	//会场视频格式为4CIF 
    	siteInfo1.setVideoFormat(0); 
    	//会场视频协议为H.263 
    	siteInfo1.setVideoProtocol(1); 
    	//预约会议需要两个以上会场，所以再新建一个会场 
    	SiteInfoEx siteInfo2 = new SiteInfoEx(); 
    	siteInfo2.setUri("123789"); 
    	siteInfo2.setRate("1920K"); 
    	siteInfo2.setName("msj2"); 
    	siteInfo2.setDialingMode(0); 
    	siteInfo2.setFrom(0); 
    	siteInfo2.setType(7); 
    	siteInfo2.setVideoFormat(0); 
    	siteInfo2.setVideoProtocol(1); 
    	//设置会议开始时间 
    	scheduleConf.setBeginTime(beginTime); 
    	//会议名称为msj_conf 
    	scheduleConf.setName("guowenzeng"); 
    	//会议速率为1920k 
    	scheduleConf.setRate("1920k"); 
    	try 
    	{ 
    	    //会议时长为60分钟 
    	    Duration duration =
    	    javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	    scheduleConf.setDuration(duration); 
    	} 
    	catch (DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	} 
    	//向会议中添加会场 
    	List<SiteInfoEx> sites = new ArrayList<SiteInfoEx>(); 
    	sites.add(siteInfo1); 
    	sites.add(siteInfo2); 
    	scheduleConf.setSites(sites); 
    	//周期性会议重复次数为5次 
    	scheduleConf.setCount(5); 
    	//周期类型为每天 
    	scheduleConf.setFrequency(0); 
    	//周期间隔为每两天一次 
    	scheduleConf.setInterval(2); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务的scheduleRecurrenceConferenceEx方法预约周期性会议，返回TPSDKResponseEx<RecurrenceConfInfoEx>对象 
    	TPSDKResponseEx<RecurrenceConfInfoEx> result = conferenceServiceEx.scheduleRecurrenceConferenceEx(scheduleConf); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if(0 == resultCode) 
    	{ 
    	    //预约成功则返回预约后的周期性会议信息 
    	    RecurrenceConfInfoEx conferenceInfo = result.getResult(); 
    	}
    }
    
    public void setSitesMuteEx()
    {
    	//java code 
    	//新建List对象，存放需要静音的会场的URI 
    	List<String> list = new ArrayList<String>(); 
    	list.add("524"); 
    	list.add("525"); 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的setSitesMuteEx方法，让会议ID为99379的会议中会场URI为523和524的会场闭音，返回Integer对象。 
    	//如果返回值为0，则表示闭音成功，否则表示闭音失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = conferenceServiceEx.setSitesMuteEx("102078",list,1);
        if (0 == resultCode)
        {
            System.out.println("setSitesMuteEx success" );
        }
    }
    
    public void synchSiteStatusEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	try 
    	{ 
    	    //开始时间为8小时后 
    	    Date beginTime = new Date(new Date().getTime() - 1000 * 60 * 30); 
    	    //发生变化的时间范围为60分钟内 
    	    Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60); 
    	    //新建一个List对象，存放需要同步忙闲状态的会场URI 
    	    List<String> list = new ArrayList<String>(); 
    	    list.add("525");
    	    //调用会议服务中的synchSiteStatusEx方法，把会场URI为524和525的会场的忙闲状态同步，返回TPSDKResponseEx<Map<String, List<FreeBusyStateEx>>>对象。 
    	    TPSDKResponseEx<Map<String, List<FreeBusyStateEx>>> result = conferenceServiceEx.synchSiteStatusEx(list,beginTime,duration); 
    	    //调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	    Integer resultCode = result.getResultCode(); 
    	    if(0 == resultCode) 
    	    { 
    	        //操作成功后，返回Map<String, List<FreeBusyStateEx>>对象 
    	        Map<String, List<FreeBusyStateEx>> status = result.getResult(); 
    	        System.out.println("status: " + status.toString());
    	    } 
    	} 
    	catch(DatatypeConfigurationException e) 
    	{ 
    	    e.printStackTrace(); 
    	}
    }
    
    public void querySitesEx()
    {
    	//java code 
    	//获取会议相关服务实例 
    	ConferenceServiceEx conferenceServiceEx = ServiceFactoryEx.getService(ConferenceServiceEx.class); 
    	//调用会议服务中的querySitesEx方法查询所有会场信息，返回TPSDKResponseEx<List<SiteInfoEx>>对象。 
    	TPSDKResponseEx<List<SiteInfoEx>> result = conferenceServiceEx.querySitesEx();
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = result.getResultCode(); 
    	if(0 == resultCode) 
    	{ 
    	    //如果查询成功，则返回所有会场信息 
    	    List<SiteInfoEx> list = result.getResult(); 
    	}
    }
    
    public void deleteSiteInfoEx()
    {
    	//java code 
    	//新建一个List对象，用于准备删除会场的URI 
    	List<String> siteUris = new ArrayList<String>(); 
    	siteUris.add("526"); 
//    	siteUris.add("03330155");
    	//获取会场相关服务实例 
    	SiteServiceEx siteServiceEx = ServiceFactoryEx.getService(SiteServiceEx.class); 
    	//调用会场服务中的deleteSiteInfoEx方法，返回Integer对象。
    	//如果返回值为0，则表示删除成功，否则表示删除失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = siteServiceEx.deleteSiteInfoEx(siteUris);
        if (0 == resultCode)
        {
            System.out.println("deleteSiteInfoEx success" );
        }
    }
    
    public void editSiteInfoEx()
    {
    	//java code 
    	//新建一个TerminalInfoEx对象 
    	TerminalInfoEx siteInfo = new TerminalInfoEx(); 
    	//设置会场名称为msj110 
    	siteInfo.setName("gwzgg"); 
    	//设置会场标识为03330154 
    	siteInfo.setUri("523"); 
    	//设置会场类型为SIP类型会场 
    	siteInfo.setType(7); 
    	//设置速率为1920 
    	siteInfo.setRate("4M"); 
    	//新建一个List对象，用于存放终端支持的视频能力参数列表 
    	List<VideoCapbilityItemEx> videoCapbility = new ArrayList<VideoCapbilityItemEx>(); 
    	//新建一个VideoCapbilityItemEx对象 
    	VideoCapbilityItemEx videoCapbilityItemEx = new VideoCapbilityItemEx(); 
    	//设置视频协议为H.261协议 
    	videoCapbilityItemEx.setVideoProtocol(1); 
    	//新建一个List对象 用于存放视频协议对应的视频格式列表 
    	List<Integer> videoFormat = new ArrayList<Integer>(); 
    	//添加视频格式4CIF 
    	videoFormat.add(1); 
    	videoCapbilityItemEx.setVideoFormat(videoFormat); 
    	videoCapbility.add(videoCapbilityItemEx); 
    	siteInfo.setVideoCapbility(videoCapbility); 
    	//设置用户名esdk_tp 
    	siteInfo.setRegUser("esdk_tp"); 
    	//设置密码Huawei@123 
    	siteInfo.setRegPassword("Huawei@123"); 
    	//获取会场相关服务实例 
    	SiteServiceEx siteServiceEx = ServiceFactoryEx.getService(SiteServiceEx.class); 
    	//调用会场服务中的editSiteInfoEx方法，返回Integer对象。 
    	//如果返回值为0，则表示操作成功，否则表示操作失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = siteServiceEx.editSiteInfoEx(siteInfo);
        if (0 == resultCode)
        {
            System.out.println("editSiteInfoEx success" );
        }
    }
    
    public void addSiteInfoEx()
    {
    	//java code 
    	//设置准备将会场添加到的组织节点ID 
    	String orgId = "1"; 
    	//新建一个TerminalInfoEx对象 
    	TerminalInfoEx siteInfo = new TerminalInfoEx(); 
    	//设置会场名称为SP1549 
    	siteInfo.setName("guo001"); 
    	//设置会场标识为03330154 
    	siteInfo.setUri("526"); 
    	//设置会场类型为SIP类型会场 
    	siteInfo.setType(7); 
    	//设置速率为1920
    	siteInfo.setRate("1920"); 
    	//新建一个List对象，用于存放终端支持的视频能力参数列表 
    	List<VideoCapbilityItemEx> videoCapbility = new ArrayList<VideoCapbilityItemEx>(); 
    	//新建一个VideoCapbilityItemEx对象 
    	VideoCapbilityItemEx videoCapbilityItemEx = new VideoCapbilityItemEx(); 
    	//设置视频协议为H.261协议 
    	videoCapbilityItemEx.setVideoProtocol(1); 
    	//新建一个List对象，用于存放视频协议对应的视频格式列表 
    	List<Integer> videoFormat = new ArrayList<Integer>();
    	//添加视频格式4CIF 
    	videoFormat.add(1); 
    	videoCapbilityItemEx.setVideoFormat(videoFormat); 
    	videoCapbility.add(videoCapbilityItemEx); 
    	siteInfo.setVideoCapbility(videoCapbility); 
    	//设置用户名esdk_tp 
    	siteInfo.setRegUser("esdk_tp"); 
    	//设置密码Huawei@123 
    	siteInfo.setRegPassword("Huawei@123"); 
    	//获取会场相关服务实例 
    	SiteServiceEx siteServiceEx = ServiceFactoryEx.getService(SiteServiceEx.class); 
    	//调用会场服务中的addSiteInfoEx方法，返回Integer对象。 
    	//如果返回值为0，则表示添加成功，否则表示添加失败，具体失败原因请参考错误码列表。 
    	Integer resultCode = siteServiceEx.addSiteInfoEx(orgId,siteInfo);
        if (0 == resultCode)
        {
            System.out.println("addSiteInfoEx success" );
        }
    }
    
    //查询SMC系统中的会场信息
    public void querySitesInfoEx()
    {
        //设置查询条件  
        QueryConfigEx queryConfig = new QueryConfigEx();
        //对查询结果按照会场名称升序方式进行排序 
        SortItemEx sortItem = new SortItemEx();
        sortItem.setItemIndex(0);
        sortItem.setSort(0);
        queryConfig.getSortItems().add(sortItem);
        //获取满足会场名称包含VCT条件的会场 
        StringFilterEx stringFilter = new StringFilterEx();
        stringFilter.setColumnIndex(0);
        stringFilter.setValue("guo001");
        queryConfig.getFilters().add(stringFilter);
        queryConfig.setFocusItem(0);
        //每页10个会场，获取第1页 
        PageParamEx pageParam = new PageParamEx();
        pageParam.setCurrentPage(1);
        pageParam.setNumberPerPage(10);
        queryConfig.setPageParam(pageParam);
        //获取会场管理的服务实例
        SiteServiceEx tpSiteMgrService = ServiceFactoryEx.getService(SiteServiceEx.class);
        // 调用会场管理服务的querySitesInfoEx方法，返回QuerySitesInfoExResponse对象 
        TPSDKResponseWithPageEx<List<TerminalInfoEx>> result = tpSiteMgrService.querySitesInfoEx(queryConfig);
        
        System.out.println("status:" + result.getResult());
        
        if (0 == result.getResultCode())
        {
            //查询成功 
            System.out.println("Sites total record:" + result.getPageInfo().getTotalRecords());
        }
        
    }
    
    //创建会议模板
    public void addAdhocConfTemplateEx()
    {
    	//获取会议服务的实例
    	ConferenceServiceEx conferenceService = ServiceFactoryEx.getService(ConferenceServiceEx.class);  
    	//设置Adhoc会议模板信息参数     
    	AdhocConfTemplateParamEx confTemplate = new AdhocConfTemplateParamEx(); 
    	confTemplate.setName("AdConf123"); 
    	 
    	try
    	{
    	    // 会议时长为60分钟
    	    Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60);
    	    confTemplate.setDuration(duration);
    	}
    	catch (DatatypeConfigurationException e)
    	{
    	    e.printStackTrace();
    	}
    	//设置会议接入号为123，后续预约会议接口通过该值找到这个会议模板
    	confTemplate.setAccessCode("523113"); 
    	//设置会议为3画面
    	confTemplate.setCpResource(3); 
    	// 新建一个SiteInfoEx对象
    	SiteInfoEx siteInfo1 = new SiteInfoEx();
    	// 会场URI为12345600
    	siteInfo1.setUri("12345600");
    	// 会场速率为1920k
    	siteInfo1.setRate("1920K");
    	// 会场名称为guo1
    	siteInfo1.setName("guo1");
    	// 呼叫方式为MCU主动呼叫会场
    	siteInfo1.setDialingMode(0);
    	// 会场来源为内部会场
    	siteInfo1.setFrom(0);
    	// 会场类型为H.323会场类型
    	siteInfo1.setType(7);
    	// 视频格式为720P
    	siteInfo1.setVideoFormat(2);
    	// 视频协议为H.264协议
    	siteInfo1.setVideoProtocol(3);
    	//再新建一个会场
    	SiteInfoEx siteInfo2 = new SiteInfoEx();
    	siteInfo2.setUri("12378900");
    	siteInfo2.setRate("1920K");
    	siteInfo2.setName("guo2");
    	siteInfo2.setDialingMode(0);
    	siteInfo2.setFrom(0);
    	siteInfo2.setType(7);
    	siteInfo2.setVideoFormat(2);
    	siteInfo2.setVideoProtocol(3);
    	List<SiteInfoEx> sites = new ArrayList<SiteInfoEx>();
    	sites.add(siteInfo1);
    	sites.add(siteInfo2);
    	confTemplate.setSites(sites);
    	//组织节点Id为1，代表是根组织节点
    	String orgId = "1";
    	// 调用会议服务的addAdhocConfTemplateEx方法，返回TPSDKResponseEx<String>对象   
    	TPSDKResponseEx<String> response = conferenceService.addAdhocConfTemplateEx(orgId, confTemplate); 
    	//调用TPSDKResponseEx<T>中的getResultCode()方法获取返回值，如果返回值为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。
    	if(0 == response.getResultCode()) 
    	{ 
    	    //获取模板Id，该参数仅用于对会议模板的修改和删除
    	    String templateId = response.getResult();
    	    System.out.println("AdhocConfTemplate id is:" + templateId);
    	}
    }
    
    //通过会议模板接入号预约会议
    public void scheduleConfExByAccessCode()
    {
        // 新建一个ConferenceInfoEx对象
        ConferenceInfoEx conferenceInfo = new ConferenceInfoEx();
        // 会议开始时间为30分钟之后
        Date beginTime = new Date(new Date().getTime() + 1000 * 30 * 60);
        // 设置会议开始时间
        conferenceInfo.setBeginTime(beginTime);
        // 会议名称为test
        conferenceInfo.setName("test");
        //设置会议激活号码
        conferenceInfo.setAccessCode("123");
        
        try
        {
            // 会议时长为60分钟
            Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60);
            conferenceInfo.setDuration(duration);
        }
        catch (DatatypeConfigurationException e)
        {
            e.printStackTrace();
        }

        
        //获取会议管理服务类实例
        ConferenceServiceEx tpConfMgrService = ServiceFactoryEx.getService(ConferenceServiceEx.class);
        // 调用会议服务的scheduleConfEx方法，返回ScheduleConfExResponse对象
        TPSDKResponseEx<ConferenceInfoEx> result = tpConfMgrService.scheduleConfEx(conferenceInfo);
        // 调用getResultCode()方法，获取返回码，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。
        Integer resultCode = result.getResultCode();
        if (0 == resultCode)
        {
            // 预约成功，则返回预约后的会议信息
            ConferenceInfoEx scheduledConf = result.getResult();
            System.out.println("ScheduleConf success , ConferenceID is " + scheduledConf.getConfId());
        }
    }
    
    //预约普通会议
    public void scheduleConfEx()
    {
        // 新建一个ConferenceInfoEx对象
        ConferenceInfoEx conferenceInfo = new ConferenceInfoEx();
        // 会议开始时间
        Date beginTime = new Date(new Date().getTime());
        // 新建一个SiteInfoEx对象
        SiteInfoEx siteInfo1 = new SiteInfoEx();        
        // 会场URI为524
        siteInfo1.setUri("525");
        // 会场速率为1920k
        siteInfo1.setRate("1920K");
        // 会场名称为guozai1
        siteInfo1.setName("guozai1");
        // 呼叫方式为MCU主动呼叫会场
        siteInfo1.setDialingMode(0);
        // 会场来源为内部会场
        siteInfo1.setFrom(0);
        // 会场类型为H.323会场类型
        siteInfo1.setType(7);
        // 视频格式为720P
        siteInfo1.setVideoFormat(2);
        // 视频协议为H.264协议
        siteInfo1.setVideoProtocol(3);
        //再新建一个会场
        SiteInfoEx siteInfo2 = new SiteInfoEx();
        siteInfo2.setUri("123789");
        siteInfo2.setRate("1920K");
        siteInfo2.setName("msj2");
        siteInfo2.setDialingMode(0);
        siteInfo2.setFrom(0);
        siteInfo2.setType(7);
        siteInfo2.setVideoFormat(2);
        siteInfo2.setVideoProtocol(3);
        // 设置会议开始时间
        conferenceInfo.setBeginTime(beginTime);
        // 会议名称为test
        conferenceInfo.setName("laowang");
        // 会议速率为1920k
        conferenceInfo.setRate("1920k");
        conferenceInfo.setIsLiveBroadcast(0);
        conferenceInfo.setIsRecording(0);
        conferenceInfo.setMediaEncryptType(2);
        RecordParamEx recordParam = new RecordParamEx();
        recordParam.setRseId(218);
        conferenceInfo.setRecordParam(recordParam);
        try
        {
            // 会议时长为60分钟
            Duration duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 60);
            conferenceInfo.setDuration(duration);
        }
        catch (DatatypeConfigurationException e)
        {
            e.printStackTrace();
        }
        // 向会议中添加会场
        conferenceInfo.getSites().add(siteInfo1);
        conferenceInfo.getSites().add(siteInfo2);

        //获取会议管理服务类实例
        ConferenceServiceEx tpConfMgrService = ServiceFactoryEx.getService(ConferenceServiceEx.class);
        // 调用会议服务的scheduleConfEx方法，返回ScheduleConfExResponse对象
        TPSDKResponseEx<ConferenceInfoEx> result = tpConfMgrService.scheduleConfEx(conferenceInfo);
        // 调用getResultCode()方法，获取返回码，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。
        Integer resultCode = result.getResultCode();
        if (0 == resultCode)
        {
            // 预约成功，则返回预约后的会议信息
            ConferenceInfoEx scheduledConf = result.getResult();
        }
    }
    
    //查询指定会场在指定时间范围内的忙闲状态
    public void querySiteStatusEx()
    {
        //创建List对象，用于存放需要查询忙闲状态的会场URI
        List<String> sites = new ArrayList<String>();
//        sites.add("123456");
        sites.add("524");
        //示例中开始时间为now
        Date date = new Date(new Date().getTime());
        Duration duration = null;
        try
        {
            //需要查询的时间范围为120分钟之内
            duration = javax.xml.datatype.DatatypeFactory.newInstance().newDuration(1000 * 60 * 120);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //获取会议管理服务类实例
        ConferenceServiceEx tpConfMgrService = ServiceFactoryEx.getService(ConferenceServiceEx.class);
        // 调用会场管理服务的querySiteStatusEx方法，返回QuerySiteStatusExResponse对象
        TPSDKResponseEx<Map<String, List<FreeBusyStateEx>>> result = tpConfMgrService.querySiteStatusEx(sites, date, duration);
        if (0 == result.getResultCode())
        {
            //如果查询成功，则返回一个List<SiteFreeBusyStatesEx>对象 
            Map<String, List<FreeBusyStateEx>> status = result.getResult();
            //输出会场忙闲状态
            System.out.println("QuerySiteStatus success:" + status.size());
        }
    }
    
    //查询组织节点信息
    public void queryOrganizationEx()
    {
    	//获取组织节点管理服务类实例
    	OrganizationServiceEx orageMgrService = ServiceFactoryEx.getService(OrganizationServiceEx.class); 
    	// 调用组织节点管理服务的queryOrganizationEx方法，返回TPSDKResponseEx<List<OrganizationItemEx>>对象
    	TPSDKResponseEx<List<OrganizationItemEx>> response = orageMgrService.queryOrganizationEx();
        if (0 == response.getResultCode())
        {
            //如果查询成功，则返回一个List<OrganizationItemEx>对象 
            List<OrganizationItemEx> orgs = response.getResult();
            //输出组织节点个数
            System.out.println("QueryOrganization success:" + orgs.size());
        }
    }
    
    public void login(String userName, String password)
    {
    	//获取鉴权服务类实例
    	AuthorizeServiceEx authorizeService = ServiceFactoryEx.getService(AuthorizeServiceEx.class); 
    	//调用服务类中的登录接口，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。
    	int loginResult = authorizeService.login(userName,password);
    	//loginResult是返回码，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表
    	if(0 == loginResult)
    	{
    		System.out.println("login method successfully.");
    	}
    	else
    	{
    	    System.out.println("login method failed, error code is:" + loginResult);
    	}

    }
    
    public void keepAlive()
    {
    	//获取鉴权服务类实例
    	AuthorizeServiceEx authorizeService = ServiceFactoryEx.getService(AuthorizeServiceEx.class); 
    	//调用服务类中的保活接口，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。
    	Integer ret = authorizeService.keepAlive();
    	// ret是返回码，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表
    	if(0 == ret)
    	{
    		System.out.println("keepAlive method successfully.");
    	}
    	else
    	{
    	    System.out.println("keepAlive method failed, error code is:" + ret);
    	}
    }
    
    
    //登出
    public void logout()
    {
    	//获取鉴权服务类实例
    	AuthorizeServiceEx authorizeService = ServiceFactoryEx.getService(AuthorizeServiceEx.class); 
    	//调用服务类中的登出接口，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表。
    	Integer ret = authorizeService.logout();
    	// ret是返回码，如果返回码为0，则表示成功，否则表示失败，具体失败原因请参考错误码列表
    	if(0 == ret)
    	{
    		System.out.println("logout method successfully.");
    	}
    	else
    	{
    	    System.out.println("logout method failed, error code is:" + ret);
    	}
    }
}
