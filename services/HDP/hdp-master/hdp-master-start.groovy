import org.cloudifysource.dsl.context.ServiceContextFactory
import org.cloudifysource.dsl.context.ServiceContext;
import org.cloudifysource.dsl.context.ServiceInstance;

def context = ServiceContextFactory.getServiceContext()
def installScriptSh = "${context.serviceDirectory}/script-sh/installAmbari.sh"
def builder = new AntBuilder()

def hostsFileUpdater = new HostsFileUpdater()
hostsFileUpdater.updateHosts(context)
builder.sequential {
	exec(executable: installScriptSh, failonerror: "true")
}
println "${context.serviceName}-preinstall.groovy: Host ${context.getPrivateAddress()} is started!!!"
context.attributes.thisInstance.isReady = true