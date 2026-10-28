#include<fcntl.h>
#include<unistd.h>
#include<termios.h>
#include<sys/ioctl.h>
#include<string.h>
#include<stdlib.h>
#include<stdio.h>
int openSerialPort(const char * device,speed_t speed){
	int fd=open(device,O_RDWR|O_NOCTTY);
	if(fd<0){
		perror(device);
		return -1;
	}
	struct termios tio;
	memset(&tio,0,sizeof(tio));
	tio.c_cflag=speed|CS8|CLOCAL|CREAD;
	tio.c_iflag=IGNPAR;
	tio.c_oflag=0;
	tio.c_lflag=0;
	tio.c_cc[VTIME]=0;
	tio.c_cc[VMIN]=1;
	cfsetospeed(&tio,speed);
	cfsetispeed(&tio,speed);
	tcflush(fd, TCIFLUSH);
	if (tcsetattr(fd, TCSANOW, &tio) < 0) {
		perror("tcsetattr");
		close(fd);
		return -1;
	}
	int status;
	ioctl(fd,TIOCMGET,&status);
	status&=~TIOCM_RTS;
	status&=~TIOCM_DTR;
	ioctl(fd, TIOCMSET, &status);
	return fd;
}
