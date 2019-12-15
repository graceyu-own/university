package aliyun

import (
	"fmt"
	"os"

	"github.com/aliyun/alibaba-cloud-sdk-go/services/dm"
	"github.com/aliyun/alibaba-cloud-sdk-go/sdk/requests"

	"github.com/joho/godotenv"

	"Falleaves/falleaves-server-go/mail/consumer"
)

var (
	regionId  string
	keyId     string
	keySecret string
	sendMail  string
)

func init() {
	err := godotenv.Load()
	if err != nil {
		//log.Fatal("Error loading .env file")
	}
	regionId = os.Getenv("ALIYUN_REGION_ID")
	keyId = os.Getenv("ALIYUN_KEY_ID")
	keySecret = os.Getenv("ALIYUN_KEY_SECRET")
	sendMail = os.Getenv("ALIYUN_SEND_MAIL")
}

func SendMail(data *consumer.Data) (string, error) {
	client, err := dm.NewClientWithAccessKey(regionId, keyId, keySecret)
	if err != nil {
		return "", err
	}

	request := dm.CreateSingleSendMailRequest()
	request.Scheme = "https"

	request.AccountName = sendMail
	request.AddressType = requests.NewInteger(1)
	request.ReplyToAddress = requests.NewBoolean(false)
	request.ToAddress = data.UseMail
	request.Subject = "strluck邮箱验证码"
	request.HtmlBody = "您的验证码为：090065"
	request.FromAlias = "strluck官方"

	response, err := client.SingleSendMail(request)
	if err != nil {
		return "", err
	}
	
	fmt.Printf("response is %#v\n", response)

	return response.String(), nil
}

func Get() string {
	return regionId
}
