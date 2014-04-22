/*******************************************************************************
 * preInstall script for every node
 *******************************************************************************/
import org.cloudifysource.dsl.context.ServiceContextFactory
 
def context = ServiceContextFactory.getServiceContext()

def builder = new AntBuilder()
 
	 
def preInstallScriptSh = "${context.serviceDirectory}/scripts-sh/prepareHost.sh"
	 	 
println "${context.serviceName}-preinstall.groovy: Running ${preInstallScriptSh}..."
	 
builder.sequential {
	chmod(file:preInstallScriptSh, perm:"ugo+rx")
	exec(executable: preInstallScriptSh, failonerror: "true")
}
println "${context.serviceName}-preinstall.groovy: Host ${context.getPrivateAddress()} ready for installations!"
