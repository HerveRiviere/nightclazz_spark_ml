import boto.ec2

REGION = 'eu-west-1'

def getInstances():
	ec2 = boto.ec2.connect_to_region(REGION)
	return ec2.get_only_instances(filters={'tag:Name':'nightclazz'})
	
def printInstancesState() : 
	reservations = getInstances()
	for instance in reservations : 
		ip_adress = instance.ip_address
		if(ip_adress!=None):
			print ip_adress+"\t"+instance.state


def generateInventory() : 
	reservations = getInstances()
	print "[nightclazz]"
	for instance in reservations : 
		ip_adress = instance.ip_address
		if(ip_adress!=None):
			print ip_adress+"  ansible_user=ec2-user"

