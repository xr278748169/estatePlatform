// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SmsSingleVoiceSenderResult.java

package com.kerry.qcloud.sms;


public class SmsSingleVoiceSenderResult
{

	public int result;
	public String errmsg;
	public String ext;
	public String callid;

	public SmsSingleVoiceSenderResult()
	{
		ext = "";
	}

	public String toString()
	{
		if (result == 0)
			return String.format("SmsSingleVoiceSenderResult\nresult %d\nerrmsg %s\next %s\ncallid %s", new Object[] {
				Integer.valueOf(result), errmsg, ext, callid
			});
		else
			return String.format("SmsSingleVoiceSenderResult\nresult %d\nerrmsg %s\next %s", new Object[] {
				Integer.valueOf(result), errmsg, ext
			});
	}
}
