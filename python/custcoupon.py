'''
Example module for processing data from the Customer table to calculate Coupon based on the amount spent.
Using pyodbc to interface with SQL Server.
'''
import pyodbc

try:
   conn=pyodbc.connect("Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor=conn.cursor()
   conn1=pyodbc.connect("Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor1=conn1.cursor()
   conn2 = pyodbc.connect(
       "Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor2 = conn2.cursor()
   cursor.execute("select c.cust_id,c.custname,c.age,c.sex,ct.amount from custinfo c inner join cust_tran ct on c.cust_id=ct.cust_id")
   row=cursor.fetchone()
   while row:
       select_str='''select coupon_int from coupon_info where trans_amt=(select min(trans_amt) from coupon_info where trans_amt>={var})'''
       sql=select_str.format(var=row[4])
       cursor1.execute(sql)
       row1=cursor1.fetchone()
       coupon_amt=row1[0]
       insert_str='''insert into cust_coupon_info values ({cust_id},'{cust_name}',{age},'{sex}',{coupon_amt})'''
       sql1=insert_str.format(cust_id=row[0],cust_name=row[1],age=row[2],sex=row[3],coupon_amt=coupon_amt)
       cursor2.execute(sql1)
       row=cursor.fetchone()


except Exception as e:
   print(e)

finally:
    conn2.commit()
    conn.close()
    conn1.close()
    conn2.close()