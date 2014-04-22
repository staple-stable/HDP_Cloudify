service {
	extend "../hdp-common"
	name "hdp-worker"
	numInstances 1

	
	compute {
		template "SMALL_LINUX"
	}

	lifecycle{

		init "hdp-worker-init.groovy"

		start "hdp-worker-start.groovy"

		startDetection {

			//def test = context.attributes.thisInstance.isReady
						
			return true
		}
		locator {
           NO_PROCESS_LOCATORS
        }
		stop "hdp-worker-stop.groovy"
	}
}