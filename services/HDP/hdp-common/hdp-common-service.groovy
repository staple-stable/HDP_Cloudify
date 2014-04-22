service {
	name "hdp-common"
	numInstances 1

	

	lifecycle {
		preInstall "hdp-common-preInstall.groovy"
	}
}