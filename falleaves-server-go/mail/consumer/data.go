package consumer

type Data struct {
	UseMail          string		//目标用户邮箱
	MailType         int		//邮件类型
	VerificationCode string		//验证码
}

func NewData(useMail string, mailType int, verCode string) *Data {
	data := &Data{
		UseMail: useMail,
		MailType: mailType,
		VerificationCode: verCode,
	}
	return data
}


