package com.huawei.esdk.bsdemo.codesample;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.xml.ws.soap.SOAPFaultException;

import com.huawei.esdk.tp.professional.local.ServiceFactoryEx;
import com.huawei.esdk.tp.professional.local.authentication.AuthorizeServiceEx;

/** 
 * ESDK SESSION保活线程 
 * <p> 
 * @since 
 * @see com.huawei.esdk.bsdemo.codesample.demo.servlet.ConnectTimer 
 * @date 2017-05-04 
 */ 
public class ConnectTimer implements Runnable 
{ 
    AuthorizeServiceEx tpCommonService = ServiceFactoryEx.getService(AuthorizeServiceEx.class);
     
    private static ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor(); 
     
    /** 
     * 连续失败次数 
     * <br>如果保活连续三次失败，则停止保活 
     */ 
    private int failedTimes = 0; 
     
    /** 
     * 定时任务：向ESDK Server发送保活请求 
     * @author   
     * @since  
     */ 
    @Override 
    public void run() 
    { 
        System.out.println("keepAlive method start"); 
         
        try 
        { 
            // 调用保持心跳接口进行保活 
            int keepAliveResponse = tpCommonService.keepAlive(); 
            // 保活接口返回值不为0 
            if (0 != keepAliveResponse) 
            { 
                if (2 > failedTimes) 
                { 
                    failedTimes += 1; 
                    System.out.println("[ConnectTimer]keepAlive error: " + failedTimes + ",ResultCode=" 
                        + keepAliveResponse); 
                } 
                else 
                { 
                    failedTimes = 0; 
                    ConnectTimer.timer.shutdown(); 
                    System.out.println("[ConnectTimer]keepAlive error: ResultCode=" + keepAliveResponse
                        + "Stop to keepAlive"); 
                } 
                return; 
            } 
             
            failedTimes = 0; 
            System.out.println("[ConnectTimer]keepAlive success"); 
        } 
        catch (SOAPFaultException e) 
        {   // 捕获调用保活接口中产生的SOAP异常信息 
            if (2 > failedTimes) 
            { 
                failedTimes += 1; 
                System.out.println("[ConnectTimer]keepAlive failed:Session Failure." + failedTimes); 
            } 
            else 
            { 
                failedTimes = 0; 
                ConnectTimer.timer.shutdown(); 
                System.out.println("[ConnectTimer]keepAlive failed:Session Failure.Stop to keepAlive"); 
            } 
        } 
        catch (Exception e) 
        { 
            System.out.println("keepAlive method error:" + e.toString()); 
        } 
    } 
     
    public static ScheduledExecutorService getTimer() 
    { 
        return timer; 
    } 
     
    public static void setTimer(ScheduledExecutorService timer) 
    { 
        ConnectTimer.timer = timer; 
    } 
     
}

