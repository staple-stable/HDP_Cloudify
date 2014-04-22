import org.cloudifysource.dsl.context.ServiceContextFactory
import org.cloudifysource.dsl.context.ServiceContext;
import org.cloudifysource.dsl.context.ServiceInstance;

import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
 
def context = ServiceContextFactory.getServiceContext()

def builder = new AntBuilder()
 
	 
def installScriptSh = "${context.serviceDirectory}/script-sh/installAmbari.sh"
def keygenSSH = "${context.serviceDirectory}/script-sh/keygenSSH.sh"
def setSSHScriptSh = "${context.serviceDirectory}/script-sh/setSSH.sh"
	 	 
println "${context.serviceName}-preinstall.groovy: Running ${keygenSSH}..."
	 
builder.sequential {
	chmod(file:keygenSSH, perm:"ugo+rx")
	println("changed permissins successfuly")
	//chmod(file:setSSHScriptSh, perm:"ugo+rx")
	chmod(file:installScriptSh, perm:"ugo+rx")
	exec(executable:keygenSSH, failonerror:"true")
	//exec(executable: installScriptSh, failonerror: "true")
}

/*if (context.waitForService("hdp-worker", 1, TimeUnit.SECONDS) == null) {return}
serviceInstances = context.waitForService("hdp-worker", 20, TimeUnit.SECONDS).getInstances()
serviceInstances.each{ins->
	def attrs = context.attibutes["hdp-worker"].instances[ins.getInstanceId()]
	def ip = attrs.ipAddress
	def arg = "user@"+ip
	builder.sequential {
		exec(executable:setSSHScriptSh, failonerror:"true") {
			arg(value:arg)
		}
	}
}*/

/*builder.sequential {
	exec(executable: installScriptSh, failonerror: "true")
}*/
println "${context.serviceName}-preinstall.groovy: Host ${context.getPrivateAddress()} ready for installations!"