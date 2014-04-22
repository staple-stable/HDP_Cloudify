/*******************************************************************************
 * init script for hdp worker
 *******************************************************************************/
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import org.cloudifysource.dsl.context.ServiceContextFactory
 
 def context = ServiceContextFactory.getServiceContext()
 //def config = new ConfigSlurper().parse(new File("cdh4-slave-service.properties").toURL())
 builder = new AntBuilder()

 //saving some data in the instance attributes
 context.attributes.thisInstance.isReady = false
 
 println "hdp-worker-init.groovy: Setting ipAddress to ${context.getPrivateAddress()}"
 context.attributes.thisInstance.ipAddress = context.getPrivateAddress()
 
 println "hdp-worker-init.groovy: Saving the publicIp ${context.getPublicAddress()}"
 context.attributes.thisInstance.publicAddress = context.getPublicAddress()
 
 //def hostAlias = InetAddress.getLocalHost().getHostName()
 def hostAlias = context.getServiceName()+"-"+context.getInstanceId()
 println "hdp-worker-init.groovy: Setting hostname to ${hostAlias}"
 context.attributes.thisInstance.hostAlias = hostAlias
 
 //initializing the  hostMap
 context.attributes.thisInstance.hostsMap = [localhost:"127.0.0.1"]
 //context.startMaintenanceMode(60l, TimeUnit.MINUTES)
 def hostsFileUpdater = new HostsFileUpdater()

