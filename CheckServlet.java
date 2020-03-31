package com.vaidehi;

/* Program created by Vaidehi Pandya */

// word taken should not include any spaces in between

import javax.servlet.http.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class CheckServlet extends HttpServlet
{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException,NullPointerException
	{
			String str=req.getParameter("str");		//taking string
			Vector <Character> ch=new Vector<Character>();	//vector for storing characters
			Vector <Integer>count=new Vector<Integer>();	//vector for storing count of characters
			int i=0,k=0,tmp_index=0;
			while(i<str.length())	//go until length of string
			{
				char temp=str.charAt(i);	
				int flag=0;
				for(k=0;k<ch.size();k++)	//checking for all char. in vector
				{
					if((ch.get(k))==temp)	//if character is in vector
					{
						tmp_index=k;	//store index of character
						flag=1;	
					}
				}
				if(flag==1)	//character is already there in vector
				{
					int tmp_int=count.get(tmp_index).intValue();
					tmp_int++;
					count.add(tmp_index, tmp_int);
				}
				else
				{
					ch.add(str.charAt(i));
					count.add(1);	//set count to 1
				}
				i++;	//increase i by 1 and repeat until string length
			}
			for(i=0;i<ch.size();i++)	//using bubble sort to store character count wise.
			{
				for(int j=0;j<ch.size()-i-1;j++)
				{
					if(count.get(j)>count.get(j+1))
					{
						char tmp_ch=ch.get(j);
						int tmp_cnt=count.get(j);
						ch.setElementAt(ch.get(j+1),j);
						count.setElementAt(count.get(j+1), j);
						ch.setElementAt(tmp_ch, j+1);
						count.setElementAt(tmp_cnt, j+1);
					}
				}	
			}
			boolean pyramid_flag=true;	//setting initial to true
			if(count.get(0)!=1)	//if initial frequency is not 1
			{
				pyramid_flag=false;
			}
			for(i=1;i<ch.size();i++)
			{
				if(!(count.get(i)==count.get(i-1)+1))	//if count is not in '+1' order
				{
					pyramid_flag=false;	//set to false
					break;	//move out of loop. No need of further check
				}
			}	
			PrintWriter out=res.getWriter();
			out.print("\n Given String : " +str);
			out.print("\n\n---- Frequency counts ---- \n\n ");
			for(i=0;i<ch.size();i++)
			{
				out.print("\n "+ch.get(i)+"\t "+count.get(i));
			}
			if(pyramid_flag==true)
			{
				out.print("\n\n The word is pyramid!");
			}
			else
			{
				out.print("\n\n The word is not pyramid!");
			}
	}
}
