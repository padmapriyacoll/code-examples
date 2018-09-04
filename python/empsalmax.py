'''
Example module to read from 2 files and print the output.
'''
try:
    emp = {}
    with open("c://priyafiles//empdet.txt", "r") as ff:

        for line in ff:
            (emp_id,emp_name,mgr_id,salary)=line.split(",")
            emp_dict={}
            emp_dict["emp_id"] = int(emp_id)
            emp_dict["emp_name"]=emp_name
            emp_dict["salary"] = int(salary)

            if int(mgr_id) in emp:
                v= emp[int(mgr_id)]
                if int(salary)>v["salary"]:
                    emp[int(mgr_id)]=emp_dict
            else:
                  emp[int(mgr_id)]=emp_dict

    with open("c://priyafiles//manager.txt","r") as mm:
        for line in mm:
            (mgr_id,mgr_name,dep_name)=line.split(",")
            v=emp[int(mgr_id)]
            emp_id=v["emp_id"]
            emp_name=v["emp_name"]
            salary=v["salary"]
            print(emp_id,emp_name,salary,mgr_name,dep_name)
except Exception as e:
    print(e)