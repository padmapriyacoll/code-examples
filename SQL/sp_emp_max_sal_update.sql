USE [PriyaDB]
GO
/****** Object:  StoredProcedure [dbo].[Senior_emp]    Script Date: 9/3/2018 10:24:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER proc [dbo].[Senior_emp]
AS
BEGIN
  set nocount on;
  DECLARE @salary int,@mgr_id int,@mgr_name nvarchar(50),@max_salary  int,@temp int,@first int =1,@temp1 nvarchar(10),@emp_id int,@emp_name nvarchar(20),@temp2 int,@temp3 nvarchar(20);


DECLARE Senior_cursor CURSOR
FOR
   SELECT e.mgr_id,m.mgr_name,e.emp_id,e.emp_name,e.salary FROM emp_detail e inner join manager_detail m on e.mgr_id = m.mgr_id ORDER BY e.mgr_id;
   OPEN Senior_cursor
   FETCH NEXT FROM Senior_cursor INTO @mgr_id,@mgr_name,@emp_id,@emp_name,@salary;

   WHILE @@FETCH_STATUS = 0
     BEGIN
        IF @first=1
           BEGIN
              set @temp=@mgr_id;set @temp1=@mgr_name;  set @temp2=@emp_id; set @temp3=@emp_name; set @max_salary=@salary; set @first=0;
			END
        ELSE
			 BEGIN
				 IF @mgr_id=@temp
					BEGIN
					   IF @max_salary<@salary
						  BEGIN
							 set @max_salary=@salary;set @temp3=@emp_name;set @temp2=@emp_id;
						   END
					  ELSE
						BEGIN
			    			SET @max_salary=@max_salary;
			 			END
					END
	         ELSE
	           BEGIN
		        IF @mgr_id!=@temp
		            IF exists(select * from senior_employee where mgr_id=@temp and emp_id=@temp2 )
			           update senior_employee set max_salary=@max_salary where mgr_id=@temp and emp_id=@temp2 ;
			        ELSE
			           BEGIN
			              insert into senior_employee values(@temp,@temp1,@temp2,@temp3,@max_salary)
			            END
			            set @temp=@mgr_id; set @temp1=@mgr_name; set @temp2=@emp_id;set @temp3=@emp_name;set @max_salary=@salary
			    END
	END
  FETCH NEXT FROM Senior_cursor into @mgr_id,@mgr_name,@emp_id,@emp_name,@salary;
 END

 
  IF exists(select * from senior_employee where mgr_id=@temp)
			   update senior_employee set max_salary=@max_salary where mgr_id=@temp 
  ELSE
	  BEGIN
			insert into senior_employee values(@temp,@temp1,@temp2,@temp3,@max_salary)
      END
 CLOSE Senior_cursor
DEALLOCATE Senior_cursor
END