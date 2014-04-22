service {
	extend "../../../services/HDP/hdp-worker"
	name = "hdp-worker"
	numInstances 6
	minAllowedInstances 1
	maxAllowedInstances 6
}