application {
	name="hdp_cluster"

	service {
		name = "hdp-master"
		dependsOn = ["hdp-worker"]
	}

	service {
		name = "hdp-worker"
	}

	/*service {
		name = "resource_manager"
	}

	service {
		name = "hiveServer"
	}

	service {
		name = "hbaseServer"
	}

	service {
		name = "secondary_namenode"
	}

	service {
		name = "administration"
	}*/
}