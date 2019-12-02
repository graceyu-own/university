package main

import (
	"fmt"
	"time"
	"gopkg.in/gomail.v2"
)

func main() {

	/*engine := gin.Default()
	_ = engine.Run()*/

	ch := make(chan int)

	go func() {
		fmt.Println("This is thread 1")
		ch <- 1
	}()

	go func() {
		fmt.Println("This is thread 2")
		ch <- 2
	}()

	time.Sleep(100)
	fmt.Println(<- ch, <- ch)



}

