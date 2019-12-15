package consumer

// TODO 无法使用

import (
	"context"
	"fmt"
	"time"

	"github.com/mailgun/mailgun-go/v3"
)


func SendSimpleMessage(domain, apiKey string) {
	mg := mailgun.NewMailgun("https://api.mailgun.net/v3/sandboxac76c6d5b68f4e809b95e315cedf39e2.mailgun.org", "b177ce949f55362590ea20683cd1c0eb-5645b1f9-684f6f18")

	sender := "s@sandboxac76c6d5b68f4e809b95e315cedf39e2.mailgun.org"
	subject := "这是一封测试邮件的标题"
	body := "这是一封测试邮件的正文"
	recipient := "1092570726@qq.com"

	message := mg.NewMessage(sender, subject, body, recipient)

	ctx, cancel := context.WithTimeout(context.Background(), time.Second*10)
	defer cancel()

	// Send the message	with a 10 second timeout
	resp, id, err := mg.Send(ctx, message)

	if err != nil {
		//fmt.Printf(err)
	}

	fmt.Printf("ID: %s Resp: %s\n", id, resp)
}
