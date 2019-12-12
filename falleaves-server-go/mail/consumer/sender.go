package consumer

import (
	"github.com/go-gomail/gomail"
)

type Sender struct {
	User       string
	Passwd     string
	ServerHost string
	ServerPort int
}

// 新建一个 寄件者
func NewSender(user string, pass string, host string, port int) *Sender {
	m := &Sender{
		User:       user,
		Passwd:     pass,
		ServerHost: host,
		ServerPort: port,
	}
	return m
}

// TODO 发送邮件 函数
func (email *Sender) SendMail(d Data) error {
	//创建消息结构体
	message := gomail.NewMessage()
}