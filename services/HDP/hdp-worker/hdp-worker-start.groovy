import org.cloudifysource.dsl.context.ServiceContextFactory

def context = ServiceContextFactory.getServiceContext()
def hostsFileUpdater = new HostsFileUpdater()
hostsFileUpdater.updateHosts(context)
println("+++++++++++++++++++++++STARTED++++++++++++++++++++++++")
context.attributes.thisInstance.isReady = true