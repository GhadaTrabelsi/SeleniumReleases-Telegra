package myTests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeleniumNewsBot extends TelegramLongPollingBot {

    private static final String SELENIUM_URL = "https://www.selenium.dev/";

    public String getBotUsername() {
        return ""; // Name of my Bot
    }

    @Override
    public String getBotToken() {
        return ""; //  token of my Bot
    }
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userMessage = update.getMessage().getText();

         // Log the received message
            System.out.println("Message reçu: " + userMessage);

            if (userMessage.trim().equalsIgnoreCase("/start")) {
                System.out.println("Commande /start reçue");
                sendMessage(chatId, "Bienvenue sur le bot Selenium Releases!");
            } else if (userMessage.trim().equalsIgnoreCase("hello") || userMessage.trim().equalsIgnoreCase("hi")) {
                sendMessage(chatId, "Hello! Comment puis-je vous aider ?");
            }else {
                System.out.println("Autre message reçu: " + userMessage);
                String releaseInfo = getSeleniumReleaseInfo();
                sendMessage(chatId, releaseInfo);
            }
        }
    }
     // Function to scrape release information from the Selenium website
    private String getSeleniumReleaseInfo() {
        StringBuilder releaseInfo = new StringBuilder();
        try {
            Document doc = Jsoup.connect(SELENIUM_URL).get();
            Elements releaseDivs = doc.select("div.card-body");
            Elements seleniumReleases = doc.getElementsContainingOwnText("Released");

            Map<String, String> releaseMap = new HashMap<String, String>();

            for (Element release : seleniumReleases) {
                releaseMap.put(release.text(), release.baseUri());
            }

            for (Element div : releaseDivs) {
                Element link = div.selectFirst("a.selenium-link");
                if (link != null) {
                    String href = link.attr("href");
                    String version = href.split("selenium-")[1].split("-released")[0].replace("-", ".");
                    releaseInfo.append("Version: ").append(version).append(" - ").append(href).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        releaseInfo.append("\nPour plus de détails, consultez le changelog : ");
        releaseInfo.append("https://github.com/SeleniumHQ/selenium/blob/trunk/java/CHANGELOG"); // Ajout du lien GitHub pour le changelog
        return releaseInfo.toString();
    }

    // Send message through Telegram
    private void sendMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            System.out.println("Envoi du message: " + text);
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi du message");
        }
    }

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new SeleniumNewsBot()); // Save  bot
            System.out.println("Le bot est démarré !");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
