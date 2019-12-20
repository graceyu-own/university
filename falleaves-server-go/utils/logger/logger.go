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
	LevelPanic
)
// 日志等级 string
const (
	LevelDebugString   = "DEBUG"
	LevelInfoString    = "INFO"
	LevelWarningString = "WARNING"
	LevelErrorString   = "ERROR"
	LevelPanicString   = "PANIC"
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
		case LevelPanicString:
			rootLogger = LevelPanic
	}
	Debug("rootLogger:", rootLogger)
}

// Println 打印
func Println(msg string) {
	fmt.Printf("%s %s\n", time.Now().Format("2006-01-02 15:04:05"), msg)
}

// Panic 极端错误
func Panic(format string, v ...interface{}) {
	if LevelPanic > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelPanicString, format), v...)
	Println(msg)
	os.Exit(0)
}

// Error 错误
func Error(format string, v ...interface{}) {
	if LevelError > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelErrorString, format), v...)
	Println(msg)
}

// Warning 警告
func Warning(format string, v ...interface{}) {
	if LevelWarning > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelWarningString, format), v...)
	Println(msg)
}

// Info 信息
func Info(format string, v ...interface{}) {
	if LevelInfo > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelInfoString, format), v...)
	Println(msg)
}

// Debug 校验
func Debug(format string, v ...interface{}) {
	if LevelDebug > rootLogger {
		return
	}
	msg := fmt.Sprintf(fmt.Sprintf("[%s] %s", LevelDebugString, format), v...)
	Println(msg)
}