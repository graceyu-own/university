package sendcloud

import (
	"bytes"
	"fmt"
	"time"
	"os"

	"net/http"
	"net/url"

	"io/ioutil"

	"github.com/joho/godotenv"

	"Falleaves/falleaves-server-go/mail/consumer"
)

var (
	user     string
	key      string
	sendUrl  string
	sendMail string
)

func init() {
	err := godotenv.Load()
	if err != nil {
		//log.Fatal("Error loading .env file")
	}
	user = os.Getenv("SENDCLOUD_USER")
	key = os.Getenv("SENDCLOUD_KEY")
	sendUrl = os.Getenv("SENDCLOUD_SEND_URL")
	sendMail = os.Getenv("SENDCLOUD_SEND_MAIL")
}

// TODO 发送邮件 函数
func SendMail(data *consumer.Data) error {
	
	formdata := url.Values{}
	formdata.Set("apiUser", user)
	formdata.Set("apiKey", key)
	formdata.Set("from", sendMail)
	formdata.Set("fromName", "strluck")
	formdata.Set("to", data.UseMail)
	formdata.Set("subject", "注册验证码")
	formdata.Set("html", "您的注册码为：045046")

	// 生成client客户端
	client := &http.Client{
		Timeout: 10 * time.Second,
	}
	// 生成Request对象
	req, err := http.NewRequest("POST", sendUrl, bytes.NewBufferString(formdata.Encode()))
	if err != nil {
		return err
	}
	req.Header.Add("Content-Type", "application/x-www-form-urlencoded")
	// 发起请求
	resp, err := client.Do(req)
	if err != nil {
		return err
	}
	// 设定关闭响应体
	defer resp.Body.Close()

	fmt.Println(resp.StatusCode)

	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return err
	}
	fmt.Println(string(body))
	return nil
}
