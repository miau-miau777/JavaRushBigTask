package client;

import server.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            String textMessage = "Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.";
            sendTextMessage(textMessage);
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            SimpleDateFormat dateFormat = null;
            if (message.contains(": ")) {
                String[] array = message.split(": ");
                if (array.length==2 && array[1]!=null) {
                    String userName = array[0];
                    String text = array[1].toLowerCase();
                    switch (text) {
                        case "дата":
                            dateFormat = new SimpleDateFormat("d.MM.YYYY");
                            break;
                        case "день":
                            dateFormat = new SimpleDateFormat("d");
                            break;
                        case "месяц":
                            dateFormat = new SimpleDateFormat("MMMM");
                            break;
                        case "год":
                            dateFormat = new SimpleDateFormat("YYYY");
                            break;
                        case "время":
                            dateFormat = new SimpleDateFormat("H:mm:ss");
                            break;
                        case "час":
                            dateFormat = new SimpleDateFormat("H");
                            break;
                        case "минуты":
                            dateFormat = new SimpleDateFormat("m");
                            break;
                        case "секунды":
                            dateFormat = new SimpleDateFormat("s");
                            break;
                    }
                    if (dateFormat != null) {
                        sendTextMessage("Информация для " + userName + ": " +  dateFormat.format(Calendar.getInstance().getTime()));
                    }
                }


            }

        }
    }

    @Override
    protected Client.SocketThread getSocketThread() {
        return new BotSocketThread();
    }
    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        String newUserName = "date_bot_" + ((int)(Math.random() * 100));
        return newUserName;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

}

