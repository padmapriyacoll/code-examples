'''
This module finds customers with over due books.
Using pyodbc to interface with SQL Server.
Using datetime module for date calculations.
'''

import pyodbc
from datetime import datetime

try:
   conn=pyodbc.connect("Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor=conn.cursor()
   conn1= pyodbc.connect(
       "Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor1 = conn1.cursor()

   cursor.execute("select * from check_info where check_in_date is null ")
   row=cursor.fetchone()
   book={}
   while row:
       book_dict={}
       book_dict["cust_id"] = row[0]
       book_dict["book_id"] = row[1]
       m2=row[2]

       now=datetime.today().date()
       m=datetime.strptime(m2,"%Y-%m-%d").date()
       m3=(now - m).days
       print(m)
       if (now - m ).days >7:
           insert_str='''insert into cust_status values ({cust_id},{book_id},{no_of_days})'''
           sql=insert_str.format(cust_id=row[0],book_id=row[1],no_of_days= m3)
           cursor1.execute(sql)

       row=cursor.fetchone()
   print(book)
   conn.close()
   conn1.commit()
   conn1.close()

except Exception as e:
    print(e)






