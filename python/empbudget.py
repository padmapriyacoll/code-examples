'''
 Example module to calculate budget for each department based on the employees' salary.
 Using pyodbc to interface with SQL Server.
'''

import pyodbc
try:
   conn=pyodbc.connect("Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor=conn.cursor()
   conn1=pyodbc.connect("Driver={SQL Server};" "Server=localhost,53551;" "Database=PriyaDB;" "Trusted-connection = yes;")
   cursor1=conn1.cursor()
   cursor.execute("select e.emp_id,e.dept_id,en.salary from empdet e inner join emp_newsal en on e.emp_id=en.emp_id")
   row=cursor.fetchone()
   dept={}
   while row:
       dept_dict={}
       dept_dict["dept_id"]=row[1]
       dept_dict["amount"] =row[2]
       if row[1] in dept:
           v=dept[row[1]]
           v["amount"]=row[2]+v["amount"]

           v["dept_id"]=row[1]
       else:
           dept[row[1]]=dept_dict
       row=cursor.fetchone()

   for key, value in dept.items():

       insert_str = '''insert into dept_budget values({dept_id},{amount})'''
       sql_command = insert_str.format( dept_id=value["dept_id"], amount=value["amount"])

       print(sql_command)
       cursor1.execute(sql_command)
   conn.commit()
   conn1.commit()

   conn.close()
   conn1.close()

except Exception as e:
   print(e)