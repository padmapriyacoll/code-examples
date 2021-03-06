USE [PriyaDB]
GO
/****** Object:  StoredProcedure [dbo].[Emp_increment]    Script Date: 9/3/2018 10:13:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER  Procedure [dbo].[Emp_increment] 
   @manager_id int, 
   @incramt int
AS 
BEGIN
   SET nocount on
   DECLARE  @emp_id int,@mgr_id int,@salary int,@emp_name nvarchar(50);
   DECLARE Incr_cursor CURSOR
   FOR
     SELECT emp_id,mgr_id,salary FROM emp_detail WHERE mgr_id=@manager_id;
   OPEN Incr_cursor
   FETCH NEXT FROM Incr_cursor INTO @emp_id,@mgr_id,@salary;
   WHILE @@FETCH_STATUS=0
     BEGIN
	        SET @salary=(@salary+@incramt);
		    UPDATE emp_detail SET salary=@salary WHERE mgr_id = @manager_id and emp_id=@emp_id;
		    FETCH NEXT FROM Incr_cursor INTO @emp_id,@mgr_id,@salary;
	 END 
	CLOSE Incr_cursor
	DEALLOCATE Incr_cursor
END	 