import atexit
import email
import imaplib
import requests

support_request = "https://discordapp.com/api/webhooks/417542287770255361/H90EHlFUjhSORW_pG1FEKLF9q2ZJ-azlfhi9VPvJt-KuzFznOdMF7kFYNFTuEDpI6s35"
bot_alert = "https://discordapp.com/api/webhooks/417702711497195522/P_vZCZxHZW8hwfDwehi2yqR__Y-wOUuQGdDmI4wLUPlHbqrVx4RG233gczjvx1uuGiZY"

def printToDiscord(bot_name, message, channel):
    s = requests.Session()
    
    data = {
        "username": bot_name,
        "content": message
        }
    r = s.post(url = channel, data = data)

atexit.register(printToDiscord, "Bot Offline", "The Fobdango Customer Support bot is now shutting down", support_request)
printToDiscord("Bot Online", "The Fobdango Customer Support bot has been successfully enabled.", support_request)

try:
    mail = imaplib.IMAP4_SSL('imap.gmail.com',993)
    mail.login('csci4050@gmail.com','thisisapassword')
    
    while True:
        mail.select('INBOX')
        status, response = mail.search(None,'UNSEEN')
        for msg_id in response[0].split():
            throwaway, body = mail.fetch(msg_id, '(UID BODY[TEXT])')
            message = email.message_from_bytes(body[0][1])
            message = message.get_payload(decode = True).decode('utf-8')
            ID = message[2:message.find('Content-Type') - 12]
            print(message)
            message = message[message.find('-8"') + 3:]
            message = message[:message.find('--') - 2]
            printToDiscord("Request ID " + ID, message, support_request)
            mail.store(msg_id, '+FLAGS', '\Seen')
            
except Exception as e:
    printToDiscord("Error Alert", "Something has happened :(\n\n" + str(e), bot_alert)
