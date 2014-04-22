echo "- - -" > /sys/class/scsi_host/host0/scan 
echo "- - -" > /sys/class/scsi_host/host1/scan 
echo "- - -" > /sys/class/scsi_host/host2/scan 
echo "1" > /sys/class/scsi_device/2\:0\:0\:0/device/rescan 
echo "1" > /sys/class/scsi_device/2\:0\:1\:0/device/rescan

#for each device, change sdb for required name
echo y | mkfs.ext4 /dev/sdb
mount /dev/sdb /path/to/
