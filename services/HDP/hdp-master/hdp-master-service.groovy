service {
	extend "../hdp-common"
	name "hdp-master"
	numInstances 1
	elastic false

	compute {
		template "SMALL_LINUX"
	}

	lifecycle{
		init "hdp-master-init.groovy"

		install "hdp-master-install.groovy"

		start "hdp-master-start.groovy"
		
		startDetection {

			//def test = context.attributes.thisInstance.isReady
						
			return true
		}
		locator {
           NO_PROCESS_LOCATORS
        }
	}
}