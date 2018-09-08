import datetime

def main():
    s = datetime.datetime.now()
    print(s)

    while True :
        from time import sleep
        sleep(1)
        print("message")

    return s


if __name__ == "__main__":
    main()