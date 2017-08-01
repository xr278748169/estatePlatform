// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SmsVoiceUploaderResult.java

package com.kerry.qcloud.sms;


public class SmsVoiceUploaderResult
{

	public int result;
	public String msg;
	public String file;

	public SmsVoiceUploaderResult()
	{
	}

	public String toString()
	{
		if (result == 0)
			return String.format("SmsVoiceUploaderResult:result %d\nmsg %s\nfile %s", new Object[] {
				Integer.valueOf(result), msg, file
			});
		else
			return String.format("SmsVoiceUploaderResult:result %d\nmsg %s", new Object[] {
				Integer.valueOf(result), msg
			});
	}
}
