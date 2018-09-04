'''
Example module for getting input from a Database table and Updating data in a target table.
Using pyodbc library for interfacing with SQL Server Database.
'''
import pyodbc
try:
   conn=pyodbc.connect("Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor=conn.cursor()
   conn1=pyodbc.connect("Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor1=conn1.cursor()
   cursor.execute("select c.cust_id,c.cust_name,a.acct_id,a.balance from cust_info c inner join acct_info a on c.cust_id = a.cust_id ")
   row=cursor.fetchone()
   bank={}
   while row:
       bank_dict={}
       bank_dict["cust_name"]=row[1]
       bank_dict["balance"] = int(row[3])
       if row[0] in bank:
           v=bank[row[0]]
           v["balance"]= v["balance"]+int(row[3])

       else:
           bank[row[0]]=bank_dict
       row=cursor.fetchone()
   print(bank)
   for key, value in bank.items():
      print(key, value)
      insert_str = '''insert into statement1_info values({cust_id},'{cust_name}',{balance})'''
      sql_command = insert_str.format(cust_id=key, cust_name=value["cust_name"], balance=value["balance"])
      cursor1.execute(sql_command)
except Exception as e:
    print(e)

finally:
    conn1.commit()
    conn.close()
    conn1.close()