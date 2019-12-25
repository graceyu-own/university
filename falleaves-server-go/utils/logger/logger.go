package logger

import (
	"fmt"
	"os"
	"time"

	"github.com/joho/godotenv"
)

// 日志等级
const (
	LevelDebug = iota
	LevelInfo
	LevelWarning
	LevelError
	LevelFatal
)

// 日志等级 string
const (
	_LevelDebugString   = "DEBUG"
	_LevelInfoString    = "INFO"
	_LevelWarningString = "WARNING"
	_LevelErrorString   = "ERROR"
	_LevelFatalString   = "FATAL"
)

var color = []int{
	// debug, info, warnig, error, fatal
	0, 32, 33, 31, 31,
}

// 默认日志等级为 INFO
var rootLogger int = LevelInfo

func init() {
	err := godotenv.Load()
	if err != nil {
		Fatal("Error loading .env file")
	}

	level := os.Getenv("ROOTLOGGER")
	switch level {
	case _LevelDebugString:
		rootLogger = LevelDebug
		break
	case _LevelInfoString:
		rootLogger = LevelInfo
		break
	case _LevelWarningString:
		rootLogger = LevelWarning
		break
	case _LevelErrorString:
		rootLogger = LevelError
		break
	case _LevelFatalString:
		rootLogger = LevelFatal
		break
	}

	Debug("LoggerLevel: %s", level)
}

func getLevelMag(level int) string {
	if level > LevelFatal {
		level = LevelFatal
	} else if level < LevelDebug {
		level = LevelDebug
	}

	var mag string

	switch level {
	case LevelDebug:
		mag = _LevelDebugString
		break
	case LevelInfo:
		mag = _LevelInfoString
		break
	case LevelWarning:
		mag = _LevelWarningString
		break
	case LevelError:
		mag = _LevelErrorString
		break
	case LevelFatal:
		mag = _LevelFatalString
		break
	}
	return fmt.Sprintf("%c[1;0;%dm%s%c[0m", 0x1B, color[level], mag, 0x1B)
}

// Println 打印
func Println(v ...interface{}) {
	fmt.Printf("%s %s", time.Now().Format("2006-01-02 15:04:05"), fmt.Sprintln(v...))
}

func println(level int, format string, v ...interface{}) {
	Println(fmt.Sprintf(fmt.Sprintf("[%s] %s", getLevelMag(level), format), v...))
}

// Fatal 严重错误
// 指出每个严重的错误事件将会导致应用程序的退出
func Fatal(format string, v ...interface{}) {
	if LevelFatal < rootLogger {
		return
	}
	println(LevelFatal, format, v...)
	os.Exit(0)
}

// Error 错误
// 指出虽然发生错误事件，但仍然不影响系统的继续运行
// 打印错误和异常信息，如果不想输出太多的日志，可以使用这个级别
func Error(format string, v ...interface{}) {
	if LevelError < rootLogger {
		return
	}
	println(LevelError, format, v...)
}

// Warning 警告
// 表明会出现潜在错误的情形，有些信息不是错误信息
func Warning(format string, v ...interface{}) {
	if LevelWarning < rootLogger {
		return
	}
	println(LevelWarning, format, v...)
}

// Info 信息
// 消息在粗粒度级别上突出强调应用程序的运行过程
func Info(format string, v ...interface{}) {
	if LevelInfo < rootLogger {
		return
	}
	println(LevelInfo, format, v...)
}

// Debug 调试
func Debug(format string, v ...interface{}) {
	if LevelDebug < rootLogger {
		return
	}
	println(LevelDebug, format, v...)
}
