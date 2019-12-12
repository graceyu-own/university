package consumer

import ()

type Data struct {
	UseMail          string
	MailType         int
	VerificationCode string
}

func NewData(useMail string, mailType int, verCode string) *Data {
	data := &Data{
		UseMail: useMail,
		MailType: mailType,
		VerificationCode: verCode,
	}
	return data
}


