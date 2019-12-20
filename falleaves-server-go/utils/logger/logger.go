package logger

import (
	"fmt"
	"os"
	"time"

	"github.com/joho/godotenv"
)
// 日志等级
const (
	LevelDebug   = iota
	LevelInfo
	LevelWarning
	LevelError
	LevelFatal
)
// 日志等级 string
const (
	LevelDebugString   = "DEBUG"
	LevelInfoString    = "INFO"
	LevelWarningString = "WARNING"
	LevelErrorString   = "ERROR"
	LevelFatalString   = "FATAL"
)

var rootLogger int

func init() {
	err := godotenv.Load()
	if err != nil {
		//log.Fatal("Error loading .env file")
	}
	switch level := os.Getenv("ROOTLOGGER"); level {
		case LevelDebugString:
			rootLogger = LevelDebug
		case LevelInfoString:
			rootLogger = LevelInfo
		case LevelWarningString:
			rootLogger = LevelWarning
		case LevelErrorString:
			rootLogger = LevelError
		case LevelFatalString:
			rootLogger = LevelFatal
	}
	Debug("rootLogger:", rootLogger)
}

// Println 打印
func Println(msg string) {
	fmt.Printf("%s %s\n", time.Now().Format("2006-01-02 15:04:05"), msg)
}

// Fatal 严重错误
// 指出每个严重的错误事件将会导致应用程序的退出
func Fatal(format string, v ...interface{}) {
	if LevelFatal > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelFatalString, format), v...)
	Println(msg)
	os.Exit(0)
}

// Error 错误
// 指出虽然发生错误事件，但仍然不影响系统的继续运行
// 打印错误和异常信息，如果不想输出太多的日志，可以使用这个级别
func Error(format string, v ...interface{}) {
	if LevelError > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelErrorString, format), v...)
	Println(msg)
}

// Warning 警告
// 表明会出现潜在错误的情形，有些信息不是错误信息
func Warning(format string, v ...interface{}) {
	if LevelWarning > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelWarningString, format), v...)
	Println(msg)
}

// Info 信息
// 消息在粗粒度级别上突出强调应用程序的运行过程
func Info(format string, v ...interface{}) {
	if LevelInfo > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelInfoString, format), v...)
	Println(msg)
}

// Debug 调试
func Debug(format string, v ...interface{}) {
	if LevelDebug > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelDebugString, format), v...)
	Println(msg)
}