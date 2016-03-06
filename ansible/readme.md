##Prerquisite
Ansible 2.X
boto (python aws cli)

##Amazon pem file
chmod 400 *.pem
connect to ssh : ssh -l ec2-user -i <path_to_pem_file> ip

##Ansible : don't ask ssh fingerprint 
export ANSIBLE_HOST_KEY_CHECKING=False
cd nightclazz_spark_ml/ansible

## Check connection : 
python -c 'import aws_tools as aws;aws.printInstancesState()'


## Generate inventory : 
python -c 'import aws_tools as aws;aws.generateInventory()'> inventory/inventory

## Check inventory : 
ansible all -i inventory/inventory --private-key <path_to_pem_file> -m ping

##launch install
ansible-playbook -i inventory/inventory --private-key <path_to_pem_file> deploy_containers.yml


