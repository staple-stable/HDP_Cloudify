import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

import org.cloudifysource.dsl.context.ServiceContext;
import org.cloudifysource.dsl.context.ServiceContextFactory
import org.cloudifysource.dsl.context.ServiceInstance;

//def context = binding.variables.context as ServiceContext
def context = ServiceContextFactory.getServiceContext()

def masterServiceName = "hdp-master"
def workerServiceName = "hdp-worker"
def serviceNames = [master:(masterServiceName), worker:(workerServiceName)]
def hostsMap = context.attributes.thisInstance.hostsMap
def hostEntries = ""

println "updateHosts.groovy: on ${context.getServiceName()}-${context.getInstanceId()}:${context.getPrivateAddress()}..."

hostsMap= hostsMap==null?[:]:hostsMap

def attrs
def serviceInstances
try{
	//update /etc/hosts
	def hostsPath = "/etc/hosts"
	serviceNames.each {
		if (context.waitForService(it.value,1,TimeUnit.SECONDS) == null){return}
		serviceInstances = context.waitForService(it.value, 20, TimeUnit.SECONDS).getInstances()
		serviceInstances.each{ins ->
			def ip = ins.getHostAddress()
			def host = ins.getHostName()
			//attrs = context.attributes[it.value].instances[ins.getInstanceId()]
			if (ip != null && host != null) {
				println "Got: ${ip}: ${host}"
				hostsMap[host] = ip
			}
		}
	}
	hostsMap.each {
		hostEntries += it.value + "\t" + it.key + "\n"
	}
	println("++++++++++++++++++++++++++++++++++++++++++++++++++")
	println(hostEntries)
	println("++++++++++++++++++++++++++++++++++++++++++++++++++")
	new File("/tmp/hosts").text = hostEntries

	println "updateHosts.groovy: updating hosts"

	builder = new AntBuilder() 

	builder.sequential {
		chmod(file:"${context.serviceDirectory}/scripts-sh/sudoTee.sh", perm:'ugo+rx')
		exec(executable:"${context.serviceDirectory}/scripts-sh/sudoTee.sh", osfamily:"unix", failonerror:"true"){
			arg(value:"/tmp/hosts")
			arg(value:"/etc/hosts")
		}
	}
	println "updateHosts.groovy: new hostsMap is: "+hostsMap.toString()
	context.attributes.thisInstance.hostsMap = hostsMap
	
	return hostsMap
}
finally {
	println("updateHosts.groovy: releasing the lock...")
}